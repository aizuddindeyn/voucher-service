/*
 * Owned by aizuddindeyn
 * Visit https://github.com/aizuddindeyn
 */
package com.aizuddindeyn.voucher.model.voucher.validate.request;

import com.aizuddindeyn.voucher.model.common.BaseRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @author aizuddindeyn
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ValidateVoucherRequest extends BaseRequest {

    private static final long serialVersionUID = -8330923623252439887L;

    @NotNull
    private VoucherRequest body;
}
