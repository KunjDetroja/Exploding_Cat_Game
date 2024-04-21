package Card_Game;

import java.io.Console;
import java.util.ArrayList;
import java.util.Scanner;

class NewGame {
    RandomCard rc = new RandomCard();
    Register User;
    ArrayList<String> User_Card = rc.Random_Card();
    int Total_Card = User_Card.size();
    int Defuse_Card = 0;

    NewGame(Register User) {
        this.User = User;
    }

    public void New_Game(Bet bt) {
        Scanner sc = new Scanner(System.in);
        Console c = System.console();
        boolean loop = true;
        while (loop) {

            // Win Condition
            if (Total_Card == 0) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("\nYou Won the Game");
                int cardopen = 5 - Total_Card;
                double multiplier = Multiplier(bt.betType, cardopen);
                BetFormat bet = new BetFormat(bt.betAmount, multiplier);
                User.betRecords.add(bet);
                User.balance += bet.payout;
                Game.gameBalance -= bet.payout;
                if (bt.betStatus) {
                    System.out.println("Amount Won: " + bet.payout);
                    System.out.println("Game Balance: " + User.balance);
                }
                c.format("\nPress Any Key to proceed.\n");
                c.readLine();
                loop = false;
                break;
            }

            // Lose Condition
            else if (Total_Card == -1) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("\nYou Lose the Game");
                int cardopen = 0;
                double multiplier = Multiplier(bt.betType, cardopen);
                BetFormat bet = new BetFormat(bt.betAmount, multiplier);
                User.betRecords.add(bet);
                if (bt.betStatus) {
                    System.out.println("\nAmount Lost: " + bt.betAmount);
                    System.out.println("Game Balance: " + User.balance);
                }
                loop = false;
                c.format("\nPress Any Key to proceed.\n");
                c.readLine();
                break;
            }

            // Game Loop
            else if (Total_Card <= 5 && Total_Card > 0) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("\nTotal Card: " + Total_Card);
                System.out.println("Defuse Card: " + Defuse_Card);
                double multiplier1 = Multiplier(bt.betType, 5 - Total_Card);
                System.out.println("Multiplier: " + multiplier1);
                System.out.print("1) Open Card");
                if (bt.betStatus) {
                    System.out.print("\n2) Payout");
                } else {
                    System.out.print("\n2) Exit");
                }
                System.out.print("\nEnter your choice: ");
                int Ch = sc.nextInt();
                switch (Ch) {

                    // Open Card Case
                    case 1:
                        Open_Card(bt);
                        break;

                    // Payout Case
                    case 2:
                        int cardopen = 5 - Total_Card;
                        double multiplier = Multiplier(bt.betType, cardopen);
                        BetFormat bet = new BetFormat(bt.betAmount, multiplier);
                        User.betRecords.add(bet);
                        User.balance += bet.payout;
                        Game.gameBalance -= bet.payout;
                        if (bt.betStatus) {
                            System.out.println("Amount Won: " + bet.payout);
                            System.out.println("Game Balance: " + User.balance);
                        }
                        User.Total_Game++;
                        User.Win_Game++;
                        Total_Card = 5;
                        Defuse_Card = 0;
                        loop = false;
                        c.format("\nPress Any Key to proceed.\n");
                        c.readLine();
                        break;
                    default:
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        System.out.println("\nInvalid Choice");
                        break;
                }
            }
        }
    }

    // ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // Open Card Method

    void Open_Card(Bet bt) {
        Scanner sc = new Scanner(System.in);
        Console c = System.console();
        System.out.print("Enter the card number to open: ");
        int Card_Number = sc.nextInt();
        // Card Opened
        if (Card_Number > 0 && Card_Number <= Total_Card) {
            System.out.println("\nCard Opened: " + User_Card.get(Card_Number - 1));
            // Card Operations
            // Explode Card
            if (User_Card.get(Card_Number - 1) == "Explode") {

                // If Defuse Card is present then Explode Defused
                if (Defuse_Card > 0) {
                    Defuse_Card--;
                    Total_Card--;
                    User_Card.remove(Card_Number - 1);
                    System.out.println("\nExplode Defused");
                }
                // If Defuse Card is not present then Explode Done
                else {
                    System.out.println("\nExplode Done");

                    User.Total_Game++;
                    User.Lose_Game++;
                    Total_Card = -1;
                }
            }
            // Shuffle Card
            else if (User_Card.get(Card_Number - 1) == "Shuffle") {
                rc = new RandomCard();
                User_Card = rc.Random_Card();
                Total_Card = User_Card.size();
                Defuse_Card = 0;
                System.out.println("\nShuffle Done");
            }
            // Defuse Card
            else if (User_Card.get(Card_Number - 1) == "Defuse") {
                Defuse_Card++;
                Total_Card--;
                User_Card.remove(Card_Number - 1);
            }
            // Cat Card
            else if (User_Card.get(Card_Number - 1) == "Cat") {
                Total_Card--;
                User_Card.remove(Card_Number - 1);
            }
            // Win Condition
            if (Total_Card == 0) {
                User.Total_Game++;
                User.Win_Game++;
            }
            c.format("\nPress Any Key to proceed.\n");
            c.readLine();
        }

        else {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("\nInvalid Card Number");
            c.format("\nPress Any Key to proceed.\n");
            c.readLine();
        }
        // sc.close();
    }

    public double Multiplier(String type, int openCard) {
        double[] low = { 0, 0.5, 1.1, 2.5, 5, 7 };
        double[] medium = { 0, 0, 0.5, 3.5, 7, 10 };
        double[] high = { 0, 0, 0, 5, 10, 15 };
        double multiplier = 0;
        switch (type) {
            case "Low":
                multiplier = low[openCard];
                break;
            case "Medium":
                multiplier = medium[openCard];
                break;
            case "High":
                multiplier = high[openCard];
                break;
            case "None":
                multiplier = 0;
                break;
        }
        return multiplier;

    }
}
