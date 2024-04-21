package Card_Game;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.Console;

class Game {
    public static ArrayList<Register> UserDetails = new ArrayList<Register>();
    public static Bank bank = new Bank();
    static boolean loop = true;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static Register User = new Register();
    public static double gameBalance = 1000000;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Register User1 = new Register();
        User1.User_Details("Kunj", "1q2w", 25, 123456783);
        User1.Total_Game = 21;
        User1.Win_Game = 18;
        User1.Lose_Game = 3;
        UserDetails.add(User1);
        Console c = System.console();
        try {
            while (true) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("\n------------------------------------------------------------------------\n");
                System.out.println("Welcome to Exploding Cat Game");
                System.out.println(ANSI_GREEN +
                        "\nGame that consists of 4 different types of cards\n- Cat card \n- Defuse card \n- Shuffle card \n- Exploding kitten card ");
                System.out.println(
                        """
                                \nRules -\n
                                - If the card drawn from the deck is a cat card, then the card is removed from the deck.
                                - If the card is exploding kitten (bomb) then the player loses the game.
                                - If the card is a defusing card, then the card is removed from the deck. This card can be used to defuse one bomb (i.e One Exploding Kitten Card) that may come in subsequent cards drawn from the deck.
                                - If the card is a shuffle card, then the game is restarted and the deck is filled with 5 cards again."""
                                + ANSI_RESET);
                System.out.println("\n------------------------------------------------------------------------\n");
                System.out.print("\n1) Login\n2) Register\n3) Exit\nEnter your choice: ");
                int Select = sc.nextInt();

                loop = true;
                switch (Select) {

                    // Login Case
                    case 1:
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        System.out.print("Enter the Username: ");
                        String User_name = sc.next();
                        System.out.print("Enter the Password: ");
                        String Pass_word = sc.next();
                        Login lg = new Login(User_name, Pass_word);
                        boolean valid = lg.IsValid();
                        if (valid) {
                            System.out.println("\nLogin Success");
                            for (int i = 0; i < UserDetails.size(); i++) {
                                if (UserDetails.get(i).User_Name.equals(User_name)) {
                                    User = UserDetails.get(i);
                                }
                            }
                            c.format("\nPress Enter to proceed.\n");
                            c.readLine();
                        } else {
                            System.out.println("\nInvalid Username or Password");
                            c.format("\nPress Enter to proceed.\n");
                            c.readLine();
                            loop = false;
                        }

                        break;

                    // Register Case
                    case 2:
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        Register rg = new Register();
                        System.out.print("Enter the Username: ");
                        String Username = sc.next();
                        System.out.print("Enter the Password: ");
                        String Password = sc.next();
                        System.out.print("Enter the Age: ");
                        int Age = sc.nextInt();
                        System.out.print("Enter the Mobile: ");
                        long Mobile = sc.nextLong();
                        boolean result = rg.User_Details(Username, Password, Age, Mobile);
                        if (result) {
                            UserDetails.add(rg);
                            User = rg;
                            System.out.println("\nUser Registered Successfully");
                        } else {
                            System.out.println("\nUsername already exists");
                            loop = false;
                        }
                        c.format("\nPress Enter to proceed.\n");
                        c.readLine();
                        break;

                    // Exit Case
                    case 3:
                        System.out.println("\n\nThank you for playing the game !!!");
                        System.exit(0);
                        break;

                    default:
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        System.out.println("\nInvalid Choice");
                        loop = false;
                        c.format("\nPress Enter to proceed.\n");
                        c.readLine();
                        break;
                }
                while (loop) {
                    Menu(User);
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    // ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // Menu Method

    public static void Menu(Register User) {
        Scanner sc = new Scanner(System.in);
        NewGame ng = new NewGame(User);
        // GameMode gm = new GameMode();
        String accountNumber, cvv;
        int flag = 0;
        Console c = System.console();
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.print(
                "\n1) New Game\n2) Profile\n3) Venture History\n4) Deposit\n5) Withdraw\n6) Logout\nEnter your choice:");
        int Choice1 = sc.nextInt();

        switch (Choice1) {

            // New Game Case
            case 1:
                GameMode.Mode();
                break;

            // Profile Case
            case 2:
                ng = new NewGame(User);
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("\nProfile Details");
                User.Display();
                c.format("\nPress Enter to proceed.\n");
                c.readLine();
                break;

            // Venture History Case
            case 3:
                System.out.print("\033[H\033[2J");
                System.out.flush();
                if (User.betRecords.size() == 0) {
                    System.out.println("\nNo Venture History\n");
                    break;
                }
                System.out.println("\nVenture History:- ");
                for (BetFormat bet : User.betRecords) {
                    System.out.println("Venture Amount: " + bet.betAmount + "\tMultiplier: " + bet.multiplier
                            + "\tPayout: " + bet.payout);
                    System.out.println("-------------------------------------------------");
                }
                c.format("\nPress Enter to proceed.\n");
                c.readLine();
                break;

            // Deposit Case
            case 4:
                flag = 0;
                if (User.balanceDetails == false) {
                    System.out.print("Enter your account number: ");
                    accountNumber = sc.next();
                    System.out.print("Enter your cvv: ");
                    cvv = sc.next();
                    User.accountNumber = accountNumber;
                    User.cvv = cvv;
                } else {
                    accountNumber = User.accountNumber;
                    cvv = User.cvv;
                }
                for (Account acc : Bank.accounts) {
                    if (acc.accountNumber.equals(accountNumber) && acc.cvv.equals(cvv)) {
                        flag = 1;
                        User.balanceDetails = true;
                        System.out.print("Enter amount to deposit: ");
                        double amount = sc.nextDouble();
                        if (amount > acc.balance) {
                            System.out.println("\nInsufficient balance\n");
                            System.out.println("Account Balance: " + acc.balance);

                        } else {
                            acc.withdraw(amount);
                            User.balance += amount;
                            System.out.println("\nAmount Deposit successfully\n");
                            System.out.println("Game Balance: " + User.balance);
                            System.out.println("Account Balance: " + acc.balance);
                        }
                    }
                }
                if (flag == 0) {
                    User.cvv = null;
                    User.accountNumber = null;
                    User.balanceDetails = false;
                    System.out.println("\nInvalid account number or cvv\n");
                }
                c.format("\nPress Enter to proceed.\n");
                c.readLine();
                break;

            // Withdraw Case
            case 5:
                flag = 0;
                if (User.balanceDetails == false) {
                    System.out.print("Enter your account number: ");
                    accountNumber = sc.next();
                    System.out.print("Enter your cvv: ");
                    cvv = sc.next();
                    User.accountNumber = accountNumber;
                    User.cvv = cvv;
                } else {
                    accountNumber = User.accountNumber;
                    cvv = User.cvv;
                }
                for (Account acc : Bank.accounts) {
                    if (acc.accountNumber.equals(accountNumber) && acc.cvv.equals(cvv)) {
                        flag = 1;
                        User.balanceDetails = true;
                        System.out.print("Enter the amount to withdraw: ");
                        double Amount = sc.nextDouble();
                        if (Amount > User.balance) {
                            System.out.println("\nInsufficient balance\n");
                            System.out.println("Game Balance: " + User.balance);
                        } else {
                            acc.deposit(Amount);
                            User.balance -= Amount;
                            System.out.println("\nAmount Withdraw successfully\n");
                            System.out.println("Game Balance: " + User.balance);
                            System.out.println("Account Balance: " + acc.balance);
                        }
                    }
                }
                if (flag == 0) {
                    User.cvv = null;
                    User.accountNumber = null;
                    User.balanceDetails = false;
                    System.out.println("\nInvalid account number or cvv\n");

                }
                c.format("\nPress Enter to proceed.\n");
                c.readLine();
                break;

            // Logout Case
            case 6:
                loop = false;
                break;

            default:
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("\nInvalid Choice");
                c.format("\nPress Enter to proceed.\n");
                c.readLine();
                break;
        }

    }
}
