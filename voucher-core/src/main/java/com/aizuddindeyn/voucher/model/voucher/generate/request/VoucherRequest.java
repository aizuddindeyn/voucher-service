/*
 * Owned by aizuddindeyn
 * Visit https://github.com/aizuddindeyn
 */
package com.aizuddindeyn.voucher.model.voucher.generate.request;

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
public class VoucherRequest implements Serializable {

    private static final long serialVersionUID = 4741656613106708149L;

    @Max(value = 36, message = "Unique ID length cannot be more than 36")
    @NotBlank(message = "Unique ID is mandatory")
    private String uniqueId;

    @NotNull(message = "Expiry cannot be empty")
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate expiry;
}
