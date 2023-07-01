package com.example;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.ArrayList;
import java.util.List;
import com.example.Transaction;
import com.example.Deposit;

@JsonPropertyOrder({"accountNumber", "balance", "transactions"})
public class Account {
    private String accountNumber;
    private double balance;
    private List<Transaction> transactions;

    public Account(String accountNumber, double initialDeposit) {
        this.accountNumber = accountNumber;
        this.balance = initialDeposit;
        this.transactions = new ArrayList<>();
        this.transactions.add(new Deposit(initialDeposit, "Depósito inicial"));
    }

    public Account getAccount() {
        return this;
    }

    // Getters, Setters and other methods like deposit, withdraw, etc...
}
