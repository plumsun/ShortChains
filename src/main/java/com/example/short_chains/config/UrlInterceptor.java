package com.example.short_chains.config;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.short_chains.pojo.ChainInfo;
import com.example.short_chains.pojo.StatusEnum;
import com.example.short_chains.service.SortChainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;


/**
 * springmvc 拦截器
 *
 * @author plumsun
 */
@Slf4j
@Component
public class UrlInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("DispatcherServlet执行链,接口执行之前,当前调用链线程名:{}", Thread.currentThread().getName());
        StringBuffer url = request.getRequestURL();

        ServletContext servletContext = request.getServletContext();
        ApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        SortChainService myService = context.getBean(SortChainService.class);

        LambdaQueryWrapper<ChainInfo> wrapper = Wrappers.lambdaQuery(ChainInfo.class)
                .eq(ChainInfo::getSortChainUrl, url.toString())
                .eq(ChainInfo::getStatus, StatusEnum.EFFECTIVE);
        Optional<ChainInfo> optional = Optional.ofNullable(myService.getOne(wrapper));
        if (optional.isPresent()) {
            String chainUrl = optional.get().getLongChainUrl();
            log.info("{}:对应的长链:{}", url, chainUrl);
            // 重定向到某个页面
            response.sendRedirect(chainUrl);
            return false;
        }
        return true;
    }
}
