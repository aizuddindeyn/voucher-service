/*
 * Owned by aizuddindeyn
 * Visit https://github.com/aizuddindeyn
 */
package com.aizuddindeyn.voucher.model.offer.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author aizuddindeyn
 */
@Data
public class OfferRequest implements Serializable {

    private static final long serialVersionUID = 589182600333803403L;

    @Max(value = 200, message = "Name length cannot be more than 200")
    @NotBlank(message = "Name is mandatory")
    private String name;

    @Max(value = 4, message = "Prefix length cannot be more than 4")
    private String prefix;

    @Max(value = 50, message = "Code Length cannot be more than 50")
    private Integer codeLength;

    @Max(value = 100, message = "Discount cannot be more than 100")
    @NotNull(message = "Discount is mandatory")
    private Integer discount;

    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate expiry;
}
