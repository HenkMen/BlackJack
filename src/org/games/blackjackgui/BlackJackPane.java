package org.games.blackjackgui;


import java.awt.GridBagLayout;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.BorderFactory;
import java.awt.SystemColor;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.JButton;

public class BlackJackPane extends JPanel {

    private static final long serialVersionUID = 1L;

    protected JPanel restKaarten;
    protected KaartPanel bankKaarten;
    protected JPanel kaartenSpel;
    protected KaartPanel[] kaartenCpu=new KaartPanel[5];
    protected JPanel[] geldCpu=new JPanel[5];
    protected JLabel[] scorelbl=new JLabel[5];
    protected JLabel[] statuslbl=new JLabel[5];
    protected JTextField[] geldTxt=new JTextField[5];
    protected JTextField[] inzetTxt=new JTextField[5];
    protected JPanel bottomPanel;
    protected JPanel regelsPnl;
    protected JButton insertBttn;
    protected JButton dealBttn;
    protected JButton passBttn;
    protected JButton doubleBttn;
    protected JButton splitBttn;
    protected JButton[] optionBttns = new JButton[5];

    protected JLabel status;

    // transform it  //  @jve:decl-index=0:
    //private GridBagConstraints cons = new GridBagConstraints();  //  @jve:decl-index=0:
    /**
     * This is the default constructor
     */
    public BlackJackPane() {
        super();
        // BlackJackGuiHulp guih=new BlackJackGuiHulp(this);
        initialize();
        mijnInit();
    }


    /**
     * This method initializes this
     *
     * @return void
     */
    private void initialize() {

        this.setBorder(BorderFactory.createLineBorder(Color.white, 5));
        this.setBackground(new Color(59, 102, 0));
        this.setLayout(new GridBagLayout());
        this.setSize(800, 600);

    }
    private void mijnInit(){
        restKaarten = new JPanel();
        restKaarten.setLayout(new GridBagLayout());
        restKaarten.setBackground(null);
        this.addComponent(3,1,2,1,20,35,restKaarten);

        bankKaarten = new KaartPanel();
        bankKaarten.setLayout(new GridBagLayout());
        bankKaarten.setBackground(null);
        this.addComponent(5,1,2,1,55,135,bankKaarten);

        kaartenSpel = new JPanel();
        kaartenSpel.setLayout(new GridBagLayout());
        kaartenSpel.setBackground(null);
        this.addComponent(7,1,2,1,20,35,kaartenSpel);

        regelsPnl = new JPanel();
        regelsPnl.setLayout(new BoxLayout(regelsPnl, BoxLayout.Y_AXIS));
        regelsPnl.setBackground(null);
        this.addComponent(0,3,2,12,45,50,regelsPnl);

        kaartenCpu[3] = new KaartPanel();
        kaartenCpu[3].setLayout(new GridBagLayout());
        kaartenCpu[3].setBackground(null);
        this.addComponent(1,5,2,1,55,0,kaartenCpu[3]);

        kaartenCpu[1] = new KaartPanel();
        kaartenCpu[1].setLayout(new GridBagLayout());
        kaartenCpu[1].setBackground(null);
        this.addComponent(3,5,2,1,55,0,kaartenCpu[1]);

        kaartenCpu[0] = new KaartPanel();
        kaartenCpu[0].setLayout(new GridBagLayout());
        kaartenCpu[0].setBackground(null);
        this.addComponent(5,5,2,1,25,55,kaartenCpu[0]);

        kaartenCpu[2] = new KaartPanel();
        kaartenCpu[2].setLayout(new GridBagLayout());
        kaartenCpu[2].setBackground(null);
        this.addComponent(7,5,2,1,55,0,kaartenCpu[2]);

        kaartenCpu[4] = new KaartPanel();
        kaartenCpu[4].setLayout(new GridBagLayout());
        kaartenCpu[4].setBackground(null);
        this.addComponent(9,5,2,1,55,0,kaartenCpu[4]);

        GridLayout gridLayout = new GridLayout();
        gridLayout.setRows(3);
        gridLayout.setColumns(2);

        geldCpu[3] = new JPanel();
        geldCpu[3].setLayout(gridLayout);
        geldCpu[3].setBackground(null);
        this.addComponent(1,7,1,1,0,0,geldCpu[3]);

        geldCpu[1] = new JPanel();
        geldCpu[1].setLayout(gridLayout);
        geldCpu[1].setBackground(null);
        this.addComponent(3,7,1,1,0,0,geldCpu[1]);

        //De speler
        geldCpu[0] = new JPanel();
        geldCpu[0].setLayout(gridLayout);
        geldCpu[0].setBackground(null);
        this.addComponent(5,7,1,1,0,0,geldCpu[0]);

        geldCpu[2] = new JPanel();
        geldCpu[2].setLayout(gridLayout);
        geldCpu[2].setBackground(null);
        this.addComponent(7,7,1,1,0,0,geldCpu[2]);

        geldCpu[4] = new JPanel();
        geldCpu[4].setLayout(gridLayout);
        geldCpu[4].setBackground(null);
        this.addComponent(9,7,1,1,0,0,geldCpu[4]);

        bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridBagLayout());
        bottomPanel.setBackground(null);
        this.addComponent(0,9,2,12,0,0,bottomPanel);

