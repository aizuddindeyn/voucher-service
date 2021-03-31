/*
 * Owned by aizuddindeyn
 * Visit https://github.com/aizuddindeyn
 */
package com.aizuddindeyn.voucher.controller.recipient;

import com.aizuddindeyn.voucher.controller.AbstractController;
import com.aizuddindeyn.voucher.model.recipient.register.request.RegisterRecipientRequest;
import com.aizuddindeyn.voucher.model.recipient.register.response.RegisterRecipientResponse;
import com.aizuddindeyn.voucher.service.recipient.RegisterRecipientService;
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
public class RegisterRecipientController extends AbstractController<RegisterRecipientRequest, RegisterRecipientResponse> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterRecipientController.class);

    private final RegisterRecipientService service;

    @Autowired
    public RegisterRecipientController(RegisterRecipientService service) {
        this.service = service;
    }

    @PostMapping(value = "/1.0/recipient/register")
    @ResponseBody
    @Override
    public Map<String, Object> process(@Valid @RequestBody RegisterRecipientRequest request,
                                       HttpServletRequest servletRequest) {
        return super.processRequest(request, servletRequest);
    }

    @Override
    protected RegisterRecipientResponse processImpl(RegisterRecipientRequest request) {
        return service.register(request);
    }

    @Override
    protected Logger getLogger() {
        return LOGGER;
    }
}
