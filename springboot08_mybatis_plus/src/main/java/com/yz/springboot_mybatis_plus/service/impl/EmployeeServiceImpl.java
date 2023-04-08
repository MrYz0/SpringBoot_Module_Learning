package com.yz.springboot_mybatis_plus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yz.springboot_mybatis_plus.domain.Employee;
import com.yz.springboot_mybatis_plus.service.EmployeeService;
import com.yz.springboot_mybatis_plus.mapper.EmployeeMapper;
import org.springframework.stereotype.Service;

/**
* @author yangzhou
* @description 针对表【t_employee】的数据库操作Service实现
* @createDate 2023-04-08 15:46:19
*/
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee>
    implements EmployeeService{

}




