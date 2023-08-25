package com.sky.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
            .authorizeRequests(authorize -> authorize.anyRequest().permitAll())
            .csrf(csrf -> csrf.ignoringRequestMatchers("*/*/login", "/*/*/logout","*/*/register"))
            .httpBasic(withDefaults());

    return http.build();
  }
}