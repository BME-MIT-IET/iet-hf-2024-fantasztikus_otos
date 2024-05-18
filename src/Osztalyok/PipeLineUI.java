package Osztalyok;

import javax.swing.*;
import java.awt.*;

public class PipeLineUI {
    private JButton button = new JButton();
    private ImageIcon icon;
    private int x;
    private int y;

    private int height;

    private int width;


    public PipeLineUI(String _icon){
        this.icon = new ImageIcon(_icon);
        button.setIcon(icon);
    }

    public void SetPos(int x, int y){
        this.x = x;
        this.y = y;
        button.setBounds(x, y, 80, 80);
    }

    public void SetPosAndHeight(int x, int y, int w1, int w2, int h1, int h2){
        this.x = x;
        this.y = y;
        if(w1-w2 == 0){
            button.setBounds(x, y, 80, h1-h2-80);
            resize(80,h1-h2-80);
            height = h1-h2-80;
            width = 80;
        }
        else if(h1-h2 == 0){
            button.setBounds(x, y, w1-w2-80, 80);
            resize(w1-w2-80,80);
            height = 80;
            width = w1-w2-80;
        }
        else{
            button.setBounds(x, y, w1, h1);
            resize(w1,h1);
            height = h1;
            width = w1;
        }

    }
    public void resize(int w, int h) {
        Image image = this.icon.getImage();
        Image newimg = image.getScaledInstance(w, h,  java.awt.Image.SCALE_SMOOTH);
        this.icon = new ImageIcon(newimg);
        button.setIcon(this.icon);
    }

    public int GetX(){
        return this.x;
    }

    public int GetY(){
        return this.y;
    }

    public JButton GetButton(){return this.button;}

    public int GetHeight(){return this.height;}

    public int GetWidth(){return this.width;}

    public void SetIcon(ImageIcon _icon){icon = _icon;}

}
