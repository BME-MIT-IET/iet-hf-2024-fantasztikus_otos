package Osztalyok;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuUI implements ActionListener {

    private JFrame frame = new JFrame("Kimaxoljuk");
    // Gombok létrehozása
    private JButton exit = new JButton("Exit");
    private JButton start = new JButton("Start Game");
    private JButton rule = new JButton("Rules");
    private JButton settings = new JButton("Settings");
    private JButton back = new JButton("Back");
    private JLabel background = new JLabel();
    // Címke létrehozása
    private JLabel label = new JLabel("Kimaxolva");

    private JLabel ruleslabel = new JLabel();

    private static Field f;

    //Beállítások
    private static int mechNum = 2, sabNum = 2, roundNum = 5;
    private JLabel mech_lbl = new JLabel("Saboteurs");
    private JLabel sab_lbl = new JLabel("Mechanics");
    private JLabel round_lbl = new JLabel("Rounds");
    private JTextField mech_in = new JTextField(10);
    private JTextField sab_in = new JTextField(10);
    private JTextField round_in = new JTextField(10);

    private JButton mech_up = new JButton("+ M");
    private JButton mech_down = new JButton("- M");
    private JButton sab_up = new JButton("+ S");
    private JButton sab_down = new JButton("- S");
    private JButton round_up = new JButton("+ R");
    private JButton round_down = new JButton("- R");

    private JPanel buttonPanel = new JPanel();
    public MenuUI(Field f) {

        this.f = f;
        String text = "<html>A drukmákori sivatagon át bonyolult csőrendszer szállítja a vizet a hegyi forrásokból a sivatagon túl elterülő városok ciszternáiba. A csőrendszer egyszerű, elágazás nélküli csövekből és a csövekhez csatlakozó aktív elemekből (forrás, ciszterna, napelemmel működő vízátemelő pumpa stb.) áll. Egy pumpa több (de a pumpára jellemző véges számú) csövet is összeköthet, és minden pumpán külön-külön állítható, hogy éppen melyik belekötött csőből melyik másik csőbe pumpáljon, azonban egyszerre csak egy bemenete és egy kimenete lehet. A többi rákötött cső eközben el van zárva. A pumpák véletlen időközönként el tudnak romlani, ilyenkor megszűnik az adott pumpánál a vízáramlás. A pumpák mindegyike rendelkezik egy víztartállyal, amit a víz átemelése közben használ átmeneti tárolóként. A pumpa csak akkor tud vizet pumpálni egy csőbe, ha a cső szabad kapacitása ezt lehetővé teszi."
                +"A csőhálózat bővíthető, változtatható. A csövek kellően rugalmasak ahhoz, hogy az egyik végüket lecsatlakoztatva egy másik aktív elemhez elvihetők és ott felcsatlakoztathatók legyenek. A ciszternáknál folyamatosan készülnek az új csövek, amelyek egyik vége a ciszternához kapcsolódik, a másik azonban szabad. A szabad végű csövekből a csőbe betáplált víz a homokba folyik."
                +"A csőhálózatot a szerelők tartják karban. Ők javítják meg az elromlott pumpákat, ők állítják át a pumpákat, hogy mindig a lehető legtöbb víz tudjon áthaladni a hálózaton, és ha egy cső kilyukad, az ő dolguk a cső megfoltozása is. A kilyukadt csövekből a víz kifolyik, a csövek végén lévő pumpához már nem jut belőle. A szerelők dolga a ciszternáknál lévő szabad csövekkel a hálózat kapacitásának növelése. A szerelők a ciszternáknál magukhoz tudnak venni új pumpát is, amit egy cső közepén tudnak elhelyezni. A csövet ehhez ketté kell vágni, és a két végét a pumpához kell csatlakoztatni."
                +"A hálózaton élnek a nomád szabotőrök is, akik a pumpákat tudják átállítani és a csöveket szokták kilyukasztani."
                + "Mivel a sivatag veszélyes hely, a szerelők és a szabotőrök csak a csőhálózaton haladhatnak. A pumpáknál kikerülhetik egymást, de a csöveken már nem tudnak elmenni egymás mellett, egy csövön egyszerre csak egy ember állhat."
                + "A játékot a két csapat legalább 2-2 játékossal játssza. A szabotőrök dolga, hogy minél több víz folyjon el a lyukakon, a szerelők pedig azon dolgoznak, hogy minél több víz jusson a ciszternákba. Az a csapat nyer, amelyik a játék végére több vizet szerez</html>";

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(750, 500);
        frame.setLayout(null);
        frame.setResizable(false);

        ruleslabel.setText(text);
        ruleslabel.setVerticalAlignment(JLabel.CENTER);
        ruleslabel.setHorizontalAlignment(JLabel.CENTER);
        ruleslabel.setVisible(false);
        ruleslabel.setHorizontalTextPosition(JLabel.CENTER);
        ruleslabel.setBounds(0,0,470,500);
        ruleslabel.setForeground(Color.black);
        ruleslabel.setFont(new Font("MV Boli",Font.BOLD,9));
        ruleslabel.setOpaque(true);

        // Gombok hozzáadása a háttér címkéhez
        background.setLayout(null);
        background.setBounds(0, 0, 750, 500);
        background.add(start);
        background.add(exit);
        background.add(rule);
        background.add(settings);
        background.add(back);
        background.add(label);
        background.add(ruleslabel);
            //Beállításokhoz használt elemek
        background.add(mech_lbl);
        background.add(sab_lbl);
        background.add(round_lbl);
        background.add(mech_in);
        background.add(sab_in);
        background.add(round_in);

        background.add(mech_up);
        background.add(mech_down);
        background.add(sab_up);
        background.add(sab_down);
        background.add(round_up);
        background.add(round_down);


        // Gombok pozíciójának beállítása
        start.setBounds(70, 60, 200, 40);
        exit.setBounds(70, 360, 200, 40);
        rule.setBounds(70, 260, 200, 40);
        settings.setBounds(70, 160, 200, 40);
        back.setBounds(500, 400, 200, 40);
        back.setVisible(false);

        //Gombok - Beállítás
            //+
        mech_up.setBounds(100, 160, 70, 40);
        sab_up.setBounds(250, 160, 70, 40);
        round_up.setBounds(400, 160, 70, 40);
            //-
        mech_down.setBounds(100, 260, 70, 40);
        sab_down.setBounds(250, 260, 70, 40);
        round_down.setBounds(400, 260, 70, 40);

        mech_up.setVisible(false);
        sab_up.setVisible(false);
        round_up.setVisible(false);
        round_down.setVisible(false);
        mech_down.setVisible(false);
        sab_down.setVisible(false);

        // Címke beállítása
        label.setFont(new Font("Arial", Font.BOLD, 24));
        label.setForeground(Color.RED);
        label.setBounds(305, 0, 500, 400);

            //Beállítások Címkéi és input mezői
                //Mech felirat
            mech_lbl.setFont(new Font("Arial", Font.BOLD, 14));
            mech_lbl.setForeground(Color.BLUE);
            mech_lbl.setBounds(100, 60, 100, 40);
            mech_lbl.setVisible(false);
                //Mech textfield
            mech_in.setBounds(100, 360, 70, 40);
            mech_in.setFont(new Font("Arial", Font.BOLD, 15));
            mech_in.setVisible(false);
            mech_in.setEnabled(false);
                //Sab label
            sab_lbl.setFont(new Font("Arial", Font.BOLD, 14));
            sab_lbl.setForeground(Color.BLUE);
            sab_lbl.setBounds(250, 60, 100, 40);
            sab_lbl.setVisible(false);
                //Sab text field
            sab_in.setBounds(250, 360, 70, 40);
            sab_in.setFont(new Font("Arial", Font.BOLD, 15));
            sab_in.setVisible(false);
            sab_in.setEnabled(false);
                //Round label
            round_lbl.setFont(new Font("Arial", Font.BOLD, 14));
            round_lbl.setForeground(Color.BLUE);
            round_lbl.setBounds(400, 60, 100, 40);
            round_lbl.setVisible(false);
                //Round textfield
            round_in.setBounds(400, 360, 70, 40);
            round_in.setFont(new Font("Arial", Font.BOLD, 15));
            round_in.setVisible(false);
            round_in.setEnabled(false);

        // Gomb eseménykezelők hozzáadása
        start.addActionListener(this);
        exit.addActionListener(this);
        rule.addActionListener(this);
        settings.addActionListener(this);
        back.addActionListener(this);
            //Beállításokhoz
        mech_up.addActionListener(this);
        mech_down.addActionListener(this);
        sab_up.addActionListener(this);
        sab_down.addActionListener(this);
        round_up.addActionListener(this);
        round_down.addActionListener(this);

        // Háttérkép beállítása az ablakhoz
        frame.setContentPane(background);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == start) {
            f.setStartGame(mechNum,sabNum,roundNum);
            f.CreatePlayers();

            new GameUI(f);
            frame.dispose();

        }
        if(e.getSource() ==exit) {
            System.exit(0);
        }
        if(e.getSource() ==rule) {
            start.setVisible(false);
            exit.setVisible(false);
            rule.setVisible(false);
            settings.setVisible(false);
            back.setVisible(true);
            ruleslabel.setVisible(true);
            label.setVisible(false);

        }
        if(e.getSource() ==back) {
            label.setVisible(true);
            start.setVisible(true);
            exit.setVisible(true);
            rule.setVisible(true);
            settings.setVisible(true);
            back.setVisible(false);
            ruleslabel.setVisible(false);

            mech_in.setVisible(false);
            sab_in.setVisible(false);
            round_in.setVisible(false);
            mech_lbl.setVisible(false);
            sab_lbl.setVisible(false);
            round_lbl.setVisible(false);

            mech_up.setVisible(false);
            sab_up.setVisible(false);
            round_up.setVisible(false);
            round_down.setVisible(false);
            mech_down.setVisible(false);
            sab_down.setVisible(false);

        }
        if(e.getSource() ==settings) {
            start.setVisible(false);
            exit.setVisible(false);
            rule.setVisible(false);
            settings.setVisible(false);
            back.setVisible(true);
            label.setVisible(false);

            mech_in.setVisible(true);
            sab_in.setVisible(true);
            round_in.setVisible(true);
            mech_lbl.setVisible(true);
            sab_lbl.setVisible(true);
            round_lbl.setVisible(true);
            //save_settings.setVisible(true);
            mech_up.setVisible(true);
            mech_down.setVisible(true);
            sab_down.setVisible(true);
            sab_up.setVisible(true);
            round_up.setVisible(true);
            round_down.setVisible(true);

            mech_in.setText(Integer.toString(this.mechNum));
            sab_in.setText(Integer.toString(this.sabNum));
            round_in.setText(Integer.toString(this.roundNum));
        }
        if(e.getSource() == mech_up)
        {
            this.mechNum++;
            mech_in.setText(Integer.toString(this.mechNum));
        }if(e.getSource() == mech_down)
        {
            if(this.mechNum > 1)
                this.mechNum--;
            mech_in.setText(Integer.toString(this.mechNum));
        }
        if(e.getSource() == sab_up)
        {
            this.sabNum++;
            sab_in.setText(Integer.toString(this.sabNum));
        }if(e.getSource() == sab_down)
        {
            if(this.sabNum > 1)
                this.sabNum--;
            sab_in.setText(Integer.toString(this.sabNum));
        }
        if(e.getSource() == round_up)
        {
            this.roundNum++;
            round_in.setText(Integer.toString(this.roundNum));
        }
        if(e.getSource() == round_down)
        {
            if(this.roundNum > 1)
                this.roundNum--;
            round_in.setText(Integer.toString(this.roundNum));
        }
    }




}

