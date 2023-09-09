package com.sky.controller.admin;

import com.sky.dto.CategoryDTO;
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
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/category")
@Slf4j
@Tag(name = "分类模块")
public class CategoryController {

  @Autowired
  CategoryService categoryService;

  @PostMapping
  @Operation(summary = "新增分类")
  public Result<Map> addCategory(@RequestBody CategoryDTO categoryDTO) {
    Long id = categoryService.add(categoryDTO);
    // Map map = new HashMap<>();
    // map.put("id", id);

    // 使用Java 9的工厂方法创建一个不可变Map
    Map<String, Object> map = Map.of("id", id);

    return Result.success(map);
  }

  /**
   * 分类分页查询
   *
   * @param categoryPageQueryDTO
   * @return
   */
  @GetMapping("/page")
  @Operation(summary = "分类列表分页查询")
  public Result<PageResult> page(CategoryPageQueryDTO categoryPageQueryDTO) {
    log.info("分类列表分页查询: {}", categoryPageQueryDTO);
    PageResult pageResult = categoryService.page(categoryPageQueryDTO);

    return Result.success(pageResult);
  }
}
