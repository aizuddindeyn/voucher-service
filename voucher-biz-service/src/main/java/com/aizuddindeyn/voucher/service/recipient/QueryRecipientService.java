/*
 * Owned by aizuddindeyn
 * Visit https://github.com/aizuddindeyn
 */
package com.aizuddindeyn.voucher.service.recipient;

import com.aizuddindeyn.voucher.model.recipient.query.request.QueryRecipientRequest;
import com.aizuddindeyn.voucher.model.recipient.query.response.QueryRecipientResponse;

/**
 * @author aizuddindeyn
 */
public interface QueryRecipientService {

    QueryRecipientResponse query(QueryRecipientRequest request);
}
