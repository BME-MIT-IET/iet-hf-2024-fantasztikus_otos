package Osztalyok;

import javax.swing.*;

public class PlayerUI {
    private JButton button;
    private int id;
    private int x;
    private int y;

    public PlayerUI(){
        this.x = x;
        this.y = y;
    }

    public void SetIdandType(int id, String type){
        this.id = id;
        if(type == "Mechanic"){ button = new JButton("M" + id);}
        else{ button = new JButton("S" + id);}
    }

    public void SetPos(int x, int y){
        this.x = x;
        this.y = y;
        button.setBounds(x, y, 55, 55);
    }

    public int GetX(){
        return this.x;
    }

    public int GetY(){
        return this.y;
    }
    public JButton GetButton(){return this.button;}
}
