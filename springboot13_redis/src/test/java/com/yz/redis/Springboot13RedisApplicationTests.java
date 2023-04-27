package com.yz.redis;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.yz.redis.mapper.UserMapper;
import com.yz.redis.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class Springboot13RedisApplicationTests {

    @Resource
    private UserMapper userMapper;


    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void contextLoads() {
        // 插入键-name 值-yz
        stringRedisTemplate.opsForValue().set("name", "yz");
        // 获取值，根据键name获取
        String name = stringRedisTemplate.opsForValue().get("name");
        System.out.println("name = " + name);
    }


    @Test
    void testObject(){
        User user = new User();
        user.setName("John");
        user.setSex("男");
        // 1、使用fastjson的序列化，将user对象转换为序列化，存入redis，stringRedisTemplate key 和value都为string类型，所以需要序列化
        String jsonString = JSON.toJSONString(user);
        stringRedisTemplate.opsForValue().set("user:object", jsonString);
        String jsonResult = stringRedisTemplate.opsForValue().get("user:object");
        // 2、反序列化
        User parsedObject = JSON.parseObject(jsonResult, User.class);
        System.out.println("parsedObject = " + parsedObject);
    }

    @Test
    void testInsertArray(){

        List<User> users = userMapper.selectList(null);
        String json = JSON.toJSONString(users);
        stringRedisTemplate.opsForValue().set("user:array",json);

        String user = stringRedisTemplate.opsForValue().get("user:array");
        System.out.println("user = " + user);
        List<User> parsedArray = JSON.parseArray(user, User.class);
        System.out.println("parsedObject = " + parsedArray);

    }
}
