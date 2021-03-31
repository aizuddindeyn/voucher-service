/*
 * Owned by aizuddindeyn
 * Visit https://github.com/aizuddindeyn
 */
package com.aizuddindeyn.voucher.controller.voucher;

import com.aizuddindeyn.voucher.controller.AbstractController;
import com.aizuddindeyn.voucher.model.voucher.redeem.request.RedeemVoucherRequest;
import com.aizuddindeyn.voucher.model.voucher.redeem.response.RedeemVoucherResponse;
import com.aizuddindeyn.voucher.service.voucher.RedeemVoucherService;
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
public class RedeemVoucherController extends AbstractController<RedeemVoucherRequest, RedeemVoucherResponse> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedeemVoucherController.class);

    private final RedeemVoucherService service;

    @Autowired
    public RedeemVoucherController(RedeemVoucherService service) {
        this.service = service;
    }

    @PostMapping(value = "/1.0/voucher/redeem")
    @ResponseBody
    @Override
    public Map<String, Object> process(@Valid @RequestBody RedeemVoucherRequest request,
                                       HttpServletRequest servletRequest) {
        return super.processRequest(request, servletRequest);
    }

    @Override
    protected RedeemVoucherResponse processImpl(RedeemVoucherRequest request) {
        return service.redeem(request);
    }

    @Override
    protected Logger getLogger() {
        return LOGGER;
    }
}
