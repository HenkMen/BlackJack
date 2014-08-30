package org.games.blackjack;

public class Kaart {
    private int waarde;
    private Soort soort;

    public Kaart(int waarde, Soort soort) {
        this.waarde = waarde;
        this.soort = soort;
    }

    /**
     * Geef de waarde van de kaart
     * @return int waarde van kaart
     */
    public int getWaarde() {
        return waarde;
    }

    /**
     * Geef de soort van de kaart
     * @return Soort soort van kaart
     */
    public Soort getSoort() {
        return soort;
    }



}
