/*
 * Owned by aizuddindeyn
 * Visit https://github.com/aizuddindeyn
 */
package com.aizuddindeyn.voucher.service.impl;

import com.aizuddindeyn.voucher.dao.VoucherBatch;
import com.aizuddindeyn.voucher.repository.VoucherBatchRepository;
import com.aizuddindeyn.voucher.service.VoucherBatchService;
import com.aizuddindeyn.voucher.util.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author aizuddindeyn
 */
@Service
public class VoucherBatchServiceImpl implements VoucherBatchService {

    private static final Logger LOGGER = LoggerFactory.getLogger(VoucherBatchServiceImpl.class);

    private static final String SERVICE_CLASS = "VoucherBatchService";

    private final VoucherBatchRepository repository;

    @Autowired
    public VoucherBatchServiceImpl(VoucherBatchRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public List<VoucherBatch> findByStatus(VoucherBatch.Status status) {
        LogUtil.logDebug(LOGGER, "{} - Query voucher_batch by status = {}", SERVICE_CLASS,
                status.name());
        return repository.findByStatus(status);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public VoucherBatch save(VoucherBatch batch) {
        LogUtil.logDebug(LOGGER, "{} - Saving voucher_batch: {}", SERVICE_CLASS, batch.toString());
        return repository.save(batch);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveAll(List<VoucherBatch> batches) {
        LogUtil.logDebug(LOGGER, "{} - Saving list of voucher_batch", SERVICE_CLASS);
        repository.saveAll(batches);
    }
}
