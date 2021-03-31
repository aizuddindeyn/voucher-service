/*
 * Owned by aizuddindeyn
 * Visit https://github.com/aizuddindeyn
 */
package com.aizuddindeyn.voucher.service.voucher;

import com.aizuddindeyn.voucher.model.voucher.generate.request.GenerateVoucherRequest;
import com.aizuddindeyn.voucher.model.voucher.generate.response.GenerateVoucherResponse;

/**
 * @author aizuddindeyn
 */
public interface GenerateVoucherService {

    GenerateVoucherResponse generate(GenerateVoucherRequest request);
}
