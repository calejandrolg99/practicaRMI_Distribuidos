package com.example;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("my-running-server", 1099);
            BankInterface bank = (BankInterface) registry.lookup("Bank");
            System.out.println(bank.createAccount("123", "John Doe", "johndoe", "password"));
            System.out.println(bank.deposit("123", 1000));
            // Call other methods
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
