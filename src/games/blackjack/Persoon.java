package games.blackjack;

import java.util.ArrayList;

public class Persoon {
    //Slechts één hand; dat is wat de dealer heeft. De spelers kunnen meer handen hebben
    //en daarom wordt onderstaande code overrided
    protected Hand hand=new Hand();
    //Krijg een kaart aan de hand
    public void krijgKaart(Kaart kaart){
        hand.voegKaartToe(kaart);
    }
    //Geef de totaalwaarde van de hand
    public int[] getWaardeHand(){
        return hand.geefTotaalWaarde();
    }
    public ArrayList<Kaart> verwijderKaarten(){
        ArrayList<Kaart> kaarten=hand.getKaarten();
        //Verwijder oude hand (dmv garbage collection) en maak nieuwe
        hand=new Hand();
        //En geef gebruikte kaarten terug
        return kaarten;
    }
}
