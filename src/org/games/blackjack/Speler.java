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
    //Voeg inzet aan eerste hand toe
    public void addInzet(int inzet){
        hand.get(0).setInzet(inzet);
        geld-=inzet;
    }
    //Geef bedrag van speler
    public int getGeld(){
        return geld;
    }
    //Trek bedraf af van geld
    public void changeGeld(int i){
        geld-=i;
    }
    //Geef kaart aan actuele hand
    @Override
    public void krijgKaart(Kaart kaart){
        hand.get(cI).voegKaartToe(kaart);
    }
    //Controleer of actuele hand splitsbaar is
    public boolean isSplittable(){
        return hand.get(cI).isSplittable();
    }
    //Splits de actuele hand
    public void splitKaarten(){
        Hand nieuwehand=new Hand();
        nieuwehand.voegKaartToe(hand.get(cI).splitKaarten());
        nieuwehand.setInzet(hand.get(cI).getInzet());
        changeGeld(hand.get(cI).getInzet());
        hand.add(nieuwehand);
    }
    public int doubleInzet(){
        changeGeld(hand.get(cI).getInzet());
        hand.get(cI).doubleInzet();
        return hand.get(cI).getInzet();
    }
    //Geef aan of er nog een hand is
    public boolean isNogEenHand(){
        return hand.size()>cI+1;
    }
    //Verplaats Iterator indien nog een hand aanwezig en return true
    public boolean volgendeHand(){
        if(isNogEenHand()){
            cI++;
            return true;
        }
        return false;
    }
    //Zet iterator weer aan het begin (0)
    public void resetCI(){
        cI=0;
    }
    //Verander de status van de hand
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
    public int[] getWaardeHand(){
        return hand.get(cI).geefTotaalWaarde();
    }
    //Controleer of hand blackjack heeft
    public boolean isBlackJack(){
        return hand.get(cI).isBlackJack();
    }
    //Verwijder alle handen; geef verwijderde kaarten terug zodat deze in
    //gebruiktekaartendeck gestopt kunnen worden. Maak weer één lege nieuwe hand aan en reset iterator.
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
    public boolean isDoubable() {
        if(hand.get(cI).geefAantalKaarten()==2&&(hand.get(cI).geefTotaalWaarde()[0]==9||hand.get(cI).geefTotaalWaarde()[0]==10||hand.get(cI).geefTotaalWaarde()[0]==11)){
            return true;
        }
        // TODO Auto-generated method stub
        return false;
    }
    /*public int getHandSize(){
        return hand.size();
    }*/
    public int[] getHandenStatus(){
        int[] handstatus=new int[hand.size()];
        for(int i=0;i<hand.size();i++){
            handstatus[i]=hand.get(i).getStatus();
        }
        return handstatus;
    }
    public void passTrue(){
        hand.get(cI).setPass(true);
    }
    public boolean getPass(){
        return hand.get(cI).getPass();
    }
    public void setInsurance(){
        insurance=hand.get(cI).getInzet();
    }
    public boolean isOver21(){

        if(hand.get(cI).geefTotaalWaarde()[0]>21){
            hand.get(cI).setPass(true);
            if(volgendeHand())return false;

            return true;
        }
        return false;
    }
    public int getAantalKaartenEersteHand(){
        return hand.get(0).geefAantalKaarten();
    }
    public boolean isInsured(){
        return insurance>0;
    }
    public void payInsurance(){
        geld+=(insurance);
        insurance=0;
    }

}
	

