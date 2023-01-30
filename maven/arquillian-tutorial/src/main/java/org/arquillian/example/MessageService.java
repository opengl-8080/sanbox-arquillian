package org.arquillian.example;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MessageService {
    public String format(String message) {
        return String.format("★★★ %s ★★★", message);
    }
}
