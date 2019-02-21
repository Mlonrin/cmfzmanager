package com.baizhi.cmfz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * (CmfzArticle)实体类
 *
 * @author makejava
 * @since 2019-01-05 22:23:06
 */
@TableName("cmfz_article")
public class Article implements Serializable {
    private static final long serialVersionUID = 821807065016049164L;

    @TableId(value = "article_id",type = IdType.AUTO)
    private Integer articleId;

    @TableField("article_name")
    private String articleName;

    @TableField("article_image")
    private String articleImage;

    @TableField("article_content")
    private String articleContent;

    @TableField("guru_id")
    private Integer guruId;

    @TableField("article_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date articleDate;

    @TableField(exist = false)
    private Guru guru;


    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public String getArticleImage() {
        return articleImage;
    }

    public void setArticleImage(String articleImage) {
        this.articleImage = articleImage;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public Integer getGuruId() {
        return guruId;
    }

    public void setGuruId(Integer guruId) {
        this.guruId = guruId;
    }

    public Date getArticleDate() {
        return articleDate;
    }

    public void setArticleDate(Date articleDate) {
        this.articleDate = articleDate;
    }

    public Guru getGuru() {
        return guru;
    }

    public void setGuru(Guru guru) {
        this.guru = guru;
    }

    @Override
    public String toString() {
        return "Article{" +
                "articleId=" + articleId +
                ", articleName='" + articleName + '\'' +
                ", articleImage='" + articleImage + '\'' +
                ", articleContent='" + articleContent + '\'' +
                ", guruId=" + guruId +
                ", articleDate=" + articleDate +
                ", guru=" + guru +
                '}';
    }
}