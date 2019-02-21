package com.baizhi.cmfz.controller;

import com.baizhi.cmfz.entity.MapDTO;
import com.baizhi.cmfz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/selectByPage")
    public Map selectByPage(Integer page,Integer rows,String name){
        return userService.selectByPage(page,rows,name);
    }


    @RequestMapping("/multiDelete")
    public boolean multiDelete(Integer[] ids){
        try {
            userService.multiDelete(ids);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }

    @RequestMapping("/activate")
    public boolean activate(Integer id){
        try {
            userService.activate(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }

    @RequestMapping("/freeze")
    public boolean freeze(Integer id){
        try {
            userService.freeze(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }

    @RequestMapping("/unfreeze")
    public boolean unfreeze(Integer id){
        try {
            userService.unfreeze(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }

    @RequestMapping("/getCountBySex")
    public Map<String, Integer> getCountBySex(){
        return userService.getCountSex();

    }

    @RequestMapping("/selectByDate")
    public List<Integer> selectByDate(){
        return userService.selectByDate();

    }
    @RequestMapping("/selectProvince")
    public List<MapDTO> selectProvince() {
        return userService.selectProvince();
    }

    @RequestMapping("/getAll")
    public Map getAll(){
        long begin = System.currentTimeMillis();
        Map map = userService.getAll();
        long end = System.currentTimeMillis();
        System.out.println(end-begin);
        return map;
    }

    @RequestMapping("/getAll1")
    public Map getAll1(){
        long begin = System.currentTimeMillis();
        Map map = userService.getAll1();
        long end = System.currentTimeMillis();
        System.out.println(end-begin);
        return map;

    }

}
