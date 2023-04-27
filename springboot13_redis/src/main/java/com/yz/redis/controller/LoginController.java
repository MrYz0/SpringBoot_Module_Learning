package com.yz.redis.controller;

import com.yz.redis.pojo.dto.LoginFormDTO;
import com.yz.redis.service.UserService;
import com.yz.redis.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("code")
    public Result code(@RequestParam("phone") String phone){
        // TODO 发送短信验证码并保存验证码
        return userService.sendCode(phone);
    }

    @PostMapping("login")
    public Result login(@RequestBody LoginFormDTO loginFormDTO){

        return userService.login(loginFormDTO);
    }
}
