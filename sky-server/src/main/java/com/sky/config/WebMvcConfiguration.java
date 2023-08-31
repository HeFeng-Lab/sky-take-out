package com.sky.config;

import com.sky.interceptor.JwtTokenAdminInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
@Slf4j
public class WebMvcConfiguration extends WebMvcConfigurationSupport {
  @Autowired
  private JwtTokenAdminInterceptor jwtTokenAdminInterceptor;

  /**
   * 注册自定义拦截器
   *
   * @param registry
   */
  protected void addInterceptors(InterceptorRegistry registry) {
    log.info("开始注册自定义拦截器...");
    // 后台
    registry.addInterceptor(jwtTokenAdminInterceptor)
            .addPathPatterns("/admin/**")
            .excludePathPatterns("/admin/employee/login");
  }
}
