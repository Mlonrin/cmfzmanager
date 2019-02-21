package com.baizhi.cmfz.dao;

import com.baizhi.cmfz.entity.Lesson;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface LessonMapper extends BaseMapper<Lesson> {
    int deleteByPrimaryKey(Integer lessonId);

    int insert(Lesson record);

    int insertSelective(Lesson record);

    Lesson selectByPrimaryKey(Integer lessonId);

    int updateByPrimaryKeySelective(Lesson record);

    int updateByPrimaryKey(Lesson record);

    List<Lesson> selectAll();

    List<Lesson> selectByUserId(Integer userId);
}