package com.yz.springboot07_mybatis.service.impl;

import com.yz.springboot07_mybatis.domain.Dept;
import com.yz.springboot07_mybatis.mapper.DeptMapper;
import com.yz.springboot07_mybatis.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;
    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }
}
