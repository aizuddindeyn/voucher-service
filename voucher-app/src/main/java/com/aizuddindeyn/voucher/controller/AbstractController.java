/*
 * Owned by aizuddindeyn
 * Visit https://github.com/aizuddindeyn
 */
package com.aizuddindeyn.voucher.controller;

import com.aizuddindeyn.voucher.enums.ResultCode;
import com.aizuddindeyn.voucher.exception.VoucherGenericException;
import com.aizuddindeyn.voucher.resolver.MessageResolver;
import com.aizuddindeyn.voucher.resolver.MapperResolver;
import com.aizuddindeyn.voucher.model.common.BaseRequest;
import com.aizuddindeyn.voucher.model.common.BaseResponse;
import com.aizuddindeyn.voucher.util.LogUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;

import static com.aizuddindeyn.voucher.util.LogUtil.DEBUG_ID;

/**
 * @author aizuddindeyn
 */
public abstract class AbstractController<R extends BaseRequest, P extends BaseResponse> {

    private static final String X_FORWARDED_FOR = "x-forwarded-for";

    @Autowired
    protected MessageResolver messageResolver;

    protected Map<String, Object> processRequest(R request, HttpServletRequest servletRequest) {

        MDC.put(DEBUG_ID, request.getHeader().getRequestMsgId());

        StopWatch stopWatch = new StopWatch();

        Map<String, Object> result = null;
        BaseResponse response = new BaseResponse();
        ObjectMapper mapper = MapperResolver.getInstance();

        String requestIp =
                StringUtils.defaultString(servletRequest.getHeader(X_FORWARDED_FOR), servletRequest.getRemoteAddr());
        Locale locale = servletRequest.getLocale();

        try {
            stopWatch.start();

            //TODO: Validate signature

            String jsonRequest = mapper.writer().writeValueAsString(request);
            LogUtil.logInfo(getLogger(), "{}Request | {} - Request: {}", getFunction(), requestIp,
                    jsonRequest);

            response = processImpl(request);

            String message = resolveResponseMessage(response.getBody().getStatusCode(),
                    response.getBody().getMessage(), locale);
            response = constructResponse(request, response, response.getBody().getStatusCode(), message);

            String jsonResponse = mapper.writer().writeValueAsString(response);

            stopWatch.stop();
            LogUtil.logInfo(getLogger(), "{}Request | {}ms | {}-{}: {}", getFunction(),
                    stopWatch.getTotalTimeMillis(), response.getBody().getStatusCode(),
                    response.getBody().getMessage(), jsonResponse);

        } catch (VoucherGenericException ex) {
            stopWatch.stop();
            LogUtil.logError(getLogger(), "{}Request Error | {}ms | {}-{}: {}", getFunction(),
                    stopWatch.getTotalTimeMillis(), ex.getResultCode().getCode(), ex.getResultCode().getMessage(),
                    ex.getMessage());
            String message = resolveResponseMessage(ex.getResultCode().getCode(), ex.getResultCode().getMessage(),
                    locale);

            if (StringUtils.isNotBlank(ex.getMessage())) {
                message = message + " - " + ex.getMessage();
            }

            response = constructResponse(request, response, ex.getResultCode().getCode(), message);

        } catch (IOException ex) {
            stopWatch.stop();
            LogUtil.logError(getLogger(), "{}Request | {}ms | {}-{}: Invalid request format from {}",
                    getFunction(), stopWatch.getTotalTimeMillis(), ResultCode.INVALID_REQUEST.getCode(),
                    ResultCode.INVALID_REQUEST.getMessage(), requestIp);
            String message = resolveResponseMessage(ResultCode.INVALID_REQUEST.getCode(),
                    ResultCode.INVALID_REQUEST.getMessage(), locale);

            response = constructResponse(request, response, ResultCode.INVALID_REQUEST.getCode(), message);

        } catch (Exception ex) {
            stopWatch.stop();
            LogUtil.logError(getLogger(), "{}Request | {}ms | {}-{}: Unexpected Error: {}", getFunction(),
                    stopWatch.getTotalTimeMillis(), ResultCode.SYSTEM_ERROR.getCode(),
                    ResultCode.SYSTEM_ERROR.getMessage(), ex);
            String message = resolveResponseMessage(ResultCode.SYSTEM_ERROR.getCode(),
                    ResultCode.SYSTEM_ERROR.getMessage(), locale);

            response = constructResponse(request, response, ResultCode.SYSTEM_ERROR.getCode(), message);

        } finally {
            //TODO: Generate signature
            result = mapper.convertValue(response, new TypeReference<Map<String, Object>>() {
            });
        }

        MDC.clear();

        return result;
    }

    /**
     * API Endpoint
     *
     * @param request        API request
     * @param servletRequest Http Servlet Request
     * @return Response map
     */
    public abstract Map<String, Object> process(R request, HttpServletRequest servletRequest);

    protected abstract P processImpl(R request);

    protected abstract Logger getLogger();

    protected String getFunction() {
        return this.getClass().getSimpleName();
    }

    protected BaseResponse constructResponse(R request, BaseResponse response, String statusCode, String message) {
        response.setHeader(request.getHeader());

        response.getBody().setStatusCode(statusCode);
        response.getBody().setMessage(message);

        return response;
    }

    protected String resolveResponseMessage(String statusCode, String defaultMessage, Locale locale,
                                            String... parameters) {
        if (locale == null) {
            locale = Locale.getDefault();
        }

        return messageResolver.resolveResultCode(statusCode, defaultMessage, locale, parameters);
    }
}
