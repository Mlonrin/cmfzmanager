package com.baizhi.cmfz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

@TableName("cmfz_lesson")
public class Lesson implements Serializable {
    @TableId(value = "lesson_id",type = IdType.AUTO)
    private Integer lessonId;

    @TableField("lesson_name")
    private String lessonName;

    @TableField(exist = false)
    private Integer userId;

    @TableField("lesson_status")
    private Integer lessonStatus;

    private static final long serialVersionUID = 1L;

    public Integer getLessonId() {
        return lessonId;
    }

    public void setLessonId(Integer lessonId) {
        this.lessonId = lessonId;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName == null ? null : lessonName.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getLessonStatus() {
        return lessonStatus;
    }

    public void setLessonStatus(Integer lessonStatus) {
        this.lessonStatus = lessonStatus;
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
        Lesson other = (Lesson) that;
        return (this.getLessonId() == null ? other.getLessonId() == null : this.getLessonId().equals(other.getLessonId()))
            && (this.getLessonName() == null ? other.getLessonName() == null : this.getLessonName().equals(other.getLessonName()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getLessonStatus() == null ? other.getLessonStatus() == null : this.getLessonStatus().equals(other.getLessonStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getLessonId() == null) ? 0 : getLessonId().hashCode());
        result = prime * result + ((getLessonName() == null) ? 0 : getLessonName().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getLessonStatus() == null) ? 0 : getLessonStatus().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", lessonId=").append(lessonId);
        sb.append(", lessonName=").append(lessonName);
        sb.append(", userId=").append(userId);
        sb.append(", lessonStatus=").append(lessonStatus);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}