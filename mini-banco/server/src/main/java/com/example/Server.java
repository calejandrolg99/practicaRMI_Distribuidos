package com.example;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.example.BankInterface;
import com.example.User;
import com.example.Account;
import com.example.Transaction;
import com.example.Deposit;
import com.example.Withdrawal;
import com.example.Transference;

public class Server extends UnicastRemoteObject implements BankInterface {
    private List<User> users;

    protected Server() throws RemoteException {
        super();
        users = new ArrayList<>();
    }

    //implement all the methods from the BankInterface interface
    //and override the methods from the BankInterface interface
    //in this class

    public String withdraw(String accountNumber, double amount) throws RemoteException {
        for (User user : users) {
            if (user.getAccount(accountNumber).equals(accountNumber)) {
                Withdrawal withdraw = new Withdrawal(amount, "Withdraw");
                user.getAccount(accountNumber).getTransactions().add(withdraw);
                saveData();
                return "Withdraw successful";
            }
        }
        return "Account not found";
    }

    public String transfer (String fromAccountNumber, String toAccountNumber, double amount) throws RemoteException {
        for (User user : users) {
            if (user.getAccount(fromAccountNumber).equals(fromAccountNumber)) {
                for (User user2 : users) {
                    if (user2.getAccount(toAccountNumber).equals(toAccountNumber)) {
                        Transference transfer = new Transference(amount, "Transfer", user2.getAccount(toAccountNumber));
                        user.getAccount(toAccountNumber).getTransactions().add(transfer);
                        saveData();
                        return "Transfer successful";
                    }
                }
            }
        }
        return "Account not found";
    }

    @Override
    public String getAccountDetails(String accountId) {
        // Implementation of the getAccountDetails method
        // Return the account details as a String
        String whatever = "whatever";
        return whatever;
    }

    @Override
    public String createAccount(String id, String name, String username, String password) throws RemoteException {
        User newUser = new User(id, name, username, password);
        users.add(newUser);
        saveData();
        return "Account created";
    }

    @Override
    public String deposit(String accountNumber, double amount) throws RemoteException {
        for (User user : users) {
            if (user.getAccount(accountNumber).equals(accountNumber)) {
                Deposit deposit = new Deposit(amount, "Deposit");
                user.getAccount(accountNumber).getTransactions().add(deposit);
                saveData();
                return "Deposit successful";
            }
        }
        return "Account not found";
    }

    public static void main(String[] args) {
        try {
            Server server = loadData();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("Bank", server);
            System.out.println("Server is ready");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveData() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File("data.json"), this.users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

private static Server loadData() throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    Server server = mapper.readValue(new File("data.json"), new TypeReference<Server>() {});
    return server;
}
}
