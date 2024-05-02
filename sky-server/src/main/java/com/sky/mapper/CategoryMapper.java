package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryMapper {

    /**
     * 新增分类
     * @param category
     */
    @Insert("INSERT INTO category " +
            "( type, name, sort, status, create_time, update_time, create_user, update_user)" +
            "VALUES ( #{type}, #{name}, #{sort}, #{status}, #{createTime}, #{updateTime}, #{createUser}, #{updateUser} )")
    void add(Category category);


    /**
     * 分页查询
     * @param dto
     * @return
     */
    Page<Category> findByPage(CategoryPageQueryDTO dto);


    /**
     * 根据id删除
     * @param id
     */
    void deleteById(Long id);

    /**
     * 根据id修改
     * @param category
     */
    void updateById(Category category);


    /**
     * 根据类型查询分类
     * @param type
     * @return
     */
    @Select("SELECT * FROM category WHERE type = #{type}")
    List<Category> list(Integer type);

    /**
     * 根据id查询分类
     * @param id
     * @return
     */
    @Select("SELECT * FROM category WHERE id = #{id}")
    Category findById(Long id);
}
