package Card_Game;

import java.util.ArrayList;

class Bank {
    public static ArrayList<Account> accounts = new ArrayList<Account>();

    public Bank() {
        Account account = new Account("Kunj", 10000, "123qwe", "123");
        accounts.add(account);
        Account account1 = new Account("Rajan", 20000, "456rty", "456");
        accounts.add(account1);
        Account account2 = new Account("Rahul", 30000, "789uio", "789");
        accounts.add(account2);
    }
}

