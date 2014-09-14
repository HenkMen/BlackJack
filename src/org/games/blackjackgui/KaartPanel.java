package org.games.blackjackgui;


import org.games.blackjack.Soort;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class KaartPanel extends JPanel {
    //private BufferedImage image;
    //private BufferedImage cardImage=createImage();
    private ArrayList<Integer[]> kaarten = new ArrayList<Integer[]>();
    private ArrayList<Integer[]> gedraaidekaarten = new ArrayList<Integer[]>();

    public KaartPanel(){
    }


    public ArrayList<Integer[]> getKaarten() {
        return kaarten;
    }
    public void addKaart(int aantal, Soort soort,Boolean turned){
        int soortnr=1;
        switch(soort) {
            case KLAVER:soortnr=4;
                break;
            case RUITEN:soortnr=3;
                break;
            case SCHOPPEN:soortnr=1;
                break;
            case HART:soortnr=2;
                break;
        }
        if(turned)addKaartTurned(aantal,soortnr);
        else addKaart(aantal,soortnr);
    }
    public void addKaart(int aantal, int soort){
        Integer[] t={aantal,soort};
        kaarten.add(t);
        repaint();
    }
    public void addKaartTurned(int aantal, int soort){
        Integer[] t={aantal,soort};
        gedraaidekaarten.add(t);
        repaint();
    }

    public void leegKaarten(){
        kaarten.clear();
        gedraaidekaarten.clear();
    }


    public BufferedImage createImage(int aantal, int soort, boolean turned)
    {
        // create a card image.
        int w=getWidth();  // should be 200
        int h=getHeight(); // should be 200
        double neww=(double)w*0.5+1;
        if(h==0)h=1;
        double newh=(double)h;

        if(turned){
            newh=(double)h*0.5+1;
            h=(int)newh;
            if(w==0)w=1;
            neww=w;
        }

        int cardWidth=(int) (neww), cardHeight=(int)(newh);
        BufferedImage image=new BufferedImage(cardWidth, cardHeight, BufferedImage.TYPE_INT_ARGB);


        // get a graphics object of the image for drawing.
        java.awt.Graphics2D gr = (java.awt.Graphics2D) image.getGraphics();

        // draw a white playing card
        gr.setColor(java.awt.Color.WHITE);
        gr.fillRect(0,0,cardWidth,cardHeight);
        // with black border
        gr.setColor(java.awt.Color.BLACK);
        gr.drawRect(0,0,cardWidth-1,cardHeight-1);

        // draw the "three of Spade"

        Font font=new Font("Dialog",Font.PLAIN, 20);
        gr.setFont(font);

        String soortstring="";
        switch(soort){
            case 1:soortstring="\u2660";// unicode for the "spade" character
                break;
            case 2:soortstring="\u2665";   // unicode for the "heart" character
                gr.setColor(Color.red);
                break;
            case 3:soortstring="\u2666";   // unicode for the "diamond" character
                gr.setColor(Color.red);
                break;
            case 4:soortstring="\u2663"; //unicode for the "clover" character
                break;
        }

        String typekaart = Integer.toString(aantal);
        if(aantal==1)typekaart="A";
        if(aantal==11)typekaart="B";
        if(aantal==12)typekaart="Q";
        if(aantal==13)typekaart="K";




        if(turned){

            //Start draaien van graphic canvas
            AffineTransform old = gr.getTransform();
            //Draai 270 graden
            gr.rotate(Math.toRadians(270));
            //Bovenkant type en aantal
            gr.drawString(typekaart,-16,25);
            gr.drawString(soortstring,-21,45);
            //Plaats ze op het graphic canvas
            gr.setTransform(old);
            //Gebruik opnieuw
            old = gr.getTransform();
            //Draai 90 graden
            gr.rotate(Math.toRadians(90));
            //Onderkant type en aantal
            gr.drawString(typekaart,h-16,-getWidth()+25);
            gr.drawString(soortstring,h-21,-getWidth()+45);
            //Plaats ze op het canvas
            gr.setTransform(old);

        }
        else {
            //Bovenkant type en aantal
            gr.drawString(typekaart,5,25);
            gr.drawString(soortstring,2,45);
            //Start draaien van graphic canvas
            AffineTransform old = gr.getTransform();
            //Draai 180 graden
            gr.rotate(Math.toRadians(180));
            //Onderkant type en aantal
            gr.drawString(soortstring,-((int) neww-2),-(getHeight()-45));
            gr.drawString(typekaart,-((int) neww-5),-(getHeight()-25));
            //Plaats gedraaide strings
            gr.setTransform(old);
        }



        return image;
    }

    protected void paintComponent(java.awt.Graphics g){
        super.paintComponent(g);


        // get panel dimension
        int w=getWidth();  // should be 200
        int h=getHeight(); // should be 200
        int paddingcards=20;

        // create a Graphics2D object for drawing shape
        java.awt.Graphics2D gr=(java.awt.Graphics2D) g;
        int loc=0;
        for(int i=0;i<kaarten.size();i++){

            if((double)w*0.5+1+loc*paddingcards>getWidth())loc=0;
            gr.drawImage(createImage(kaarten.get(i)[0],kaarten.get(i)[1],false),loc*paddingcards,0,this);  // draw a card
            loc++;

        }

        //Create cards for doubled option
        loc=0;

        for(int i=0;i<gedraaidekaarten.size();i++){

            if((double)h*0.5+1+loc*paddingcards>getHeight())loc=0;
            gr.drawImage(createImage(gedraaidekaarten.get(i)[0],gedraaidekaarten.get(i)[1],true),0,loc*paddingcards,this);  // draw a card
            loc++;

        }

    }


    public static void main(String[] args) {
        // TODO Auto-generated method stub
        JFrame thisClass = new JFrame();
        thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //thisClass.setLayout(new GridBagLayout());
        thisClass.setVisible(true);
        thisClass.setSize(600,600);

        KaartPanel p = new KaartPanel();
        p.addKaart(1, 2);
        p.addKaart(13, 1);
        p.addKaart(12, 4);
        p.addKaart(11, 3);
        p.addKaart(10, 2);
        p.addKaartTurned(3, 4);
        p.addKaartTurned(3, 4);
        p.setSize(600, 600);
        p.setPreferredSize(new Dimension(600,600));
        thisClass.setContentPane(p);
    }

}