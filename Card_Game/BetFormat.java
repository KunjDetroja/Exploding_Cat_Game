package Card_Game;

class BetFormat {
    double betAmount;
    double multiplier;
    double payout;

    public BetFormat(double betAmount, double multiplier) {
        this.betAmount = betAmount;
        this.multiplier = multiplier;
        this.payout = betAmount * multiplier;
    }
}
