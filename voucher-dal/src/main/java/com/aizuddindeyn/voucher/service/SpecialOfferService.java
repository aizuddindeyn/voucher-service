/*
 * Owned by aizuddindeyn
 * Visit https://github.com/aizuddindeyn
 */
package com.aizuddindeyn.voucher.service;

import com.aizuddindeyn.voucher.dao.SpecialOffer;
import com.aizuddindeyn.voucher.model.offer.request.OfferRequest;
import com.aizuddindeyn.voucher.model.offer.response.OfferResponse;

/**
 * @author aizuddindeyn
 */
public interface SpecialOfferService {

    SpecialOffer findByUniqueId(String uniqueId);

    SpecialOffer save(SpecialOffer offer);

    OfferResponse save(OfferRequest request);
}
