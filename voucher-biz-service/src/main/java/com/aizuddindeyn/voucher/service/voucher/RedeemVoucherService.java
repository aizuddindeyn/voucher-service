/*
 * Owned by aizuddindeyn
 * Visit https://github.com/aizuddindeyn
 */
package com.aizuddindeyn.voucher.service.voucher;

import com.aizuddindeyn.voucher.model.voucher.redeem.request.RedeemVoucherRequest;
import com.aizuddindeyn.voucher.model.voucher.redeem.response.RedeemVoucherResponse;

/**
 * @author aizuddindeyn
 */
public interface RedeemVoucherService {

    RedeemVoucherResponse redeem(RedeemVoucherRequest request);
}
