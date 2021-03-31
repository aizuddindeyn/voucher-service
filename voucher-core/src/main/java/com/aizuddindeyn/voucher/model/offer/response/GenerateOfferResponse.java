/*
 * Owned by aizuddindeyn
 * Visit https://github.com/aizuddindeyn
 */
package com.aizuddindeyn.voucher.model.offer.response;

import com.aizuddindeyn.voucher.model.common.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author aizuddindeyn
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class GenerateOfferResponse extends BaseResponse {

    private static final long serialVersionUID = -3020869837820320794L;

    private OfferResponse body = OfferResponse.builder().build();
}
