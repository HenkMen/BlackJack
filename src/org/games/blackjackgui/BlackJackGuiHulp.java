package org.games.blackjackgui;
import org.games.blackjack.GameLoad;
import org.games.blackjack.Soort;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by rafaelabreed on 08-09-14.
 */
public class BlackJackGuiHulp implements BlackJackController {

    private GameLoad spel;
    private BlackJackPane pnl;
    private int aantalSpelers=4;
    private int aantalKaartspellen=5;

    public BlackJackGuiHulp(BlackJackPane pnl){
        this.pnl=pnl;
        pnl.addBottomComponents();
        this.spel = new GameLoad(aantalKaartspellen,aantalSpelers,this);
        addActionsButton();
        geefInvoer();
    }

    public void addActionsButton(){
        //Optie0=Hit; Optie1=Stand; Optie2=Split; Optie3=Double; Optie4=Insurance; Optie5=Ga verder zonder iets nog te doen
            pnl.optionBttns[0].addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {

                    System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
                    voerGekozenOptieUit(0);
                }
            });
        pnl.optionBttns[1].addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {

                System.out.println("actionPerformed() + 1"); // TODO Auto-generated Event stub actionPerformed()
                voerGekozenOptieUit(1);
            }
        });
        pnl.optionBttns[2].addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {

                System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
                voerGekozenOptieUit(2);
            }
        });
        pnl.optionBttns[3].addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {

                System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
                voerGekozenOptieUit(3);
            }
        });
        pnl.optionBttns[4].addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {

                System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
                voerGekozenOptieUit(4);
            }
        });



    }

    public void geefGeld(int speler,int geld){

        pnl.geldTxt[speler].setText(geld+"");
    }
    @Override
    public void geefInzet(int speler, int inzet) {

        pnl.inzetTxt[speler].setText(inzet+"");
    }

    @Override
    public void geefInvoer() {
        for(int i=0;i<pnl.optionBttns.length;i++){
            if(i!=1)pnl.optionBttns[i].setEnabled(false);
        }
        for( ActionListener al : pnl.optionBttns[1].getActionListeners() ) {
            pnl.optionBttns[1].removeActionListener( al );
        }
        pnl.optionBttns[1].addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                System.out.println("actionPerformed() + nieuweronde");
                startNieuweRonde();
            }
        });

        pnl.optionBttns[1].setEnabled(true);
        pnl.optionBttns[1].setText("Start nieuw spel");
        pnl.inzetTxt[0].setEnabled(true);

    }
    public void startNieuweRonde(){
        pnl.bankKaarten.leegKaarten();
        for(KaartPanel k:pnl.kaartenCpu){
            k.leegKaarten();
        }
        int inzet=Integer.parseInt(pnl.inzetTxt[0].getText());
        spel.resetSpel();
        spel.startSpel(inzet);
        for( ActionListener al : pnl.optionBttns[1].getActionListeners() ) {
            pnl.optionBttns[1].removeActionListener( al );
        }

        pnl.optionBttns[1].addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                System.out.println("actionPerformed() + 1n");
                voerGekozenOptieUit(1);
            }
        });
        pnl.inzetTxt[0].setEnabled(false);
        pnl.optionBttns[1].setEnabled(true);
        pnl.optionBttns[1].setText("Pass");
        for(JLabel lbl:pnl.statuslbl) {
            lbl.setText("");
        }

    }
    //TODO DOUBLE APART INVOEREN!
    @Override
    public void geefKaart(int i, int waarde, Soort soort) {
        if(i==aantalSpelers+1)pnl.bankKaarten.addKaart(waarde,soort,false);
        else pnl.kaartenCpu[i].addKaart(waarde, soort, false);
    }

    @Override
    public void geefOpties(boolean[] bs) {

        for(int i=0;i<bs.length-1;i++) {
            if (bs[i]) {
                pnl.optionBttns[i].setEnabled(true);
            }
            else{
                pnl.optionBttns[i].setEnabled(false);
            }
        }
        if(bs[5]){

            for( ActionListener al : pnl.optionBttns[1].getActionListeners() ) {
                pnl.optionBttns[1].removeActionListener( al );
            }

            pnl.optionBttns[1].addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {

                    System.out.println("actionPerformed() + 5"); // TODO Auto-generated Event stub actionPerformed()
                    voerGekozenOptieUit(5);
                }
            });

            pnl.optionBttns[1].setEnabled(true);
            pnl.optionBttns[1].setText("Continue");
        }
    }

    @Override
    public void spelerPass(int i) {
        System.out.println("Speler "+i+" past.");
    }

    @Override
    public void kaartenZijnGesplitst(int i) {
        System.out.println("Kaarten zijn gesplitst voor speler "+i);
    }

    @Override
    public void inzetVerdubbeld(int i) {
        System.out.println("Inzet is verdubbeld voor speler "+i);
    }

    @Override
    public void verzekering(int i) {
        System.out.println("Verzekering voor speler "+i);
    }

    @Override
    public void voerGekozenOptieUit(int gekozenOptie) {
        switch (gekozenOptie) {
            case 0:
                spel.hit(0);
                spel.speelOverigeSpelers();
                break;
            case 1:
                spel.pass(0);
                spel.speelOverigeSpelers();
                break;
            case 2:
                spel.split(0);
                //OOK GUI FUNCTIE WAARIN ZICHTBAAR KAARTEN WORDEN GESPLITST!
                spel.speelOverigeSpelers();
                break;
            case 3:
                spel.doubleInzet(0);
                spel.speelOverigeSpelers();
                break;
            case 4:
                spel.insure(0);
                spel.speelOverigeSpelers();
                break;
            case 5:
                spel.speelOverigeSpelers();
                //gl.geefStatusSpelers();
                break;

        }
    }

    @Override
    public void blackJack(int speler) {


    }

    @Override
    public void geefStatusHand(int speler, int status, int hand) {
        String first=pnl.statuslbl[speler].getText();
        String statusst="";
        if(status==1)statusst="gewonnen";
        if(status==2)statusst="verloren";
        if(status==3)statusst="gelijkgespeeld";
        if(status==4)statusst="blackjack";

        pnl.statuslbl[speler].setText("<html>"+first+"<br> Speler "+speler+" heeft "+statusst+" met hand "+hand+"</html>");
    }

    @Override
    public void geefWaarde(int speler, int[] waarde) {
        String dewaarde=waarde[0]+"";
        if(waarde[0]!=waarde[1])dewaarde+=" of "+waarde[1];
        pnl.scorelbl[speler].setText(dewaarde);
    }

    @Override
    public void leegGebruikteKaarten() {

    }


    public static void main(String[] args) {
        // TODO Auto-generated method stub
        JFrame thisClass = new JFrame();
        thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //thisClass.setLayout(new GridBagLayout());
        thisClass.setVisible(true);
        thisClass.setSize(600, 600);
        BlackJackPane p = new BlackJackPane();
        p.setSize(600, 600);
        p.setPreferredSize(new Dimension(600,600));
        thisClass.setContentPane(p);
        BlackJackGuiHulp bjgh=new BlackJackGuiHulp(p);
    }



}
