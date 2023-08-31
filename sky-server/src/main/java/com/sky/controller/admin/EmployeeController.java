package com.sky.controller.admin;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sky.constant.JwtClaimsConstant;
import com.sky.context.BaseContext;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.entity.Employee;
import com.sky.properties.JwtProperties;
import com.sky.result.Result;
import com.sky.service.EmployeeService;
import com.sky.utils.JwtUtil;
import com.sky.vo.EmployeeLoginVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/admin/employee")
@Tag(name = "用户模块")
public class EmployeeController {

  @Autowired
  private EmployeeService employeeService;

  @Autowired
  private JwtProperties jwtProperties;


  @Operation(summary = "用户登录",description = "根据账号密码进行登录")
  @PostMapping("/login")
  public Result<EmployeeLoginVO> login(HttpServletRequest request, @RequestBody EmployeeLoginDTO employeeLoginDTO) {
    log.info("员工登录： {}", employeeLoginDTO);

    LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.eq(Employee::getUsername, employeeLoginDTO.getUsername());
    // queryWrapper.eq(Employee::getPassword, employeeLoginDTO.getPassword());

    Employee employee = employeeService.getOne(queryWrapper);

    log.info(String.valueOf(employee));

    // 未查到账户
    if (employee == null) {
      return Result.error("登录失败");
    }

    String password = DigestUtils.md5DigestAsHex((employeeLoginDTO.getPassword()).getBytes());

    log.info(password);

    // 密码不匹配
    if (!password.equals(employee.getPassword())) {
      return Result.error("登录失败");
    }

    // 账户已禁用
    if (employee.getStatus() == 0) {
      return Result.error("账户已禁用");
    }


    request.getSession().setAttribute("employeeId", employee.getId());

    Map<String, Object> claims = new HashMap<>();
    claims.put(JwtClaimsConstant.EMP_ID, employee.getId());

    String token = JwtUtil.createJWT(
            jwtProperties.getAdminSecretKey(),
            jwtProperties.getUserTtl(),
            claims
    );


    EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
            .id(employee.getId())
            .userName(employee.getName())
            .name(employee.getName())
            .token(token)
            .build();

    return Result.success(employeeLoginVO);
  }

  @Operation(summary = "退出登录",description = "")
  @PostMapping("/logout")
  public Result<String> logout() {
    BaseContext.removeCurrentId();
    return Result.success();
  }

  @PostMapping
  @Operation(summary = "新增员工", description = "")
  public Result<String> save(HttpServletRequest request, @RequestBody Employee employee) {
    log.info("新增员工: {}", employee);

    // 初始化密码
    employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));

    employee.setCreateTime(LocalDateTime.now());
    employee.setUpdateTime(LocalDateTime.now());

    // 获取当前登录用户信息
    Long employId = (Long) request.getSession().getAttribute("employeeId");
    employee.setCreateUser(employId);
    employee.setUpdateUser(employId);

    employeeService.save(employee);

    return Result.success("新增员工成功");
  }
}
