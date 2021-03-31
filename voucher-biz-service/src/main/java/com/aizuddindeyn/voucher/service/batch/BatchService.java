/*
 * Owned by aizuddindeyn
 * Visit https://github.com/aizuddindeyn
 */
package com.aizuddindeyn.voucher.service.batch;

import com.aizuddindeyn.voucher.dao.VoucherBatch;
import com.aizuddindeyn.voucher.dao.VoucherCode;

import java.util.List;

/**
 * @author aizuddindeyn
 */
public interface BatchService {

    List<VoucherBatch> findPendingVoucherBatch();

    void save(VoucherCode voucher, VoucherBatch batch);
}
