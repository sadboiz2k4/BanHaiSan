package com.example.project.Model;

public class AddressUser {
    private int addressUserID;
    private String fullNameReceiver;
    private String phoneReceiver;
    private AddressTK address;
    private String addressType;
    private boolean isPrimary;

    public AddressUser() {
    }

    public AddressUser(int addressUserID, String fullNameReceiver, String phoneReceiver, AddressTK address, String addressType, boolean isPrimary) {
        this.addressUserID = addressUserID;
        this.fullNameReceiver = fullNameReceiver;
        this.phoneReceiver = phoneReceiver;
        this.address = address;
        this.addressType = addressType;
        this.isPrimary = isPrimary;
    }

    public int getAddressUserID() {
        return addressUserID;
    }

    public void setAddressUserID(int addressUserID) {
        this.addressUserID = addressUserID;
    }

    public String getFullNameReceiver() {
        return fullNameReceiver;
    }

    public void setFullNameReceiver(String fullNameReceiver) {
        this.fullNameReceiver = fullNameReceiver;
    }

    public String getPhoneReceiver() {
        return phoneReceiver;
    }

    public void setPhoneReceiver(String phoneReceiver) {
        this.phoneReceiver = phoneReceiver;
    }

    public AddressTK getAddress() {
        return address;
    }

    public void setAddress(AddressTK address) {
        this.address = address;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public boolean isPrimary() {
        return isPrimary;
    }

    public void setPrimary(boolean primary) {
        isPrimary = primary;
    }

    @Override
    public String toString() {
        return "AddressUser{" +
                "addressUserID=" + addressUserID +
                ", fullNameReceiver='" + fullNameReceiver + '\'' +
                ", phoneReceiver='" + phoneReceiver + '\'' +
                ", address=" + address +
                ", addressType='" + addressType + '\'' +
                ", isPrimary=" + isPrimary +
                '}';
    }
}
