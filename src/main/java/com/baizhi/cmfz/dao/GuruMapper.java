package com.baizhi.cmfz.dao;

import com.baizhi.cmfz.entity.Guru;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GuruMapper {
    int deleteByPrimaryKey(Integer guruId);

    int insert(Guru record);

    int insertSelective(Guru record);

    Guru selectByPrimaryKey(Integer guruId);

    int updateByPrimaryKeySelective(Guru record);

    int updateByPrimaryKey(Guru record);

    void multiInsert(@Param("gurus") List<Guru> gurus);
}