package com.example.paymentreminders.Model;

public class TransactionHistory {

    private String Day, requester, title, date, time, status, detail;
    private int timeAgo, price;

    public TransactionHistory(String day, String requester, String title, String date, String time, String status, String detail, int timeAgo, int price) {
        Day = day;
        this.requester = requester;
        this.title = title;
        this.date = date;
        this.time = time;
        this.status = status;
        this.detail = detail;
        this.timeAgo = timeAgo;
        this.price = price;
    }

    public String getDay() {
        return Day;
    }

    public void setDay(String day) {
        Day = day;
    }

    public String getRequester() {
        return requester;
    }

    public void setRequester(String requester) {
        this.requester = requester;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getTimeAgo() {
        return timeAgo;
    }

    public void setTimeAgo(int timeAgo) {
        this.timeAgo = timeAgo;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
