package games.blackjack;

import java.util.ArrayList;
import java.util.Collections;

public class KaartSpel {
    private ArrayList<Kaart> ongebruikteKaartDeck=new ArrayList<Kaart>();
    private ArrayList<Kaart> gebruikteKaartDeck=new ArrayList<Kaart>();

    //Min 1, max 6 kaartspellen; toevoegen aan deck
    public KaartSpel(int aantalKaartSpellen){
        //Maak de kaartspellen aan
        geefKaartSpel(aantalKaartSpellen);
        schudKaarten();
    }

    //Schud ongebruikte kaarten
    public void schudKaarten(){
        Collections.shuffle(ongebruikteKaartDeck);
    }

    //Geef een kaart uit het deck
    public Kaart geefKaart(){
        return ongebruikteKaartDeck.remove(0);
    }

    //Bij einde spel voeg gebruikte kaarten toe aan gebruikte KaartDeck
    public void voegGebruikteKaartenToe(ArrayList<Kaart> kaarten){
        gebruikteKaartDeck.addAll(kaarten);
    }
    //Return true als gebruikte kaartdeck groter is dan ongebruikte kaartdeck
    public boolean isOngebruikteKaartenOnderHelft(){
        return gebruikteKaartDeck.size()>ongebruikteKaartDeck.size();
    }

    //Indien deck over helft is, voeg gebruikte kaarten toe en schud
    public void voegGebruikteKaartenToe(){
        ongebruikteKaartDeck.addAll(gebruikteKaartDeck);
        schudKaarten();
    }

    //Maak kaartspel(len) aan
    private void geefKaartSpel(int aantalKaartSpellen){
        //Aantal kaartdecks die gekozen zijn
        for(int i=1;i<=aantalKaartSpellen;i++){
            //Aantal waardes (1=Aas; 11=Boer; 12=Vrouw; 13=Heer)
            for(int j=1;j<=13;j++){
                //Aantal soorten
                for(Soort s:Soort.values()){
                    ongebruikteKaartDeck.add(new Kaart(j,s));
                }
            }
        }
    }
}
