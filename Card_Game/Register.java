package Card_Game;

import java.util.ArrayList;

class Register {
    String User_Name;
    String Password;
    int Age;
    long Mobile;
    int Total_Game = 0;
    int Win_Game = 0;
    int Lose_Game = 0;
    double balance = 0;
    boolean balanceDetails = false;
    String accountNumber;
    String cvv;
    ArrayList<BetFormat> betRecords = new ArrayList<>();

    public boolean User_Details(String Username, String pw, int Age, long Mobile) {
        for (int i = 0; i < Game.UserDetails.size(); i++) {
            if (Game.UserDetails.get(i).User_Name.equals(Username)) {
                return false;
            }
        }
        this.User_Name = Username;
        this.Password = pw;
        this.Age = Age;
        this.Mobile = Mobile;
        return true;
    }

    public void Display() {
        System.out.println("Name: " + User_Name);
        System.out.println("Age: " + Age);
        System.out.println("Mobile: " + Mobile);
        System.out.println("Total Game: " + Total_Game);
        System.out.println("Win Game: " + Win_Game);
        System.out.println("Lose Game: " + Lose_Game);
        System.out.println("Balance: " + balance);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("CVV: " + cvv);
    }
}
