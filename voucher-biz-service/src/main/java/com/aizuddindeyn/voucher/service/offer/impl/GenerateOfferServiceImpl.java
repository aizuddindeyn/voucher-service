/*
 * Owned by aizuddindeyn
 * Visit https://github.com/aizuddindeyn
 */
package com.aizuddindeyn.voucher.service.offer.impl;

import com.aizuddindeyn.voucher.enums.ResultCode;
import com.aizuddindeyn.voucher.model.offer.request.GenerateOfferRequest;
import com.aizuddindeyn.voucher.model.offer.response.GenerateOfferResponse;
import com.aizuddindeyn.voucher.model.offer.response.OfferResponse;
import com.aizuddindeyn.voucher.service.SpecialOfferService;
import com.aizuddindeyn.voucher.service.offer.GenerateOfferService;
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
public class GenerateOfferServiceImpl implements GenerateOfferService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GenerateOfferServiceImpl.class);

    private final SpecialOfferService specialOfferService;

    @Autowired
    public GenerateOfferServiceImpl(SpecialOfferService specialOfferService) {
        this.specialOfferService = specialOfferService;
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public GenerateOfferResponse generate(GenerateOfferRequest request) {
        LogUtil.logDebug(LOGGER, "{} - Processing generate offer request", "GenerateOfferService");

        OfferResponse responseBody = specialOfferService.save(request.getBody());
        responseBody.setStatusCode(ResultCode.SUCCESS.getCode());
        responseBody.setMessage(ResultCode.SUCCESS.getMessage());

        GenerateOfferResponse response = new GenerateOfferResponse();
        response.setBody(responseBody);

        return response;
    }
}
