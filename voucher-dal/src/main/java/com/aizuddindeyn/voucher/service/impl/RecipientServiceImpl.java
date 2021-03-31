/*
 * Owned by aizuddindeyn
 * Visit https://github.com/aizuddindeyn
 */
package com.aizuddindeyn.voucher.service.impl;

import com.aizuddindeyn.voucher.dao.Recipient;
import com.aizuddindeyn.voucher.model.recipient.register.request.RecipientRequest;
import com.aizuddindeyn.voucher.repository.RecipientRepository;
import com.aizuddindeyn.voucher.service.RecipientService;
import com.aizuddindeyn.voucher.util.LogUtil;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author aizuddindeyn
 */
@Service
public class RecipientServiceImpl implements RecipientService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RecipientServiceImpl.class);

    private static final String SERVICE_CLASS = "RecipientService";

    private final RecipientRepository repository;

    @Autowired
    public RecipientServiceImpl(RecipientRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public Recipient findByEmail(String email) {
        LogUtil.logDebug(LOGGER, "{} - Query recipient by email {}", SERVICE_CLASS, email);

        return repository.findByEmail(email);
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public List<Recipient> findAll() {
        LogUtil.logDebug(LOGGER, "{} - Query all recipient", SERVICE_CLASS);

        return Lists.newArrayList(repository.findAll());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Recipient save(RecipientRequest request) {
        LogUtil.logDebug(LOGGER, "{} - Saving recipient: {}", SERVICE_CLASS, request.toString());

        Recipient recipient = new Recipient();
        recipient.setName(request.getName());
        recipient.setEmail(request.getEmail());
        recipient.setStatus(Recipient.Status.ACTIVE);
        recipient.setCreatedTime(LocalDateTime.now());

        return repository.save(recipient);
    }
}
