package com.baizhi.cmfz.service;

import com.baizhi.cmfz.entity.Guru;

import java.util.List;
import java.util.Map;

public interface GuruService {

    List<Guru> selectAll();

    void addGuru(Guru guru);

    void updateStatus(Integer id, Integer status);

    void update(Guru guru);

    Map selectByPage(int page, int rows, String name);

    void multiInsert(List<Guru> gurus);
}
