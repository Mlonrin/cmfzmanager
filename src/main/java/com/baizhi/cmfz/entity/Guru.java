package com.baizhi.cmfz.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import com.baizhi.cmfz.util.ExcelName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

@TableName("cmfz_guru")
public class Guru implements Serializable {
    @ExcelName(name = "编号")
    @TableId(value = "guru_id",type = IdType.AUTO)
    @Excel(name = "编号")
    private Integer guruId;

    @ExcelName(name = "姓名")
    @TableField("guru_name")
    @Excel(name = "姓名")
    private String guruName;

    @ExcelName(name = "图片")
    @TableField("guru_image")

    /**
     *  声明读出为图片
     *  属性保存绝对路径或者请求路径（部署项目）
     *  相对路径没用
     *  savePath 为保存路径
     */
    @Excel(name = "照片",type = 2,width = 20,height = 25,imageType = 1,savePath = "E:\\test")
    private String guruImage;

    @ExcelName(name = "法号")
    @TableField("guru_nickname")
    @Excel(name = "法号")
    private String guruNickname;

    @ExcelName(name = "状态")
    @TableField("guru_status")
    @Excel(name = "状态",replace = {"正常_0","冻结_1"})
    private Integer guruStatus;

    @ExcelIgnore
    private static final long serialVersionUID = 1L;

    public Integer getGuruId() {
        return guruId;
    }

    public void setGuruId(Integer guruId) {
        this.guruId = guruId;
    }

    public String getGuruName() {
        return guruName;
    }

    public void setGuruName(String guruName) {
        this.guruName = guruName == null ? null : guruName.trim();
    }

    public String getGuruImage() {
        return guruImage;
    }

    public void setGuruImage(String guruImage) {
        this.guruImage = guruImage == null ? null : guruImage.trim();
    }

    public String getGuruNickname() {
        return guruNickname;
    }

    public void setGuruNickname(String guruNickname) {
        this.guruNickname = guruNickname == null ? null : guruNickname.trim();
    }

    public Integer getGuruStatus() {
        return guruStatus;
    }

    public void setGuruStatus(Integer guruStatus) {
        this.guruStatus = guruStatus;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Guru other = (Guru) that;
        return (this.getGuruId() == null ? other.getGuruId() == null : this.getGuruId().equals(other.getGuruId()))
            && (this.getGuruName() == null ? other.getGuruName() == null : this.getGuruName().equals(other.getGuruName()))
            && (this.getGuruImage() == null ? other.getGuruImage() == null : this.getGuruImage().equals(other.getGuruImage()))
            && (this.getGuruNickname() == null ? other.getGuruNickname() == null : this.getGuruNickname().equals(other.getGuruNickname()))
            && (this.getGuruStatus() == null ? other.getGuruStatus() == null : this.getGuruStatus().equals(other.getGuruStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getGuruId() == null) ? 0 : getGuruId().hashCode());
        result = prime * result + ((getGuruName() == null) ? 0 : getGuruName().hashCode());
        result = prime * result + ((getGuruImage() == null) ? 0 : getGuruImage().hashCode());
        result = prime * result + ((getGuruNickname() == null) ? 0 : getGuruNickname().hashCode());
        result = prime * result + ((getGuruStatus() == null) ? 0 : getGuruStatus().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", guruId=").append(guruId);
        sb.append(", guruName=").append(guruName);
        sb.append(", guruImage=").append(guruImage);
        sb.append(", guruNickname=").append(guruNickname);
        sb.append(", guruStatus=").append(guruStatus);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}