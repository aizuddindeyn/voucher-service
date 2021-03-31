/*
 * Owned by aizuddindeyn
 * Visit https://github.com/aizuddindeyn
 */
package com.aizuddindeyn.voucher.model.recipient.query.response;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author aizuddindeyn
 */
@Data
@Builder
public class Voucher implements Serializable {

    private static final long serialVersionUID = 46499939415191977L;

    private String offer;

    private String code;
}
