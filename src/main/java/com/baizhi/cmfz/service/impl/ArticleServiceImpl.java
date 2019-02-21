package com.baizhi.cmfz.service.impl;

import com.baizhi.cmfz.dao.ArticleDao;
import com.baizhi.cmfz.entity.Article;
import com.baizhi.cmfz.lucene.LuceneDao;
import com.baizhi.cmfz.service.ArticleService;
import com.baizhi.cmfz.util.LuceneUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private LuceneDao luceneDao;

    @Override
    public Map selectByPage(int page, int rows, String name) {

        if(name==null){
            name = "%";
        }else {
            name = "%"+name+"%";
        }

        int start = (page-1)*rows;

        List<Article> articles = articleDao.selectByPage(start, rows, name);

        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        wrapper.like("article_name",name);

        int count = articleDao.selectCount(wrapper);

        Map map = new HashMap();

        map.put("rows",articles);
        map.put("total",count);

        return map;
    }

    @Override
    public void multiDelete(Integer[] ids) {
        articleDao.deleteBatchIds(Arrays.asList(ids));
        for (Integer id : ids) {
            luceneDao.deleteIndexById(id);
        }

    }

    @Override
    public void addArticle(Article article) {
        articleDao.insert(article);
        luceneDao.addIndex(article);
    }

    @Override
    public void update(Article article) {
        articleDao.updateById(article);
        luceneDao.updateIndexById(article);
    }

    @Override
    public Article selectOne(Integer id) {
        return articleDao.selectById(id);
    }

    @Override
    public Map search(int page, int rows,String keyWrod) {
        Map map = new HashMap();

        //判断索引库是否为空，索引库是否有文件
        if(!LuceneUtil.judgeIndexDB()){
            //为空则创建索引
            luceneDao.createIndex(articleDao.selectList(null));
        }
        List<Article> articleList = luceneDao.luceneSeleteByKeyword(keyWrod);
        map.put("rows",articleList);
        map.put("total", articleList.size());

        return map;
    }

}
