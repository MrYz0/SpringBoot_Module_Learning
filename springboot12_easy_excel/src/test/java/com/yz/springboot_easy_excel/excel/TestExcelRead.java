package com.yz.springboot_easy_excel.excel;

import com.alibaba.excel.EasyExcel;

public class TestExcelRead {
    public static void main(String[] args) {
        // 实现excel读操作
        // 1.设置读取文件夹地址和excel文件名称
        String fileName = "/Users/yangzhou/write.xlsx";

        EasyExcel.read(fileName, DemoData.class,new ExcelListener()).sheet().doRead();
    }
}
