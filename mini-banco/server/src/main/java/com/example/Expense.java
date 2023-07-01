package com.example;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.example.Transaction;

@JsonPropertyOrder({"amount", "description"})
public class Expense extends Transaction {

    public Expense(double amount, String description) {
        super(amount, description);
    }
}
