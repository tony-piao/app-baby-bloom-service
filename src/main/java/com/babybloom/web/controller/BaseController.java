package com.babybloom.web.controller;

import com.babybloom.web.constant.BusinessErrorId;
import com.babybloom.web.constant.CommonErrorId;
import com.babybloom.web.exception.ApplicationException;
import com.babybloom.web.manager.RedisManager;
import com.babybloom.web.model.entity.GeneralJsonResult;
import com.babybloom.web.utility.StringUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * controller父类
 *
 * @author zz
 */
public abstract class BaseController {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private RedisManager redisManager;

    private final String AUTH_KEY = "token";

    /**
     * 基于ExceptionHandler的统一controller异常处理
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler
    @ResponseBody
    public final GeneralJsonResult exception(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        if (ex instanceof ApplicationException) {
            return GeneralJsonResult.error(((ApplicationException) ex).getErrorId());
        } else {
            return GeneralJsonResult.error(CommonErrorId.ERROR_UNKNOWN);
        }
    }

    /**
     * 做token认证并返回用户信息,如果认证失败,返回error
     *
     * @return
     */
    protected Long authAndGetUid() throws ApplicationException {
        String token = getToken();
        if (StringUtility.isNullOrEmpty(token)) {
            throw new ApplicationException(BusinessErrorId.ERROR_LOGIN_TIMEOUT);
        }
        Long uid = redisManager.getUidByToken(token);
        if (uid == null) {
            throw new ApplicationException(BusinessErrorId.ERROR_LOGIN_TIMEOUT);
        }
        return uid;
    }

    /**
     * 根据token获取uid
     *
     * @return
     * @throws ApplicationException
     */
    protected Long getUid() throws ApplicationException {
        String token = getToken();
        if (!StringUtility.isNullOrEmpty(token)) {
            return redisManager.getUidByToken(token);
        }
        return null;
    }

    /**
     * 获取token
     *
     * @return
     */
    private String getToken() {
        return request.getHeader(AUTH_KEY);
    }
}
