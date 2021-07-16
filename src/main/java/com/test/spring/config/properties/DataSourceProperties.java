package com.test.spring.config.properties;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Getter
@PropertySource("classpath:application.properties")
public class DataSourceProperties {
    @Value("${source.driveClassName}")
    private String driverClassName;
    @Value("${source.url}")
    private String url;
    @Value("${source.username}")
    private String username;
    @Value("${source.password}")
    private String password;
}
