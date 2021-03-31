/*
 * Owned by aizuddindeyn
 * Visit https://github.com/aizuddindeyn
 */
package com.aizuddindeyn.voucher.service.batch.impl;

import com.aizuddindeyn.voucher.dao.VoucherBatch;
import com.aizuddindeyn.voucher.dao.VoucherCode;
import com.aizuddindeyn.voucher.service.VoucherBatchService;
import com.aizuddindeyn.voucher.service.VoucherCodeService;
import com.aizuddindeyn.voucher.service.batch.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author aizuddindeyn
 */
@Service
public class BatchServiceImpl implements BatchService {

    private final VoucherCodeService voucherCodeService;

    private final VoucherBatchService voucherBatchService;

    @Autowired
    public BatchServiceImpl(VoucherCodeService voucherCodeService, VoucherBatchService voucherBatchService) {
        this.voucherCodeService = voucherCodeService;
        this.voucherBatchService = voucherBatchService;
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public List<VoucherBatch> findPendingVoucherBatch() {
        return voucherBatchService.findByStatus(VoucherBatch.Status.NEW);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(VoucherCode voucher, VoucherBatch batch) {
        voucherCodeService.save(voucher);
        voucherBatchService.save(batch);
    }
}
