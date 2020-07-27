package com.example.paymentreminders.Model;

public class Users {
    private String username;
    private String phoneNo;
    private String password;
    private String email;


    public Users(String username, String phoneNo, String password, String email) {
        this.username = username;
        this.phoneNo = phoneNo;
        this.password = password;
        this.email = email;
    }

    public Users() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
