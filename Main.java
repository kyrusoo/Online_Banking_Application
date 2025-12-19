public class Main {
    public static void main(String[] args) {
        BankAccount account1 = new BankAccount("ACC001", 1000.0, "Savings");
        BankAccount account2 = new BankAccount("ACC002", 1500.0, "Checking");
        BankAccount account3 = new BankAccount("ACC001", 1000.0, "Savings"); // Same as account1 for comparison
        BankAccount account_empty = new BankAccount();

        Customer customer1 = new Customer("CUST001", "Kairat Nurtas", "kair_n@gmail.com", "8-701-943-3310");
        Customer customer2 = new Customer("CUST002", "Ernar Aidar", "ernar-aidar@mail.ru", "8-727-391-3552");
        Customer customer_empty = new Customer();

        Bank bank = new Bank("MyBank", "MB001");
        Bank bank_empty = new Bank();
        bank.addCustomer(customer1);
        bank.addCustomer(customer2);
        bank.addAccount(account1);
        bank.addAccount(account2);
        bank_empty.addAccount(account_empty);

        System.out.println("Bank Details:");
        System.out.println(bank);
        System.out.println("\nCustomer 1:");
        System.out.println(customer1);
        System.out.println("\nCustomer 2:");
        System.out.println(customer2);
        System.out.println("\n(TESTED)\nEmpty Customer:");
        System.out.println(customer_empty);
        System.out.println("\nAccount 1:");
        System.out.println(account1);
        System.out.println("\nAccount 2:");
        System.out.println(account2);
        System.out.println("\nAccount 3:");
        System.out.println(account3);
        System.out.println("\n(TESTED)\nEmpty Account:");
        System.out.println(account_empty);

        System.out.println("\nComparisons:");
        System.out.println("Account1 equals Account2: " + account1.equals(account2)); // false
        System.out.println("Account1 equals Account3: " + account1.equals(account3)); // true
        System.out.println("Customer1 equals Customer2: " + customer1.equals(customer2)); // false

        account1.deposit(500.0);
        System.out.println("\nAfter depositing 500 to Account1:");
        System.out.println(account1);

        boolean withdrawn = account2.withdraw(200.0);
        System.out.println("\nWithdraw 200 from Account2 (success: " + withdrawn + "):");
        System.out.println(account2);
    }
}