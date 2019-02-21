package com.baizhi.cmfz.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.io.Serializable;

/**
 * (CmfzAudio)实体类
 *
 * @author makejava
 * @since 2019-01-05 20:48:23
 */
@TableName("cmfz_audio")
public class Audio implements Serializable {
    private static final long serialVersionUID = 881638190198348276L;

    @TableId(value = "audio_id",type = IdType.AUTO)
    @JsonProperty(value = "albumId")
    @Excel(name = "音频编号")
    private Integer audioId;

    @TableField("audio_name")
    @JsonProperty(value = "albumName")
    @Excel(name = "章节名")
    private String audioName;

    @TableField("album_id")
    @ExcelIgnore
    private Integer albumId;

    @TableField("audio_url")
    @Excel(name = "音频路径")
    private String audioUrl;

    @TableField("audio_size")
    @Excel(name = "大小")
    private String audioSize;

    @TableField("audio_order")
    @Excel(name = "顺序")
    private Integer audioOrder;

    @JsonGetter(value = "albumId")
    public Integer getAudioId() {
        return audioId;
    }

    @JsonSetter(value = "albumId")
    public void setAudioId(Integer audioId) {
        this.audioId = audioId;
    }

    @JsonGetter(value = "albumName")
    public String getAudioName() {
        return audioName;
    }

    @JsonSetter(value = "albumName")
    public void setAudioName(String audioName) {
        this.audioName = audioName;
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    public String getAudioSize() {
        return audioSize;
    }

    public void setAudioSize(String audioSize) {
        this.audioSize = audioSize;
    }

    public Integer getAudioOrder() {
        return audioOrder;
    }

    public void setAudioOrder(Integer audioOrder) {
        this.audioOrder = audioOrder;
    }


    @Override
    public String toString() {
        return "Audio{" +
                "audioId=" + audioId +
                ", audioName='" + audioName + '\'' +
                ", albumId=" + albumId +
                ", audioUrl='" + audioUrl + '\'' +
                ", audioSize='" + audioSize + '\'' +
                ", audioOrder=" + audioOrder +
                '}';
    }
}