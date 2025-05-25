package com.example.project.Model;

import java.sql.Date;

public class Customer {
    private int customerID;
    private int userID;
    private String firstname;
    private String lastname;
    private Date dateOfBirth;
    private String gender;
    private User user;

    public Customer() {
    }
    
    // Constructor with all fields
    public Customer(int customerID, int userID, String firstname, String lastname, Date dateOfBirth, String gender, User user) {
        this.customerID = customerID;
        this.userID = userID;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.user = user;
    }
    
    // Getters and Setters
    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String isGender() {
        return gender;
    }
    public String getGender() { return gender; }
    public void setGender(String gender) {
        this.gender = gender;
    }


    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    @Override
    public String toString() {
        return "Customer{" + 
               "customerID=" + customerID + 
               ", userID=" + userID + 
               ", firstname='" + firstname +
                ",lastname='" + lastname +'\'' +
               ", dateOfBirth=" + dateOfBirth + 
               ", gender=" + gender + 
               '}';
    }
}