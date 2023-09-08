package com.sky;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.sky.mapper")
public class SkyServerApplication {

  public static void main(String[] args) {
    SpringApplication.run(SkyServerApplication.class, args);
  }

}
