package com.baizhi.cmfz.service.impl;

import com.baizhi.cmfz.dao.UserDao;
import com.baizhi.cmfz.entity.MapDTO;
import com.baizhi.cmfz.entity.User;
import com.baizhi.cmfz.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.gson.Gson;
import io.goeasy.GoEasy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;

@Service
public class UserServiceImpl implements UserService {

   /* @Autowired
    private final ThreadPoolExecutor executor;*/


    @Autowired
    private UserDao userDao;
    @Override
    public void multiDelete(Integer[] ids) {
        for (Integer id : ids) {
            User user = userDao.selectById(id);
            user.setUserStatus(3);
            userDao.updateById(user);
        }
    }

    @Override
    public Map selectByPage(Integer page, Integer rows, String name) {

        if(name==null){
            name = "";
        }

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like("autograph",name).notIn("user_status",3);

        IPage<User> ipage = new Page<>(page,rows);

        List<User> userList = userDao.selectPage(ipage, wrapper).getRecords();

        int count = userDao.selectCount(wrapper);

        Map map = new HashMap();
        map.put("rows",userList);
        map.put("total",count);

        return map;
    }

    @Override
    public void activate(Integer id) {
        User user = userDao.selectById(id);
        user.setUserStatus(1);
        userDao.updateById(user);
    }

    @Override
    public void freeze(Integer id) {
        User user = userDao.selectById(id);
        user.setUserStatus(2);
        userDao.updateById(user);
    }

    @Override
    public void unfreeze(Integer id) {
        User user = userDao.selectById(id);
        user.setUserStatus(1);
        userDao.updateById(user);
    }

    @Override
    public void multiInsert(List<User> userList) {
//        添加用户触发发布及时消息
        userDao.multiInsert(userList);

//        获取最新数据
        Map map = getAll1();
//        对象转化为json
        Gson gson = new Gson();

        String ss = gson.toJson(map);

//        创建goeasy对象
        GoEasy goEasy = new GoEasy( "http://rest-hangzhou.goeasy.io","BC-c5daff84bbe44baabf72dab5bb69a9e3");

        /***
         *  发布通道名称  testChannel
         *  发布内容       map的json形式
         */
//        发布,map以字符串发布回去
        goEasy.publish("testChannel", ss);
    }

    @Override
    public List<Map<String,Object>> getCountBySex() {

        return userDao.getCountBySex();
    }

    @Override
    public Map<String,Integer> getCountSex() {

        List<Integer> countSex = userDao.getCountSex();
        Map<String,Integer> map = new HashMap<>();
        map.put("women",countSex.get(0));
        map.put("men",countSex.get(1));
        return map;
    }

    @Override
    public List<Integer> selectByDate() {

        return userDao.selectByDate();

    }

    @Override
    public List<MapDTO> selectProvince() {

        return userDao.selectProvince();
    }

    @Override
    public Map getAll() {

        Map map = new HashMap();

        List<Integer> countSex = userDao.getCountSex();

        Map<String,Integer> map1 = new HashMap<>();

        map1.put("women",countSex.get(0));
        map1.put("men",countSex.get(1));

        map.put("sex",map1);

        map.put("count",userDao.selectByDate());

        map.put("province",userDao.selectProvince());

        return map;
    }

    @Override
    public Map getAll1() {
//        1.创建一个任务计数器的类
        CountDownLatch countDownLatch = new CountDownLatch(3);

        Map map = new HashMap();
/*//        性别数量查询
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                List<Integer> countSex = userDao.getCountSex();
                Map<String,Integer> map1 = new HashMap<>();
                map1.put("women",countSex.get(0));
                map1.put("men",countSex.get(1));
                map.put("sex",map1);
                countDownLatch.countDown();
            }
        });

//        近三周注册量查询
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                map.put("count",userDao.selectByDate());
                countDownLatch.countDown();
            }
        });

//        用户数量省份分布
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                map.put("province",userDao.selectProvince1());
                countDownLatch.countDown();
            }
        });

        executor.submit(thread1);
        executor.submit(thread2);
        executor.submit(thread3);*/

       /* try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        return map;
    }


}
