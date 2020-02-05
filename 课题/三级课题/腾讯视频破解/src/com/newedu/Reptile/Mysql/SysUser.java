package com.newedu.Reptile.Mysql;



public class SysUser {
    private  String username;
    private  String pwd;

    public String[] getall() {
        String[] all={username,pwd};
        return all;
    }

    public String getUsername() {
        return username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public SysUser() {
    }

    public SysUser(String username, String age) {
        this.username = username;
        this.pwd = age;
    }

    @Override
    public String toString() {
        return "SysUser{" +
                "username='" + username + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}
