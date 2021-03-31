/*
 * Owned by aizuddindeyn
 * Visit https://github.com/aizuddindeyn
 */
package com.aizuddindeyn.voucher.model.recipient.query.response;

import com.aizuddindeyn.voucher.model.common.BaseResponseBody;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author aizuddindeyn
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class RecipientResponse extends BaseResponseBody {

    private static final long serialVersionUID = 8025143764270074771L;

    private String name;

    private String email;

    private List<Voucher> vouchers;
}
