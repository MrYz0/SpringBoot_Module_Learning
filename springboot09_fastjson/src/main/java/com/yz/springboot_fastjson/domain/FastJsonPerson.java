package com.yz.springboot_fastjson.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FastJsonPerson {
    private String name;
    private Integer age;
    private String other;
    private List<Card> cards;
}
