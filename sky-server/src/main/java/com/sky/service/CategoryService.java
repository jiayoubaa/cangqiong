package com.sky.service;


import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;

import java.util.List;

public interface CategoryService {

    /**
     * 新增分类
     * @param dto
     */
    void addCategory(CategoryDTO dto);

    /**
     * 分页查询
     * @param dto
     * @return
     */
    PageResult page(CategoryPageQueryDTO dto);

    /**
     * 根据id删除分类
     * @param id
     */
    void deleteById(Long id);

    /**
     * 修改分类
     * @param dto
     */
    void updateCategory(CategoryDTO dto);


    /**
     * 修改状态
     * @param status
     * @param id
     */
    void updateStatus(Integer status, Long id);


    /**
     * 根据类型查询分类
     * @param type
     * @return
     */
    List<Category> list(Integer type);
}
