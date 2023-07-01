package com.example;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.example.Transaction;
import com.example.Account;

@JsonPropertyOrder({"amount", "description"})
public class Transference extends Transaction {
    private Account destinationAccount;

    public Transference(double amount, String description, Account destinationAccount) {
        super(amount, description);
        this.destinationAccount = destinationAccount;
    }
}
