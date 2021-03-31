/*
 * Owned by aizuddindeyn
 * Visit https://github.com/aizuddindeyn
 */
package com.aizuddindeyn.voucher.model.voucher.validate.response;

import com.aizuddindeyn.voucher.model.common.BaseResponseBody;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author aizuddindeyn
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class VoucherResponse extends BaseResponseBody {

    private static final long serialVersionUID = 7474994467515735162L;

    private Integer discount;
}
