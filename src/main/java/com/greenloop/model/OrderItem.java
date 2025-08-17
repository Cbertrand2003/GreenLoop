package com.greenloop.model;

import java.math.BigDecimal;

public class OrderItem {
    private int id;
    private int orderId;
    private int productId;
    private String productName;
    private BigDecimal price;
    private int quantity;

    // getters/setters
    public int getId(){return id;}
    public void setId(int id){this.id=id;}
    public int getOrderId(){return orderId;}
    public void setOrderId(int orderId){this.orderId=orderId;}
    public int getProductId(){return productId;}
    public void setProductId(int productId){this.productId=productId;}
    public String getProductName(){return productName;}
    public void setProductName(String productName){this.productName=productName;}
    public BigDecimal getPrice(){return price;}
    public void setPrice(BigDecimal price){this.price=price;}
    public int getQuantity(){return quantity;}
    public void setQuantity(int quantity){this.quantity=quantity;}
}
