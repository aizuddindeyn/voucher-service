/*
 * Owned by aizuddindeyn
 * Visit https://github.com/aizuddindeyn
 */
package com.aizuddindeyn.voucher.service;

import com.aizuddindeyn.voucher.dao.VoucherBatch;

import java.util.List;

/**
 * @author aizuddindeyn
 */
public interface VoucherBatchService {

    List<VoucherBatch> findByStatus(VoucherBatch.Status status);

    VoucherBatch save(VoucherBatch batch);

    void saveAll(List<VoucherBatch> batches);
}
