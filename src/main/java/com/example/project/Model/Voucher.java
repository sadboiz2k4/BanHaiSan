package com.example.project.Model;

public class Voucher {
    private int voucherId;
    private String voucherName;
    private String description;
    private String voucherCode;
    private String discountType;
    private int discountValue;
    private int maxDiscount;
    private int minOrder;
    private Time startDate;
    private Time endDate;

    public Voucher() {
    }

    public Voucher(int voucherId, String voucherName, String description, String voucherCode,
                   String discountType, int discountValue, int maxDiscount, int minOrder,
                   Time startDate, Time endDate) {
        this.voucherId = voucherId;
        this.voucherName = voucherName;
        this.description = description;
        this.voucherCode = voucherCode;
        this.discountType = discountType;
        this.discountValue = discountValue;
        this.maxDiscount = maxDiscount;
        this.minOrder = minOrder;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(int voucherId) {
        this.voucherId = voucherId;
    }

    public String getVoucherName() {
        return voucherName;
    }

    public void setVoucherName(String voucherName) {
        this.voucherName = voucherName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVoucherCode() {
        return voucherCode;
    }

    public void setVoucherCode(String voucherCode) {
        this.voucherCode = voucherCode;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public int getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(int discountValue) {
        this.discountValue = discountValue;
    }

    public int getMaxDiscount() {
        return maxDiscount;
    }

    public void setMaxDiscount(int maxDiscount) {
        this.maxDiscount = maxDiscount;
    }

    public int getMinOrder() {
        return minOrder;
    }

    public void setMinOrder(int minOrder) {
        this.minOrder = minOrder;
    }

    public Time getStartDate() {
        return startDate;
    }

    public void setStartDate(Time startDate) {
        this.startDate = startDate;
    }

    public Time getEndDate() {
        return endDate;
    }

    public void setEndDate(Time endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Voucher{" +
                "voucherId=" + voucherId +
                ", voucherName='" + voucherName + '\'' +
                ", description='" + description + '\'' +
                ", voucherCode='" + voucherCode + '\'' +
                ", discountType='" + discountType + '\'' +
                ", discountValue=" + discountValue +
                ", maxDiscount=" + maxDiscount +
                ", minOrder=" + minOrder +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
