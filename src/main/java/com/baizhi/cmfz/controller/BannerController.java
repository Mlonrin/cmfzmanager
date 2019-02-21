package com.baizhi.cmfz.controller;

import com.baizhi.cmfz.entity.Banner;
import com.baizhi.cmfz.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/banner")
public class BannerController {

    @Autowired
    private BannerService bannerService;

    @RequestMapping("/addBanner")
    public boolean addBanner(Banner banner, MultipartFile file){
        try {

            String filename = file.getOriginalFilename();

            String begin = UUID.randomUUID().toString();
            String end = filename.substring(filename.lastIndexOf("."));

            String newFileName = begin+end;
            Date date = new Date();

            File file1 = new File("E:\\Server\\"+newFileName);

            file.transferTo(file1);

            banner.setBannerImageUrl(newFileName);

            banner.setBannerOldName(filename);
            banner.setBannerDate(date);
            banner.setBannerState(0);
            bannerService.addBanner(banner);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping("/multiDelete")
    public boolean multiDelete(Integer[] ids){
        try {
            bannerService.delete(Arrays.asList(ids));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping("/update")
    public boolean update(Banner banner){
        try {
            bannerService.update(banner);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping("/selectByPage")
    public Map selectByPage(int page, int rows, String name){
        return bannerService.selectByPage(page,rows,name);
    }

    @RequestMapping("/updateShow")
    public boolean updateShow(int id){
        try {
            bannerService.updateShow(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping("/updateNotShow")
    public boolean updateNotShow(Integer id){
        try {
            bannerService.updateNotShow(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
