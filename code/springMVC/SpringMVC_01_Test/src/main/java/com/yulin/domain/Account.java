package com.yulin.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Account implements Serializable {
    private String name;
    private String pwd;
    private Double salary;
    private PersonInfo personInfo;
    private List<PersonInfo> list;
    private Map<String,PersonInfo> map;
    private Date date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public PersonInfo getPersonInfo() {
        return personInfo;
    }

    public void setPersonInfo(PersonInfo personInfo) {
        this.personInfo = personInfo;
    }

    public List<PersonInfo> getList() {
        return list;
    }

    public void setList(List<PersonInfo> list) {
        this.list = list;
    }

    public Map<String, PersonInfo> getMap() {
        return map;
    }

    public void setMap(Map<String, PersonInfo> map) {
        this.map = map;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Account{" +
                "name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                ", salary=" + salary +
                ", personInfo=" + personInfo +
                ", list=" + list +
                ", map=" + map +
                ", date=" + date +
                '}';
    }
}
