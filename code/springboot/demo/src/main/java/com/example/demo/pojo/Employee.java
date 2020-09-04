package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private String name;
    private String email;
    private Integer id;
    private Integer gender;

    private Department department;
    private Date birth;
}
