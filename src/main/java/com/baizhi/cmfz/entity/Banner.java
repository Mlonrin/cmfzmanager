package com.baizhi.cmfz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * (CmfzBanner)实体类
 *
 * @author makejava
 * @since 2019-01-03 21:15:36
 */
@TableName("cmfz_banner")
public class Banner implements Serializable {
    private static final long serialVersionUID = -27738780399842795L;
    @TableId(value = "banner_id",type = IdType.AUTO)
    private Integer bannerId;
    
    private String bannerImageUrl;
    //原有名称
    private String bannerOldName;
    @TableField("banner_state")
    private Integer bannerState;

    @TableField("banner_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date bannerDate;
    
    private String bannerDescription;


    public Integer getBannerId() {
        return bannerId;
    }

    public void setBannerId(Integer bannerId) {
        this.bannerId = bannerId;
    }

    public String getBannerImageUrl() {
        return bannerImageUrl;
    }

    public void setBannerImageUrl(String bannerImageUrl) {
        this.bannerImageUrl = bannerImageUrl;
    }

    public String getBannerOldName() {
        return bannerOldName;
    }

    public void setBannerOldName(String bannerOldName) {
        this.bannerOldName = bannerOldName;
    }

    public Integer getBannerState() {
        return bannerState;
    }

    public void setBannerState(Integer bannerState) {
        this.bannerState = bannerState;
    }

    public Date getBannerDate() {
        return bannerDate;
    }

    public void setBannerDate(Date bannerDate) {
        this.bannerDate = bannerDate;
    }

    public String getBannerDescription() {
        return bannerDescription;
    }

    public void setBannerDescription(String bannerDescription) {
        this.bannerDescription = bannerDescription;
    }

    @Override
    public String toString() {
        return "Banner{" +
                "bannerId=" + bannerId +
                ", bannerImageUrl='" + bannerImageUrl + '\'' +
                ", bannerOldName='" + bannerOldName + '\'' +
                ", bannerState=" + bannerState +
                ", bannerDate=" + bannerDate +
                ", bannerDescription='" + bannerDescription + '\'' +
                '}';
    }
}