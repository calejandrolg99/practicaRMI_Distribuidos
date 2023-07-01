package com.example;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BankInterface extends Remote {
    String createAccount(String id, String name, String username, String password) throws RemoteException;
    String deposit(String accountNumber, double amount) throws RemoteException;
    String withdraw(String accountNumber, double amount) throws RemoteException;
    String transfer(String fromAccountNumber, String toAccountNumber, double amount) throws RemoteException;
    String getAccountDetails(String accountNumber) throws RemoteException;
}
