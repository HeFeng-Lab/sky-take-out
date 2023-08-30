package com.sky.config;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@MapperScan("com.sky.mapper")
public class MybatisPlusConfig {

  @Bean
  public MybatisPlusInterceptor mybatisPlusInterceptor() {
    MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
    mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor());
    return mybatisPlusInterceptor;
  }

  @Primary
  @Bean
  public MybatisSqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) throws IOException, IOException {
    MybatisSqlSessionFactoryBean sessionFactory = new MybatisSqlSessionFactoryBean();
    sessionFactory.setDataSource(dataSource);

    // 设置 MyBatis Plus的配置
    MybatisConfiguration mybatisConfiguration = new MybatisConfiguration();
    mybatisConfiguration.setMapUnderscoreToCamelCase(true); // 开启驼峰命名转换
    sessionFactory.setConfiguration(mybatisConfiguration);

    return sessionFactory;
  }

}
