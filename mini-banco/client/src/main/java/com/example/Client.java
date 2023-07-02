package com.example;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("my-running-server", 1099);
            BankInterface bank = (BankInterface) registry.lookup("Bank");

            Scanner sc = new Scanner(System.in);

            while (true) {
                System.out.println("ATM");
                System.out.println("Seleccione 1 para Apertura de Cuenta");
                System.out.println("Seleccione 2 para Consulta de Cuentas");
                System.out.println("Seleccione 3 para Deposito");
                System.out.println("Seleccione 4 para Retiro");
                System.out.println("Seleccione 5 para Transferencia");
                System.out.println("Seleccione 6 para Salir");
                System.out.print("Escoger opcion:");

                int choice = sc.nextInt();
                System.out.print("\033[H\033[2J");
                System.out.flush();

                String accountNumber;
                double amount;

                switch (choice) {
                    case 1:
                        System.out.print("Ingrese ID:\n");
                        String id = sc.next();
                        System.out.print("Ingrese Nombre:\n");
                        String name = sc.next();
                        System.out.print("Ingrese Nombre de Usuario:\n");
                        String username = sc.next();
                        System.out.print("Ingrese Contraseña:\n");
                        String password = sc.next();
                        System.out.println(bank.createAccount(id, name, username, password));
                        System.out.println();
                        break;

                    case 2:
                        System.out.print("Ingrese numero de cuenta:\n");
                        break;

                    case 3:
                        System.out.print("Ingrese numero de cuenta:\n");
                        accountNumber = sc.next();
                        System.out.print("Ingrese monto a depositar:\n");
                        amount = sc.nextDouble();
                        System.out.println(bank.deposit(accountNumber, amount));
                        System.out.println();
                        break;

                    case 4:
                        System.out.print("Ingrese numero de cuenta:\n");
                        accountNumber = sc.next();
                        System.out.print("Ingrese monto a retirar:\n");
                        amount = sc.nextDouble();
                        System.out.println(bank.withdraw(accountNumber, amount));
                        System.out.println();
                        break;

                    case 5:
                        System.out.print("Ingrese numero de cuenta de origen:\n");
                        String fromAccountNumber = sc.next();
                        System.out.print("Ingrese numero de cuenta de destino:\n");
                        String toAccountNumber = sc.next();
                        System.out.print("Ingrese monto a transferir:\n");
                        amount = sc.nextDouble();
                        System.out.println(bank.transfer(fromAccountNumber, toAccountNumber, amount));
                        System.out.println();
                        break;

                    case 6:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice\n");
                }
            }
            // Call other methods
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
