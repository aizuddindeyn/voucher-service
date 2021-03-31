/*
 * Owned by aizuddindeyn
 * Visit https://github.com/aizuddindeyn
 */
package com.aizuddindeyn.voucher.model.offer.request;

import com.aizuddindeyn.voucher.model.common.BaseRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @author aizuddindeyn
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class GenerateOfferRequest extends BaseRequest {

    private static final long serialVersionUID = 872713492349311082L;

    @NotNull
    private OfferRequest body;
}
