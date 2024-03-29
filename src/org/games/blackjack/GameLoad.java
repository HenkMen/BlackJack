package org.games.blackjack;

import java.util.ArrayList;
import java.util.Arrays;

import org.games.blackjackgui.BlackJackGuiHulp;
import org.games.blackjackgui.TestFrame;

public class GameLoad {

    ArrayList<Speler> spelers=new ArrayList<Speler>();
    Dealer dealer=new Dealer();
    KaartSpel kaartspel;
    Kaart uitdeelkaart;
    BlackJackGuiHulp tf;

    public GameLoad(int aantalKaartSpellen, int aantalComputers){
        //tf=new TestFrame(this);
        //Maak kaartenDeck aan met aantal gekozen kaartspellen
        kaartspel=new KaartSpel(aantalKaartSpellen);
        spelers.add(new Speler());
        //Maak computers aan indien dat gekozen is
        for(int i=0;i<aantalComputers;i++){
            spelers.add(new Computer());
        }
    }

    public GameLoad(int aantalKaartSpellen, int aantalComputers,BlackJackGuiHulp gui){
        tf=gui;
        //Maak kaartenDeck aan met aantal gekozen kaartspellen
        kaartspel=new KaartSpel(aantalKaartSpellen);
        spelers.add(new Speler());
        //Maak computers aan indien dat gekozen is
        for(int i=0;i<aantalComputers;i++){
            spelers.add(new Computer());
        }
        for(int i=0;i<spelers.size();i++){
            tf.geefGeld(i,spelers.get(i).getGeld());
            tf.geefInzet(i,5);
        }
    }

    //Als op start gedrukt wordt. Zet inzet in voor alle spelers.

    /**
     * Start een nieuwe ronde met de opgegeven inzet.
     * @param inzet de hoeveelheid inzet die de speler geeft
     */
    public void startSpel(int inzet){
        for(int i=0;i<spelers.size();i++){
            tf.geefGeld(i,spelers.get(i).getGeld());
            spelers.get(i).addInzet(inzet);
            tf.geefInzet(i,inzet);
        }
        beginKaarten();
    }
    //Eerste ronde kaarten uitdelen

    /**
     * Nieuwe ronde: Geef de spelers allemaal 2 kaarten en de dealer 1 kaart.
     */
    public void beginKaarten(){
        for(int i=0;i<spelers.size();i++){
            geefSpelerKaart(i);
            geefSpelerKaart(i);
            tf.geefWaarde(i, spelers.get(i).getWaardeHand());
            if(spelers.get(i).isBlackJack()){
                spelers.get(i).passTrue();
                tf.blackJack(i);
            }
        }
        //Geef dealer 1 kaart
        geefDealerKaart();
        //Geef opties aan speler
        geefOptiesDoor();
    }
    //Geef opties per spelronde van de gekozen speler i

    /**
     * Geef de opties voor speler i.
     * @param i de index waarde van de speler uit ArrayList<Speler> spelers
     * @return de opties als boolean[] terug
     */
    public boolean[] getOpties(int i){
        //Optie0=Hit; Optie1=Stand; Optie2=Split; Optie3=Double; Optie4=Insurance; Optie5=Restart
        boolean[] opties=new boolean[6];
        //Maak alle waardes false
        Arrays.fill(opties, Boolean.FALSE);
        if(!this.isSpelerOver21(i)&&!spelers.get(i).getPass()){
            opties[1]=true;
            if(!spelers.get(i).getPass()||spelers.get(i).getWaardeHand()[0]>21){
                opties[0]=true;
                if(spelers.get(i).isSplittable())opties[2]=true;
                if(spelers.get(i).isDoubable())opties[3]=true;
                //Alleen verzekering bij eerste zet
                if(dealer.isFirstAce()&&spelers.get(i).getAantalKaartenEersteHand()==2)opties[4]=true;
            }
        }

        else opties[5]=true;
        return opties;
    }

    /**
     * Controleer of speler i over 21 is.
     * @param i de index waarde van de speler uit ArrayList<Speler> spelers
     * @return true als speler over 21 is; false als niet
     */
    public boolean isSpelerOver21(int i){
        return spelers.get(i).isOver21();
    }

    /**
     * Controleer of alle spelers gepassed hebben
     * @return true als alle spelers gepassed hebben; false als één van de spelers niet gepassed heeft
     */
    public boolean isAlleSpelersPass(){
        for(Speler s:spelers){
            if(!s.getPass())return false;
        }
        return true;
    }


