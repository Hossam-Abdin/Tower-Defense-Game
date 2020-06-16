package game.actionListeners;

import game.GameEngine;
import game.towers.Tower;
import game.utils.pairTowerAndButton;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class upgradeListener implements ActionListener {
    private pairTowerAndButton t;
    private int i;
    private GameEngine ge;

    public upgradeListener(pairTowerAndButton t, int i, GameEngine g) {
        this.i = i;
        this.t = t;
        this.ge = g;
    }

    /**
     * does the upgrading of the towers
     * @param actionEvent
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (t.getTower().getUpgradeLevel()==0){
            t.getTower().upgrade(i);
            ge.clearArray(ge.getUpgradeTypeOption());
            ge.refreshArray(ge.getUpgradeTypeOption());

            ge.getUpgradeButtons().remove(t);
            ge.remove(t.getButton());
            ge.refreshPairTowerAndArray(ge.getUpgradeButtons());
        }

    }
}
