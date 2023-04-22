package com.yz.oss.controller;

import com.yz.oss.utils.Result;
import com.yz.oss.utils.oss.OssUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * oss控制器
 *
 * @author yangzhou
 * @date 2023/04/22
 */
@RestController
@RequestMapping("oss")
public class OssController {

    @Autowired
    private OssUtils ossUtils;

    @PostMapping("upload")
    public Result upload(MultipartFile file){
        // 返回上传oss的url
        String url = ossUtils.uploadOneFile(file);
        Map<String, Object> map = new HashMap<>();
        map.put("fileName",file.getOriginalFilename());
        map.put("url",url);
        return Result.ok(map);
    }

    @PostMapping("uploadArrayFile")
    public List<String> uploadArrayFile(MultipartFile[] files) {
        //返回上传oss的url
        return ossUtils.uploadArrayFile(files);
    }

    @PostMapping("deleteFile")
    public boolean deleteFile(@RequestBody String file){
        //返回是否删除成功
        return ossUtils.deleteFile(file);
    }

}
