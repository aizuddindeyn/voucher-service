/*
 * Owned by aizuddindeyn
 * Visit https://github.com/aizuddindeyn
 */
package com.aizuddindeyn.voucher.util;

import org.slf4j.Logger;

import java.util.UUID;

/**
 * @author aizuddindeyn
 */
public class LogUtil {

    public static final String DEBUG_ID = "id";

    private LogUtil() {
        throw new IllegalStateException("Utils class");
    }

    public static void logError(Logger logger, String msg, Throwable t) {
        if (logger.isErrorEnabled()) {
            logger.error(msg, t);
        }
    }

    public static void logError(Logger logger, String format, Object... arguments) {
        if (logger.isErrorEnabled()) {
            logger.error(format, arguments);
        }
    }

    public static void logInfo(Logger logger, String format, Object... arguments) {
        if (logger.isInfoEnabled()) {
            logger.info(format, arguments);
        }
    }

    public static void logDebug(Logger logger, String format, Object... arguments) {
        if (logger.isDebugEnabled()) {
            logger.debug(format, arguments);
        }
    }
}
