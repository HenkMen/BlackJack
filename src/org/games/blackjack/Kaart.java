package org.games.blackjack;

public class Kaart {
    private int waarde;
    private Soort soort;

    public Kaart(int waarde, Soort soort) {
        this.waarde = waarde;
        this.soort = soort;
    }

    public int getWaarde() {
        return waarde;
    }

    public Soort getSoort() {
        return soort;
    }



}
