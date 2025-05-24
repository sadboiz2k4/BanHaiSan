package com.example.project.Model;

public class Salary {
    private int id;
    private int employeeId;
    private String employeeName;
    private String gender;
    private String position;
    private int totalSalary;

    public Salary(int id, int employeeId, String employeeName, String gender, String position, int totalSalary) {
        this.id = id;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.gender = gender;
        this.position = position;
        this.totalSalary = totalSalary;
    }

    public Salary() {
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getEmployeeId() { return employeeId; }
    public void setEmployeeId(int employeeId) { this.employeeId = employeeId; }

    public String getEmployeeName() { return employeeName; }
    public void setEmployeeName(String employeeName) { this.employeeName = employeeName; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    public int getTotalSalary() { return totalSalary; }
    public void setTotalSalary(int totalSalary) { this.totalSalary = totalSalary; }

    // Fixed the getter method name to match JavaBean convention
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}