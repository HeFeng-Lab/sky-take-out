package com.sky.config;

import com.sky.interceptor.JwtTokenAdminInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Slf4j
public class WebMvcConfiguration implements WebMvcConfigurer {
  @Autowired
  private JwtTokenAdminInterceptor jwtTokenAdminInterceptor;

  /**
   * 注册自定义拦截器
   *
   * @param registry
   */
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    log.info("开始注册自定义拦截器...");
    // 后台
    registry.addInterceptor(jwtTokenAdminInterceptor)
            .addPathPatterns("/admin/**")
            .excludePathPatterns("/admin/employee/login");
  }
}
