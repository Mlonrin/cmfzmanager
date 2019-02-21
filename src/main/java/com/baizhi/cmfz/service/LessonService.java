package com.baizhi.cmfz.service;

import com.baizhi.cmfz.entity.Lesson;

import java.util.Map;

public interface LessonService {

    void addLesson(Lesson lesson);

    void multiDelete(Integer[] ids);

    Map selectByPage(int page, int rows, String name);

}
