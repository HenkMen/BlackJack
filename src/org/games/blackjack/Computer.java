package org.games.blackjack;

public class Computer extends Speler{
    public int bepaalOptie(boolean[] opties){
        int optie;

        if(opties[3]){
            optie=3;
        }
        else if(opties[2]){
            optie=2;

        }
        else if(opties[4]){
            optie=4;
        }
        else if(opties[1]&&this.getPass()){
            optie=1;
        }
        else if(opties[0]&&this.getWaardeHand()[1]<17){
            optie=0;
        }
        else{
            optie=1;
        }
        return optie;
    }

}
