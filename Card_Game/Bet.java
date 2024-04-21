package Card_Game;

class Bet {
    double betAmount;
    String betType;
    boolean betStatus;

    Bet() {
        this.betAmount = 0;
        this.betStatus = false;
        this.betType = "";
    }

    public void BetAmount(double amount, String type) {
        this.betAmount = amount;
        this.betStatus = true;
        this.betType = type;
    }
}
