package Card_Game;

import java.io.Console;
import java.util.Scanner;

class GameMode {
    public static void Mode() {
        NewGame ng = new NewGame(Game.User);
        Console c = System.console();
        Bet bt = new Bet();
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("\nSelect the Game Mode");
        System.out.print("1) Simple\n2) Venture\n3) Exit\nEnter your choice: ");
        Scanner sc = new Scanner(System.in);
        int Mode = sc.nextInt();
        boolean loop = true;
        while (loop) {
            switch (Mode) {

                // Simple Mode
                case 1:
                    bt.BetAmount(0, "None");
                    bt.betStatus = false;
                    ng.New_Game(bt);
                    loop = false;
                    break;

                // Venture Mode
                case 2:
                    System.out
                            .print("\n1) Low\n2) Medium\n3) High\n4) Risk Details\n5) Exit\nEnter your Risk choice: ");
                    int Risk = sc.nextInt();

                    switch (Risk) {

                        // Low Risk
                        case 1:
                            System.out.print("Enter the Venture Amount: ");
                            double Bet_Amount = sc.nextDouble();
                            // If Bet Amount is less than the User Balance then proceed else Insufficient
                            if (Bet_Amount <= Game.User.balance) {
                                Game.User.balance -= Bet_Amount;
                                Game.gameBalance += Bet_Amount;
                                bt.BetAmount(Bet_Amount, "Low");
                                ng.New_Game(bt);
                            } else {
                                System.out.println("\nInsufficient Balance");
                                System.out.println("Game Balance: " + Game.User.balance);
                                c.format("\nPress Any Key to proceed.\n");
                                c.readLine();
                            }
                            loop = false;
                            break;

                        // Medium Risk
                        case 2:
                            System.out.print("Enter the Venture Amount: ");
                            Bet_Amount = sc.nextDouble();
                            // If Bet Amount is less than the User Balance then proceed else Insufficient
                            if (Bet_Amount <= Game.User.balance) {
                                Game.User.balance -= Bet_Amount;
                                Game.gameBalance += Bet_Amount;
                                bt.BetAmount(Bet_Amount, "Medium");
                                ng.New_Game(bt);
                            } else {
                                System.out.println("\nInsufficient Balance");
                                System.out.println("Game Balance: " + Game.User.balance);
                                c.format("\nPress Any Key to proceed.\n");
                                c.readLine();
                            }
                            loop = false;
                            break;
                        case 3:
                            System.out.print("Enter the Venture Amount: ");
                            Bet_Amount = sc.nextDouble();
                            // If Bet Amount is less than the User Balance then proceed else Insufficient
                            if (Bet_Amount <= Game.User.balance) {
                                Game.User.balance -= Bet_Amount;
                                Game.gameBalance += Bet_Amount;
                                bt.BetAmount(Bet_Amount, "High");
                                ng.New_Game(bt);
                            } else {
                                System.out.println("\nInsufficient Balance");
                                System.out.println("Game Balance: " + Game.User.balance);
                                c.format("\nPress Any Key to proceed.\n");
                                c.readLine();
                            }
                            loop = false;
                            break;

                        // Risk Details
                        case 4:
                            System.out.println("\nRisk Details:-");
                            System.out.println(
                                    "Low:-\n- 0 Cat Card: 0x\n- 1 Cat Card: 0.5x\n- 2 Cat Card: 1.1x\n- 3 Cat Card: 2.5x\n- 4 Cat Card: 5x\n- 5 Cat Card: 7x");
                            System.out.println(
                                    "Medium:-\n- 0 Cat Card: 0x\n- 1 Cat Card: 0x\n- 2 Cat Card: 0.5x\n- 3 Cat Card: 3.5x\n- 4 Cat Card: 7x\n- 5 Cat Card: 10x");
                            System.out.println(
                                    "High:-\n- 0 Cat Card: 0x\n- 1 Cat Card: 0x\n- 2 Cat Card: 0x\n- 3 Cat Card: 5x\n- 4 Cat Card: 10x\n- 5 Cat Card: 15x");
                            loop = true;
                            c.format("\nPress Any Key to proceed.\n");
                            c.readLine();
                            break;
                        case 5:
                            loop = false;
                            break;
                        default:
                            System.out.println("Invalid Choice");
                            c.format("\nPress Any Key to proceed.\n");
                            c.readLine();
                            break;
                    }
                    break;
                case 3:
                    loop = false;
                    break;
                default:
                    System.out.println("Invalid Choice");
                    c.format("\nPress Any Key to proceed.\n");
                    c.readLine();
                    // GameMode.Mode();
                    break;
            }
        }
    }
}
