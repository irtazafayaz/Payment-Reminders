package com.example.paymentreminders.Model;

public class ContactClass {

    private int id;
    private String name;
    private String phoneNo;
    private int pictureId;


    public ContactClass(int id, String name, String phoneNo, int pictureId) {
        this.id = id;
        this.name = name;
        this.phoneNo = phoneNo;
        this.pictureId = pictureId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public int getPictureId() {
        return pictureId;
    }

    public void setPictureId(int pictureId) {
        this.pictureId = pictureId;
    }
}