    /**
     * Speel de computers na de speler; indien spel over is voor alle spelers, geef dealer kaarten
     * tot min 17 is bereikt; geef dan de punten en invoerscherm om opnieuw te beginnen.
     */
    public void speelOverigeSpelers(){
        tf.geefWaarde(0, spelers.get(0).getWaardeHand());
        for(int i=1;i<spelers.size();i++){
            //Haal opties per computerspeler op
            boolean[] opties=getOpties(i);
            //Bepaal optie van computer
            int optie =((Computer) spelers.get(i)).bepaalOptie(opties);
            //Op basis van keuze voer gegeven optie uit
            switch(optie){
                case 0:hit(i);
                    break;
                case 1:pass(i);
                    tf.spelerPass(i);
                    break;
                case 2:split(i);
                    tf.kaartenZijnGesplitst(i);
                    //OOK GUI FUNCTIE WAARIN ZICHTBAAR KAARTEN WORDEN GESPLITST!
                    break;
                case 3:doubleInzet(i);
                    tf.inzetVerdubbeld(i);
                    break;
                case 4:insure(i);
                    tf.verzekering(i);
                    break;
            }
            if(spelers.get(i).getWaardeHand()[0]>21)spelers.get(i).volgendeHand();
            tf.geefWaarde(i, spelers.get(i).getWaardeHand());
        }
        //Als alle spelers klaar zijn, ga dan verder met de bank.
        if(isAlleSpelersPass()){
            geefDealerKaarten();
            //Spel is afgelopen. Controleer status van elke speler.
            setStatusSpelers();
            //Geef de statussen weer
            geefStatusSpelers();
            //Geef invoerscherm om opnieuw te beginnen
            tf.geefInvoer();
        }
        //Indien nog niet klaar, geef dan weer opties aan gui door enzovoorts...
        else{
            this.geefOptiesDoor();
        }
    }

    /**
     * Geef de bank kaarten totdat de waarde op 17 of hoger zit
     */
    public void geefDealerKaarten(){
        while(!isDealerOver17()){
            geefDealerKaart();
        }

    }

    /**
     * Geef opties door aan GUI voor speler
     */
    public void geefOptiesDoor(){
        tf.geefOpties(getOpties(0));
    }

    /**
     * Optie speel door met de hand (hit). Dus geef speler nog een kaart.
     * @param i de index waarde van de speler uit ArrayList<Speler> spelers
     */
    public void hit(int i){
        geefSpelerKaart(i);
    }

    /**
     * Optie pass; zet eigenschap pass hand op true
     * Bij een volgende hand; ga naar volgende hand
     * @param i de index waarde van de speler uit ArrayList<Speler> spelers
     */
    public void pass(int i){
        spelers.get(i).passTrue();
        spelers.get(i).volgendeHand();
    }

    /**
     * Splits de kaarten van de actuele hand
     * @param i de index waarde van de speler uit ArrayList<Speler> spelers
     */
    public void split(int i){
        spelers.get(i).splitKaarten();
    }


    /**
     * Verdubbel de inzet, geef speler nog één kaart en zet vervolgens op pass (Want je krijgt er maar eentje)
     * @param i de index waarde van de speler uit ArrayList<Speler> spelers
     */
    public void doubleInzet(int i){
        spelers.get(i).doubleInzet();
        geefSpelerKaart(i);
        pass(i);
    }

    /**
     * Indien bank aas heeft als eerste kaart, kan een verzekering worden ingesteld.
     * Hierbij wordt de verzekering van de speler op de helft van de inzet gezet.
     * @param i de index waarde van de speler uit ArrayList<Speler> spelers
     */
    public void insure(int i){
        spelers.get(i).setInsurance();
        geefSpelerKaart(i);
    }


    /**
     * Geef speler i een kaart
     * @param i de index waarde van de speler uit ArrayList<Speler> spelers
     */
    public void geefSpelerKaart(int i){
        //Gebruikte kaarten bij ongebruikte kaarten doen indien over helft
        if(kaartspel.isOngebruikteKaartenOnderHelft()){
            kaartspel.voegGebruikteKaartenToe();
            tf.leegGebruikteKaarten();
        }
        //Kaart ophalen
        uitdeelkaart=kaartspel.geefKaart();
        //Kaart naar GUI sturen
        tf.geefKaart(i,uitdeelkaart.getWaarde(),uitdeelkaart.getSoort());
        //Kaart aan speler geven
        spelers.get(i).krijgKaart(uitdeelkaart);
    }

