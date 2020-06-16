package game.actionListeners;

import game.GameEngine;
import game.actionListeners.BuildTower;
import game.utils.TowerButton;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TowerButtonListener implements ActionListener {

    private TowerButton towerPlace;
    private int dir;
    private GameEngine ge;


    public TowerButtonListener(TowerButton a, int dir, GameEngine g) {
        super();
        this.dir = dir;
        this.towerPlace = a;
        this.ge = g;

    }

    /**
     * creates the creation menu
     * @param actionEvent
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        int move;
        if (dir == 1) {
            move = 0;
        } else {
            move = 60;
        }
        ge.clearArray(ge.getUpgradeTypeOption());
        ge.refreshArray(ge.getUpgradeTypeOption());
        ge.clearArray(ge.getPickTowerButtons());
        JButton close=new JButton("Close");
        close.setBounds(towerPlace.getPosition().getCordX() - 10, towerPlace.getPosition().getCordY() - dir * 30 + move, 100, 20);
        JButton magic = new JButton("Magic (40)");
        magic.setBounds(towerPlace.getPosition().getCordX() - 10, towerPlace.getPosition().getCordY() - dir * 60 + move, 100, 20);
        JButton shooting = new JButton("Shooting (30)");
        shooting.setBounds(towerPlace.getPosition().getCordX() - 10, towerPlace.getPosition().getCordY() - dir * 90 + move, 100, 20);
        JButton rakija = new JButton("Rakija (25)");
        rakija.setBounds(towerPlace.getPosition().getCordX() - 10, towerPlace.getPosition().getCordY() - dir * 120 + move, 100, 20);
       close.addActionListener(new BuildTower(towerPlace, "close", dir, move,ge));
        magic.addActionListener(new BuildTower(towerPlace, "magic", dir, move,ge));
        rakija.addActionListener(new BuildTower(towerPlace, "rakija", dir, move,ge));
        shooting.addActionListener(new BuildTower(towerPlace, "shoot", dir, move,ge));
        ge.getPickTowerButtons().add(close);
        ge.getPickTowerButtons().add(magic);
        ge.getPickTowerButtons().add(shooting);
        ge.getPickTowerButtons().add(rakija);
        ge.refreshArray( ge.getPickTowerButtons());
    }
}