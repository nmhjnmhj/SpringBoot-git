package com.qhit.springbootmybatisdemo1.beans;

public class QuanXian {
    private Integer id;
    private String name;

    public QuanXian() {
    }

    @Override
    public String toString() {
        return "quanxian{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
