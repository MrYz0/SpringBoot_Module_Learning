package com.yz.springboot_fastjson.domain;

import com.alibaba.fastjson2.annotation.JSONField;
import com.alibaba.fastjson2.annotation.JSONType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JSONType(orders = {"id","name","age","birth","inSchool"})
public class Student {

    private String id;

    private String name;

    @JSONField(serialize = false)
    private Integer age;

    @JSONField(format = "YYYY-MM-dd")
    private Date birth;

    private Boolean inSchool;
}