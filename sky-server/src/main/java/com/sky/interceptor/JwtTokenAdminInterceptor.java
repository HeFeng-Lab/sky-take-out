package com.sky.interceptor;

import com.sky.constant.JwtClaimsConstant;
import com.sky.context.BaseContext;
import com.sky.properties.JwtProperties;
import com.sky.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class JwtTokenAdminInterceptor implements HandlerInterceptor {

  @Autowired
  private JwtProperties jwtProperties;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

    // 判断当前拦截到的是Controller的方法还是其他资源
    if (!(handler instanceof HandlerMethod)) {
      // 当前拦截到的不是动态方法，直接放行
      return true;
    }

    String token = request.getHeader(jwtProperties.getAdminTokenName());

    try {

      log.info("JWT校验: {}", token);

      Claims claims = JwtUtil.parseJWT(jwtProperties.getAdminSecretKey(), token);

      Long empId = Long.valueOf(claims.get(JwtClaimsConstant.EMP_ID).toString());

      BaseContext.setCurrentId(empId);

      log.info("当前员工 id: {}", empId);

      return true;
    } catch (Exception e) {
      response.setStatus(401);
      return false;
    }
  }
}
