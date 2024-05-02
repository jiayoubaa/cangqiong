package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.context.BaseContext;
import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.entity.Dish;
import com.sky.entity.Setmeal;
import com.sky.exception.DeletionNotAllowedException;
import com.sky.mapper.CategoryMapper;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetmealMapper;
import com.sky.result.PageResult;
import com.sky.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;
    private final DishMapper dishMapper;
    private final SetmealMapper setmealMapper;

    public CategoryServiceImpl(CategoryMapper categoryMapper, DishMapper dishMapper, SetmealMapper setmealMapper) {
        this.categoryMapper = categoryMapper;
        this.dishMapper = dishMapper;
        this.setmealMapper = setmealMapper;
    }


    @Override
    public void addCategory(CategoryDTO dto) {

        Category category = new Category();
        BeanUtils.copyProperties(dto, category);

        category.setStatus(0);
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        category.setCreateUser(BaseContext.getCurrentId());
        category.setUpdateUser(BaseContext.getCurrentId());

        categoryMapper.add(category);

    }


    @Override
    public PageResult page(CategoryPageQueryDTO dto) {

        PageHelper.startPage(dto.getPage(), dto.getPageSize());
        Page<Category> page = categoryMapper.findByPage(dto);
        long total = page.getTotal();
        List<Category> categoryList = page.getResult();

        return new PageResult(total, categoryList);
    }


    /**
     * 根据id删除分类
     * @param id
     */
    @Override
    public void deleteById(Long id) {

        Category category = categoryMapper.findById(id);
        if (category.getType() == 1) {
            List<Dish> dishes = dishMapper.list(id);
            if (dishes.isEmpty()) {
                categoryMapper.deleteById(id);
            } else {
                throw new DeletionNotAllowedException(MessageConstant.CATEGORY_BE_RELATED_BY_DISH);
            }
        } else {
            List<Setmeal> setmeals = setmealMapper.list(id);
            if (setmeals.isEmpty()) {
                categoryMapper.deleteById(id);
            } else {
                throw new DeletionNotAllowedException(MessageConstant.CATEGORY_BE_RELATED_BY_SETMEAL);
            }
        }
    }


    /**
     * 修改分类
     * @param dto
     */
    @Override
    public void updateCategory(CategoryDTO dto) {

        Category category = new Category();
        BeanUtils.copyProperties(dto, category);
        category.setUpdateTime(LocalDateTime.now());
        category.setUpdateUser(BaseContext.getCurrentId());

        categoryMapper.updateById(category);

    }


    /**
     * 修改状态
     * @param status
     * @param id
     */
    @Override
    public void updateStatus(Integer status, Long id) {

        Category category = Category.builder()
                .id(id)
                .status(status)
                .updateTime(LocalDateTime.now())
                .updateUser(BaseContext.getCurrentId())
                .build();

        categoryMapper.updateById(category);

    }


    /**
     * 根据类型查询分类
     * @param type
     * @return
     */
    @Override
    public List<Category> list(Integer type) {

        return categoryMapper.list(type);

    }


}
