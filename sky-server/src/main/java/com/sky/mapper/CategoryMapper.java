package com.sky.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

  /**
   * 分页查询
   * @param categoryPageQueryDTO
   * @return
   */
  Page<Category> pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);


  /**
   * 新增分类
   * @param category
   */
  @Insert("insert into category (type, name, sort, status, create_time, update_time, create_user, update_user) VALUES " +
    "(#{type}, #{name}, #{sort}, #{status}, #{createTime}, #{updateTime}, #{createUser}, #{updateUser})"
  )
  @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
  @AutoFill(value = OperationType.INSERT)
  int add(Category category);
}
