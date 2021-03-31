/*
 * Owned by aizuddindeyn
 * Visit https://github.com/aizuddindeyn
 */
package com.aizuddindeyn.voucher.service.voucher.impl;

import com.aizuddindeyn.voucher.dao.VoucherCode;
import com.aizuddindeyn.voucher.enums.ResultCode;
import com.aizuddindeyn.voucher.exception.VoucherGenericException;
import com.aizuddindeyn.voucher.model.voucher.redeem.request.RedeemVoucherRequest;
import com.aizuddindeyn.voucher.model.voucher.redeem.request.VoucherRequest;
import com.aizuddindeyn.voucher.model.voucher.redeem.response.RedeemVoucherResponse;
import com.aizuddindeyn.voucher.service.VoucherCodeService;
import com.aizuddindeyn.voucher.service.voucher.RedeemVoucherService;
import com.aizuddindeyn.voucher.util.LogUtil;
import org.hibernate.dialect.lock.OptimisticEntityLockException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * @author aizuddindeyn
 */
@Service
public class RedeemVoucherServiceImpl implements RedeemVoucherService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedeemVoucherServiceImpl.class);

    private static final String SERVICE_CLASS = "RedeemVoucherService";

    private static final String VOUCHER_NOT_VALID_MESSAGE = "Voucher is not valid";

    private final VoucherCodeService voucherCodeService;

    @Value("${optimistic.lock.retry:3}")
    private Integer optimisticRetry;

    @Autowired
    public RedeemVoucherServiceImpl(VoucherCodeService voucherCodeService) {
        this.voucherCodeService = voucherCodeService;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public RedeemVoucherResponse redeem(RedeemVoucherRequest request) {
        VoucherRequest requestBody = request.getBody();

        LogUtil.logDebug(LOGGER, "{} - Redeeming voucher request: {}", SERVICE_CLASS, requestBody.toString());

        VoucherCode voucher = voucherCodeService.findByCode(requestBody.getVoucher());
        if (voucher == null || VoucherCode.Status.VALID != voucher.getStatus()) {
            throw new VoucherGenericException(ResultCode.INVALID_REQUEST, VOUCHER_NOT_VALID_MESSAGE);
        }

        boolean success = false;
        for (int attempt = 1; attempt <= optimisticRetry; attempt++) {
            try {
                voucher.setStatus(VoucherCode.Status.USED);
                voucher.setRedeemTime(LocalDateTime.now());
                LogUtil.logInfo(LOGGER, "{} - Redeem attempt {}: {}", SERVICE_CLASS, attempt, voucher);

                voucher = voucherCodeService.save(voucher);
                LogUtil.logInfo(LOGGER, "{} - Redeem completed", SERVICE_CLASS);
                success = true;

            } catch (OptimisticEntityLockException ex) {
                LogUtil.logError(LOGGER, "{} - Optimistic lock occurred", SERVICE_CLASS);
            }
        }

        RedeemVoucherResponse response = new RedeemVoucherResponse();
        if (success) {
            response.getBody().setStatusCode(ResultCode.SUCCESS.getCode());
            response.getBody().setMessage(ResultCode.SUCCESS.getMessage());
        } else {
            response.getBody().setStatusCode(ResultCode.SYSTEM_ERROR.getCode());
            response.getBody().setMessage(ResultCode.SYSTEM_ERROR.getMessage());
        }

        return response;
    }
}
