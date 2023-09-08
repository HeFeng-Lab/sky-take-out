package com.sky.config;

import com.sky.interceptor.JwtTokenAdminInterceptor;
import com.sky.json.JacksonObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

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

  @Override
  public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
    log.info("starting message transformation....");

    // 创建一个消息转换对象
    MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();

    // 设置对象转换器，将java对象转化为json
    converter.setObjectMapper(new JacksonObjectMapper());

    // 将转换器加入到mvc框架容器里面
    converters.add(0,converter);
  }
}
