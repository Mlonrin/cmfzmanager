package com.baizhi.cmfz.dao;

import com.baizhi.cmfz.entity.AdminLog;

public interface AdminLogMapper {
    int deleteByPrimaryKey(Integer logId);

    int insert(AdminLog record);

    int insertSelective(AdminLog record);

    AdminLog selectByPrimaryKey(Integer logId);

    int updateByPrimaryKeySelective(AdminLog record);

    int updateByPrimaryKey(AdminLog record);
}