/*
 * Owned by aizuddindeyn
 * Visit https://github.com/aizuddindeyn
 */
package com.aizuddindeyn.voucher.service.recipient.impl;

import com.aizuddindeyn.voucher.dao.Recipient;
import com.aizuddindeyn.voucher.enums.ResultCode;
import com.aizuddindeyn.voucher.exception.VoucherGenericException;
import com.aizuddindeyn.voucher.model.recipient.register.request.RecipientRequest;
import com.aizuddindeyn.voucher.model.recipient.register.request.RegisterRecipientRequest;
import com.aizuddindeyn.voucher.model.recipient.register.response.RegisterRecipientResponse;
import com.aizuddindeyn.voucher.service.RecipientService;
import com.aizuddindeyn.voucher.service.recipient.RegisterRecipientService;
import com.aizuddindeyn.voucher.util.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author aizuddindeyn
 * @date 2/1/2021
 */
@Service
public class RegisterRecipientServiceImpl implements RegisterRecipientService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterRecipientServiceImpl.class);

    private static final String SERVICE_CLASS = "RegisterRecipientService";

    private final RecipientService recipientService;

    @Autowired
    public RegisterRecipientServiceImpl(RecipientService recipientService) {
        this.recipientService = recipientService;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public RegisterRecipientResponse register(RegisterRecipientRequest request) {
        RecipientRequest requestBody = request.getBody();

        LogUtil.logDebug(LOGGER, "{} - Registering recipient request: {}", SERVICE_CLASS,
                requestBody.toString());

        Recipient recipient = recipientService.findByEmail(requestBody.getEmail());
        if (recipient != null) {
            throw new VoucherGenericException(ResultCode.INVALID_REQUEST, "Email already been used");
        }

        recipientService.save(requestBody);

        RegisterRecipientResponse response = new RegisterRecipientResponse();
        response.getBody().setStatusCode(ResultCode.SUCCESS.getCode());
        response.getBody().setMessage(ResultCode.SUCCESS.getMessage());

        return response;
    }
}
