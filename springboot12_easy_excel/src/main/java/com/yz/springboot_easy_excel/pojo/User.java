package com.yz.springboot_easy_excel.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class User  implements Serializable {
    private static final long serialVersionUID = -75075031034829113L;


    private Integer id;

    private String name;

    private String sex;

    private Date createTime;

}
