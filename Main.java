void main() {
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

    IO.println("Bank Details:");
    IO.println(bank);
    IO.println("\nCustomer 1:");
    IO.println(customer1);
    IO.println("\nCustomer 2:");
    IO.println(customer2);
    IO.println("\n(TESTED)\nEmpty Customer:");
    IO.println(customer_empty);
    IO.println("\nAccount 1:");
    IO.println(account1);
    IO.println("\nAccount 2:");
    IO.println(account2);
    IO.println("\nAccount 3:");
    IO.println(account3);
    IO.println("\n(TESTED)\nEmpty Account:");
    IO.println(account_empty);

    IO.println("\nComparisons:");
    IO.println("Account1 equals Account2: " + account1.equals(account2)); // false
    IO.println("Account1 equals Account3: " + account1.equals(account3)); // true
    IO.println("Customer1 equals Customer2: " + customer1.equals(customer2)); // false

    account1.deposit(500.0);
    IO.println("\nAfter depositing 500 to Account1:");
    IO.println(account1);

    boolean withdrawn = account2.withdraw(200.0);
    IO.println("\nWithdraw 200 from Account2 (success: " + withdrawn + "):");
    IO.println(account2);
}