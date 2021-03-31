/*
 * Owned by aizuddindeyn
 * Visit https://github.com/aizuddindeyn
 */
package com.aizuddindeyn.voucher.controller.offer;

import com.aizuddindeyn.voucher.controller.AbstractController;
import com.aizuddindeyn.voucher.model.offer.request.GenerateOfferRequest;
import com.aizuddindeyn.voucher.model.offer.request.OfferRequest;
import com.aizuddindeyn.voucher.model.offer.response.GenerateOfferResponse;
import com.aizuddindeyn.voucher.service.offer.GenerateOfferService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Map;

/**
 * @author aizuddindeyn
 */
@Controller
public class GenerateOfferController extends AbstractController<GenerateOfferRequest, GenerateOfferResponse> {

    private static final Logger LOGGER = LoggerFactory.getLogger(GenerateOfferController.class);

    private static final int PREFIX_LENGTH = 4;

    private static final int DEFAULT_CODE_LENGTH = 8;

    private final GenerateOfferService service;

    @Value("${voucher.default.expired.day:30}")
    private Integer defaultExpiryDay;

    @Autowired
    public GenerateOfferController(GenerateOfferService service) {
        this.service = service;
    }

    @PostMapping(value = "/1.0/offer/generate")
    @ResponseBody
    @Override
    public Map<String, Object> process(@Valid @RequestBody GenerateOfferRequest request,
                                       HttpServletRequest servletRequest) {
        return super.processRequest(request, servletRequest);
    }

    @Override
    protected GenerateOfferResponse processImpl(GenerateOfferRequest request) {
        OfferRequest body = request.getBody();

        if (StringUtils.isBlank(body.getPrefix())) {
            String prefix = StringUtils.left(body.getName(), PREFIX_LENGTH).toUpperCase();
            body.setPrefix(prefix);
        }

        if (body.getCodeLength() == null) {
            body.setCodeLength(DEFAULT_CODE_LENGTH);
        }

        if (body.getExpiry() == null) {
            body.setExpiry(LocalDate.now().plusDays(defaultExpiryDay));
        }

        return service.generate(request);
    }

    @Override
    protected Logger getLogger() {
        return LOGGER;
    }
}
