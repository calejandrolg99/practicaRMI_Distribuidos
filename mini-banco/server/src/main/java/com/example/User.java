package com.example;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.ArrayList;
import java.util.List;
import com.example.Account;

//apply jackson decorator

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
    ///implement getAccount

    public String getAccount() {
        return documentId;
    }

}
