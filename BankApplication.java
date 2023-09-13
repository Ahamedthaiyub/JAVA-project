import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class BankAccount {
    String username;
    String password;
    int pin;
    List<String> transactionHistory;

    public BankAccount(String username, String password, int pin) {
        this.username = username;
        this.password = password;
        this.pin = pin;
        this.transactionHistory = new ArrayList<>();
    }

    // Other methods...

    public void addTransaction(String transaction) {
        transactionHistory.add(transaction);
    }

    public List<String> getTransactionHistory() {
        return transactionHistory;
    }

    public boolean validatePassword(String enteredPassword) {
    return this.password.equals(enteredPassword);
    }

}

public class BankApplication {
    private static Map<String, BankAccount> accounts = new HashMap<>();
    private static BankAccount loggedInAccount = null;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome to the Bank Application");
            System.out.println("1. Create Account");
            System.out.println("2. Log In");
            System.out.println("3. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1:
                    createAccount(scanner);
                    break;
                case 2:
                    login(scanner);
                    break;
                case 3:
                    System.out.println("Thank you for using the Bank Application. Goodbye!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    private static void createAccount(Scanner scanner) {
        System.out.print("Enter a username: ");
        String username = scanner.nextLine();

        if (accounts.containsKey(username)) {
            System.out.println("Username already exists. Please choose another one.");
            return;
        }

        System.out.print("Enter a password: ");
        String password = scanner.nextLine();

        System.out.print("Enter a PIN: ");
        int pin = scanner.nextInt();
        scanner.nextLine(); // Consume the newline

        BankAccount account = new BankAccount(username, password, pin);
        accounts.put(username, account);

        System.out.println("Account created successfully.");
    }

    private static void login(Scanner scanner) {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();

        if (!accounts.containsKey(username)) {
            System.out.println("Username not found. Please create an account first.");
            return;
        }

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        BankAccount account = accounts.get(username);

        if (account.validatePassword(password)) {
            loggedInAccount = account;
            System.out.println("Login successful. Welcome, " + account.username + "!");
            performLoggedInActions(scanner);
        } else {
            System.out.println("Incorrect password. Please try again.");
        }
    }

    private static void performLoggedInActions(Scanner scanner) {
        while (true) {
            System.out.println("Logged In Options:");
            System.out.println("1. Change PIN");
            System.out.println("2. Transaction History");
            System.out.println("3. Logout");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1:
                    System.out.print("Enter your new PIN: ");
                    int newPin = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline
                    loggedInAccount.pin = newPin;
                    System.out.println("PIN changed successfully.");
                    break;
                case 2:
                    displayTransactionHistory();
                    break;
                case 3:
                    System.out.println("Logout successful.");
                    loggedInAccount = null;
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    private static void displayTransactionHistory() {
        List<String> transactions = loggedInAccount.getTransactionHistory();
        System.out.println("Transaction History:");
        for (String transaction : transactions) {
            System.out.println(transaction);
        }
    }
}


