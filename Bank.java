import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Bank {
    private String bankName;
    private String bankCode;
    private List<Customer> customers;
    private List<BankAccount> accounts;

    public Bank() {
        this.bankName = "";
        this.bankCode = "";
        this.customers = new ArrayList<>();
        this.accounts = new ArrayList<>();
    }

    public Bank(String bankName, String bankCode) {
        this.bankName = bankName;
        this.bankCode = bankCode;
        this.customers = new ArrayList<>();
        this.accounts = new ArrayList<>();
    }

    public String getBankName() {
        return bankName;
    }

    public String getBankCode() {
        return bankCode;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public List<BankAccount> getAccounts() {
        return accounts;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public void addCustomer(Customer customer) {
        this.customers.add(customer);
    }

    public void addAccount(BankAccount account) {
        this.accounts.add(account);
    }

    @Override
    public String toString() {
        return "Bank{" +
                "bankName='" + bankName + '\'' +
                ", bankCode='" + bankCode + '\'' +
                ", customers=" + customers +
                ", accounts=" + accounts +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Bank bank = (Bank) obj;
        return Objects.equals(bankName, bank.bankName) &&
                Objects.equals(bankCode, bank.bankCode) &&
                Objects.equals(customers, bank.customers) &&
                Objects.equals(accounts, bank.accounts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bankName, bankCode, customers, accounts);
    }
}