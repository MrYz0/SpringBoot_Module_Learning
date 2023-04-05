package com.yz.springboot_mvc.controller;

import com.yz.springboot_mvc.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @GetMapping("list")
    public List<User> list(){
        List<User> users = new ArrayList<User>();
        User user1 = new User();
        user1.setUserId(1);
        user1.setUserName("John");
        User user2 = new User();
        user2.setUserId(2);
        user2.setUserName("张三");
        users.add(user1);
        users.add(user2);

        return users;
    }
}
