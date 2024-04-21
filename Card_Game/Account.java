package Card_Game;

class Account {
    String name;
    double balance;
    String accountNumber;
    String cvv;

    public Account(String name, double balance, String accountNumber, String cvv) {
        this.name = name;
        this.balance = balance;
        this.accountNumber = accountNumber;
        this.cvv = cvv;
    }

    public Account() {
        this.name = "";
        this.balance = 0;
        this.accountNumber = "";
        this.cvv = "";
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        balance -= amount;

    }
}
