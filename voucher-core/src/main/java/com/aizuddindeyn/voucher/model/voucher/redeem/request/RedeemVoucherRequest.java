/*
 * Owned by aizuddindeyn
 * Visit https://github.com/aizuddindeyn
 */
package com.aizuddindeyn.voucher.model.voucher.redeem.request;

import com.aizuddindeyn.voucher.model.common.BaseRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @author aizuddindeyn
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RedeemVoucherRequest extends BaseRequest {

    private static final long serialVersionUID = 1544700068489321881L;

    @NotNull
    private VoucherRequest body;
}
