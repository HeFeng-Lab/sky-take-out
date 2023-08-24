package com.sky.controller.admin;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.entity.Employee;
import com.sky.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/admin/employee")
public class EmployeeController {

  @Autowired
  EmployeeService employeeService;

  @PostMapping("/login")
  public void login(@RequestBody EmployeeLoginDTO employeeLoginDTO){
    log.info("员工登录： {}", employeeLoginDTO);

    LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();

    queryWrapper.eq(Employee::getUsername, employeeLoginDTO.getUsername());

    Employee employee = employeeService.getOne(queryWrapper);

    log.info(String.valueOf(employee));
  }
}
