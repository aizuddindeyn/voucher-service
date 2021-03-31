/*
 * Owned by aizuddindeyn
 * Visit https://github.com/aizuddindeyn
 */
package com.aizuddindeyn.voucher.util;

import org.apache.commons.lang3.RandomStringUtils;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.UUID;

/**
 * @author aizuddindeyn
 */
public class GeneratorUtil {

    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    private GeneratorUtil() {
        throw new IllegalStateException("Utils class");
    }

    public static String generateDebugId() {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }

    public static String generateUuid() {
        return UUID.randomUUID().toString();
    }

    public static String generateVoucherCode(String prefix, int length) {
        String epochStr = String.valueOf(Instant.now().toEpochMilli());
        int epochLength = epochStr.length();

        int randomLength = length - epochLength;
        int padLeftLength;
        int padRightLength;
        if (randomLength % 2 == 0) {
            padLeftLength = randomLength / 2;
            padRightLength = padLeftLength;
        } else {
            padLeftLength = randomLength / 2;
            padRightLength = padLeftLength + 1;
        }

        String padLeft = RandomStringUtils.random(padLeftLength, 0, 0, true, true, null,
                SECURE_RANDOM).toUpperCase();
        String padRight = RandomStringUtils.random(padRightLength, 0, 0, true, true, null,
                SECURE_RANDOM).toUpperCase();

        return prefix + padLeft + epochStr + padRight;
    }
}
