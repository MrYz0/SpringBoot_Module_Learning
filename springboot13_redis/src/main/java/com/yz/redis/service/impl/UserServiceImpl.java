package com.yz.redis.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yz.redis.pojo.User;
import com.yz.redis.pojo.dto.LoginFormDTO;
import com.yz.redis.pojo.dto.UserDTO;
import com.yz.redis.service.UserService;
import com.yz.redis.mapper.UserMapper;
import com.yz.redis.utils.RegexUtils;
import com.yz.redis.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.yz.redis.utils.RedisConstants.*;

/**
* @author yangzhou
* @description 针对表【user】的数据库操作Service实现
* @createDate 2023-04-27 09:47:10
*/
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public Result sendCode(String phone) {
        // 1、验证手机号
        if (RegexUtils.isPhoneInvalid(phone)){
            // 2、如果不符合，返回错误消息
            return Result.fail().message("手机号格式错误");
        }

        // 3、符合，生成验证码(使用Hutool工具生成6为随机数)
        String code = RandomUtil.randomNumbers(6);

        // 4、保存验证码到redis
        // key + code + time
        stringRedisTemplate.opsForValue().set(LOGIN_CODE_KEY + phone , code , LOGIN_CODE_TTL ,TimeUnit.MINUTES);
        // 5、发送验证码(可使用阿里云等第三方发送给用户，此处模拟就用log打印在控制台)
        log.debug("code="+ code);

        // 返回ok
        return Result.ok();
    }

    @Override
    public Result login(LoginFormDTO loginFormDTO) {
        //1、验证手机号
        String phone = loginFormDTO.getPhone();
        if (RegexUtils.isPhoneInvalid(phone)){
            return Result.fail().message("手机号格式错误");
        }
        //2、校验验证码
        String code = loginFormDTO.getCode();
        String cacheCode = stringRedisTemplate.opsForValue().get(LOGIN_CODE_KEY + phone);
        if (cacheCode == null || !cacheCode.equals(code)) {
            //3、不一致，报错
            return Result.fail().message("验证码错误");
        }
        //4、一致，根据手机号查询用户
        User user = query().eq("phone", phone).one();
        //5、判断用户是否存在
        if (user == null) {
            //6、不存在，创建用户
            user = createUserWithPhone(phone);
        }

        //7、保存用户信息到 redis
        //7.1、随机生成token令牌
        String token = UUID.randomUUID().toString(true);

        //7.2、将user对象转为Hash存储
        UserDTO userDTO = BeanUtil.copyProperties(user, UserDTO.class);
        // 对象转为map
        String string = JSON.toJSONString(userDTO);
        //7.3、存储
        String tokenKey = LOGIN_USER_KEY + token;
        stringRedisTemplate.opsForValue().set(tokenKey, string,LOGIN_USER_TTL,TimeUnit.MINUTES);

        String userObject = stringRedisTemplate.opsForValue().get(tokenKey);
        User parsedObject = JSON.parseObject(userObject, User.class);
        return Result.ok(parsedObject);
    }

    private User createUserWithPhone(String phone) {
        User user = new User();
        user.setPhone(phone);
        user.setNickName("user_" + RandomUtil.randomString(10));
        // 保存用户
        save(user);
        return user;
    }

}




