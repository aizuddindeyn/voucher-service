/*
 * Owned by aizuddindeyn
 * Visit https://github.com/aizuddindeyn
 */
package com.aizuddindeyn.voucher.service;

import com.aizuddindeyn.voucher.dao.Recipient;
import com.aizuddindeyn.voucher.dao.VoucherCode;

import java.util.List;

/**
 * @author aizuddindeyn
 */
public interface VoucherCodeService {

    List<VoucherCode> findByRecipientAndStatus(Recipient recipient, VoucherCode.Status status);

    VoucherCode findByCode(String code);

    VoucherCode save(VoucherCode voucher);
}
