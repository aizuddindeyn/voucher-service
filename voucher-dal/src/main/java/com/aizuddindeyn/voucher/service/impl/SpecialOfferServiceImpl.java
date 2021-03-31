/*
 * Owned by aizuddindeyn
 * Visit https://github.com/aizuddindeyn
 */
package com.aizuddindeyn.voucher.service.impl;

import com.aizuddindeyn.voucher.dao.SpecialOffer;
import com.aizuddindeyn.voucher.model.offer.request.OfferRequest;
import com.aizuddindeyn.voucher.model.offer.response.OfferResponse;
import com.aizuddindeyn.voucher.repository.SpecialOfferRepository;
import com.aizuddindeyn.voucher.service.SpecialOfferService;
import com.aizuddindeyn.voucher.util.GeneratorUtil;
import com.aizuddindeyn.voucher.util.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author aizuddindeyn
 */
@Service
public class SpecialOfferServiceImpl implements SpecialOfferService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpecialOfferServiceImpl.class);

    private static final String SERVICE_CLASS = "SpecialOfferService";

    private final SpecialOfferRepository repository;

    @Autowired
    public SpecialOfferServiceImpl(SpecialOfferRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public SpecialOffer findByUniqueId(String uniqueId) {
        LogUtil.logDebug(LOGGER, "{} - Query special_offer by unique_id = {}", SERVICE_CLASS,
                uniqueId);
        return repository.findByUniqueId(uniqueId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public SpecialOffer save(SpecialOffer offer) {
        LogUtil.logDebug(LOGGER, "{} - Saving special_offer record: {}", SERVICE_CLASS,
                offer.toString());
        return repository.save(offer);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public OfferResponse save(OfferRequest request) {
        SpecialOffer offer = new SpecialOffer();
        offer.setUniqueId(GeneratorUtil.generateUuid());
        offer.setPrefix(request.getPrefix());
        offer.setName(request.getName());
        offer.setStatus(SpecialOffer.Status.ACTIVE);
        offer.setCodeLength(request.getCodeLength());
        offer.setDiscount(request.getDiscount());
        offer.setExpiry(request.getExpiry().atTime(23, 59, 59));
        offer.setCreatedTime(LocalDateTime.now());

        LogUtil.logDebug(LOGGER, "{} - Saving special_offer record: {}", SERVICE_CLASS,
                offer.toString());
        SpecialOffer result = repository.save(offer);

        return OfferResponse.builder()
                .uniqueId(result.getUniqueId())
                .createdTime(result.getCreatedTime())
                .build();
    }
}
