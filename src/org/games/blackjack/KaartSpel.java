package org.games.blackjack;

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



    /**
     * Schud de ongebruikte kaarten
     */
    public void schudKaarten(){
        Collections.shuffle(ongebruikteKaartDeck);
    }


    /**
     * Geef een kaart uit de ongebruikte kaarten
     * @return Kaart
     */
    public Kaart geefKaart(){
        return ongebruikteKaartDeck.remove(0);
    }

    /**
     * Voeg de door spelers gebruikte kaarten van een spel toe aan de gebruikte kaarten
     * @param kaarten de spelers gebruikte kaarten
     */
    public void voegGebruikteKaartenToe(ArrayList<Kaart> kaarten){
        gebruikteKaartDeck.addAll(kaarten);
    }

    /**
     * Controleer of gebruikte kaarten groter is dan ongebruikte kaarten
     * @return true als gebruikte kaarten aantal groter is dan ongebruikte kaarten; false als niet
     */
    public boolean isOngebruikteKaartenOnderHelft(){
        return gebruikteKaartDeck.size()>ongebruikteKaartDeck.size();
    }

    /**
     * Als ongebruikte kaarten over de heflt is, voeg dan de gebruikte kaarten weer toe en schud
     */
    public void voegGebruikteKaartenToe(){
        ongebruikteKaartDeck.addAll(gebruikteKaartDeck);
        schudKaarten();
    }

    /**
     * Maak de kaartspel(len) aan
     * @param aantalKaartSpellen aantal gekozen kaartspellen
     */
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
