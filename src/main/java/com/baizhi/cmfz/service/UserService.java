package com.baizhi.cmfz.service;


import com.baizhi.cmfz.entity.MapDTO;
import com.baizhi.cmfz.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    void activate(Integer id);

    Map selectByPage(Integer page, Integer rows, String name);

    void multiDelete(Integer[] ids);

    void freeze(Integer id);

    void unfreeze(Integer id);

    void multiInsert(List<User> userList);

    List<Map<String,Object>> getCountBySex();

    Map<String,Integer> getCountSex();

    List<Integer> selectByDate();

    List<MapDTO> selectProvince();

    Map getAll();

    Map getAll1();
}
