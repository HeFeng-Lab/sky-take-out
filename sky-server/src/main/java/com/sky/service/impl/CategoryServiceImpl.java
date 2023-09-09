package com.sky.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.mapper.CategoryMapper;
import com.sky.result.PageResult;
import com.sky.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
