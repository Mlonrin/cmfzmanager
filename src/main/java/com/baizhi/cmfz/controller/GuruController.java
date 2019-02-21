package com.baizhi.cmfz.controller;

import com.baizhi.cmfz.entity.Guru;
import com.baizhi.cmfz.service.GuruService;
import com.baizhi.cmfz.util.POIUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/guru")
public class GuruController {

    @Autowired
    private GuruService guruService;

    @RequestMapping("/selectAll")
    public List<Guru> selectAll(){
        return guruService.selectAll();
    }

    @RequestMapping("/addGuru")
    public boolean addGuru(Guru guru, MultipartFile myFile){
        try {

            String filename = myFile.getOriginalFilename();

            String begin = UUID.randomUUID().toString();
            String end = filename.substring(filename.lastIndexOf("."));

            String newFileName = begin+end;

            Date date = new Date();

            File file1 = new File("E:\\Server\\"+newFileName);

            myFile.transferTo(file1);

            guru.setGuruImage(newFileName);

            guru.setGuruStatus(0);

            guruService.addGuru(guru);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping("/updateStatus")
    public boolean updateStatus(Integer id,Integer status){
        try {
            guruService.updateStatus(id,status);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping("/update")
    public boolean update(Guru guru){
        try {
            guruService.update(guru);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping("/selectByPage")
    public Map selectByPage(Integer page, Integer rows, String name){
        return guruService.selectByPage(page,rows,name);
    }

    @RequestMapping("/downLoad")
    public void downLoad(HttpServletResponse response) throws Exception {

        List<Guru> gurus = guruService.selectAll();

        File file = POIUtil.toExcell(gurus,new File("E:\\test2.xls"));

        String fileName = null;
        try {
            fileName = file.getName();

            fileName = URLEncoder.encode(fileName, "utf-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        response.setContentType("application/octet-stream;charset=UTF-8");

        response.setHeader("content-disposition","attachment;fileName="+fileName);

        ServletOutputStream outputStream = response.getOutputStream();
        //写入到响应流中
        FileUtils.copyFile(file,outputStream);

        file.delete();

    }

    @RequestMapping("upLoad")
    public boolean upLoad(MultipartFile file){

        try {

            file.transferTo(new File("E:\\test1.xls"));

            File file1 = new File("E:\\test1.xls");

            List<Guru> gurus = POIUtil.fromExcel(file1, "guruTitle", Guru.class);

            //file1.delete();

            guruService.multiInsert(gurus);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

}
