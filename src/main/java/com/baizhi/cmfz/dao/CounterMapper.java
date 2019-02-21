package com.baizhi.cmfz.dao;

import com.baizhi.cmfz.entity.Counter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CounterMapper {
    int deleteByPrimaryKey(Integer counterId);

    int insert(Counter record);

    int insertSelective(Counter record);

    Counter selectByPrimaryKey(Integer counterId);

    int updateByPrimaryKeySelective(Counter record);

    int updateByPrimaryKey(Counter record);

    List<Counter> selectAll(@Param("userId") Integer userId,@Param("lessonId") Integer lessonId);
}