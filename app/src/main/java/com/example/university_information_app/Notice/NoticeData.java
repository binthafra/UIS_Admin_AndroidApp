package com.example.university_information_app.Notice;

public class NoticeData {
     String tittle ,image, date, time, key;

    public NoticeData() {
    }

    public NoticeData(String tittle, String image, String date, String time, String key) {
        this.tittle = tittle;
        this.image = image;
        this.date = date;
        this.time = time;
        this.key = key;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
