package com.baizhi.cmfz.service;

import com.baizhi.cmfz.entity.Banner;

import java.util.List;
import java.util.Map;

public interface BannerService  {

    void addBanner(Banner banner);

    void delete(List<Integer> ids);

    void update(Banner banner);

    Map selectByPage(int page, int rows, String bannerOldName);

    void updateNotShow(int id);

    void updateShow(int id);
}
