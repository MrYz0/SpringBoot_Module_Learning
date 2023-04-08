package com.yz.springboot07_mybatis.mapper;

import com.yz.springboot07_mybatis.domain.Dept;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeptMapper {
    List<Dept> list();


}
