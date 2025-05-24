package com.example.project.Model;

import java.io.Serializable;
import java.sql.Date;

public class Employee implements Serializable {
    private int employeeID;
    private int OSID;
    private String firstName;
    private String lastName;
    private String position;
    private String email;
    private String phoneNumber;
    private String gender;
    private Date birthDate;
    private String address;
    private int totalSalary;// Combined address string
    // Default constructor
    public Employee() {}

    public int getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(int totalSalary) {
        this.totalSalary = totalSalary;
    }

    public Employee(int totalSalary) {
        this.totalSalary = totalSalary;
    }

    public Employee(int employeeID, int OSID, String firstName, String lastName, String position, String email, String phoneNumber, String gender, Date birthDate, String address) {
        this.employeeID = employeeID;
        this.OSID = OSID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.birthDate = birthDate;
        this.address = address;

    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public int getOSID() {
        return OSID;
    }

    public void setOSID(int OSID) {
        this.OSID = OSID;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    @Override
    public String toString() {
        return "Employee{" +
                "employeeID=" + employeeID +
                ", OSID=" + OSID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", position='" + position + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", gender='" + gender + '\'' +
                ", birthDate=" + birthDate +
                ", address='" + address + '\'' +
                '}';
    }
}