/*
 * Owned by aizuddindeyn
 * Visit https://github.com/aizuddindeyn
 */
package com.aizuddindeyn.voucher.model.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author aizuddindeyn
 */
@Data
public class BaseResponse implements Serializable {

    private static final long serialVersionUID = -4739580600613214749L;

    protected Header header;

    protected BaseResponseBody body = new BaseResponseBody();

    protected String signature = "";
}
