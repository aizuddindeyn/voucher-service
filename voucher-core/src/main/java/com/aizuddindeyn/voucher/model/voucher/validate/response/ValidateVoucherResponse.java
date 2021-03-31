/*
 * Owned by aizuddindeyn
 * Visit https://github.com/aizuddindeyn
 */
package com.aizuddindeyn.voucher.model.voucher.validate.response;

import com.aizuddindeyn.voucher.model.common.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author aizuddindeyn
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ValidateVoucherResponse extends BaseResponse {

    private static final long serialVersionUID = -29155817664863468L;

    private VoucherResponse body = VoucherResponse.builder().build();
}
