package com.sky.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CategoryPageQueryDTO implements Serializable {

  // 分类名称
  private String name;

  // 分类类型 1 菜品分类 2 套餐分类
  private Integer type;

  //页码
  private int page;

  //每页显示记录数
  private int pageSize;

}
