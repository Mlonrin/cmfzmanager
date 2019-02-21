package com.baizhi.cmfz.controller;

import com.baizhi.cmfz.entity.Audio;
import com.baizhi.cmfz.service.AudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@RestController
@RequestMapping("/audio")
public class AudioController {

    @Autowired
    private AudioService audioService;

    @RequestMapping("/addAudio")
    public boolean addAudio(Audio audio, MultipartFile myFile){
        try {

            String filename = myFile.getOriginalFilename();

            String begin = UUID.randomUUID().toString();
            String end = filename.substring(filename.lastIndexOf("."));

            String newFileName = begin+end;

            File file1 = new File("E:\\Server\\"+newFileName);

            myFile.transferTo(file1);

            long size = myFile.getSize();

            audio.setAudioSize(size+"");

            audio.setAudioUrl(newFileName);

            audioService.addAudio(audio);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
