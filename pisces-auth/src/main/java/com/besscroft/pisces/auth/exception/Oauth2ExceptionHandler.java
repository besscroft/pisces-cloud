package com.besscroft.pisces.auth.exception;

import com.besscroft.pisces.framework.common.result.AjaxResult;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description Oauth2 全局异常处理
 * @Author Bess Croft
 * @Date 2022/2/4 16:30
 */
@ControllerAdvice
public class Oauth2ExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = OAuth2Exception.class)
    public AjaxResult handleOauth2(OAuth2Exception e) {
        return AjaxResult.error(e.getMessage());
    }

}
