/*
 * Owned by aizuddindeyn
 * Visit https://github.com/aizuddindeyn
 */
package com.aizuddindeyn.voucher.model.recipient.query.response;

import com.aizuddindeyn.voucher.model.common.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author aizuddindeyn
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class QueryRecipientResponse extends BaseResponse {

    private static final long serialVersionUID = -8678678781896723990L;

    private RecipientResponse body = RecipientResponse.builder().build();
}
