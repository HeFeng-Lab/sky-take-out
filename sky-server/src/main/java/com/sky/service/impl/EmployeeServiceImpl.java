package com.sky.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.sky.result.PageResult;
import com.github.pagehelper.PageHelper;
import com.sky.conatant.PasswordConstant;
import com.sky.conatant.StatusConstant;
import com.sky.context.BaseContext;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.mapper.EmployeeMapper;
import com.sky.result.Result;
import com.sky.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;


@Slf4j
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

  @Autowired
  private EmployeeMapper employeeMapper;

  @Override
  public void save(EmployeeDTO employeeDTO) {
    Employee employee = new Employee();    //属性拷贝
    BeanUtils.copyProperties(employeeDTO, employee);

    //账号状态默认为1，正常状态
    employee.setStatus(StatusConstant.ENABLE);   //默认密码为123456
    employee.setPassword(DigestUtils.md5DigestAsHex(PasswordConstant.DEFAULT_PASSWORD.getBytes()));    //创建人、创建时间、修改人、修改时间
    employee.setCreateTime(LocalDateTime.now());
    employee.setUpdateTime(LocalDateTime.now());
    employee.setCreateUser(BaseContext.getCurrentId());
    employee.setUpdateUser(BaseContext.getCurrentId());
    employeeMapper.insert(employee);
  }

  @Override
  public PageResult page(EmployeePageQueryDTO employeePageQueryDTO) {
    PageHelper.startPage(employeePageQueryDTO.getPage(), employeePageQueryDTO.getPageSize());

    Page<Employee> page = employeeMapper.pageQuery(employeePageQueryDTO);

    log.info("page: {}", page);

    return new PageResult(page.getTotal(),page.getResult());
  }
}
