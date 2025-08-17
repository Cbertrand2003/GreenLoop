package com.greenloop.model;

import java.math.BigDecimal;

public class Product {
    private int id;
    private int vendorId;
    private String name;
    private String description;
    private BigDecimal price;
    private int stock;
    private String sustainabilityCert; // e.g., EnergyStar, FairTrade

    public Product(){}
    public Product(int id, int vendorId, String name, String description, BigDecimal price, int stock, String cert){
        this.id=id; this.vendorId=vendorId; this.name=name; this.description=description;
        this.price=price; this.stock=stock; this.sustainabilityCert=cert;
    }
    // getters/setters
    public int getId(){return id;}
    public void setId(int id){this.id=id;}
    public int getVendorId(){return vendorId;}
    public void setVendorId(int vendorId){this.vendorId=vendorId;}
    public String getName(){return name;}
    public void setName(String name){this.name=name;}
    public String getDescription(){return description;}
    public void setDescription(String description){this.description=description;}
    public BigDecimal getPrice(){return price;}
    public void setPrice(BigDecimal price){this.price=price;}
    public int getStock(){return stock;}
    public void setStock(int stock){this.stock=stock;}
    public String getSustainabilityCert(){return sustainabilityCert;}
    public void setSustainabilityCert(String sustainabilityCert){this.sustainabilityCert=sustainabilityCert;}
}
