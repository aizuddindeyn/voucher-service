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
public class BaseResponseBody implements Serializable {

    private static final long serialVersionUID = 85629102886426795L;

    protected String statusCode;

    protected String message;
}
