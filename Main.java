void main() {
    DatabaseManager customerDb = new DatabaseManager();
    AccountManager accountDb = new AccountManager();
    // INSERT INTO check
    // Customer kairat = new Customer("CUST001", "Kairat Nurtas", "kair_n@gmail.com", "8-701-943-3310");
    // customerDb.saveCustomer(kairat);
    // SELECT check
    // System.out.println("Customers in DB: " + customerDb.getAllCustomers());
    // Update check
    // customerDb.updateCustomerPhone("CUST001", "8-777-000-0000");

    Customer customer1 = new Customer("CUST001", "Kairat Nurtas", "kair_n@gmail.com", "8-701-943-3310");
    customerDb.saveCustomer(customer1);
    Customer customer2 = new Customer("CUST002", "Ernar Aidar", "ernar-aidar@mail.ru", "8-727-391-3552");
    customerDb.saveCustomer(customer2);

    BankAccount account1 = new BankAccount("ACC001", 1000.0, "Savings", "CUST001");  // Linked to customer1
    accountDb.saveAccount(account1);
    BankAccount account2 = new BankAccount("ACC002", 1500.0, "Checking", "CUST002");  // Linked to customer2
    accountDb.saveAccount(account2);
    BankAccount account3 = new BankAccount("ACC003", 1000.0, "Savings", "CUST001");  // Same as account1
    accountDb.saveAccount(account3);
    //BankAccount account_empty = new BankAccount();
    customerDb.deleteCustomer("CUST001");

    //Customer customer_empty = new Customer();
    SuperCustomer scustomer1 = new SuperCustomer(
            customer1.getCustomerId(),
            customer1.getName(),
            customer1.getEmail(),
            customer1.getPhone(),
            "15%"
    );
    customerDb.saveCustomer(scustomer1);

    Bank bank = new Bank("MyBank", "MB001");
    //Bank bank_empty = new Bank();
    bank.addCustomer(customer1);
    bank.addCustomer(customer2);
    bank.addAccount(account1);
    bank.addAccount(account2);
    //bank_empty.addAccount(account_empty);
    bank.addCustomer(scustomer1); // supercustomer check

    IO.println("Bank Details:");
    IO.println(bank);
    IO.println("\nCustomer 1:");
    IO.println(customer1);
    IO.println("\nCustomer 2:");
    IO.println(customer2);
    //IO.println("\n(TESTED)\nEmpty Customer:");
    //IO.println(customer_empty);
    IO.println("\nSuperCustomer 1 (upgraded from Customer 1):");
    IO.println(scustomer1);
    IO.println("\nAccount 1:");
    IO.println(account1);
    IO.println("\nAccount 2:");
    IO.println(account2);
    IO.println("\nAccount 3:");
    IO.println(account3);
    //IO.println("\n(TESTED)\nEmpty Account:");
    //IO.println(account_empty);

    IO.println("\nComparisons:");
    IO.println("Account1 equals Account2: " + account1.equals(account2)); // false
    IO.println("Account1 equals Account3: " + account1.equals(account3)); // true
    IO.println("Customer1 equals Customer2: " + customer1.equals(customer2)); // false
    IO.println("SuperCustomer1 equals Customer1: " + scustomer1.equals(customer1)); // false (different classes/types)
    IO.println("SuperCustomer1 bonus: " + scustomer1.getBonus());

    account1.deposit(500.0);
    accountDb.updateBalance(account1.getAccountNumber(), account1.getBalance());
    IO.println("\nAfter depositing 500 to Account1:");
    IO.println(account1);

    boolean withdrawn = account2.withdraw(200.0);
    accountDb.updateBalance(account2.getAccountNumber(), account2.getBalance());
    IO.println("\nWithdraw 200 from Account2 (success: " + withdrawn + "):");
    IO.println(account2);

    // Data Pool Operations
    IO.println("\n--- Data Pool Operations ---");
    IO.println("Find Customer by ID 'CUST001': " + bank.findCustomerById("CUST001").orElse(null));
    IO.println("Find Account by Number 'ACC002': " + bank.findAccountByNumber("ACC002").orElse(null));
    IO.println("Accounts for Customer 'CUST001': " + bank.findAccountsByCustomerId("CUST001"));
    IO.println("Accounts with balance > 1200: " + bank.filterAccountsByBalance(1200.0));
    IO.println("Savings Accounts: " + bank.filterAccountsByType("Savings"));
    IO.println("Customers with 'Kai' in name: " + bank.filterCustomersByName("Kai"));
    IO.println("Customers sorted by name: " + bank.sortCustomersByName());
    IO.println("Accounts sorted by balance (desc): " + bank.sortAccountsByBalanceDescending());
    IO.println("Accounts sorted by type: " + bank.sortAccountsByType());

    try {
        // 1. Start the Server on port 8080
        com.sun.net.httpserver.HttpServer server =
                com.sun.net.httpserver.HttpServer.create(new java.net.InetSocketAddress(8080), 0);

        // 2. Map the URL "/api/customers" to your Handler logic
        server.createContext("/api/customers", new CustomerHandler());

        // 3. Start it!
        server.start();

        System.out.println("🚀 Server is running!");
        System.out.println("Check it out here: http://localhost:8080/api/customers");

    } catch (java.io.IOException e) {
        System.err.println("Could not start server: " + e.getMessage());
    }

}