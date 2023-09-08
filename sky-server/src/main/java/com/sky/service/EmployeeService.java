package com.sky.service;

import com.sky.result.PageResult;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;

public interface EmployeeService extends IService<Employee> {
  void save(EmployeeDTO employeeDTO);

  PageResult page(EmployeePageQueryDTO employeePageQueryDTO);

  void startOrStop(Integer status, Long id);
}
