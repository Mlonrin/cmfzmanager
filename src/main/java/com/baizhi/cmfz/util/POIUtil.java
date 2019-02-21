package com.baizhi.cmfz.util;



import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class POIUtil {


    public static <T> File toExcell(List<T> list,File file) throws Exception{

//        集合为空则返回
        if(list==null||list.size()==0){
            return null;
        }

//        1.创建文件对象
        HSSFWorkbook workbook = new HSSFWorkbook();

//        2.创建表格
        HSSFSheet titleSheet = workbook.createSheet("guruTitle");

//        3.创建标题行
        HSSFRow titleRow = titleSheet.createRow(0);

//        获取类对象
        Class tClass = list.get(0).getClass();

//        获取属性
        Field[] fields = tClass.getDeclaredFields();

//        标题表格设值
        for (int i = 0; i < fields.length; i++) {
            if(fields[i].getName().equals("serialVersionUID")){
                continue;
            }
//            获取注解值(标题值)
            String name = fields[i].getAnnotation(ExcelName.class).name();

//            创建标题单元格
            HSSFCell cell = titleRow.createCell(i);
//            标题单元格设值
            cell.setCellValue(name);

        }

        for (int j = 0; j < list.size(); j++) {
//            获取当前对象
            T t = list.get(j);
//            创建行对象
            HSSFRow row = titleSheet.createRow(j + 1);

            for (int i = 0; i < fields.length; i++) {
                String fieldName = fields[i].getName();
                if(fieldName.equals("serialVersionUID")){
                    continue;
                }
//                获取get方法名
                String getName = "get" + fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);
//                获取get方法
                Method method = tClass.getMethod(getName, null);
//                调用get方法
                Object invoke = method.invoke(t);
//                获取属性类型
//                Class<?> type = fields[i].getType();

//                创建单元格
                HSSFCell cell = row.createCell(i);

//                判断类型并赋值
                if(invoke instanceof Integer){
                    cell.setCellValue((Integer)invoke);
                }else if(invoke instanceof String){
                    cell.setCellValue((String) invoke);
                }else if(invoke instanceof Date){
                    cell.setCellValue((Date) invoke);
                }else {
                    cell.setCellValue((Double) invoke);
                }

            }
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);
//        输出流写出
        workbook.write(fileOutputStream);

        //关闭流，释放资源
        fileOutputStream.close();
        return file;

    }

    public static <T> List<T> fromExcel(File file,String sheetName,Class<T> t)throws Exception {
//        获取属性
        Field[] fields = t.getDeclaredFields();

//        读取文件流
        FileInputStream inputStream = new FileInputStream(file);
//        处理文件流的数据，对象包装
        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
//        取出表对象
        HSSFSheet sheet = workbook.getSheet(sheetName);

//        获取最后一行行数
        int lastRowNum = sheet.getLastRowNum();

        List<T> list = new ArrayList<>();

        for (int i = 1; i <= lastRowNum; i++) {
//            创建对象
            T t1 = t.newInstance();

//            获取当前行
            HSSFRow row = sheet.getRow(i);
//            获取当前行最后一列列数
            short lastCellNum = row.getLastCellNum();

            for (int j = 0; j <= lastCellNum; j++) {

                String fieldName = fields[j].getName();
                if (fieldName.equals("serialVersionUID")) {
                    continue;
                }

//                获取get方法名
                String setName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

                Method method = null;

                try {
                    method = t.getMethod(setName, Integer.class);

                    method.invoke(t1,(int)row.getCell(j).getNumericCellValue());
                } catch (NoSuchMethodException e) {

                }
                try {
                    method = t.getMethod(setName, Double.class);
                    method.invoke(t1,row.getCell(j).getNumericCellValue());
                } catch (NoSuchMethodException e) {

                }
                try {
                    method = t.getMethod(setName, String.class);
                    method.invoke(t1,row.getCell(j).getStringCellValue());
                } catch (NoSuchMethodException e) {

                }
                try {
                    method = t.getMethod(setName, Date.class);
                    method.invoke(t1,row.getCell(j).getDateCellValue());
                } catch (NoSuchMethodException e) {

                }
            }
            list.add(t1);
        }
        //关闭流
        inputStream.close();
        return list;
    }

}
