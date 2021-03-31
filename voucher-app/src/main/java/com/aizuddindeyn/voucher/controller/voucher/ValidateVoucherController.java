/*
 * Owned by aizuddindeyn
 * Visit https://github.com/aizuddindeyn
 */
package com.aizuddindeyn.voucher.controller.voucher;

import com.aizuddindeyn.voucher.controller.AbstractController;
import com.aizuddindeyn.voucher.model.voucher.validate.request.ValidateVoucherRequest;
import com.aizuddindeyn.voucher.model.voucher.validate.response.ValidateVoucherResponse;
import com.aizuddindeyn.voucher.service.voucher.ValidateVoucherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

/**
 * @author aizuddindeyn
 */
@Controller
public class ValidateVoucherController extends AbstractController<ValidateVoucherRequest, ValidateVoucherResponse> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ValidateVoucherController.class);

    private final ValidateVoucherService service;

    @Autowired
    public ValidateVoucherController(ValidateVoucherService service) {
        this.service = service;
    }

    @PostMapping(value = "/1.0/voucher/validate")
    @ResponseBody
    @Override
    public Map<String, Object> process(@Valid @RequestBody ValidateVoucherRequest request,
                                       HttpServletRequest servletRequest) {
        return super.processRequest(request, servletRequest);
    }

    @Override
    protected ValidateVoucherResponse processImpl(ValidateVoucherRequest request) {
        return service.validate(request);
    }

    @Override
    protected Logger getLogger() {
        return LOGGER;
    }
}
