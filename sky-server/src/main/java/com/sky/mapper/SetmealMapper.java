package com.sky.mapper;

import com.sky.entity.Setmeal;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SetmealMapper {

    /**
     * 根据分类id查询套餐
     * @param categoryId
     * @return
     */
    @Select("SELECT * FROM setmeal WHERE category_id = #{categoryId}")
    List<Setmeal> list(Long categoryId);
}
