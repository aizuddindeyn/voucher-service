/*
 * Owned by aizuddindeyn
 * Visit https://github.com/aizuddindeyn
 */
package com.aizuddindeyn.voucher.model.common;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author aizuddindeyn
 */
@Data
public class BaseRequest implements Serializable {

    private static final long serialVersionUID = -4585754880809817415L;

    @NotNull(message = "header is mandatory")
    protected Header header;

    protected String signature;
}
