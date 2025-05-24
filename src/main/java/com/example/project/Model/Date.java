package com.example.project.Model;

import java.time.LocalDate;

public class Date {
    private int day;
    private int month;
    private int year;

    public Date() {
    }

    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public static Date valueOf(LocalDate localDate) {
        return new Date(localDate.getDayOfMonth(), localDate.getMonthValue(), localDate.getYear());
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    public String toString(){
        String s = "";
        s+=year + "-";
        if(month < 10){
            s+="0" + month + "-";
        }else{
            s+=month + "-";
        }
        if(day < 10){
            s+="0" + day;
        }else{
            s+=day;
        }
        return s;
    }

}
