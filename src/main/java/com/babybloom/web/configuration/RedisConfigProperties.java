package com.babybloom.web.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="redis")
@Data
public class RedisConfigProperties {
    private String host;
    private Integer port;
    private String password;
    private Integer timeout;
    private Integer maxActive;
    private Integer maxWait;
    private Integer maxIdle;
}
