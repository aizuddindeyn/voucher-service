/*
 * Owned by aizuddindeyn
 * Visit https://github.com/aizuddindeyn
 */
package com.aizuddindeyn.voucher.service;

import com.aizuddindeyn.voucher.dao.Recipient;
import com.aizuddindeyn.voucher.model.recipient.register.request.RecipientRequest;

import java.util.List;

/**
 * @author aizuddindeyn
 */
public interface RecipientService {

    Recipient findByEmail(String email);

    List<Recipient> findAll();

    Recipient save(RecipientRequest request);
}
