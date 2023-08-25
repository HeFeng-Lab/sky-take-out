package com.sky.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.entity.Employee;
import com.sky.result.Result;
import com.sky.service.EmployeeService;
import com.sky.vo.EmployeeLoginVO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
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
  public Result<EmployeeLoginVO> login(HttpServletRequest request, @RequestBody EmployeeLoginDTO employeeLoginDTO) {
    log.info("员工登录： {}", employeeLoginDTO);

    LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.eq(Employee::getUsername, employeeLoginDTO.getUsername());
    // queryWrapper.eq(Employee::getPassword, employeeLoginDTO.getPassword());

    Employee employee = employeeService.getOne(queryWrapper);

    // 未查到账户
    if (employee == null) {
      return Result.error("登录失败");
    }

    // 密码不匹配
    if(!employee.getPassword().equals(employeeLoginDTO.getPassword())) {
      return Result.error("登录失败");
    }

    // 账户已禁用
    if(employee.getStatus() == 0) {
      return Result.error("账户已禁用");
    }

    request.getSession().setAttribute("employee", employee.getId());

    EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
            .id(employee.getId())
            .userName(employee.getName())
            .name(employee.getName())
            .build();

    return Result.success(employeeLoginVO);
  }
}