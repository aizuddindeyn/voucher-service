/*
 * Owned by aizuddindeyn
 * Visit https://github.com/aizuddindeyn
 */
package com.aizuddindeyn.voucher.exception;

import com.aizuddindeyn.voucher.enums.ResultCode;

/**
 * @author aizuddindeyn
 */
public class VoucherGenericException extends RuntimeException {

    protected final ResultCode resultCode;

    public VoucherGenericException(ResultCode resultCode, String message) {
        super(message);

        this.resultCode = resultCode;
    }

    public VoucherGenericException(ResultCode resultCode, String message, Throwable t) {
        super(message, t);

        this.resultCode = resultCode;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }
}
