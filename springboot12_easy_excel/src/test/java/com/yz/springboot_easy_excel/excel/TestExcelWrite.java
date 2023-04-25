package com.yz.springboot_easy_excel.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

public class TestExcelWrite {


    public static void main(String[] args) {
        // 实现excel写操作
        // 1.设置写入文件夹地址和excel文件名称
        String fileName = "/Users/yangzhou/write.xlsx";
        // 2.调用easyExcel里面的方法实现写操作
        // write方法的两个参数：第一个参数文件路径名称，第二个参数实体类的class
        EasyExcel.write(fileName, DemoData.class).sheet("学生列表").doWrite(getData());

    }

    // 创建方法返回list集合
    private static List<DemoData> getData(){
        List<DemoData> list  = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DemoData demoData = new DemoData();
            demoData.setSno(i);
            demoData.setSname("yz" + i);
            list.add(demoData);
        }
        return list;
    }


}
