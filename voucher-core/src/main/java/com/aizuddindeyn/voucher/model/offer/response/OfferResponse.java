/*
 * Owned by aizuddindeyn
 * Visit https://github.com/aizuddindeyn
 */
package com.aizuddindeyn.voucher.model.offer.response;

import com.aizuddindeyn.voucher.model.common.BaseResponseBody;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author aizuddindeyn
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class OfferResponse extends BaseResponseBody {

    private static final long serialVersionUID = 645307722938057590L;

    private String uniqueId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss[.SSS]", shape = JsonFormat.Shape.STRING)
    private LocalDateTime createdTime;
}
