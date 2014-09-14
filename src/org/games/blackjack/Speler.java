package org.games.blackjack;

import java.util.ArrayList;

public class Speler extends Persoon{
    private static final int PAYBJ = 3;
    //Maak van hand een array
    protected ArrayList<Hand> hand=new ArrayList<Hand>();
    //Gebruik currentIterator om door handen te scrollen
    protected int cI=0;
    //Standaard beginnen met 500
    protected int geld=500;
    //Insurance eigenschap; is insurance *2; omdat dat uitbetaling is en er met int wordt gewerkt (anders gaat het mis)
    private int insurance=0;
    //Maak nieuwe speler aan met gelijk een hand
    public Speler(){
        hand.add(new Hand());
    }

    /**
     * Voeg de inzet aan de eerste hand toe
     * @param inzet de inzet van de speler
     */
    public void addInzet(int inzet){
        hand.get(0).setInzet(inzet);
        geld-=inzet;
    }

    /**
     * Vraag het geld van de speler op
     * @return het geld
     */
    public int getGeld() {
        return geld;
    }

    /**
     * Trek het bedrag i af van het geld
     * @param i het af te trekken bedrag
     */
    public void changeGeld(int i){

        geld-=i;
    }
    //Geef kaart aan actuele hand

    /**
     * Geef de actuele hand een kaart
     * @param kaart kaart die in ontvangst wordt genomen door hand
     */
    @Override
    public void krijgKaart(Kaart kaart){

        hand.get(cI).voegKaartToe(kaart);
    }

    /**
     * Controleer of actuele hand splitsbaar is
     * @return true als hand splitsbaar is; false zo niet
     */
    public boolean isSplittable(){

        return hand.get(cI).isSplittable();
    }

    /**
     * Splits de actuele hand, zodat er een nieuwe hand ontstaat
     */
    public void splitKaarten(){
        Hand nieuwehand=new Hand();
        nieuwehand.voegKaartToe(hand.get(cI).splitKaarten());
        nieuwehand.setInzet(hand.get(cI).getInzet());
        changeGeld(hand.get(cI).getInzet());
        hand.add(nieuwehand);
    }

    /**
     * Verdubbel de inzet
     * @return de nieuwe inzet
     */
    public int doubleInzet(){
        changeGeld(hand.get(cI).getInzet());
        hand.get(cI).doubleInzet();
        return hand.get(cI).getInzet();
    }

    /**
     * Controleer of er nog een hand is
     * @return true als er nog een hand is; false zo niet
     */
    public boolean isNogEenHand(){
        return hand.size()>cI+1;
    }


    /**
     * Verplaats Iterator indien nog een hand aanwezig is en return true (Verplaats naar volgende hand)
     * @return true als naar een volgende hand is verplaatst; false zo niet
     */
    public boolean volgendeHand(){
        if(isNogEenHand()){
            cI++;
            return true;
        }
        return false;
    }

    /**
     * Zet iterator weer aan het begin (0) (Ga naar eerste hand)
     */
    public void resetCI(){
        cI=0;
    }

    /**
     * Controleer status en geef geldberekening
     * Verander de status van de hand
     * @param status de status van de hand van de speler
     */
    public void setStatusHand(int status){
        //Indien gewonnen geef dubbele inzet terug
        if(status==1)geld+=hand.get(cI).getInzet()*2;
        //Indien gelijkspel geef inzet terug
        if(status==3)geld+=hand.get(cI).getInzet();
        //Indien BlackJack geef PAYBJ terug
        if(status==4)geld+=hand.get(cI).getInzet()*PAYBJ;
        hand.get(cI).setStatus(status);
    }
    //Geef de totale waarde van de hand

    /**
     * Geef de totale waarde van de hand; zo dat er twee waardes zijn indien een aas gegeven is
     * [0] is aas met als waarde 1; [1] is aas met als waarde 11 (indien aanwezig)
     * @return int[] waardes van de kaarten van de hand
     */
    public int[] getWaardeHand() {
        return hand.get(cI).geefTotaalWaarde();
    }

    /**
     * Controleer of de actuele hand een BlackJack is
     * @return
     */
    public boolean isBlackJack(){
        return hand.get(cI).isBlackJack();
    }


    /**
     * Verwijder alle handen; geef verwijderde kaarten terug zodat deze in
     * gebruiktekaartendeck gestopt kunnen worden. Maak weer één lege nieuwe hand aan en reset iterator.
     * @return kaarten van handen
     */
    public ArrayList<Kaart> verwijderHanden(){
        ArrayList<Kaart> kaarten=new ArrayList<Kaart>();
        for(Hand h:hand){
            kaarten.addAll(h.getKaarten());
        }
        hand.clear();
        hand.add(new Hand());
        resetCI();
        return kaarten;
    }

    /**
     * Controleer of verdubbeld kan worden met de actuele hand
     * @return true als verdubbeld kan worden; false zo niet
     */
    public boolean isDoubable() {
        if(hand.get(cI).geefAantalKaarten()==2&&(hand.get(cI).geefTotaalWaarde()[0]==9||hand.get(cI).geefTotaalWaarde()[0]==10||hand.get(cI).geefTotaalWaarde()[0]==11)){
            return true;
        }
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * Geef de statussen van alle handen van de speler
     * @return de statussen als int[] van de speler
     */
    public int[] getHandenStatus(){
        int[] handstatus=new int[hand.size()];
        for(int i=0;i<hand.size();i++){
            handstatus[i]=hand.get(i).getStatus();
        }
        return handstatus;
    }

    /**
     * Zet pass op true voor hand van de speler
     */
    public void passTrue(){
        hand.get(cI).setPass(true);
    }

    /**
     * Controleer pass van actuele hand van speler
     * @return true als actuele hand pass=true heeft; false zo niet
     */
    public boolean getPass(){
        return hand.get(cI).getPass();
    }

    /**
     * Stel de verzekering in
     */
    public void setInsurance(){
        insurance=hand.get(cI).getInzet();
    }

    /**
     * Controleer of speler over de 21 heen is
     * @return true als speler over 21 is; false zo niet
     */
    public boolean isOver21(){

        if(hand.get(cI).geefTotaalWaarde()[0]>21){
            hand.get(cI).setPass(true);
            if(volgendeHand())return false;

            return true;
        }
        return false;
    }

    /**
     * Geef aantal kaarten van de eerste hand
     * @return aantal kaarten van de eerste hand
     */
    public int getAantalKaartenEersteHand(){
        return hand.get(0).geefAantalKaarten();
    }

    /**
     * Controleer of er (al) een verzekering is
     * @return true als verzekering hoger dan 0 is; false zo niet
     */
    public boolean isInsured(){
        return insurance>0;
    }

    /**
     * Betaal verzekering uit
     */
    public void payInsurance(){
        geld+=(insurance);
        insurance=0;
    }

}
	

