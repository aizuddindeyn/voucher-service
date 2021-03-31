/*
 * Owned by aizuddindeyn
 * Visit https://github.com/aizuddindeyn
 */
package com.aizuddindeyn.voucher.controller;

import com.aizuddindeyn.voucher.enums.ResultCode;
import com.aizuddindeyn.voucher.model.common.BaseRequest;
import com.aizuddindeyn.voucher.model.common.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class StatusController extends AbstractController<BaseRequest, BaseResponse> {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatusController.class);

    @PostMapping(value = "/1.0/status")
    @ResponseBody
    @Override
    public Map<String, Object> process(@Valid @RequestBody BaseRequest request,
                                       HttpServletRequest servletRequest) {
        return super.processRequest(request, servletRequest);
    }

    @Override
    protected BaseResponse processImpl(BaseRequest request) {
        BaseResponse response = new BaseResponse();
        response.getBody().setStatusCode(ResultCode.SUCCESS.getCode());
        response.getBody().setMessage(ResultCode.SUCCESS.getMessage());

        return response;
    }

    @Override
    protected Logger getLogger() {
        return LOGGER;
    }
}
