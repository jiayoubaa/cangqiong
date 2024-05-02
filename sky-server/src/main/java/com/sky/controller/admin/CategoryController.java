package com.sky.controller.admin;


import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"分类相关接口"})
@RestController
@RequestMapping("/admin/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @ApiOperation(value = "新增分类")
    @PostMapping
    public Result addCategory(@RequestBody CategoryDTO dto) {

        categoryService.addCategory(dto);
        return Result.success();

    }

    @ApiOperation(value = "分类分页查询")
    @GetMapping("/page")
    public Result<PageResult> page(CategoryPageQueryDTO dto) {

        PageResult result = categoryService.page(dto);
        return Result.success(result);

    }

    @ApiOperation(value = "根据id删除分类")
    @DeleteMapping
    public Result deleteCategory(Long id) {

        categoryService.deleteById(id);
        return Result.success();

    }


    @ApiOperation(value = "修改分类")
    @PutMapping
    public Result updateCategory(@RequestBody CategoryDTO dto) {

        categoryService.updateCategory(dto);
        return Result.success();

    }


    @ApiOperation(value = "启用禁用分类")
    @PostMapping("/status/{status}")
    public Result updateStatus(@PathVariable Integer status, Long id) {

        categoryService.updateStatus(status, id);
        return Result.success();

    }


    @ApiOperation(value = "根据类型查询分类")
    @GetMapping("/list")
    public Result<List<Category>> list(Integer type) {

        List<Category> data = categoryService.list(type);
        return Result.success(data);

    }

}
