package com.greenloop.model;

import java.time.LocalDate;

public class Review {
    private int id;
    private int productId;
    private int customerId;
    private int rating; // 1..5
    private String comment;
    private LocalDate date;

    // getters/setters
    public int getId(){return id;}
    public void setId(int id){this.id=id;}
    public int getProductId(){return productId;}
    public void setProductId(int productId){this.productId=productId;}
    public int getCustomerId(){return customerId;}
    public void setCustomerId(int customerId){this.customerId=customerId;}
    public int getRating(){return rating;}
    public void setRating(int rating){this.rating=rating;}
    public String getComment(){return comment;}
    public void setComment(String comment){this.comment=comment;}
    public LocalDate getDate(){return date;}
    public void setDate(LocalDate date){this.date=date;}
}
