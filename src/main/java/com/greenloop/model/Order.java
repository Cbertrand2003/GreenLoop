package com.greenloop.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private int id;
    private int customerId;
    private LocalDateTime createdAt;
    private String status; // PENDING, PAID, SHIPPED, DELIVERED
    private String invoicePath;
    private List<OrderItem> items;

    public int getId(){return id;}
    public void setId(int id){this.id=id;}
    public int getCustomerId(){return customerId;}
    public void setCustomerId(int customerId){this.customerId=customerId;}
    public LocalDateTime getCreatedAt(){return createdAt;}
    public void setCreatedAt(LocalDateTime createdAt){this.createdAt=createdAt;}
    public String getStatus(){return status;}
    public void setStatus(String status){this.status=status;}
    public String getInvoicePath(){return invoicePath;}
    public void setInvoicePath(String invoicePath){this.invoicePath=invoicePath;}
    public List<OrderItem> getItems(){return items;}
    public void setItems(List<OrderItem> items){this.items=items;}

    public BigDecimal getTotal(){
        return items.stream()
            .map(i -> i.getPrice().multiply(new java.math.BigDecimal(i.getQuantity())))
            .reduce(java.math.BigDecimal.ZERO, java.math.BigDecimal::add);
    }
}
