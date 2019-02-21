package com.baizhi.cmfz.dao;

import com.baizhi.cmfz.entity.MapDTO;
import com.baizhi.cmfz.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserDao extends BaseMapper<User> {

    void multiInsert(@Param("userList") List<User> userList);

    void insertOne(@Param("user") User user);

    List<Map<String,Object>> getCountBySex();

    List<Integer> getCountSex();

    List<Integer> selectByDate();

    List<MapDTO> selectProvince();

    List<Map> selectProvince1();

    void updateUserInfo(User user);

    void updatePassword(@Param("userId") Integer userId,@Param("password") String password);
}
