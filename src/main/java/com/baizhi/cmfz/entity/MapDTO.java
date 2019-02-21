package com.baizhi.cmfz.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MapDTO {
    @JsonProperty(value = "name")
    private String province;
    @JsonProperty(value = "value")
    private Integer count;

    public MapDTO() {
    }

    public MapDTO(String province, Integer count) {

        this.province = province;
        this.count = count;
    }

    public Integer getCount() {

        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getProvince() {

        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Override
    public String toString() {
        return "MapDTO{" +
                "province='" + province + '\'' +
                ", count=" + count +
                '}';
    }
}
