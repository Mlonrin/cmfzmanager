package com.baizhi.cmfz.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * (CmfzAlbum)实体类
 *
 * @author makejava
 * @since 2019-01-05 09:15:05
 */
@TableName("cmfz_album")
public class Album implements Serializable {
    private static final long serialVersionUID = 361046196806754697L;

    @TableId(value = "album_id",type=IdType.AUTO)
    @Excel(name = "专辑编号")
    private Integer albumId;

    @TableField("album_name")
    @Excel(name = "专辑名")
    private String albumName;

    @TableField("album_author")
    @Excel(name = "作者")
    private String albumAuthor;

    @TableField("album_teller")
    @Excel(name = "朗读者")
    private String albumTeller;

    @TableField("album_Episodes")
    @Excel(name = "集数")
    private Integer albumEpisodes;

    @TableField("album_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发布日期",format = "yyyy-MM-dd")
    private Date albumDate;

    @TableField("album_content")
    @Excel(name = "专辑内容")
    private String albumContent;

    @TableField("album_image")
    @Excel(name = "图片")
    private String albumImage;

    @TableField("album_star")
    @Excel(name = "评分",replace = {"一星_1","二星_2","三星_3","四星_4","五星_5"})
    private Integer albumStar;

    @TableField(exist = false)
    @ExcelCollection(name = "音频列表")
    private List<Audio> children;

    public Album() {
    }

    public Album(Integer albumId, String albumName, String albumAuthor, String albumTeller, Integer albumEpisodes, Date albumDate, String albumContent, String albumImage, Integer albumStar, List<Audio> children) {
        this.albumId = albumId;
        this.albumName = albumName;
        this.albumAuthor = albumAuthor;
        this.albumTeller = albumTeller;
        this.albumEpisodes = albumEpisodes;
        this.albumDate = albumDate;
        this.albumContent = albumContent;
        this.albumImage = albumImage;
        this.albumStar = albumStar;
        this.children = children;
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getAlbumAuthor() {
        return albumAuthor;
    }

    public void setAlbumAuthor(String albumAuthor) {
        this.albumAuthor = albumAuthor;
    }

    public String getAlbumTeller() {
        return albumTeller;
    }

    public void setAlbumTeller(String albumTeller) {
        this.albumTeller = albumTeller;
    }

    public Integer getAlbumEpisodes() {
        return albumEpisodes;
    }

    public void setAlbumEpisodes(Integer albumEpisodes) {
        this.albumEpisodes = albumEpisodes;
    }

    public Date getAlbumDate() {
        return albumDate;
    }

    public void setAlbumDate(Date albumDate) {
        this.albumDate = albumDate;
    }

    public String getAlbumContent() {
        return albumContent;
    }

    public void setAlbumContent(String albumContent) {
        this.albumContent = albumContent;
    }

    public String getAlbumImage() {
        return albumImage;
    }

    public void setAlbumImage(String albumImage) {
        this.albumImage = albumImage;
    }

    public Integer getAlbumStar() {
        return albumStar;
    }

    public void setAlbumStar(Integer albumStar) {
        this.albumStar = albumStar;
    }

    public List<Audio> getChildren() {
        return children;
    }

    public void setChildren(List<Audio> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "Album{" +
                "albumId=" + albumId +
                ", albumName='" + albumName + '\'' +
                ", albumAuthor='" + albumAuthor + '\'' +
                ", albumTeller='" + albumTeller + '\'' +
                ", albumEpisodes=" + albumEpisodes +
                ", albumDate=" + albumDate +
                ", albumContent='" + albumContent + '\'' +
                ", albumImage='" + albumImage + '\'' +
                ", albumStar=" + albumStar +
                ", children=" + children +
                '}';
    }
}