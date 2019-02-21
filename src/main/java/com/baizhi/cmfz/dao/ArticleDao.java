package com.baizhi.cmfz.dao;

import com.baizhi.cmfz.entity.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface ArticleDao extends BaseMapper<Article> {

    List<Article> selectByPage(int start, int rows, String name);

    List<Article> getAll(Integer userId);

    List<Article> getAll1(Integer userId);
}
