package com.sky;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
@MapperScan("com.sky.mapper")
public class SkyServerApplication {

  public static void main(String[] args) {
    SpringApplication.run(SkyServerApplication.class, args);
  }

}
