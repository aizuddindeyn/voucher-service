/*
 * Owned by aizuddindeyn
 * Visit https://github.com/aizuddindeyn
 */
package com.aizuddindeyn.voucher.enums;

/**
 * @author aizuddindeyn
 */
public enum Fields {

    RECIPIENT("recipient"),

    SPECIAL_OFFER("specialOffer"),
    ;

    private final String key;

    Fields(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
