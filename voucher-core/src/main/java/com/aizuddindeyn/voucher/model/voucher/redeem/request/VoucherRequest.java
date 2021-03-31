/*
 * Owned by aizuddindeyn
 * Visit https://github.com/aizuddindeyn
 */
package com.aizuddindeyn.voucher.model.voucher.redeem.request;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author aizuddindeyn
 */
@Data
public class VoucherRequest implements Serializable {

    private static final long serialVersionUID = -5059666033007752983L;

    @Max(value = 54, message = "Voucher length cannot be more than 54")
    @NotBlank(message = "Voucher is mandatory")
    private String voucher;
}
