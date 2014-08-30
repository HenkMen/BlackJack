package org.games.blackjack;

import java.util.ArrayList;

public class Persoon {
    //Slechts één hand; dat is wat de dealer heeft. De spelers kunnen meer handen hebben
    //en daarom wordt onderstaande code overrided
    protected Hand hand=new Hand();
    //Krijg een kaart aan de hand

    /**
     * Geef de hand een kaart
     * @param kaart kaart die in ontvangst wordt genomen door hand
     */
    public void krijgKaart(Kaart kaart){
        hand.voegKaartToe(kaart);
    }
    //Geef de totaalwaarde van de hand

    /**
     * Geef de totaal waarde van de hand
     * @return totaal waarde hand
     */
    public int[] getWaardeHand(){
        return hand.geefTotaalWaarde();
    }

    /**
     * Pak de kaarten uit de hand en maak een nieuwe hand.
     * @return de kaarten uit de hand
     */
    public ArrayList<Kaart> verwijderKaarten(){
        ArrayList<Kaart> kaarten=hand.getKaarten();
        //Verwijder oude hand (dmv garbage collection) en maak nieuwe
        hand=new Hand();
        //En geef gebruikte kaarten terug
        return kaarten;
    }
}
