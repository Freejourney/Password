package com.example.admin.password;

public class Pwd {

    private int id;
    private String appname;
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Pwd{" +
                "id=" + id +
                ", appname='" + appname + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}