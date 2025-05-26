package com.example.project.Model;

public class Payment {
    private int paymentID;
    private String paymentName;
    private String imgURL;
    private String imgAltText;
    private String sponsorName;


    public Payment() {
    }

    public Payment(int paymentID, String paymentName, String imgURL, String imgAltText, String sponsorName) {
        this.paymentID = paymentID;
        this.paymentName = paymentName;
        this.imgURL = imgURL;
        this.imgAltText = imgAltText;
        this.sponsorName = sponsorName;
    }

    public int getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public String getPaymentName() {
        return paymentName;
    }

    public void setPaymentName(String paymentName) {
        this.paymentName = paymentName;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public String getImgAltText() {
        return imgAltText;
    }

    public void setImgAltText(String imgAltText) {
        this.imgAltText = imgAltText;
    }

    public String getSponsorName() {
        return sponsorName;
    }

    public void setSponsorName(String sponsorName) {
        this.sponsorName = sponsorName;
    }
}
