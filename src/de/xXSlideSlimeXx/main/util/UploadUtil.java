package de.xXSlideSlimeXx.main.util;

import java.util.Random;

/**
 * @author xXSlideSlimeXx
 * @since 19.05.2024
 */
public final class UploadUtil {
    private static final Random RDM;

    static {
        RDM = new Random();
    }

    public static String generateRandom() {
        return generateRandom(16);
    }
    public static String generateRandom(final int to) {
        String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i< to; i++) {
            builder.append(abc.charAt(RDM.nextInt(abc.length())));
        }
        return builder.toString();
    }
}
