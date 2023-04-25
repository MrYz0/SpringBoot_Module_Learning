package com.yz.springboot_easy_excel.controller;

import com.alibaba.excel.EasyExcel;
import com.sun.deploy.net.URLEncoder;
import com.yz.springboot_easy_excel.config.exception.CustomException;
import com.yz.springboot_easy_excel.pojo.dto.UserDTO;
import com.yz.springboot_easy_excel.service.UserService;
import com.yz.springboot_easy_excel.utils.ResponseEnum;
import com.yz.springboot_easy_excel.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/import")
    public Result batchImport(
        @RequestParam("file") MultipartFile file) {

        try {
            InputStream inputStream = file.getInputStream();
            userService.importData(inputStream);
            return Result.ok().message("批量导入成功");
        } catch (Exception e) {
            throw new CustomException(-103,"文件上传失败");
        }
    }


    @GetMapping("/export")
    public void export(HttpServletResponse response){

        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和EasyExcel没有关系
            String fileName = URLEncoder.encode("mydict", "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
            EasyExcel.write(response.getOutputStream(), UserDTO.class).sheet("学生列表").doWrite(userService.listUserDTOData());

        } catch (IOException e) {
            //EXPORT_DATA_ERROR(104, "数据导出失败"),
            // throw  new CustomException(104, "数据导出失败");
            throw  new CustomException(ResponseEnum.EXPORT_DATA_ERROR,e);
        }
    }

}
