package game.utils;

import game.towers.Tower;

import javax.swing.*;

public class pairTowerAndButton {
    private Tower t;
    private JButton button;
    public pairTowerAndButton(Tower t,JButton b)
    {
        this.t=t;
        this.button=b;
    }
    public JButton getButton(){return this.button; }
    public Tower getTower(){return  this.t;}
}
