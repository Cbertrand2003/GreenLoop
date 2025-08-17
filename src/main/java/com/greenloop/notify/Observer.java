package com.greenloop.notify;

public interface Observer {
    void onEvent(EventType type, String message);
}
