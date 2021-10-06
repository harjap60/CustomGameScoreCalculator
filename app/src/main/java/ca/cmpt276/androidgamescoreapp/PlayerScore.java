package ca.cmpt276.androidgamescoreapp;

public class PlayerScore {
    private int cards;

    private int cards_sum;
    private int wager_cards;
    //Constructor takes in all main parameters to get created

    public PlayerScore(int cards, int sum, int wager_cards) {
        //Throw exceptions for invalid arguments
        if(cards < 0){
            throw new IllegalArgumentException("Negative integers are cards are not applicable.");
        }

        if(sum < 0){
            throw new IllegalArgumentException("Negative integers for sum is not applicable.");
        }

        if(wager_cards < 0){
            throw new IllegalArgumentException("Negative integers for wagers are not applicable.");
        }
        this.cards = cards;
        cards_sum = sum;
        this.wager_cards = wager_cards;
    }
    //Returns the current players total points
    //(Cards - 20) * (wager_cards + 1) + 20(if (cards + wager_cards) > 8)

    public int getPoints() {
        if(cards == 0) return 0; //If cards equal 0, ignore all other contraints
        int points = cards_sum - 20;
        if(wager_cards > 0){
            points *= (wager_cards + 1);
        }
        if((cards + wager_cards) > 7){
            points += 20;
        }
        return points;
    }

    public int getCards() {
        return cards;
    }

    public int getCards_sum() {
        return cards_sum;
    }

    public int getWager_cards() {
        return wager_cards;
    }
}
