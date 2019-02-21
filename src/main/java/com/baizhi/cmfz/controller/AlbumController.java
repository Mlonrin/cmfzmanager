package com.baizhi.cmfz.controller;

import com.baizhi.cmfz.entity.Album;
import com.baizhi.cmfz.entity.Audio;
import com.baizhi.cmfz.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/album")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @RequestMapping("/addAlbum")
    @ResponseBody
    public boolean addAlbum(Album album, MultipartFile file){
        try {

            String filename = file.getOriginalFilename();

            String begin = UUID.randomUUID().toString();
            String end = filename.substring(filename.lastIndexOf("."));

            String newFileName = begin+end;

            Date date = new Date();

            File file1 = new File("E:\\Server\\"+newFileName);

            file.transferTo(file1);

            album.setAlbumDate(date);

            album.setAlbumImage(newFileName);

            albumService.addAlbum(album);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping("/multiDelete")
    @ResponseBody
    boolean multiDelete(Integer[] ids){
        try {
            albumService.multiDelete(ids);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping("/update")
    @ResponseBody
    boolean update(Album album,MultipartFile file){
        try {

            String filename = file.getOriginalFilename();

            String begin = UUID.randomUUID().toString();
            String end = filename.substring(filename.lastIndexOf("."));

            String newFileName = begin+end;

            Date date = new Date();

            File file1 = new File("E:\\Server\\"+newFileName);

            file.transferTo(file1);

            album.setAlbumDate(date);

            album.setAlbumImage(newFileName);

            albumService.update(album);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping("/selectByPage")
    @ResponseBody
    public Map selectByPage(Integer page, Integer rows, String name){
        return albumService.selectByPage(page,rows,name);
    }

    @RequestMapping("/selectAll")
    @ResponseBody
    public List<Album> selectAll(){
        List<Album> albumList = albumService.selectAll();

        for (Album album : albumList) {

            for (Audio audio : album.getChildren()) {
                System.out.println(audio);
            }
        }
        return albumList;
    }

    @RequestMapping("/selectOne")
    @ResponseBody
    public Album selectAll(Integer id){
        return albumService.selectOne(id);
    }

}
