package com.yz.springboot_exception.controller;


import com.yz.springboot_exception.config.exception.CustomException;
import com.yz.springboot_exception.domain.User;
import com.yz.springboot_exception.service.UserService;
import com.yz.springboot_exception.utils.ResponseEnum;
import com.yz.springboot_exception.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("list")
    public Result list(){

        try {
            int i = 10/0;
        }catch (Exception e){
            throw new CustomException(ResponseEnum.SERVICE_ERROR);
        }
        List<User> list = userService.list();
        return Result.ok(list);
    }

    // 增
    @PostMapping
    public Result save(@RequestBody User user){
        boolean result = userService.save(user);
        if (result) {
            return Result.ok();
        }else{
            return Result.fail();
        }
    }
    // 删
    @DeleteMapping("{id}")
    public Result delete(@PathVariable Integer id){
        boolean result = userService.removeById(id);
        if (result) {
            return Result.ok();
        }else{
            return Result.fail();
        }
    }
    // 改
    @PutMapping
    public Result edit(@RequestBody User user){
        boolean result = userService.updateById(user);
        if (result) {
            return Result.ok();
        }else{
            return Result.fail();
        }
    }
    // 查
    @GetMapping("{id}")
    public Result getUSerById(@PathVariable Integer id){
        User user = userService.getById(id);
        return Result.ok(user);
    }
}
