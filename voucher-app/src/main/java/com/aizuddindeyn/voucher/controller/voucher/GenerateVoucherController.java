/*
 * Owned by aizuddindeyn
 * Visit https://github.com/aizuddindeyn
 */
package com.aizuddindeyn.voucher.controller.voucher;

import com.aizuddindeyn.voucher.controller.AbstractController;
import com.aizuddindeyn.voucher.model.voucher.generate.request.GenerateVoucherRequest;
import com.aizuddindeyn.voucher.model.voucher.generate.response.GenerateVoucherResponse;
import com.aizuddindeyn.voucher.service.voucher.GenerateVoucherService;
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
public class GenerateVoucherController extends AbstractController<GenerateVoucherRequest, GenerateVoucherResponse> {

    private static final Logger LOGGER = LoggerFactory.getLogger(GenerateVoucherController.class);

    private final GenerateVoucherService service;

    @Autowired
    public GenerateVoucherController(GenerateVoucherService service) {
        this.service = service;
    }

    @PostMapping(value = "/1.0/voucher/generate")
    @ResponseBody
    @Override
    public Map<String, Object> process(@Valid @RequestBody GenerateVoucherRequest request,
                                       HttpServletRequest servletRequest) {
        return super.processRequest(request, servletRequest);
    }

    @Override
    protected GenerateVoucherResponse processImpl(GenerateVoucherRequest request) {
        return service.generate(request);
    }

    @Override
    protected Logger getLogger() {
        return LOGGER;
    }
}
