package com.example.project.Model;

import java.time.LocalDateTime;

public class Time {
    private int seconds;
    private int minutes;
    private int hours;
    private int day;
    private int month;
    private int year;

    public Time(int seconds, int minutes, int hours, int day, int month, int year) {
        this.seconds = seconds;
        this.minutes = minutes;
        this.hours = hours;
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public Time() {
    }

    public Time(LocalDateTime now) {
    }

    public int getSeconds() {
        return seconds;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getHours() {
        return hours;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public int [] timeMinus(Time time) {
        int[] timeMinus = new int[3];
        int day = this.day - time.day;
        int month = this.month - time.month;
        int year = this.year - time.year;
        if (day < 0) {
            month--;
            day += 30;
        }
        if (month < 0) {
            year--;
            month += 12;
        }
        int hours = this.hours - time.hours;
        int minutes = this.minutes - time.minutes;
        int seconds = this.seconds - time.seconds;
        if (seconds < 0) {
            minutes--;
            seconds += 60;
        }
        if (minutes < 0) {
            hours--;
            minutes += 60;
        }
        if(hours<0 && day>0){
            day--;
            hours+=24;
        }
        timeMinus[2] = seconds;
        timeMinus[1] = minutes;
        timeMinus[0] = hours;
        return timeMinus;
    }
    public int timeMinusToSecond(Time time) {
        int day = this.day - time.day;
        int month = this.month - time.month;
        int year = this.year - time.year;
        if (day < 0) {
            month--;
            day += 30;
        }
        if (month < 0) {
            year--;
            month += 12;
        }
        int hours = this.hours - time.hours;
        int minutes = this.minutes - time.minutes;
        int seconds = this.seconds - time.seconds;
        if (seconds < 0) {
            minutes--;
            seconds += 60;
        }
        if (minutes < 0) {
            hours--;
            minutes += 60;
        }
        if(hours<0 && day>0){
            day--;
            hours+=24;
        }
        return seconds + minutes * 60 + hours * 3600 + day * 86400 + month * 2592000 + year * 31104000;
    }
    public static Time convertLocalDatetimeToTime(LocalDateTime localDateTime){
        return new Time(localDateTime.getSecond(), localDateTime.getMinute(), localDateTime.getHour(),
                localDateTime.getDayOfMonth(), localDateTime.getMonthValue(), localDateTime.getYear());
    }

    @Override
    public String toString() {
        return "Time{" +
                "seconds=" + seconds +
                ", minutes=" + minutes +
                ", hours=" + hours +
                ", day=" + day +
                ", month=" + month +
                ", year=" + year +
                '}';
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }
    public String toDateString(){
        return day + "/" + month + "/" + year;
    }
    public static void main(String[] args) {
        Time time1 = new Time(10, 10, 10, 10, 10, 2021);
        Time time2 = new Time(0, 12, 8, 10, 10, 2021);
        int[] timeMinus = time1.timeMinus(time2);
        System.out.println(timeMinus[0] + " " + timeMinus[1] + " " + timeMinus[2]);
    }
}
