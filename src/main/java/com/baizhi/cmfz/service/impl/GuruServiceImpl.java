package com.baizhi.cmfz.service.impl;

import com.baizhi.cmfz.dao.GuruDao;
import com.baizhi.cmfz.dao.GuruMapper;
import com.baizhi.cmfz.entity.Guru;
import com.baizhi.cmfz.service.GuruService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GuruServiceImpl implements GuruService {

    @Resource
    private GuruDao guruDao;

    @Resource
    private GuruMapper guruMapper;

    @Override
    public List<Guru> selectAll() {

        QueryWrapper<Guru> wrapper = new QueryWrapper<>();

        wrapper.eq("guru_status",0);

        return guruDao.selectList(wrapper);
    }

    @Override
    public void addGuru(Guru guru) {
        guruMapper.insert(guru);
    }

    @Override
    public void updateStatus(Integer id,Integer status) {
        Guru guru = new Guru();

            guru.setGuruId(id);
            guru.setGuruStatus(status);
            guruMapper.updateByPrimaryKeySelective(guru);

    }

    @Override
    public void update(Guru guru) {
        guruMapper.updateByPrimaryKeySelective(guru);
    }

    @Override
    public Map selectByPage(int page, int rows, String name) {

        if(name == null){
            name = "";
        }
        Map map = new HashMap();

        IPage<Guru> iPage = new Page<>(page,rows);

        QueryWrapper<Guru> wrapper = new QueryWrapper<>();

        wrapper.like("guru_name",name);

        List<Guru> guruList = guruDao.selectPage(iPage, wrapper).getRecords();

        int count = guruDao.selectCount(wrapper);

        map.put("rows",guruList);
        map.put("total",count);

        return map;
    }

    @Override
    public void multiInsert(List<Guru> gurus) {

        guruMapper.multiInsert(gurus);



    }
}
