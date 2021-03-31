/*
 * Owned by aizuddindeyn
 * Visit https://github.com/aizuddindeyn
 */
package com.aizuddindeyn.voucher.service.voucher;

import com.aizuddindeyn.voucher.model.voucher.validate.request.ValidateVoucherRequest;
import com.aizuddindeyn.voucher.model.voucher.validate.response.ValidateVoucherResponse;

/**
 * @author aizuddindeyn
 */
public interface ValidateVoucherService {

    ValidateVoucherResponse validate(ValidateVoucherRequest request);
}
