import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server extends UnicastRemoteObject implements BankInterface {
    protected Server() throws RemoteException {
        super();
    }

    public String createAccount(String id, String name, String username, String password) throws RemoteException {
        // Implement your logic to create account
        return "Account created";
    }

    public String deposit(String accountNumber, double amount) throws RemoteException {
        // Implement your logic to deposit
        return "Deposit successful";
    }

    // Implement other methods

    public static void main(String[] args) {
        try {
            Server server = new Server();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("Bank", server);
            System.out.println("Server is ready");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String withdraw(String accountNumber, double amount) throws RemoteException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'withdraw'");
    }

    @Override
    public String transfer(String fromAccountNumber, String toAccountNumber, double amount) throws RemoteException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'transfer'");
    }

    @Override
    public String getAccountDetails(String accountNumber) throws RemoteException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAccountDetails'");
    }
}
