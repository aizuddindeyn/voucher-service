/*
 * Owned by aizuddindeyn
 * Visit https://github.com/aizuddindeyn
 */
package com.aizuddindeyn.voucher.model.recipient.register.request;

import com.aizuddindeyn.voucher.model.common.BaseRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @author aizuddindeyn
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RegisterRecipientRequest extends BaseRequest {

    private static final long serialVersionUID = -9178477231877599336L;

    @NotNull
    private RecipientRequest body;
}
