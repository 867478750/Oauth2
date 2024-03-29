package org.nlb.security.entity;

import java.io.Serializable;

public class Student implements Serializable {
    private int id;
    private String ext;
    private String password;
    private String name;



    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", ext='" + ext + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
