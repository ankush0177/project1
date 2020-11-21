package com.example.databaseproject.models;

public class Account {

    String email;
    String pwd;

    public Account(String email, String pwd) {
        this.email = email;
        this.pwd = pwd;
    }

    public String getusername() {
        return email;
    }

    public void setusername(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
