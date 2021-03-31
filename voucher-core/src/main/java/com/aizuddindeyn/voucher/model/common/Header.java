/*
 * Owned by aizuddindeyn
 * Visit https://github.com/aizuddindeyn
 */
package com.aizuddindeyn.voucher.model.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author aizuddindeyn
 */
@Data
public class Header implements Serializable {

    private static final long serialVersionUID = 4280563177156479965L;

    @NotBlank(message = "Invalid function")
    private String function;

    @NotBlank(message = "Invalid version")
    private String version;

    @NotBlank(message = "Invalid request ID")
    private String requestMsgId;

    @NotBlank(message = "Invalid request time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss[.SSS]", shape = JsonFormat.Shape.STRING)
    private LocalDateTime requestTime;
}
