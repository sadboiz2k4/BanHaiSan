package com.example.project.Model;

import java.util.Date;

public class Address {
    private int addressID;
    private String street;
    private String wardOrCommune;
    private String district;
    private String provinceOrCity;
    private String fullnameReceiver;
    private String numberPhoneReceiver;
    private String addressType;
    private boolean isPrimary;
    private Date createDate;
    private Date lastUpdateDate;

    // Getters and Setters
    public int getAddressID() { return addressID; }
    public void setAddressID(int addressID) { this.addressID = addressID; }
    
    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }
    
    public String getWardOrCommune() { return wardOrCommune; }
    public void setWardOrCommune(String wardOrCommune) { 
        this.wardOrCommune = wardOrCommune; 
    }
    
    public String getDistrict() { return district; }
    public void setDistrict(String district) { this.district = district; }
    
    public String getProvinceOrCity() { return provinceOrCity; }
    public void setProvinceOrCity(String provinceOrCity) { 
        this.provinceOrCity = provinceOrCity; 
    }
    
    public String getFullnameReceiver() { return fullnameReceiver; }
    public void setFullnameReceiver(String fullnameReceiver) { 
        this.fullnameReceiver = fullnameReceiver; 
    }
    
    public String getNumberPhoneReceiver() { return numberPhoneReceiver; }
    public void setNumberPhoneReceiver(String numberPhoneReceiver) { 
        this.numberPhoneReceiver = numberPhoneReceiver; 
    }
    
    public String getAddressType() { return addressType; }
    public void setAddressType(String addressType) { 
        this.addressType = addressType; 
    }
    
    public boolean isPrimary() { return isPrimary; }
    public void setPrimary(boolean primary) { isPrimary = primary; }
    
    public Date getCreateDate() { return createDate; }
    public void setCreateDate(Date createDate) { this.createDate = createDate; }
    
    public Date getLastUpdateDate() { return lastUpdateDate; }
    public void setLastUpdateDate(Date lastUpdateDate) { 
        this.lastUpdateDate = lastUpdateDate; 
    }
}