        //Vulling van lege grids

        JLabel vulling1 = new JLabel();
        this.addComponent(4,6,1,1,0,0,vulling1);
        JLabel vulling2 = new JLabel();
        this.addComponent(6,5,1,1,0,0,vulling2);
        JLabel vulling3 = new JLabel();
        this.addComponent(2,10,1,1,0,0,vulling3);
        JLabel vulling4 = new JLabel();
        this.addComponent(0,1,1,1,0,0,vulling4);
        JLabel vulling5 = new JLabel();
        this.addComponent(11,0,1,1,0,0,vulling5);
        JLabel vulling6 = new JLabel();
        this.addComponent(8,8,1,1,0,0,vulling6);
        JLabel vulling7 = new JLabel();
        // this.addComponent(2,8,1,1,0,0,vulling7);

        this.addRulesComp();
        this.addGeldComp();
    }
    public void addRulesComp(){
        JLabel dealerRule = new JLabel();
        dealerRule.setText("Dealer must stand on 16 and draw up to 17");
        dealerRule.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        JLabel insuranceRule = new JLabel();
        insuranceRule.setText("INSURANCE PAYS 2 TO 1");
        insuranceRule.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        JLabel blackjackRule = new JLabel();
        blackjackRule.setText("BLACKJACK PAYS 2 TO 1");
        blackjackRule.setFont(new Font("Lucida Grande", Font.PLAIN, 18));

        status = new JLabel();
        status.setFont(new Font("Lucida Grande", Font.PLAIN, 18));

        blackjackRule.setAlignmentX(Component.CENTER_ALIGNMENT);
        insuranceRule.setAlignmentX(Component.CENTER_ALIGNMENT);
        dealerRule.setAlignmentX(Component.CENTER_ALIGNMENT);
        status.setAlignmentX(Component.CENTER_ALIGNMENT);
        regelsPnl.add(blackjackRule, null);
        regelsPnl.add(insuranceRule, null);
        regelsPnl.add(dealerRule, null);
        regelsPnl.add(status,null);
    }

    public void addGeldComp(){
        for(int i=0;i<=4;i++){
            this.scorelbl[i]=new JLabel("Punten");
            this.statuslbl[i]=new JLabel("");

            this.geldTxt[i]=new JTextField();
            this.geldTxt[i].setEnabled(false);
            this.inzetTxt[i]=new JTextField();
            if(i!=0)this.inzetTxt[i].setEnabled(false);
            // addComponent()

            geldCpu[i].add(this.scorelbl[i]);
            geldCpu[i].add(this.statuslbl[i]);
            geldCpu[i].add(new JLabel("Inzet:"));
            geldCpu[i].add(this.inzetTxt[i]);
            geldCpu[i].add(new JLabel("Geld:"));
            geldCpu[i].add(this.geldTxt[i]);




        }

    }

    public void addBottomComponents(){

        dealBttn = new JButton();
        dealBttn.setText("Deal");

        passBttn = new JButton();
        passBttn.setText("Pass");

        doubleBttn = new JButton();
        doubleBttn.setText("Double");

        insertBttn = new JButton();
        insertBttn.setText("Insurance");

        splitBttn = new JButton();
        splitBttn.setText("Split");


        GridBagConstraints consbttn = new GridBagConstraints();
        consbttn.fill = GridBagConstraints.VERTICAL;
        consbttn.weighty = 1;
        consbttn.gridy = 0;

        bottomPanel.add(dealBttn,consbttn);
        optionBttns[0]=dealBttn;
        bottomPanel.add(passBttn,consbttn);
        optionBttns[1]=passBttn;

        bottomPanel.add(splitBttn,consbttn);
        optionBttns[2]=splitBttn;
        bottomPanel.add(doubleBttn,consbttn);
        optionBttns[3]=doubleBttn;
        bottomPanel.add(insertBttn,consbttn);
        optionBttns[4]=insertBttn;
    }

    public void addComponent(int x, int y, int gridh, int gridw, int ipadx, int ipady, JComponent com){
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = x;
        gridBagConstraints.gridy = y;
        gridBagConstraints.gridheight = gridh;
        gridBagConstraints.gridwidth = gridw;
        gridBagConstraints.weightx=0.1;
        gridBagConstraints.weighty=0.1;
        gridBagConstraints.ipadx=ipadx;
        gridBagConstraints.ipady=ipady;
        gridBagConstraints.fill=GridBagConstraints.BOTH;
        this.add(com,gridBagConstraints);
    }
    public void addComponent(int x, int y, int gridh, int gridw, int ipadx, int ipady, JComponent com,JComponent pcom){
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = x;
        gridBagConstraints.gridy = y;
        gridBagConstraints.gridheight = gridh;
        gridBagConstraints.gridwidth = gridw;
        gridBagConstraints.weightx=0.1;
        gridBagConstraints.weighty=0.1;
        gridBagConstraints.ipadx=ipadx;
        gridBagConstraints.ipady=ipady;
        gridBagConstraints.fill=GridBagConstraints.BOTH;
        pcom.add(com,gridBagConstraints);
    }


}  //  @jve:decl-index=0:visual-constraint="10,10"
