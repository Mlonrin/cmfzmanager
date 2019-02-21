package com.baizhi.cmfz.service.impl;

import com.baizhi.cmfz.dao.BannerDao;
import com.baizhi.cmfz.entity.Banner;
import com.baizhi.cmfz.service.BannerService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerDao bannerDao;

    @Override
    public void addBanner(Banner banner) {
        bannerDao.insert(banner);
    }

    @Override
    public void delete(List<Integer> ids) {
        for (Integer id : ids) {
            Banner banner = bannerDao.selectById(id);
            banner.setBannerState(2);
            bannerDao.updateById(banner);
        }
    }

    @Override
    public void update(Banner banner) {
        Banner banner1 = bannerDao.selectById(banner.getBannerId());

        banner1.setBannerOldName(banner.getBannerOldName());
        banner1.setBannerDescription(banner.getBannerDescription());

        bannerDao.updateById(banner1);
    }

    @Override
    public Map selectByPage(int page, int rows, String bannerOldName) {

        if(bannerOldName==null){
            bannerOldName="%%";
        }

        IPage<Banner> iPage = new Page<>(page,rows);

        QueryWrapper<Banner> wrapper = new QueryWrapper<>();

        wrapper.like("banner_old_name",bannerOldName).notIn("banner_state",2);

        List<Banner> bannerList = bannerDao.selectPage(iPage, wrapper).getRecords();

        int count = bannerDao.selectCount(wrapper);

        Map map = new HashMap();

        map.put("rows",bannerList);
        map.put("total",count);

        return map;
    }

    @Override
    public void updateNotShow(int id) {
        Banner banner = bannerDao.selectById(id);
        banner.setBannerState(0);
        bannerDao.updateById(banner);
    }

    @Override
    public void updateShow(int id) {
        Banner banner = bannerDao.selectById(id);
        banner.setBannerState(1);
        bannerDao.updateById(banner);
    }
}
