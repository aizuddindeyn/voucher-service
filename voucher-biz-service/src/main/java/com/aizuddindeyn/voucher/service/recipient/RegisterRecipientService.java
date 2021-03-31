/*
 * Owned by aizuddindeyn
 * Visit https://github.com/aizuddindeyn
 */
package com.aizuddindeyn.voucher.service.recipient;

import com.aizuddindeyn.voucher.model.recipient.register.request.RegisterRecipientRequest;
import com.aizuddindeyn.voucher.model.recipient.register.response.RegisterRecipientResponse;

/**
 * @author aizuddindeyn
 * @date 2/1/2021
 */
public interface RegisterRecipientService {

    RegisterRecipientResponse register(RegisterRecipientRequest request);
}
