package org.games.blackjack;

public class Dealer extends Persoon{
    public boolean isDealerOver17(){
        return hand.geefTotaalWaarde()[0]>=17||hand.isBlackJack();
    }
    public boolean isBlackJack(){
        return hand.isBlackJack();
    }
    public boolean isFirstAce(){
        return hand.isFirstAce();
    }

}
