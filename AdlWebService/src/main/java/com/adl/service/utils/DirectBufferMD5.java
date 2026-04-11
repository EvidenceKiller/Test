package com.adl.service.utils;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DirectBufferMD5 {

    // 1MB
    private static final int BUFFER_SIZE = 1024 * 1024;

    /**
     * 使用ByteBuffer直接更新
     */
    public static String getMD5DirectUpdate(String filePath)
            throws IOException, NoSuchAlgorithmException {

        Path path = Paths.get(filePath);
        MessageDigest md = MessageDigest.getInstance("MD5");
        ByteBuffer buffer = ByteBuffer.allocateDirect(BUFFER_SIZE);

        try (FileChannel channel = FileChannel.open(path, StandardOpenOption.READ)) {
            while (channel.read(buffer) != -1) {
                buffer.flip();

                // 使用ByteBuffer直接更新（需要MessageDigest支持）
                if (buffer.hasArray()) {
                    md.update(buffer.array(),
                            buffer.arrayOffset() + buffer.position(),
                            buffer.remaining());
                } else {
                    // 对于直接缓冲区，需要复制到数组
                    byte[] temp = new byte[buffer.remaining()];
                    buffer.get(temp);
                    md.update(temp);
                }

                buffer.clear();
            }
        }

        return bytesToHexOptimized(md.digest());
    }

    /**
     * 性能最优化的版本
     */
    public static String getMD5Optimized(String filePath)
            throws IOException, NoSuchAlgorithmException {

        Path path = Paths.get(filePath);
        long fileSize = java.nio.file.Files.size(path);
        MessageDigest md = MessageDigest.getInstance("MD5");

        // 根据文件大小动态调整缓冲区
        int bufferSize = determineBufferSize(fileSize);
        ByteBuffer buffer = ByteBuffer.allocateDirect(bufferSize);
        byte[] arrayBuffer = new byte[bufferSize];

        try (FileChannel channel = FileChannel.open(path, StandardOpenOption.READ)) {
            long totalRead = 0;

            while (channel.read(buffer) != -1) {
                buffer.flip();
                int remaining = buffer.remaining();

                // 批量处理数据
                buffer.get(arrayBuffer, 0, remaining);
                md.update(arrayBuffer, 0, remaining);

                totalRead += remaining;
                buffer.clear();
            }
        }

        return bytesToHexOptimized(md.digest());
    }

    /**
     * 根据文件大小确定缓冲区大小
     */
    private static int determineBufferSize(long fileSize) {
        if (fileSize <= 1024 * 1024) {                      // <= 1MB
            return 8 * 1024;                                // 8KB
        } else if (fileSize <= 100 * 1024 * 1024) {         // <= 100MB
            return 256 * 1024;                              // 256KB
        } else if (fileSize <= 1024 * 1024 * 1024) {        // <= 1GB
            return 1024 * 1024;                             // 1MB
        } else {                                            // > 1GB
            return 4 * 1024 * 1024;                         // 4MB
        }
    }

    /**
     * 优化的十六进制转换（使用查表法）
     */
    private static String bytesToHexOptimized(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        int v;
        for (int i = 0; i < bytes.length; i++) {
            v = bytes[i] & 0xFF;
            hexChars[i * 2] = HEX_CHARS[v >>> 4];
            hexChars[i * 2 + 1] = HEX_CHARS[v & 0x0F];
        }
        return new String(hexChars);
    }

    private static final char[] HEX_CHARS = {
            '0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
    };
}