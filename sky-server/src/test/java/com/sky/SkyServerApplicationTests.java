package com.sky;

import com.sky.mapper.EmployeeMapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = SkyServerApplication.class)
class SkyServerApplicationTests {

  @Test
  void contextLoads() {
  }

  @Resource()
  private EmployeeMapper employeeMapper;
  @Test
  public void selectUser(){
    employeeMapper.selectList(null).stream().forEach(System.out::println);
  }


}
