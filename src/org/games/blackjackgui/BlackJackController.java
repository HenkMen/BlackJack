package org.games.blackjackgui;

import org.games.blackjack.Soort;

/**
 * Created by rafaelabreed on 08-09-14.
 */
public interface BlackJackController {
    void geefGeld(int i, int g);

    void geefInzet(int i, int inzet);

    void geefInvoer();

    void geefKaart(int i, int waarde, Soort soort);

    void geefOpties(boolean[] bs);

    void spelerPass(int i);

    void kaartenZijnGesplitst(int i);

    void inzetVerdubbeld(int i);

    void verzekering(int i);

    //Zet gekozen nummer in dit geval om. Hiervoor in de plaats komt straks
    //dat iedere button een eigen functie in gameload uitvoert.
    void voerGekozenOptieUit(int gekozenOptie);

    void blackJack(int speler);

    void geefStatusHand(int speler, int status, int hand);

    void geefWaarde(int speler, int[] waarde);

    void leegGebruikteKaarten();
}
