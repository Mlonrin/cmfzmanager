package com.baizhi.cmfz.service.impl;

import com.baizhi.cmfz.dao.LessonMapper;
import com.baizhi.cmfz.entity.Lesson;
import com.baizhi.cmfz.service.LessonService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LessonServiceImpl implements LessonService {

    @Autowired
    private LessonMapper lessonMapper;
    @Override
    public void addLesson(Lesson lesson) {
        lesson.setLessonStatus(0);
        lessonMapper.insertSelective(lesson);
    }

    @Override
    public void multiDelete(Integer[] ids) {
        Lesson lesson = new Lesson();
        for (Integer id : ids) {
            System.out.println(ids);
            lesson.setLessonId(id);
            lesson.setLessonStatus(2);
            lessonMapper.updateByPrimaryKeySelective(lesson);
        }
    }

    @Override
    public Map selectByPage(int page, int rows, String name) {
        if(name == null){
            name = "";
        }
        Map map = new HashMap();

        IPage<Lesson> iPage = new Page<>(page,rows);

        QueryWrapper<Lesson> wrapper = new QueryWrapper<>();

        wrapper.like("lesson_name",name).eq("lesson_status",0);

        List<Lesson> guruList = lessonMapper.selectPage(iPage, wrapper).getRecords();

        int count = lessonMapper.selectCount(wrapper);

        map.put("rows",guruList);
        map.put("total",count);

        return map;
    }
}
