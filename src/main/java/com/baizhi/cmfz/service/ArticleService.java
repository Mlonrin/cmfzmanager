package com.baizhi.cmfz.service;

import com.baizhi.cmfz.entity.Article;

import java.util.List;
import java.util.Map;

public interface ArticleService {

    Map selectByPage(int page, int rows, String name);

    void multiDelete(Integer[] ids);

    void addArticle(Article article);


    void update(Article article);

    Article selectOne(Integer id);

    Map search(int page, int rows,String keyWrod);
}
