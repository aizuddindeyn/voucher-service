/*
 * Owned by aizuddindeyn
 * Visit https://github.com/aizuddindeyn
 */
package com.aizuddindeyn.voucher.model.voucher.validate.request;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author aizuddindeyn
 */
@Data
public class VoucherRequest implements Serializable {

    private static final long serialVersionUID = -2469868618527693681L;

    @Max(value = 54, message = "Voucher length cannot be more than 54")
    @NotBlank(message = "Voucher is mandatory")
    private String voucher;

    @Max(value = 200, message = "Email length cannot be more than 200")
    @NotBlank(message = "Email is mandatory")
    private String email;
}
