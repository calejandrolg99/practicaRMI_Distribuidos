package com.example;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.example.Transaction;

@JsonPropertyOrder({"amount", "description"})
public class Withdrawal extends Transaction {

    public Withdrawal(double amount, String description) {
        super(amount, description);
    }
}
