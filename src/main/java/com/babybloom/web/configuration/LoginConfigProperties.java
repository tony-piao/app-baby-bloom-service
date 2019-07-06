package com.babybloom.web.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="login")
@Data
public class LoginConfigProperties {
    private int expireTime;
}
