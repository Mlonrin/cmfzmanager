package com.baizhi.cmfz.service.impl;

import com.baizhi.cmfz.dao.AlbumDao;
import com.baizhi.cmfz.entity.Album;
import com.baizhi.cmfz.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    private AlbumDao albumDao;

    @Override
    public void addAlbum(Album album) {
        albumDao.addAlbum(album);
    }

    @Override
    public void multiDelete(Integer[] ids) {
        albumDao.multiDelete(ids);
    }

    @Override
    public void update(Album album) {
        albumDao.update(album);
    }

    @Override
    public void updateEpisodes(Integer id,Integer episodes) {
        albumDao.updateEpisodes(id,episodes);
    }

    @Override
    public Map selectByPage(Integer page, Integer rows, String name) {
        if(name==null){
            name = "%";
        }else {
            name = "%"+name+"%";
        }

        int start = (page-1)*rows;

        List<Album> albumList = albumDao.selectByPage(start,rows,name);

        int count = albumDao.getCount(name);

        Map map = new HashMap();

        map.put("rows",albumList);

        map.put("total",count);

        return map;
    }

    @Override
    public List<Album> selectAll() {
        return albumDao.selectAll();
    }

    @Override
    public Album selectOne(Integer id) {

        return albumDao.selectById(id);
    }

}
