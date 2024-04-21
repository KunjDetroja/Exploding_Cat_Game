package Card_Game;

import java.util.ArrayList;
import java.util.Random;

class RandomCard {
    Random rand = new Random();
    String[] Card = { "Cat", "Defuse", "Shuffle", "Explode" };
    ArrayList<String> User_Card = new ArrayList<String>();

    public ArrayList<String> Random_Card() {
        for (int i = 0; i < 5; i++) {
            User_Card.add(Card[rand.nextInt(4)]);
        }
        return User_Card;
    }
}