    /**
     * Geef de dealer een kaart
     */
    public void geefDealerKaart(){
        //Gebruikte kaarten bij ongebruikte kaarten doen indien over helft
        if(kaartspel.isOngebruikteKaartenOnderHelft()){
            kaartspel.voegGebruikteKaartenToe();
            tf.leegGebruikteKaarten();
        }
        uitdeelkaart=kaartspel.geefKaart();
        tf.geefKaart(spelers.size(),uitdeelkaart.getWaarde(),uitdeelkaart.getSoort());
        dealer.krijgKaart(uitdeelkaart);
    }


    /**
     * Als spel afgelopen is; verwijder alle handen van zowel spelers als dealer
     */
    public void resetSpel(){
        for(Speler s:spelers){
            kaartspel.voegGebruikteKaartenToe(s.verwijderHanden());
        }
        kaartspel.voegGebruikteKaartenToe(dealer.verwijderKaarten());
    }


    /**
     * Controleer of dealer over 17 is
     * @return true als dealer over 17 is; false als niet
     */
    public boolean isDealerOver17(){
        return dealer.isDealerOver17();
    }


    /**
     * Controleer & zet status handen van de speler (gewonnen, verloren, gelijkspel)
     * @param i de index waarde van de speler uit ArrayList<Speler> spelers
     */
    public void setStatusSpeler(int i){
        spelers.get(i).resetCI();
        do{

            spelers.get(i).setStatusHand(checkStatus(i));

        }while(spelers.get(i).volgendeHand());
        spelers.get(i).resetCI();
    }
    public void setStatusSpelers(){
        for(int i=0;i<spelers.size();i++){
            setStatusSpeler(i);

        }

    }

    /**
     * Controleer de status van een hand van een speler i en geeft statuswaarde terug.
     * 1=Gewonnen;2=verloren;3=gelijkspel;4=blackjack gewonnen
     * @param i de index waarde van de speler uit ArrayList<Speler> spelers
     * @return statuswaarde
     */
    public int checkStatus(int i){
        int waarde=spelers.get(i).getWaardeHand()[1];
        //1=Gewonnen;2=verloren;3=gelijkspel;4=blackjack gewonnen

        //Gelijkspel: gelijk als bank
        //Verloren: hoger dan 21 of lager dan bank
        int status=0;
        //Alleen indien het spel is afgelopen mag status veranderd worden.
        if(isDealerOver17()){
            System.out.println(waarde+" - "+dealer.getWaardeHand()[1]);



                //Gewonnen: hoger dan bank of bank heeft hoger dan 21 en lager of gelijk aan 21
            if((waarde>dealer.getWaardeHand()[1]||dealer.getWaardeHand()[0]>21)&&waarde<=21)status=1;
            //Gelijkspel: gelijke waardes dealer en hand speler
            if(waarde==dealer.getWaardeHand()[1])status=3;
            //BlackJack: 21 en dealer geen BJ
            if(spelers.get(i).isBlackJack()&&!dealer.isBlackJack())status=4;
            //Als BlackJack, dan niet gewonnen status opgeven (anders zou geld dubbel worden gestort)
            //Verloren: hoger dan 21 of lager dan dealer als dealer lager of gelijk aan 21 is
            if(waarde>21||(waarde<dealer.getWaardeHand()[1]&&dealer.getWaardeHand()[1]<=21))status=2;

            //Indien speler verloren heeft en dealer blackjack had en speler verzekering had, krijgt speler
            //zijn inzet weer terug.
            if(dealer.isBlackJack()&&status==2&&spelers.get(i).isInsured())spelers.get(i).payInsurance();
        }
        return status;
    }

    /**
     * Geeft van alle spelers de status per hand
     */
    public void geefStatusSpelers(){
        for(int i=0;i<spelers.size();i++){
            int j=0;
            for(int s:spelers.get(i).getHandenStatus()){
                tf.geefStatusHand(i, s, j++);
            }


        }
    }

    /**
     * Geef de totale grootte van het aantal spelers & dealer
     * @return totale grootte aantal spelers en dealer
     */
    public int getSizeSpelers(){
        return spelers.size()+1;
    }
    public static void main(String[] args) {
        GameLoad gl=new GameLoad(3,2);
        gl.startSpel(5);

    }


}
