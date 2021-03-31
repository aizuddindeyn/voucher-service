/*
 * Owned by aizuddindeyn
 * Visit https://github.com/aizuddindeyn
 */
package com.aizuddindeyn.voucher.service.voucher.impl;

import com.aizuddindeyn.voucher.dao.Recipient;
import com.aizuddindeyn.voucher.dao.SpecialOffer;
import com.aizuddindeyn.voucher.dao.VoucherCode;
import com.aizuddindeyn.voucher.enums.ResultCode;
import com.aizuddindeyn.voucher.exception.VoucherGenericException;
import com.aizuddindeyn.voucher.model.voucher.validate.request.ValidateVoucherRequest;
import com.aizuddindeyn.voucher.model.voucher.validate.request.VoucherRequest;
import com.aizuddindeyn.voucher.model.voucher.validate.response.ValidateVoucherResponse;
import com.aizuddindeyn.voucher.service.SpecialOfferService;
import com.aizuddindeyn.voucher.service.VoucherCodeService;
import com.aizuddindeyn.voucher.service.voucher.ValidateVoucherService;
import com.aizuddindeyn.voucher.util.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author aizuddindeyn
 */
@Service
public class ValidateVoucherServiceImpl implements ValidateVoucherService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ValidateVoucherServiceImpl.class);

    private static final String SERVICE_CLASS = "ValidateVoucherService";

    private static final String VOUCHER_NOT_VALID_MESSAGE = "Voucher is not valid";

    private final VoucherCodeService voucherCodeService;

    @Autowired
    public ValidateVoucherServiceImpl(VoucherCodeService voucherCodeService) {
        this.voucherCodeService = voucherCodeService;
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public ValidateVoucherResponse validate(ValidateVoucherRequest request) {
        VoucherRequest requestBody = request.getBody();

        LogUtil.logDebug(LOGGER, "{} - Validating voucher request: {}", SERVICE_CLASS, requestBody.toString());

        VoucherCode voucher = voucherCodeService.findByCode(requestBody.getVoucher());
        if (voucher == null || VoucherCode.Status.VALID != voucher.getStatus()) {
            throw new VoucherGenericException(ResultCode.INVALID_REQUEST, VOUCHER_NOT_VALID_MESSAGE);
        }

        Recipient recipient = voucher.getRecipient();
        if (recipient == null || !requestBody.getEmail().equals(recipient.getEmail())) {
            throw new VoucherGenericException(ResultCode.INVALID_REQUEST, VOUCHER_NOT_VALID_MESSAGE);
        }

        SpecialOffer specialOffer = voucher.getSpecialOffer();
        if (specialOffer == null || SpecialOffer.Status.ACTIVE != specialOffer.getStatus()) {
            throw new VoucherGenericException(ResultCode.INVALID_REQUEST, VOUCHER_NOT_VALID_MESSAGE);
        }

        ValidateVoucherResponse response = new ValidateVoucherResponse();
        response.getBody().setDiscount(specialOffer.getDiscount());
        response.getBody().setStatusCode(ResultCode.SUCCESS.getCode());
        response.getBody().setMessage(ResultCode.SUCCESS.getMessage());

        return response;
    }
}
