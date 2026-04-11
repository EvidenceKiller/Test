package com.adl.service.common;

import android.os.Environment;

import java.io.File;

/**
 * ProjectName: AiPowerAuth
 * Author     : 南山
 * Date       : 2024/7/4
 * Describe   : 类描述
 */
public class FileUtil {

    private static String SportRoot = IDefine.DefSportRoot;

    public static void setSportRoot(String root) {
        SportRoot = root;
    }

    private static File createDir(String name) {
        File sdcard = Environment.getExternalStorageDirectory();
        File root = createDir(sdcard, name);
        return root;
    }

    public static File createDir(File dir, String name) {
        File root = new File(dir, name);
        if (!root.exists()) {
            root.mkdirs();
        }
        return root;
    }

    public static File createDirectory(String dirName) {
        return createDir(createDir(SportRoot), dirName);
    }

    public static File createLocalVideo() {
        return createDirectory(IDefine.DirVideo);
    }

    public static File createLocalImage() {
        return createDirectory(IDefine.DirImage);
    }

    public static File createLocalFile() {
        return createDirectory(IDefine.DirFile);
    }

    public static File createLog(String fileName) {
        File other = createDirectory(IDefine.DirLog);
        return new File(other, fileName);
    }
}
