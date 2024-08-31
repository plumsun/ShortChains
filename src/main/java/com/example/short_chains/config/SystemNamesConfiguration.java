package com.example.short_chains.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author plumsun Created on 2023/12/3
 */
@ConfigurationProperties(value = "application.yml")
@Configuration
@Data
public class SystemNamesConfiguration {

    private List<String> systemNames;
}
