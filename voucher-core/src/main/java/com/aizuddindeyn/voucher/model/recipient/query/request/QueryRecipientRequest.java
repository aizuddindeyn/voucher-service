/*
 * Owned by aizuddindeyn
 * Visit https://github.com/aizuddindeyn
 */
package com.aizuddindeyn.voucher.model.recipient.query.request;

import com.aizuddindeyn.voucher.model.common.BaseRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @author aizuddindeyn
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class QueryRecipientRequest extends BaseRequest {

    private static final long serialVersionUID = 6513152124924375478L;

    @NotNull
    private RecipientRequest body;
}
