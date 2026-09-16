package com.example.Ecommerce_Site.Model;

import org.springframework.stereotype.Component;


import java.time.LocalDateTime;
import java.time.LocalTime;


public class ErrorResponse {
    private String message;
    private String details;
    private LocalDateTime timestamp;

   public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public ErrorResponse(String message,String details,LocalDateTime timestamp) {
        this.message = message;
        this.details = details;
        this.timestamp = timestamp;
    }

}
