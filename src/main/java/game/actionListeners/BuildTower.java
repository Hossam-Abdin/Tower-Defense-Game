package game.actionListeners;

import game.GameEngine;
import game.towers.MagicTower;
import game.towers.RakiaTower;
import game.towers.ShootingTower;
import game.towers.Tower;
import game.utils.TowerButton;
import game.utils.pairTowerAndButton;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class BuildTower implements ActionListener {
    private String type;
    private TowerButton towerPlace;
    private int direction,move;
    private GameEngine ge;
    public BuildTower(TowerButton tb, String type, int dir, int move, GameEngine g){
        this.type=type;
        this.towerPlace=tb;
        this.direction=dir;
        this.move=move;
        this.ge=g;
    }

    /**
     * to check if we can build tower
     * @param t
     * @return
     */
    public boolean valid(Tower t){
        return type!="close" && ge.getCoins() - t.getPrice() >=0;
    }

    /**
     * Paying for the tower
     * @param t
     */
    public void pay(Tower t) {
        if(valid(t)) {
            ge.setCoins(ge.getCoins() -t.getPrice());
        }
    }

    /**
     * Describes what happens if the tower placement button is clicked
     * @param actionEvent
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Tower t = null;

        switch (type) {
            case "close":
                ge.clearArray(ge.getPickTowerButtons());
                break;
            case "rakija":
                t = new RakiaTower(towerPlace.getPosition());

                break;
            case "shoot":
                t = new ShootingTower(towerPlace.getPosition());

                break;
            case "magic":
                t = new MagicTower(towerPlace.getPosition());
                break;

        }



        if(valid(t)) {//PUT HERE TRUE IF U WANNA BUY UNLIMITED NUMBER OF TOWERS FOR TESTING
            ge.getTowers().add(t);
            pay(t);
            ge.clearArray(ge.getPickTowerButtons());
            ge.refreshArray(ge.getPickTowerButtons());
            ge.getTowerPositions().remove(towerPlace);
            ge.removeTowerButton(towerPlace);
            JButton upgrade=new JButton("Upgrade");
            upgrade.setBounds(towerPlace.getPosition().getCordX()-10,towerPlace.getPosition().getCordY()-direction*20+move,100,20);
            upgrade.addActionListener(new TowerUpgradeOption(new pairTowerAndButton(t,upgrade),direction,move,ge));

            ge.getUpgradeButtons().add(new pairTowerAndButton(t,upgrade));
            ge.refreshPairTowerAndArray(ge.getUpgradeButtons());
            ge.refreshTowerButton(ge.getTowerPositions());

        }
    }
}

