package com.baizhi.cmfz.test;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.baizhi.cmfz.entity.Guru;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class PoiTest {

    public static void main(String[] args) throws Exception {

        List<Guru> gurus = new ArrayList<>();
        Guru guru = new Guru();
        guru.setGuruId(1);
        guru.setGuruName("李四");
        guru.setGuruImage("/img/tet.jpg");
        guru.setGuruNickname("阿拉还是");
        guru.setGuruStatus(0);
        gurus.add(guru);

//        定义导出相关参数
        ExportParams exportParams = new ExportParams("上师信息","guru",ExcelType.HSSF);
//        创建workbook对象
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams,Guru.class,gurus);

//          保存到本地
        workbook.write(new FileOutputStream(new File("E:\\Guru.xls")));
    }
}
