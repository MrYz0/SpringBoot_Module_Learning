package com.yz.springboot_mybatis_plus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yz.springboot_mybatis_plus.domain.Dept;
import com.yz.springboot_mybatis_plus.service.DeptService;
import com.yz.springboot_mybatis_plus.mapper.DeptMapper;
import org.springframework.stereotype.Service;

/**
* @author yangzhou
* @description 针对表【t_dept】的数据库操作Service实现
* @createDate 2023-04-08 15:46:24
*/
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept>
    implements DeptService{

}




