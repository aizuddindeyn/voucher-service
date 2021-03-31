/*
 * Owned by aizuddindeyn
 * Visit https://github.com/aizuddindeyn
 */
package com.aizuddindeyn.voucher.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * @author aizuddindeyn
 */
@Component
public class MessageResolver {

    private static final String RESULT_CODE_PREFIX = "result.code.";

    private final MessageSource msResultCode;

    @Autowired
    public MessageResolver(@Qualifier(value = "resultCodeMessageSource") MessageSource msResultCode) {
        this.msResultCode = msResultCode;
    }

    public String resolveResultCode(String statusCode, String defaultMessage, Locale locale, String... parameters) {
        String messageKey = RESULT_CODE_PREFIX + statusCode;

        return msResultCode.getMessage(messageKey, parameters, defaultMessage, locale);
    }
}
