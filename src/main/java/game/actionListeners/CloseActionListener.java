package game.actionListeners;

import game.GameEngine;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CloseActionListener  implements ActionListener {
    private GameEngine ge;
    public CloseActionListener(GameEngine g){
        this.ge=g;
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        ge.clearArray(ge.getUpgradeTypeOption());
        ge.refreshArray(ge.getUpgradeTypeOption());
    }
}
