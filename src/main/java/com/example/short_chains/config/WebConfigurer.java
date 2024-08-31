package com.example.short_chains.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * springmvc自定义配置
 *
 * @author plumsun
 */
@RequiredArgsConstructor
@Configuration
public class WebConfigurer implements WebMvcConfigurer {

    private final UrlInterceptor urlInterceptor;

    /**
     * 注册自定义拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(urlInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/chains/generation/**");
    }
}
