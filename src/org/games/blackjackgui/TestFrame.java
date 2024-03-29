package org.games.blackjackgui;

import java.util.Scanner;

import org.games.blackjack.GameLoad;
import org.games.blackjack.Soort;

public class TestFrame implements BlackJackController {
    GameLoad gl;
    public TestFrame(GameLoad gl){
        this.gl=gl;
    }

    public static void main(String[] args) {
        GameLoad gl=new GameLoad(3,2);

        gl.startSpel(5);
    }

    @Override
    public void geefGeld(int i, int g){
        System.out.println("Speler "+i+" heeft "+g+" aan geld.");
    }
    @Override
    public void geefInzet(int i, int inzet){
        System.out.println("Speler "+i+" zet "+inzet+" aan inzet in.");
    }
    @Override
    public void geefInvoer(){
        Scanner in=new Scanner(System.in);
        System.out.print("Nieuw spel beginnen? (Ja, J, j of ja)");

        String an=in.next();
        if(an.equals("Ja")||an.equals("J")||an.equals("j")||an.equals("ja")){
            gl.resetSpel();
            gl.startSpel(5);
        }
        else geefInvoer();
    }
    @Override
    public void geefKaart(int i, int waarde, Soort soort) {
        Soort kaartsoort=soort;
        if(i==gl.getSizeSpelers()-1)System.out.println("Dealer krijgt kaart "+waarde);
        else System.out.println("Speler "+i+" krijgt kaart "+waarde);
    }


    @Override
    public void geefOpties(boolean[] bs) {

        // TODO Auto-generated method stub
        System.out.print("Beschikbare opties: ");
        for(int i=0;i<bs.length;i++){
            if(bs[i]) {
                System.out.print("Optie " + i + " ");
            }
        }
        System.out.println();
        System.out.println("Optie0=Hit; Optie1=Stand; Optie2=Split; Optie3=Double; Optie4=Insurance; Optie5=Ga verder zonder iets nog te doen");
        Scanner gekozenoptie=new Scanner(System.in);
        System.out.print("Kies een optie: ");
        voerGekozenOptieUit(gekozenoptie.nextInt());


    }
    @Override
    public void spelerPass(int i){
        System.out.println("Speler "+i+" past.");
    }
    @Override
    public void kaartenZijnGesplitst(int i){
        System.out.println("Kaarten zijn gesplitst voor speler "+i);
    }
    @Override
    public void inzetVerdubbeld(int i){
        System.out.println("Inzet is verdubbeld voor speler "+i);
    }
    @Override
    public void verzekering(int i){
        System.out.println("Verzekering voor speler "+i);
    }
    //Zet gekozen nummer in dit geval om. Hiervoor in de plaats komt straks
    //dat iedere button een eigen functie in gameload uitvoert.
    @Override
    public void voerGekozenOptieUit(int gekozenOptie){
        switch(gekozenOptie){
            case 0:gl.hit(0);
                gl.speelOverigeSpelers();
                break;
            case 1:gl.pass(0);
                gl.speelOverigeSpelers();
                break;
            case 2:gl.split(0);
                //OOK GUI FUNCTIE WAARIN ZICHTBAAR KAARTEN WORDEN GESPLITST!
                gl.speelOverigeSpelers();
                break;
            case 3:gl.doubleInzet(0);
                gl.speelOverigeSpelers();
                break;
            case 4:gl.insure(0);
                gl.speelOverigeSpelers();
                break;
            case 5:
                gl.speelOverigeSpelers();
                //gl.geefStatusSpelers();
                break;
        }

    }
    @Override
    public void blackJack(int speler){
        System.out.println("BlackJack voor speler "+speler);
    }

    @Override
    public void geefStatusHand(int speler, int status, int hand){
        String statusst="";
        if(status==1)statusst="gewonnen";
        if(status==2)statusst="verloren";
        if(status==3)statusst="gelijkgespeeld";
        if(status==4)statusst="blackjack";
        System.out.println("Speler "+speler+" heeft "+statusst+" met hand "+hand);
    }
    @Override
    public void geefWaarde(int speler, int[] waarde){
        System.out.println("Speler "+speler+" heeft nu waarde "+waarde[0]+" - "+waarde[1]);
    }
    @Override
    public void leegGebruikteKaarten(){
        System.out.println("Gebruikte kaarten worden bij ongebruikte kaarten gevoegd en geschud.");
    }


}
