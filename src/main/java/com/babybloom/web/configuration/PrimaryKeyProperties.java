package com.babybloom.web.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="primarykey")
@Data
public class PrimaryKeyProperties {
    private Long datacenter;
    private Long worker;
}
