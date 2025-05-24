package com.example.project.Model;

public class AddressTK {
    private int addressId;
    private String street;
    private String WardOrCommune;
    private String District;
    private String CityOrProvince;

    public AddressTK() {
    }

    public AddressTK(int addressId, String street, String wardOrCommune, String district, String cityOrProvince) {
        this.addressId = addressId;
        this.street = street;
        WardOrCommune = wardOrCommune;
        District = district;
        CityOrProvince = cityOrProvince;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getWardOrCommune() {
        return WardOrCommune;
    }

    public void setWardOrCommune(String wardOrCommune) {
        WardOrCommune = wardOrCommune;
    }

    public String getDistrict() {
        return District;
    }

    public void setDistrict(String district) {
        District = district;
    }

    public String getCityOrProvince() {
        return CityOrProvince;
    }

    public void setCityOrProvince(String cityOrProvince) {
        CityOrProvince = cityOrProvince;
    }
}
