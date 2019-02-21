package com.baizhi.cmfz.controller;

import com.baizhi.cmfz.entity.Lesson;
import com.baizhi.cmfz.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/lesson")
public class LessonController {

    @Autowired
    private LessonService lessonService;

    @RequestMapping("/addLesson")
    public boolean addLesson(Lesson lesson){
        try {
            lessonService.addLesson(lesson);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping("/multiDelete")
    public boolean multiDelete(Integer[] ids){
        try {

            lessonService.multiDelete(ids);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping("/selectByPage")
    public Map selectByPage(Integer page,Integer rows,String name){
        return lessonService.selectByPage(page,rows,name);
    }

}
