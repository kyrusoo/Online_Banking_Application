import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.*;
import java.util.stream.Collectors;

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
        return new ArrayList<>(customers);  // Return a copy to prevent external modification
    }

    public List<BankAccount> getAccounts() {
        return new ArrayList<>(accounts);  // Return a copy
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

    // Data pool operations: Searching
    public Optional<Customer> findCustomerById(String id) {
        return customers.stream()
                .filter(c -> c.getCustomerId().equals(id))
                .findFirst();
    }

    public Optional<BankAccount> findAccountByNumber(String accountNumber) {
        return accounts.stream()
                .filter(a -> a.getAccountNumber().equals(accountNumber))
                .findFirst();
    }

    public List<BankAccount> findAccountsByCustomerId(String customerId) {
        return accounts.stream()
                .filter(a -> a.getCustomerId().equals(customerId))
                .collect(Collectors.toList());
    }

    // Data pool operations: Filtering
    public List<BankAccount> filterAccountsByBalance(double minBalance) {
        return accounts.stream()
                .filter(a -> a.getBalance() > minBalance)
                .collect(Collectors.toList());
    }

    public List<BankAccount> filterAccountsByType(String type) {
        return accounts.stream()
                .filter(a -> a.getAccountType().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }

    public List<Customer> filterCustomersByName(String nameSubstring) {
        return customers.stream()
                .filter(c -> c.getName().toLowerCase().contains(nameSubstring.toLowerCase()))
                .collect(Collectors.toList());
    }

    // Data pool operations: Sorting
    public List<Customer> sortCustomersByName() {
        return customers.stream()
                .sorted(Comparator.comparing(Customer::getName))
                .collect(Collectors.toList());
    }

    public List<BankAccount> sortAccountsByBalanceDescending() {
        return accounts.stream()
                .sorted(Comparator.comparingDouble(BankAccount::getBalance).reversed())
                .collect(Collectors.toList());
    }

    public List<BankAccount> sortAccountsByType() {
        return accounts.stream()
                .sorted(Comparator.comparing(BankAccount::getAccountType))
                .collect(Collectors.toList());
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