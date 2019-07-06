package com.babybloom.web.manager;

import com.babybloom.web.configuration.LoginConfigProperties;
import com.babybloom.web.constant.CommonErrorId;
import com.babybloom.web.constant.RedisConstant;
import com.babybloom.web.exception.ApplicationException;
import com.babybloom.web.service.RedisService;
import com.babybloom.web.utility.StringUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RedisManager implements RedisConstant {

    @Autowired
    private RedisService redisService;
    @Autowired
    private LoginConfigProperties LoginConfig;

    /**
     * 保存登录的uid和token
     *
     * @param uid
     */
    public String saveLoginToken(Long uid) {
        if (uid == null) {
            throw new ApplicationException(CommonErrorId.ERROR_PARAMETER, "uid");
        }
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        String key = LOGIN_PRE + token;
        redisService.setex(key, LoginConfig.getExpireTime(), String.valueOf(uid));
        return token;
    }

    /**
     * 根据token获取uid
     *
     * @param token
     * @return
     */
    public Long getUidByToken(String token) {
        String key = LOGIN_PRE + token;
        String uidStr = redisService.get(key);
        if(StringUtility.isNullOrEmpty(uidStr)){
            return null;
        }
        return Long.valueOf(uidStr);
    }
}
