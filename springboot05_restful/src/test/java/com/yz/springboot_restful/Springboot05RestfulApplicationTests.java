package com.yz.springboot_restful;

import com.yz.springboot_restful.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Springboot05RestfulApplicationTests {

	@Autowired
	private UserMapper userMapper;

	@Test
	void contextLoads() {
		userMapper.selectList(null);
	}

}
