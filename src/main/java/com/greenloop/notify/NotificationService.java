package com.greenloop.notify;

public class NotificationService extends Subject {
    public void orderPlaced(String info){ notifyAll(EventType.ORDER_PLACED, info); }
    public void lowStock(String info){ notifyAll(EventType.LOW_STOCK, info); }
}
