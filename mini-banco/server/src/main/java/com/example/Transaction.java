package com.example;
import java.time.LocalDateTime;
import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"amount", "description"})
public abstract class Transaction {
    protected String id;
    protected double amount;
    protected String description;
    protected LocalDateTime date;

    public Transaction(double amount, String description) {
        this.id = UUID.randomUUID().toString();
        this.amount = amount;
        this.description = description;
        this.date = LocalDateTime.now();
    }

    public String getId() {
    return this.id;
    }

    public double getAmount() {
        return this.amount;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public LocalDateTime getDate() {
        return this.date;
    }

}
