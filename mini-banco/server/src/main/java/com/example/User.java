package com.example;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder({"documentId", "name", "username", "password", "accounts"})
public class User {
    private String documentId;
    private String name;
    private String username;
    private String password;
    private List<Account> accounts;

    public User(String documentId, String name, String username, String password) {
        this.documentId = documentId;
        this.name = name;
        this.username = username;
        this.password = password; // Consider using encryption here
        this.accounts = new ArrayList<>();
    }

    public Account getAccount(String accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    // Añade los demás getters y setters según sean necesarios

    public String getDocumentId() {
        return this.documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
