package com.sky.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {

  @Insert("insert into employee"+"(name, username, password, phone, sex, id_number, status, create_time, update_time, create_user, update_user)" +"VALUES" +
          "(#{name}, #{username}, #{password}, #{phone}, #{sex}, #{idNumber}, #{status}, #{createTime},#{updateTime},#{createUser}, #{updateUser})")
  @AutoFill(value = OperationType.INSERT)
  int insert(Employee employee);

  Page<Employee> pageQuery(EmployeePageQueryDTO employeePageQueryDTO);
}

