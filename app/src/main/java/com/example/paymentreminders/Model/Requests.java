package com.example.paymentreminders.Model;

public class Requests {
    private String borrower_name;
    private String borrower_num;
    private String giver_num;
    private String giver_name;
    private String amount;
    private String date;
    private String time;
    private String description;
    private String title;
    private String status;

    public String getPush_id() {
        return push_id;
    }

    public void setPush_id(String push_id) {
        this.push_id = push_id;
    }

    private String push_id;


    public Requests(String push_id, String borrower, String borrower_num, String giver_name, String giver_num, String amount, String date, String time, String description, String title, String status) {
        this.borrower_name = borrower;
        this.giver_name = giver_name;
        this.borrower_num = borrower_num;
        this.giver_num = giver_num;
        this.amount = amount;
        this.date = date;
        this.time = time;
        this.description = description;
        this.title = title;
        this.status = status;
        this.push_id = push_id;
    }

    public Requests() {
    }

    public String getBorrower_name() {
        return borrower_name;
    }

    public void setBorrower_name(String borrower_name) {
        this.borrower_name = borrower_name;
    }

    public String getBorrower_num() {
        return borrower_num;
    }

    public void setBorrower_num(String borrower_num) {
        this.borrower_num = borrower_num;
    }

    public String getGiver_num() {
        return giver_num;
    }

    public void setGiver_num(String giver_num) {
        this.giver_num = giver_num;
    }

    public String getGiver_name() {
        return giver_name;
    }

    public void setGiver_name(String giver_name) {
        this.giver_name = giver_name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
