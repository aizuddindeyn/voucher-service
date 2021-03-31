/*
 * Owned by aizuddindeyn
 * Visit https://github.com/aizuddindeyn
 */
package com.aizuddindeyn.voucher.service.offer;

import com.aizuddindeyn.voucher.model.offer.request.GenerateOfferRequest;
import com.aizuddindeyn.voucher.model.offer.response.GenerateOfferResponse;

/**
 * @author aizuddindeyn
 */
public interface GenerateOfferService {

    GenerateOfferResponse generate(GenerateOfferRequest request);
}
