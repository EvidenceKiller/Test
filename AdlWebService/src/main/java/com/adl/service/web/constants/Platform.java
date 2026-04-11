package com.adl.service.web.constants;

import androidx.annotation.Nullable;

public final class Platform {
    private static final String PLATFORM_K12 = "k12";
    private static final String PLATFORM_GX = "gx";

    public static final Platform K12 = new Platform();
    public static final Platform GX = new Platform();

    private Platform() {}

    @Override
    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof Platform)) {
            throw new IllegalArgumentException("Invalid argument equals : " + obj);
        }
        return super.equals(obj);
    }

    public static String toString(Platform platform) throws IllegalArgumentException {
        if (platform == K12) {
            return PLATFORM_K12;
        } else if (platform == GX) {
            return PLATFORM_GX;
        } else {
            throw new IllegalArgumentException("Invalid platform toString : " + platform);
        }
    }

    public static Platform fromString(String platform) {
        if (PLATFORM_K12.equalsIgnoreCase(platform)) {
            return K12;
        } else if (PLATFORM_GX.equalsIgnoreCase(platform)) {
            return GX;
        } else {
            throw new IllegalArgumentException("Invalid platform fromString : " + platform);
        }
    }


}
