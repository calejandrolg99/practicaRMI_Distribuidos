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
import java.util.Random;

public class Server extends UnicastRemoteObject implements BankInterface {
    private List<User> users;

    protected Server() throws RemoteException {
        super();
        users = new ArrayList<>();
    }

    @Override
    public String withdraw(String accountNumber, double amount) throws RemoteException {
        for (User user : users) {
            Account account = user.getAccount(accountNumber);
            if (account != null) {
                List<Transaction> transactions = account.getTransactions();
                if (transactions != null) {
                    if (account.getBalance() < amount) {
                        return "Insufficient balance";
                    }
                    Withdrawal withdrawal = new Withdrawal(amount, "Withdraw");
                    transactions.add(withdrawal);
                    account.setBalance(account.getBalance() - amount);
                    saveData();
                    return "Withdraw successful";
                } else {
                    return "Account transactions list is null";
                }
            }
        }
        return "Account not found";
    }



    public String transfer(String fromAccountNumber, String toAccountNumber, double amount) throws RemoteException {
        for (User user : users) {
            Account fromAccount = user.getAccount(fromAccountNumber);
            if (fromAccount != null) {
                List<Transaction> transactions = fromAccount.getTransactions();
                if (transactions != null) {
                    for (User user2 : users) {
                        Account toAccount = user2.getAccount(toAccountNumber);
                        if (toAccount != null) {
                            if (fromAccount.getBalance() < amount) {
                                return "Insufficient balance";
                            }
                            List<Transaction> transactions2 = toAccount.getTransactions();
                            if (transactions2 != null) {
                                Transference transfer = new Transference(amount, "Transfer",
                                        user2.getAccount(toAccountNumber));
                                user.getAccount(toAccountNumber).getTransactions().add(transfer);
                                fromAccount.setBalance(fromAccount.getBalance() - amount);
                                toAccount.setBalance(toAccount.getBalance() + amount);
                                saveData();
                                return "Transfer successful";
                            } else {
                                return "To Account transactions list is null";
                            }
                        }
                    }
                } else {
                    return "From Account transactions list is null";
                }
            }
        }
        return "Account not found";
    }


    @Override
    public String getAccountDetails(String userId, String password) throws RemoteException {
        if (authenticate(userId, password) != null) {
            StringBuilder details = new StringBuilder();
            for (User user : users) {
                if (user.getId().equals(userId)) {
                    for (Account account : user.getAccounts()) {
                        details.append("Account Number: ").append(account.getAccountNumber()).append("\n");
                        details.append("Balance: ").append(account.getBalance()).append("\n");
                        details.append("Transactions: ").append("\n");
                        for (Transaction transaction : account.getTransactions()) {
                            details.append("Transaction ID: ").append(transaction.getId()).append("\n");
                            details.append("Amount: ").append(transaction.getAmount()).append("\n");
                            details.append("Description: ").append(transaction.getDescription()).append("\n");
                            details.append("Date: ").append(transaction.getDate()).append("\n");
                        if (transaction instanceof Transference) {
                            Transference transference = (Transference) transaction;
                            details.append("To Account: ").append(transference.getDestinationAccount().getAccountNumber()).append("\n");
                        }
                        details.append("\n");
                    }
                    details.append("\n");
                }
            }
        }
            return details.toString();
        } else {
            return "Invalid credentials.";
        }
    }

    public User authenticate(String documentId, String password) throws RemoteException {
        for (User user : users) {
            if (user.getId().equals(documentId) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public String createAccount(String documentNumber, String name, String username, String password, double initialAmount) throws RemoteException {
        for (User user : users) {
            if (user.getId().equals(documentNumber)) {
                if (user.getAccounts().size() >= 3) {
                    return "You have reached the limit of accounts.";
                }

                if (!user.authenticate(username, password)) {
                    return "Invalid credentials.";
                }
            }
        }

        User newUser = new User(documentNumber, name, username, password);

        String accountNumber = String.format("%09d", new Random().nextInt(1_000_000_000));

        Account newAccount = new Account(accountNumber, initialAmount);
        newUser.getAccounts().add(newAccount);

        users.add(newUser);
        saveData();
        return "Account created with account number: " + accountNumber;
    }

    @Override
    public String deposit(String accountNumber, double amount) throws RemoteException {
        for (User user : users) {
            Account account = user.getAccount(accountNumber);
            if (account != null) {
                List<Transaction> transactions = account.getTransactions();
                if (transactions != null) {
                    Deposit deposit = new Deposit(amount, "Deposit");
                    transactions.add(deposit);
                    // Update the account balance
                    account.setBalance(account.getBalance() + amount);
                    saveData();
                    return "Deposit successful";
                } else {
                    return "Account transactions list is null";
                }
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
        File file = new File("data.json");
        ObjectMapper mapper = new ObjectMapper();
        Server server = null;

        // Check if the file is empty
        if (file.length() == 0) {
            // If the file is empty, initialize server with an empty list of users
            try {
                server = new Server();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            // If the file is not empty, read the list of users from the file
            List<User> users = mapper.readValue(file, new TypeReference<List<User>>() {
            });
            try {
                server = new Server();
                server.users = users;
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        return server;
    }

}
