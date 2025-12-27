import java.util.Objects;

public class BankAccount {
    private String accountNumber;
    private double balance;
    private String accountType; // e.g., "Savings" or "Checking"
    private String customerId;  // Added for linking to Customer

    public BankAccount() {
        this.accountNumber = "";
        this.balance = 0.0;
        this.accountType = "Savings";
        this.customerId = "";  // Default
    }

    public BankAccount(String accountNumber, double balance, String accountType) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.accountType = accountType;
        this.customerId = "";  // Default; set via setter if needed
    }
    // New constructor for linking
    public BankAccount(String accountNumber, double balance, String accountType, String customerId) {

        this.accountNumber = accountNumber;

        this.balance = balance;

        this.accountType = accountType;

        this.customerId = customerId;

    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getCustomerId() { return customerId; }

    public void setCustomerId(String customerId) { this.customerId = customerId; }

    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && this.balance >= amount) {
            this.balance -= amount;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                ", accountType='" + accountType + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        BankAccount that = (BankAccount) obj;
        return Double.compare(that.balance, balance) == 0 &&
                Objects.equals(accountNumber, that.accountNumber) &&
                Objects.equals(accountType, that.accountType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber, balance, accountType);
    }
}