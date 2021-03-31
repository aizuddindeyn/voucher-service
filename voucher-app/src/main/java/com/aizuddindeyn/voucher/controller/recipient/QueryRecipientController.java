/*
 * Owned by aizuddindeyn
 * Visit https://github.com/aizuddindeyn
 */
package com.aizuddindeyn.voucher.controller.recipient;

import com.aizuddindeyn.voucher.controller.AbstractController;
import com.aizuddindeyn.voucher.model.recipient.query.request.QueryRecipientRequest;
import com.aizuddindeyn.voucher.model.recipient.query.response.QueryRecipientResponse;
import com.aizuddindeyn.voucher.service.recipient.QueryRecipientService;
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
public class QueryRecipientController extends AbstractController<QueryRecipientRequest, QueryRecipientResponse> {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueryRecipientController.class);

    private final QueryRecipientService service;

    @Autowired
    public QueryRecipientController(QueryRecipientService service) {
        this.service = service;
    }

    @PostMapping(value = "/1.0/recipient/query")
    @ResponseBody
    @Override
    public Map<String, Object> process(@Valid @RequestBody QueryRecipientRequest request,
                                       HttpServletRequest servletRequest) {
        return super.processRequest(request, servletRequest);
    }

    @Override
    protected QueryRecipientResponse processImpl(QueryRecipientRequest request) {
        return service.query(request);
    }

    @Override
    protected Logger getLogger() {
        return LOGGER;
    }
}
