/*
 * Owned by aizuddindeyn
 * Visit https://github.com/aizuddindeyn
 */
package com.aizuddindeyn.voucher.model.recipient.query.request;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author aizuddindeyn
 */
@Data
public class RecipientRequest implements Serializable {

    private static final long serialVersionUID = -3013285424015352112L;

    @Max(value = 200, message = "Email length cannot be more than 200")
    @NotBlank(message = "Email is mandatory")
    private String email;
}
