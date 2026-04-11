package com.ai.k4;

import android.media.MediaCodec;
import android.media.MediaFormat;

import com.google.android.exoplayer2.BaseRenderer;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.RendererCapabilities;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.util.MimeTypes;

import java.nio.ByteBuffer;

/**
 * ProjectName: AdlMountGeneral
 * Author     : 南山
 * Date       : 2025/1/26
 * Describe   : 类描述
 */
public class YuvRenderer extends BaseRenderer {

    private MediaCodec mediaCodec;
    private MediaFormat mediaFormat;
    private boolean isCodecConfigured;
    DecoderInputBuffer buffer;

    public YuvRenderer() {
        super(C.TRACK_TYPE_VIDEO);

        buffer = new DecoderInputBuffer(DecoderInputBuffer.BUFFER_REPLACEMENT_MODE_DISABLED);
    }

    @Override
    public int supportsFormat(Format format) {
        if (MimeTypes.isVideo(format.sampleMimeType)) {
            return RendererCapabilities.create(C.FORMAT_HANDLED);
        }
        return RendererCapabilities.create(C.FORMAT_UNSUPPORTED_TYPE);
    }

    @Override
    protected void onStreamChanged(Format[] formats, long startPositionUs, long offsetUs) throws ExoPlaybackException {
        if (mediaCodec != null) {
            mediaCodec.stop();
            mediaCodec.release();
            mediaCodec = null;
        }
        Format format = formats[0];
        mediaFormat = MediaFormat.createVideoFormat(format.sampleMimeType, format.width, format.height);
        isCodecConfigured = false;
    }

    @Override
    protected void onPositionReset(long positionUs, boolean joining) throws ExoPlaybackException {
        if (mediaCodec != null) {
            mediaCodec.flush();
        }
    }

    @Override
    public String getName() {
        return "vv";
    }

    @Override
    public void render(long positionUs, long elapsedRealtimeUs) throws ExoPlaybackException {

        try {
            if (!isCodecConfigured) {
                mediaCodec = MediaCodec.createDecoderByType(mediaFormat.getString(MediaFormat.KEY_MIME));
                mediaCodec.configure(mediaFormat, null, null, 0);
                mediaCodec.start();
                isCodecConfigured = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        int inputBufferIndex = mediaCodec.dequeueInputBuffer(-1);
        if (inputBufferIndex >= 0) {

            ByteBuffer inputBuffer = mediaCodec.getInputBuffer(inputBufferIndex);
            if (inputBuffer != null) {
                // 填充输入缓冲区数据，这里需要根据实际情况实现
                // 例如从 ExoPlayer 的数据源获取数据
                // inputBuffer.data.put(...)

                getStream().readData(null, buffer, 1);


                mediaCodec.queueInputBuffer(inputBufferIndex, 0, inputBuffer.limit(), 0, 0);
            }
        }

        MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
        int outputBufferIndex = mediaCodec.dequeueOutputBuffer(bufferInfo, 0);
        while (outputBufferIndex >= 0) {
            ByteBuffer outputBuffer = mediaCodec.getOutputBuffer(outputBufferIndex);
            byte[] yuvData = new byte[bufferInfo.size];
            outputBuffer.get(yuvData);

            // 处理提取的 YUV 数据
            processYuvData(yuvData);

            mediaCodec.releaseOutputBuffer(outputBufferIndex, true);
            outputBufferIndex = mediaCodec.dequeueOutputBuffer(bufferInfo, 0);
        }
    }

    private void processYuvData(byte[] yuvData) {

        // 在这里处理提取的 YUV 数据，例如保存到文件或进行图像处理
        // 示例：打印 YUV 数据长度
        System.out.println("YUV data length: " + yuvData.length);
    }

    @Override
    protected void onDisabled() {
        if (mediaCodec != null) {
            mediaCodec.stop();
            mediaCodec.release();
            mediaCodec = null;
        }
    }

    @Override
    public boolean isEnded() {
        return false;
    }

    @Override
    public boolean isReady() {
        return true;
    }
}
