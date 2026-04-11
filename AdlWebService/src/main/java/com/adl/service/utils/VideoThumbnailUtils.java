package com.adl.service.utils;

import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import com.adl.base.log.AdlLogger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class VideoThumbnailUtils {
    private static final String TAG = "VideoThumbnailUtils";

    public static String saveVideoThumbnailToInternal(String videoPath,
                                                      String fullFileName, int quality) {
        try {
            // 1. 获取视频封面
            Bitmap bitmap = getVideoThumbnail(videoPath);
            if (bitmap == null) {
                AdlLogger.e(TAG + " Failed to get video thumbnail");
                return null;
            }

            // 2. 创建输出文件
//            String fullFileName = fileName + ".jpg";
            File outputFile = new File(fullFileName);

            // 3. 保存为JPG
            boolean saved = saveBitmapAsJpg(bitmap, outputFile, quality);
            if (!saved) {
                AdlLogger.e(TAG + "IndicatorsValue Failed to save bitmap as JPG");
                return null;
            }

            // 4. 回收Bitmap
            bitmap.recycle();

            return outputFile.getAbsolutePath();
        } catch (Exception e) {
            AdlLogger.e(TAG + "IndicatorsValue Error saving thumbnail to internal storage" + e.getMessage());
            return null;
        }
    }

    /**
     * 获取视频封面（核心方法）
     */
    public static Bitmap getVideoThumbnail(String videoPath) {
        return getVideoThumbnail(videoPath, 0, 0, 0);
    }

    public static Bitmap getVideoThumbnail(String videoPath, long timeMs,
                                           int maxWidth, int maxHeight) {
        MediaMetadataRetriever retriever = null;

        try {
            retriever = new MediaMetadataRetriever();
            retriever.setDataSource(videoPath);

            // 获取指定时间点的帧
            Bitmap bitmap = retriever.getFrameAtTime(timeMs * 1000,
                    MediaMetadataRetriever.OPTION_CLOSEST);

            // 如果失败，尝试其他时间点
            if (bitmap == null) {
                long[] times = {1000000, 3000000, 5000000}; // 1秒, 3秒, 5秒
                for (long time : times) {
                    bitmap = retriever.getFrameAtTime(time,
                            MediaMetadataRetriever.OPTION_CLOSEST);
                    if (bitmap != null && !isBlackFrame(bitmap)) {
                        break;
                    }
                }
            }

            if (bitmap == null) {
                return null;
            }

            // 检测是否是黑色帧
            if (isBlackFrame(bitmap)) {
                // 如果是黑色帧，尝试其他时间点
                bitmap = retriever.getFrameAtTime(2000000,
                        MediaMetadataRetriever.OPTION_CLOSEST);
            }

            // 调整大小
            if (maxWidth > 0 && maxHeight > 0) {
                bitmap = resizeBitmap(bitmap, maxWidth, maxHeight);
            }

            // 纠正旋转
            String rotationStr = retriever.extractMetadata(
                    MediaMetadataRetriever.METADATA_KEY_VIDEO_ROTATION);
            if (rotationStr != null) {
                int rotation = Integer.parseInt(rotationStr);
                if (rotation != 0) {
                    bitmap = rotateBitmap(bitmap, rotation);
                }
            }

            return bitmap;

        } catch (Exception e) {
            Log.e(TAG, "Error getting video thumbnail", e);
            return null;
        } finally {
            if (retriever != null) {
                try {
                    retriever.release();
                } catch (Exception e) {
                    Log.e(TAG, "Error releasing MediaMetadataRetriever", e);
                }
            }
        }
    }

    /**
     * 保存Bitmap为JPG文件
     */
    public static boolean saveBitmapAsJpg(Bitmap bitmap, File file, int quality) {
        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(file);
            boolean success = bitmap.compress(Bitmap.CompressFormat.JPEG, quality, fos);
            fos.flush();
            return success;
        } catch (Exception e) {
            AdlLogger.e(TAG + "Error saving bitmap as JPG" + e.getMessage());
            return false;
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (Exception e) {
                    AdlLogger.e(TAG + "Error closing file output stream" + e.getMessage());
                }
            }
        }
    }

    /**
     * 调整Bitmap大小
     */
    public static Bitmap resizeBitmap(Bitmap bitmap, int maxWidth, int maxHeight) {
        if (bitmap == null) return null;

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        if (width <= maxWidth && height <= maxHeight) {
            return bitmap;
        }

        float scale = Math.min(
                (float) maxWidth / width,
                (float) maxHeight / height
        );

        int newWidth = (int) (width * scale);
        int newHeight = (int) (height * scale);

        return Bitmap.createScaledBitmap(bitmap, newWidth, newHeight, true);
    }

    /**
     * 旋转Bitmap
     */
    public static Bitmap rotateBitmap(Bitmap bitmap, int degrees) {
        if (bitmap == null || degrees == 0) return bitmap;

        Matrix matrix = new Matrix();
        matrix.postRotate(degrees);

        return Bitmap.createBitmap(bitmap, 0, 0,
                bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    /**
     * 检测是否是黑色帧
     */
    public static boolean isBlackFrame(Bitmap bitmap) {
        return isBlackFrame(bitmap, 0.95);
    }

    public static boolean isBlackFrame(Bitmap bitmap, double threshold) {
        if (bitmap == null) return true;

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        if (width <= 0 || height <= 0) return true;

        int blackPixels = 0;
        int totalPixels = width * height;

        // 采样检测（提高性能）
        int sampleStep = 10;
        int sampleCount = 0;

        for (int x = 0; x < width; x += sampleStep) {
            for (int y = 0; y < height; y += sampleStep) {
                int pixel = bitmap.getPixel(x, y);
                int r = android.graphics.Color.red(pixel);
                int g = android.graphics.Color.green(pixel);
                int b = android.graphics.Color.blue(pixel);

                // 判断是否接近黑色
                if (r < 30 && g < 30 && b < 30) {
                    blackPixels++;
                }
                sampleCount++;
            }
        }

        if (sampleCount == 0) return true;

        double blackRatio = (double) blackPixels / sampleCount;
        return blackRatio > threshold;
    }

    /**
     * 获取视频信息
     */
    public static VideoInfo getVideoInfo(String videoPath) {
        MediaMetadataRetriever retriever = null;

        try {
            retriever = new MediaMetadataRetriever();
            retriever.setDataSource(videoPath);

            VideoInfo info = new VideoInfo();

            // 获取宽度
            String widthStr = retriever.extractMetadata(
                    MediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH);
            if (widthStr != null) {
                try {
                    info.width = Integer.parseInt(widthStr);
                } catch (NumberFormatException e) {
                    info.width = 0;
                }
            }

            // 获取高度
            String heightStr = retriever.extractMetadata(
                    MediaMetadataRetriever.METADATA_KEY_VIDEO_HEIGHT);
            if (heightStr != null) {
                try {
                    info.height = Integer.parseInt(heightStr);
                } catch (NumberFormatException e) {
                    info.height = 0;
                }
            }

            // 获取时长
            String durationStr = retriever.extractMetadata(
                    MediaMetadataRetriever.METADATA_KEY_DURATION);
            if (durationStr != null) {
                try {
                    info.duration = Long.parseLong(durationStr);
                } catch (NumberFormatException e) {
                    info.duration = 0;
                }
            }

            // 获取旋转角度
            String rotationStr = retriever.extractMetadata(
                    MediaMetadataRetriever.METADATA_KEY_VIDEO_ROTATION);
            if (rotationStr != null) {
                try {
                    info.rotation = Integer.parseInt(rotationStr);
                } catch (NumberFormatException e) {
                    info.rotation = 0;
                }
            }

            // 获取比特率
            String bitrateStr = retriever.extractMetadata(
                    MediaMetadataRetriever.METADATA_KEY_BITRATE);
            if (bitrateStr != null) {
                try {
                    info.bitrate = Integer.parseInt(bitrateStr);
                } catch (NumberFormatException e) {
                    info.bitrate = 0;
                }
            }

            return info;

        } catch (Exception e) {
            Log.e(TAG, "Error getting video info", e);
            return new VideoInfo();
        } finally {
            if (retriever != null) {
                try {
                    retriever.release();
                } catch (Exception e) {
                    Log.e(TAG, "Error releasing MediaMetadataRetriever", e);
                }
            }
        }
    }

    /**
     * 视频信息类
     */
    public static class VideoInfo {
        public int width;
        public int height;
        public long duration; // 毫秒
        public int rotation;
        public int bitrate;

        @Override
        public String toString() {
            return "VideoInfo{" +
                    "width=" + width +
                    ", height=" + height +
                    ", duration=" + duration +
                    ", rotation=" + rotation +
                    ", bitrate=" + bitrate +
                    '}';
        }
    }
}
