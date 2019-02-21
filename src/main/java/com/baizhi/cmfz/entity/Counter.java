package com.baizhi.cmfz.entity;

import java.io.Serializable;
import java.util.Date;

public class Counter implements Serializable {

    private Integer counterId;

    private String counterName;

    private Date counterDate;

    private String lessonId;

    private Integer userId;

    private Integer counterCount;

    private Integer counterStatus;

    private static final long serialVersionUID = 1L;

    public Integer getCounterId() {
        return counterId;
    }

    public void setCounterId(Integer counterId) {
        this.counterId = counterId;
    }

    public String getCounterName() {
        return counterName;
    }

    public void setCounterName(String counterName) {
        this.counterName = counterName == null ? null : counterName.trim();
    }

    public Date getCounterDate() {
        return counterDate;
    }

    public void setCounterDate(Date counterDate) {
        this.counterDate = counterDate;
    }

    public String getLessonId() {
        return lessonId;
    }

    public void setLessonId(String lessonId) {
        this.lessonId = lessonId == null ? null : lessonId.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCounterCount() {
        return counterCount;
    }

    public void setCounterCount(Integer counterCount) {
        this.counterCount = counterCount;
    }

    public Integer getCounterStatus() {
        return counterStatus;
    }

    public void setCounterStatus(Integer counterStatus) {
        this.counterStatus = counterStatus;
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
        Counter other = (Counter) that;
        return (this.getCounterId() == null ? other.getCounterId() == null : this.getCounterId().equals(other.getCounterId()))
            && (this.getCounterName() == null ? other.getCounterName() == null : this.getCounterName().equals(other.getCounterName()))
            && (this.getCounterDate() == null ? other.getCounterDate() == null : this.getCounterDate().equals(other.getCounterDate()))
            && (this.getLessonId() == null ? other.getLessonId() == null : this.getLessonId().equals(other.getLessonId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getCounterCount() == null ? other.getCounterCount() == null : this.getCounterCount().equals(other.getCounterCount()))
            && (this.getCounterStatus() == null ? other.getCounterStatus() == null : this.getCounterStatus().equals(other.getCounterStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCounterId() == null) ? 0 : getCounterId().hashCode());
        result = prime * result + ((getCounterName() == null) ? 0 : getCounterName().hashCode());
        result = prime * result + ((getCounterDate() == null) ? 0 : getCounterDate().hashCode());
        result = prime * result + ((getLessonId() == null) ? 0 : getLessonId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getCounterCount() == null) ? 0 : getCounterCount().hashCode());
        result = prime * result + ((getCounterStatus() == null) ? 0 : getCounterStatus().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", counterId=").append(counterId);
        sb.append(", counterName=").append(counterName);
        sb.append(", counterDate=").append(counterDate);
        sb.append(", lessonId=").append(lessonId);
        sb.append(", userId=").append(userId);
        sb.append(", counterCount=").append(counterCount);
        sb.append(", counterStatus=").append(counterStatus);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}