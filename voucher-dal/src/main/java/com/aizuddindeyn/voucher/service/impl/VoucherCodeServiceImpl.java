/*
 * Owned by aizuddindeyn
 * Visit https://github.com/aizuddindeyn
 */
package com.aizuddindeyn.voucher.service.impl;

import com.aizuddindeyn.voucher.dao.Recipient;
import com.aizuddindeyn.voucher.dao.VoucherCode;
import com.aizuddindeyn.voucher.repository.VoucherCodeRepository;
import com.aizuddindeyn.voucher.service.VoucherCodeService;
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
public class VoucherCodeServiceImpl implements VoucherCodeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(VoucherCodeServiceImpl.class);

    private static final String SERVICE_CLASS = "VoucherCodeService";

    private final VoucherCodeRepository repository;

    @Autowired
    public VoucherCodeServiceImpl(VoucherCodeRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public List<VoucherCode> findByRecipientAndStatus(Recipient recipient, VoucherCode.Status status) {
        LogUtil.logDebug(LOGGER, "{} - Query voucher_code by recipient and status: {}, {}", SERVICE_CLASS,
                recipient.getId(), status.name());

        return repository.findByRecipientAndStatus(recipient, status);
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public VoucherCode findByCode(String code) {
        LogUtil.logDebug(LOGGER, "{} - Query voucher_code by code: {}", SERVICE_CLASS, code);

        return repository.findByCode(code);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public VoucherCode save(VoucherCode voucher) {
        LogUtil.logDebug(LOGGER, "{} - Saving voucher_code: {}", SERVICE_CLASS, voucher.toString());

        return repository.save(voucher);
    }
}
