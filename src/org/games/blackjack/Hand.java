package org.games.blackjack;

import java.util.ArrayList;

public class Hand {
    private ArrayList<Kaart> kaarten=new ArrayList<Kaart>();
    private boolean pass=false;
    private int inzet=0;
    //Status 0=Onbekend; status 1=gewonnen; status 2=verloren; status 3=gelijkspel; status 4=blackjack
    private int status=0;


    public void setInzet(int inzet){
        this.inzet=inzet;
    }
    public int getInzet(){
        return inzet;
    }
    public void doubleInzet(){
        inzet=inzet*2;
    }
    public void setPass(boolean pass){
        this.pass=pass;
    }
    public boolean getPass(){
        return pass;
    }
    public void setStatus(int status){
        this.status=status;
    }
    public int getStatus(){
        return status;
    }

    public void voegKaartToe(Kaart kaart){
        kaarten.add(kaart);
    }
    public ArrayList<Kaart> getKaarten(){
        return kaarten;
    }
    public boolean isBlackJack(){
        return kaarten.size()==2&&geefTotaalWaarde()[1]==21;
    }
    public boolean isSplittable(){
        return kaarten.size()==2&&kaarten.get(0).getWaarde()==kaarten.get(1).getWaarde();
    }
    public Kaart splitKaarten(){
        return kaarten.remove(1);
    }
    public int[] geefTotaalWaarde(){
        int[] totaalWaarde=new int[2];
        boolean bevatAas=false;
        for(Kaart k:kaarten){
            int waarde=k.getWaarde();
            if(waarde==11||waarde==12||waarde==13)waarde=10;
            totaalWaarde[0]+=waarde;
            totaalWaarde[1]+=waarde;
            //Indien er nog geen aas is, wordt de waarde van deze aas in totaalwaarde[1] 11 (1+10)..
            //Daarna geen aas met waarde 11 meer gebruiken..
            if(waarde==1&&!bevatAas){
                totaalWaarde[1]+=10;
                bevatAas=true;
            }
        }
        //Indien aas totaalwaarde beinvloedt en de totaalwaarde over 21 gaat,
        //dan mag totaalwaarde gelijk zijn..
        if(totaalWaarde[1]!=totaalWaarde[0]&&totaalWaarde[1]>21)totaalWaarde[1]=totaalWaarde[0];
        return totaalWaarde;
    }
    public boolean isFirstAce(){
        return kaarten.size()==1&&kaarten.get(0).getWaarde()==1;
    }
    public int geefAantalKaarten(){
        return kaarten.size();
    }

}
