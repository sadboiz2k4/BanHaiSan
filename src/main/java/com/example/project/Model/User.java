package com.example.project.Model;

import java.util.Date;
import java.util.List;

public class User {
    private int userID;
    private String email;
    private String phoneNumber;
    private String username;
    private String password;
    private boolean isAdmin;
    private boolean isBlock;
    private String status;
    private int numberOfFailedLogin;
    private int rank;
    private int point;
    private Date createDate;
    private Date lastUpdateDate;
    private Date lastLoginDate;
    private List<Address> addresses;
    private String provider;
    private String providerId;
    private String accessToken;

    public User(int userID, String email, String phoneNumber, String username, String password, boolean isAdmin, boolean isBlock, String status, int numberOfFailedLogin, int rank, int point, Date createDate, Date lastUpdateDate, Date lastLoginDate, List<Address> addresses, String provider, String providerId, String accessToken) {
        this.userID = userID;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
        this.isBlock = isBlock;
        this.status = status;
        this.numberOfFailedLogin = numberOfFailedLogin;
        this.rank = rank;
        this.point = point;
        this.createDate = createDate;
        this.lastUpdateDate = lastUpdateDate;
        this.lastLoginDate = lastLoginDate;
        this.addresses = addresses;
        this.provider = provider;
        this.providerId = providerId;
        this.accessToken = accessToken;
    }

    public User() {
    }


    // Getters and Setters
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public boolean isBlock() {
        return isBlock;
    }

    public void setBlock(boolean block) {
        isBlock = block;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getNumberOfFailedLogin() {
        return numberOfFailedLogin;
    }

    public void setNumberOfFailedLogin(int numberOfFailedLogin) {
        this.numberOfFailedLogin = numberOfFailedLogin;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }


    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}