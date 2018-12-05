package com.example.admin.password;

public class Pwd {

    private String id;
    private String appname;
    private String password;
    private int pwdcolor;       // 下一步：将颜色加入数据库

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPwdcolor() {
        return pwdcolor;
    }

    public void setPwdcolor(int pwdcolor) {
        this.pwdcolor = pwdcolor;
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