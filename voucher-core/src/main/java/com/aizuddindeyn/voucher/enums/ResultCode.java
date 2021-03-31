/*
 * Owned by aizuddindeyn
 * Visit https://github.com/aizuddindeyn
 */
package com.aizuddindeyn.voucher.enums;

/**
 * @author aizuddindeyn
 */
public enum ResultCode {

    SUCCESS("00", "Success"),

    MISSING_PARAMETER("76", "Invalid parameter"),

    INVALID_REQUEST("79", "Invalid request"),

    INVALID_SIGNATURE("A9", "Invalid signature"),

    SYSTEM_ERROR("A0", "Unexpected Error"),
    ;

    private final String code;

    private final String message;

    ResultCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
