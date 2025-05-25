package com.example.project.Model;

import java.util.Date;

public class Review {
    private int reviewID;
    private int productID;
    private double rating;
    private String reviewContent;
    private int helpfulCount;
    private int reportedCount;
    private boolean isRead;
    private String response;
    private Date createDate;
    private Date lastUpdateDate;

    // Constructors
    public Review() {}

    @Override
    public String toString() {
        return "Review{" +
                "reviewID=" + reviewID +
                ", productID=" + productID +
                ", rating=" + rating +
                ", reviewContent='" + reviewContent + '\'' +
                ", helpfulCount=" + helpfulCount +
                ", reportedCount=" + reportedCount +
                ", isRead=" + isRead +
                ", response='" + response + '\'' +
                ", createDate=" + createDate +
                ", lastUpdateDate=" + lastUpdateDate +
                '}';
    }

    // Getters and Setters
    public int getReviewID() { return reviewID; }
    public void setReviewID(int reviewID) { this.reviewID = reviewID; }

    public int getProductID() { return productID; }
    public void setProductID(int productID) { this.productID = productID; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }

    public String getReviewContent() { return reviewContent; }
    public void setReviewContent(String reviewContent) { this.reviewContent = reviewContent; }

    public int getHelpfulCount() { return helpfulCount; }
    public void setHelpfulCount(int helpfulCount) { this.helpfulCount = helpfulCount; }

    public int getReportedCount() { return reportedCount; }
    public void setReportedCount(int reportedCount) { this.reportedCount = reportedCount; }

    public boolean isRead() { return isRead; }
    public void setRead(boolean read) { isRead = read; }

    public String getResponse() { return response; }
    public void setResponse(String response) { this.response = response; }

    public Date getCreateDate() { return createDate; }
    public void setCreateDate(Date createDate) { this.createDate = createDate; }

    public Date getLastUpdateDate() { return lastUpdateDate; }
    public void setLastUpdateDate(Date lastUpdateDate) { this.lastUpdateDate = lastUpdateDate; }
}
