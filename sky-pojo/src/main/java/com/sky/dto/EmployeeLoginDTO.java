package com.sky.dto;

import lombok.Data;

import java.io.Serializable;


/**
 *  DTO: 数据传输对象，通常用于程序中各层之间传递数据
 */
@Data
public class EmployeeLoginDTO implements Serializable {
  private String username;
  private String password;
}
