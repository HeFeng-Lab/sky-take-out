package com.sky.controller.admin;


import com.sky.result.PageResult;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sky.constant.JwtClaimsConstant;
import com.sky.context.BaseContext;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
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
import org.springframework.web.bind.annotation.*;

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


  @Operation(summary = "用户登录", description = "根据账号密码进行登录")
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

  @Operation(summary = "退出登录", description = "")
  @PostMapping("/logout")
  public Result<String> logout() {
    BaseContext.removeCurrentId();
    return Result.success();
  }

  @PostMapping
  @Operation(summary = "新增员工", description = "")
  public Result<String> save(HttpServletRequest request, @RequestBody EmployeeDTO employeeDTO) {
    log.info("新增员工: {}", employeeDTO);

    employeeService.save(employeeDTO);

    return Result.success("新增员工成功");
  }


  @GetMapping("page")
  @Operation(summary = "员工列表分页查询", description = "")
  public Result<PageResult> queryEmployee(EmployeePageQueryDTO employeePageQueryDTO) {
    log.info("员工列表分页查询: {}", employeePageQueryDTO);
    PageResult pageResult = employeeService.page(employeePageQueryDTO);
    return Result.success(pageResult);
  }

  /**
   * 启用禁用员工账号
   * @param status
   * @param id
   * @return
   */
  @PostMapping("/status/{status}")
  @Operation(summary = "启用/禁用员工账号")
  public Result<String> startOrStop(@PathVariable Integer status, Long id) {
    log.info("启用或禁用员员工账户：{},{}", status, id);
    employeeService.startOrStop(status, id);
    return Result.success();
  }


  /**
   * 根据员工 id 查询详情
   * @param id
   * @return
   */
  @GetMapping("/{id}")
  @Operation(summary = "根据员工 id 查询详情")
  public Result<Employee> getById(@PathVariable Long id) {
    Employee employee = employeeService.getById(id);

    return Result.success(employee);
  }

  /**
   * 更新员工信息
   * @param employeeDTO
   * @return
   */
  @PutMapping()
  @Operation(summary = "更新员工信息")
  public Result<String> updateEmploy(@RequestBody EmployeeDTO employeeDTO) {
    try {
      employeeService.update(employeeDTO);
      return Result.success("success");
    } catch (RuntimeException runtimeException) {
      return Result.error("error");
    }
  }
}
