package com.sky.controller.admin;

import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/category")
@Slf4j
@Tag(name = "分类模块")
public class CategoryController {

  @Autowired
  CategoryService categoryService;

  @GetMapping("/page")
  @Operation(summary = "分类列表分页查询")
  public Result<PageResult> page(CategoryPageQueryDTO categoryPageQueryDTO) {
    log.info("分类列表分页查询: {}", categoryPageQueryDTO);
    PageResult pageResult = categoryService.page(categoryPageQueryDTO);

    return Result.success(pageResult);
  }
}
