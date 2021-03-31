/*
 * Owned by aizuddindeyn
 * Visit https://github.com/aizuddindeyn
 */
package com.aizuddindeyn.voucher.service.recipient.impl;

import com.aizuddindeyn.voucher.dao.Recipient;
import com.aizuddindeyn.voucher.dao.SpecialOffer;
import com.aizuddindeyn.voucher.dao.VoucherCode;
import com.aizuddindeyn.voucher.enums.ResultCode;
import com.aizuddindeyn.voucher.exception.VoucherGenericException;
import com.aizuddindeyn.voucher.model.recipient.query.request.QueryRecipientRequest;
import com.aizuddindeyn.voucher.model.recipient.query.request.RecipientRequest;
import com.aizuddindeyn.voucher.model.recipient.query.response.QueryRecipientResponse;
import com.aizuddindeyn.voucher.model.recipient.query.response.Voucher;
import com.aizuddindeyn.voucher.service.RecipientService;
import com.aizuddindeyn.voucher.service.VoucherCodeService;
import com.aizuddindeyn.voucher.service.recipient.QueryRecipientService;
import com.aizuddindeyn.voucher.util.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author aizuddindeyn
 */
@Service
public class QueryRecipientServiceImpl implements QueryRecipientService {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueryRecipientServiceImpl.class);

    private static final String SERVICE_CLASS = "QueryRecipientService";

    private static final String EMAIL_NOT_VALID_MESSAGE = "Email is not valid";

    private final RecipientService recipientService;

    private final VoucherCodeService voucherCodeService;

    @Autowired
    public QueryRecipientServiceImpl(RecipientService recipientService, VoucherCodeService voucherCodeService) {
        this.recipientService = recipientService;
        this.voucherCodeService = voucherCodeService;
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public QueryRecipientResponse query(QueryRecipientRequest request) {
        RecipientRequest requestBody = request.getBody();

        LogUtil.logDebug(LOGGER, "{} - Query recipient request: {}", SERVICE_CLASS, requestBody.toString());

        Recipient recipient = recipientService.findByEmail(requestBody.getEmail());
        if (recipient == null) {
            throw new VoucherGenericException(ResultCode.INVALID_REQUEST, EMAIL_NOT_VALID_MESSAGE);
        }

        List<Voucher> vouchers = new ArrayList<>();
        List<VoucherCode> voucherCodes =
                voucherCodeService.findByRecipientAndStatus(recipient, VoucherCode.Status.VALID);
        LogUtil.logDebug(LOGGER, "{} - Found {} voucher_code for recipient: {}", SERVICE_CLASS,
                voucherCodes.size(), recipient.getName());

        for (VoucherCode voucherCode : voucherCodes) {
            SpecialOffer offer = voucherCode.getSpecialOffer();
            String offerName = "";
            if (offer != null) {
                offerName = offer.getName();
            }

            Voucher voucher = Voucher.builder()
                    .offer(offerName)
                    .code(voucherCode.getCode())
                    .build();

            vouchers.add(voucher);
        }

        QueryRecipientResponse response = new QueryRecipientResponse();
        response.getBody().setName(recipient.getName());
        response.getBody().setEmail(recipient.getEmail());
        response.getBody().setVouchers(vouchers);
        response.getBody().setStatusCode(ResultCode.SUCCESS.getCode());
        response.getBody().setMessage(ResultCode.SUCCESS.getMessage());

        return response;
    }
}
