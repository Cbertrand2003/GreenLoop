package com.greenloop.notify;

import java.util.*;

public class Subject {
    private final List<Observer> observers = new ArrayList<>();
    public void subscribe(Observer o){ observers.add(o); }
    public void unsubscribe(Observer o){ observers.remove(o); }
    public void notifyAll(EventType t, String msg){ observers.forEach(o -> o.onEvent(t, msg)); }
}
