package com.yz.delivery.utils;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.Map;

public class LogisticsUtils {

    public static final String appcode = "1214b9361c7544e3bfa8d46971117f19";

    public static String getLogistics(String type,String shipNumber) throws Exception {
        String host = "https://api09.aliyun.venuscn.com";
        String path = "/express/trace/query";
        String method = "GET";
        Map<String, String> headers = new HashMap<>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<>();
        querys.put("comid", type);
        querys.put("number", shipNumber);

        HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
        //获取response的body
        String result = EntityUtils.toString(response.getEntity());
        return result;
    }


}
