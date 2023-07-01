package com.example;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.example.Transaction;

@JsonPropertyOrder({"amount", "description"})
public class Deposit extends Transaction {

    public Deposit(double amount, String description) {
        super(amount, description);
    }
}
