package com.sky.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.conatant.StatusConstant;
import com.sky.context.BaseContext;
import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.mapper.CategoryMapper;
import com.sky.result.PageResult;
import com.sky.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

  @Autowired
  CategoryMapper categoryMapper;

  @Override
  public PageResult page(CategoryPageQueryDTO categoryPageQueryDTO) {
    int currentPage = categoryPageQueryDTO.getPage();
    int pageSize = categoryPageQueryDTO.getPageSize();

    PageHelper.startPage(currentPage, pageSize);

    Page<Category> page = categoryMapper.pageQuery(categoryPageQueryDTO);

    log.info("page: {}", page);


    return new PageResult(currentPage, pageSize, page.getTotal(), page.getResult());
  }

  @Override
  public Long add(CategoryDTO categoryDTO) {
    Category category = new Category();

    BeanUtils.copyProperties(categoryDTO, category);

    // 分类状态默认为禁用状态0
    category.setStatus(StatusConstant.DISABLE);

    category.setCreateTime(LocalDateTime.now());
    category.setUpdateTime(LocalDateTime.now());
    category.setCreateUser(BaseContext.getCurrentId());
    category.setUpdateUser(BaseContext.getCurrentId());

    Integer res = categoryMapper.add(category);
    log.info("res: {}", res);
    return category.getId();
  }
}
