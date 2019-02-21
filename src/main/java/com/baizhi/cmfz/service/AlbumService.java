package com.baizhi.cmfz.service;

import com.baizhi.cmfz.entity.Album;

import java.util.List;
import java.util.Map;

public interface AlbumService {

    void addAlbum(Album album);

    void multiDelete(Integer[] ids);

    void update(Album album);

    void updateEpisodes(Integer id, Integer episodes);

    Map selectByPage(Integer page, Integer rows, String name);

    List<Album> selectAll();


    Album selectOne(Integer id);
}
