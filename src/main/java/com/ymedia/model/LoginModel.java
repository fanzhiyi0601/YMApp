package com.ymedia.model;

public class LoginModel {


    private String username;
    private String password;
    private boolean isAgree;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAgree() { return isAgree; }

    public void setAgree(boolean agree) { isAgree = agree; }
}
