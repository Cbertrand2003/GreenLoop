package com.greenloop.model;

import java.math.BigDecimal;
import java.util.*;

public class ShoppingCart {
    private final Map<Integer, CartItem> items = new LinkedHashMap<>();

    public void add(Product p, int qty){
        CartItem existing = items.get(p.getId());
        if(existing == null) items.put(p.getId(), new CartItem(p, qty));
        else existing.setQuantity(existing.getQuantity()+qty);
    }
    public void update(int productId, int qty){
        CartItem ci = items.get(productId);
        if(ci != null){
            if(qty <= 0) items.remove(productId);
            else ci.setQuantity(qty);
        }
    }
    public void remove(int productId){ items.remove(productId); }
    public Collection<CartItem> getItems(){ return items.values(); }

    public BigDecimal total(){
        return items.values().stream()
                .map(CartItem::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    public boolean isEmpty(){ return items.isEmpty(); }
    public void clear(){ items.clear(); }
}
