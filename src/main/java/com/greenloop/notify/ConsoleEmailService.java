package com.greenloop.notify;

public class ConsoleEmailService implements Observer {
    @Override
    public void onEvent(EventType type, String message) {
        // In production you’d send via SMTP (Jakarta Mail).
        System.out.println("[EMAIL NOTIFY] " + type + " -> " + message);
    }
}
