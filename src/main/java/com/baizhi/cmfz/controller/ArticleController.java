package com.baizhi.cmfz.controller;

import com.baizhi.cmfz.entity.Article;
import com.baizhi.cmfz.entity.Guru;
import com.baizhi.cmfz.service.ArticleService;
import com.baizhi.cmfz.service.GuruService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private GuruService guruService;

    @RequestMapping("/selectByPage")
    public Map selectByPage(int page, int rows, String name){
        return articleService.selectByPage(page,rows,name);
    }

    @RequestMapping("/multiDelete")
    public boolean multiDelete(Integer[] ids){
        try {
            articleService.multiDelete(ids);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping("/selectOne")
    public Map selectOne(Integer id){
        Article article = articleService.selectOne(id);
        List<Guru> guruList = guruService.selectAll();

        Map map = new HashMap();
        map.put("article",article);
        map.put("guruList",guruList);
        return map;
    }

    @RequestMapping("/addArticle")
    public boolean addArticle(Article article, MultipartFile file) {
        try {

            String filename = file.getOriginalFilename();

            String begin = UUID.randomUUID().toString();
            String end = filename.substring(filename.lastIndexOf("."));

            String newFileName = begin+end;

            Date date = new Date();

            File file1 = new File("E:\\Server\\"+newFileName);

            file.transferTo(file1);

            article.setArticleImage(newFileName);
            article.setArticleDate(date);

            articleService.addArticle(article);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping("/update")
    public boolean update(Article article,MultipartFile file){
        try {

            String filename = file.getOriginalFilename();

            String begin = UUID.randomUUID().toString();
            String end = filename.substring(filename.lastIndexOf("."));

            String newFileName = begin+end;

            Date date = new Date();

            File file1 = new File("E:\\Server\\"+newFileName);

            file.transferTo(file1);

            article.setArticleImage(newFileName);
            article.setArticleDate(date);

            articleService.update(article);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping("/search")
    public Map search(int page, int rows,String keyWrod){
        return articleService.search(page,rows,keyWrod);
    }

}
