package com.baizhi.cmfz.dao;

import com.baizhi.cmfz.entity.Album;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AlbumDao extends BaseMapper<Album> {

    void addAlbum(Album album);

    void multiDelete(Integer[] ids);

    void update(Album album);

    void updateEpisodes(@Param("id") Integer id, @Param("episodes") Integer episodes);

    List<Album> selectByPage(@Param("start") Integer start, @Param("rows") Integer rows, @Param("name") String name);

    int getCount(String name);

    List<Album> selectAll();

    Album getById(Integer albumId);
}
