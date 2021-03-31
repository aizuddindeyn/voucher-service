/*
 * Owned by aizuddindeyn
 * Visit https://github.com/aizuddindeyn
 */
package com.aizuddindeyn.voucher.service.voucher.impl;

import com.aizuddindeyn.voucher.dao.Recipient;
import com.aizuddindeyn.voucher.dao.SpecialOffer;
import com.aizuddindeyn.voucher.dao.VoucherBatch;
import com.aizuddindeyn.voucher.enums.Fields;
import com.aizuddindeyn.voucher.enums.ResultCode;
import com.aizuddindeyn.voucher.exception.VoucherGenericException;
import com.aizuddindeyn.voucher.model.voucher.generate.request.GenerateVoucherRequest;
import com.aizuddindeyn.voucher.model.voucher.generate.request.VoucherRequest;
import com.aizuddindeyn.voucher.model.voucher.generate.response.GenerateVoucherResponse;
import com.aizuddindeyn.voucher.resolver.MapperResolver;
import com.aizuddindeyn.voucher.service.RecipientService;
import com.aizuddindeyn.voucher.service.SpecialOfferService;
import com.aizuddindeyn.voucher.service.VoucherBatchService;
import com.aizuddindeyn.voucher.service.voucher.GenerateVoucherService;
import com.aizuddindeyn.voucher.util.LogUtil;
import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author aizuddindeyn
 */
@Service
public class GenerateVoucherServiceImpl implements GenerateVoucherService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GenerateVoucherServiceImpl.class);

    private static final String SERVICE_CLASS = "GenerateVoucherService";

    private final SpecialOfferService specialOfferService;

    private final RecipientService recipientService;

    private final VoucherBatchService voucherBatchService;

    @Autowired
    public GenerateVoucherServiceImpl(SpecialOfferService specialOfferService, RecipientService recipientService,
                                      VoucherBatchService voucherBatchService) {
        this.specialOfferService = specialOfferService;
        this.recipientService = recipientService;
        this.voucherBatchService = voucherBatchService;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public GenerateVoucherResponse generate(GenerateVoucherRequest request) {
        VoucherRequest requestBody = request.getBody();

        LogUtil.logDebug(LOGGER, "{} - Processing generate voucher request: {}", SERVICE_CLASS,
                requestBody.toString());

        SpecialOffer offer = specialOfferService.findByUniqueId(requestBody.getUniqueId());
        if (offer == null) {
            throw new VoucherGenericException(ResultCode.INVALID_REQUEST, "Special offer is not valid");
        }

        offer.setExpiry(requestBody.getExpiry().atTime(23, 59, 59));
        offer.setUpdatedTime(LocalDateTime.now());
        offer = specialOfferService.save(offer);

        List<VoucherBatch> batches = new ArrayList<>();
        List<Recipient> recipients = recipientService.findAll();
        ImmutableMap.Builder<String, Object> builder = ImmutableMap.builder();
        for (Recipient r : recipients) {
            try {
                Map<String, Object> extendInfoMap = builder
                        .put(Fields.RECIPIENT.getKey(), r)
                        .put(Fields.SPECIAL_OFFER.getKey(), offer)
                        .build();
                String extendInfo = MapperResolver.getInstance().writer().writeValueAsString(extendInfoMap);

                VoucherBatch batch = new VoucherBatch();
                batch.setStatus(VoucherBatch.Status.NEW);
                batch.setExtendInfo(extendInfo);
                batch.setCreatedTime(LocalDateTime.now());

                batches.add(batch);

            } catch (IOException ex) {
                LogUtil.logError(LOGGER, "{} - Failed to serialize extend info",SERVICE_CLASS);
            }
        }

        if (!batches.isEmpty()) {
            voucherBatchService.saveAll(batches);
        }

        GenerateVoucherResponse response = new GenerateVoucherResponse();
        response.getBody().setStatusCode(ResultCode.SUCCESS.getCode());
        response.getBody().setMessage(ResultCode.SUCCESS.getMessage());

        return response;
    }
}
