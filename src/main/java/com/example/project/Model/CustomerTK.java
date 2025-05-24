package com.example.project.Model;


public class CustomerTK {
    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private boolean gender;
    private Date birthDay;

    public CustomerTK() {
    }

    public CustomerTK(int userId, String firstName, String lastName, String email, String phone, boolean gender, Date birthDay) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.birthDay = birthDay;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean getGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String toString() {
        return userId + " " + firstName + " " + lastName + " " + email + " " + phone + " " + gender + " " + birthDay.toString();
    }
}
