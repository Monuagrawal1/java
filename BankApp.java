import java.util.*;
import java.sql.*;

class BankApp {
    static Connection con;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            // Step 1: Connect to SQL Server
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(
                "jdbc:sqlserver://DESKTOP-5HMSKEO\\SQLEXPRESS;databaseName=BankDB;encrypt=true;trustServerCertificate=true",
                "sa", "monu"
            );

            int choice;
            do {
                // Menu
                System.out.println("\n--- Bank Account Menu ---");
                System.out.println("1. Create Account");
                System.out.println("2. Deposit");
                System.out.println("3. Withdraw");
                System.out.println("4. Check Balance");
                System.out.println("5. View All Accounts");
                System.out.println("6. Exit");
                System.out.print("Choose: ");
                choice = sc.nextInt();

                switch (choice) {
                    case 1: createAccount(); break;
                    case 2: deposit(); break;
                    case 3: withdraw(); break;
                    case 4: checkBalance(); break;
                    case 5: viewAccounts(); break;
                    case 6: System.out.println("Thank you!"); break;
                    default: System.out.println("Invalid choice.");
                }
            } while (choice != 6);

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    static void createAccount() throws SQLException {
        System.out.print("Enter Name: ");
        sc.nextLine(); // Clear buffer
        String name = sc.nextLine();
        System.out.print("Enter initial deposit: ");
        float balance = sc.nextFloat();

        PreparedStatement pst = con.prepareStatement("INSERT INTO accounts(Name, Balance) VALUES (?, ?)");
        pst.setString(1, name);
        pst.setFloat(2, balance);
        int rows = pst.executeUpdate();
        System.out.println(rows > 0 ? "Account created." : "Failed to create.");
    }

    static void deposit() throws SQLException {
        System.out.print("Enter Account ID: ");
        int id = sc.nextInt();
        System.out.print("Enter amount to deposit: ");
        float amount = sc.nextFloat();

        PreparedStatement pst = con.prepareStatement("UPDATE accounts SET Balance = Balance + ? WHERE AccountID = ?");
        pst.setFloat(1, amount);
        pst.setInt(2, id);
        int rows = pst.executeUpdate();
        System.out.println(rows > 0 ? "Amount deposited." : "Invalid Account ID.");
    }

    static void withdraw() throws SQLException {
        System.out.print("Enter Account ID: ");
        int id = sc.nextInt();
        System.out.print("Enter amount to withdraw: ");
        float amount = sc.nextFloat();

        // Check balance
        PreparedStatement pst = con.prepareStatement("SELECT Balance FROM accounts WHERE AccountID = ?");
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            float current = rs.getFloat("Balance");
            if (current >= amount) {
                pst = con.prepareStatement("UPDATE accounts SET Balance = Balance - ? WHERE AccountID = ?");
                pst.setFloat(1, amount);
                pst.setInt(2, id);
                pst.executeUpdate();
                System.out.println("Amount withdrawn.");
            } else {
                System.out.println("Insufficient balance.");
            }
        } else {
            System.out.println("Invalid Account ID.");
        }
    }

    static void checkBalance() throws SQLException {
        System.out.print("Enter Account ID: ");
        int id = sc.nextInt();

        PreparedStatement pst = con.prepareStatement("SELECT Name, Balance FROM accounts WHERE AccountID = ?");
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            System.out.println("Name: " + rs.getString("Name"));
            System.out.println("Balance: Rs." + rs.getFloat("Balance"));
        } else {
            System.out.println("Account not found.");
        }
    }

    static void viewAccounts() throws SQLException {
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM accounts");

        System.out.println("\nAll Accounts:");
        while (rs.next()) {
            System.out.println("ID: " + rs.getInt("AccountID") +
                               ", Name: " + rs.getString("Name") +
                               ", Balance: Rs." + rs.getFloat("Balance"));
        }
    }
}
