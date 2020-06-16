package game.actionListeners;

import game.GameEngine;
import game.towers.Tower;
import game.utils.pairTowerAndButton;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TowerUpgradeOption implements ActionListener {
    private pairTowerAndButton t;
    private int dir;
    private int move;

    private GameEngine ge;

    public TowerUpgradeOption(pairTowerAndButton t,int dir,int move,GameEngine g){
        this.t=t;
        this.dir=dir;
        this.move=move;
        this.ge=g;

    }

    /**
     * creates the upgrade menu
     * @param actionEvent
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        ge.clearArray(ge.getPickTowerButtons());
        ge.refreshArray(ge.getPickTowerButtons());
        String option1="";
        String option2="";
        switch(t.getTower().getName()){
            case "Magic":
                option1="Magic1";
                option2="Magic2";
                break;
            case "Rakia":
                option1="rakija1";
                option2="rakija2";
                break;
            case "Shooting":
                option1="Shot1";
                option2="Shot2";
                break;

        }
        JButton close=new JButton("Close");
        close.setBounds(t.getTower().getPosition().getCordX()-10,t.getTower().getPosition().getCordY()-dir*70+move,100,20);
        JButton first=new JButton(option1);
        first.setBounds(t.getTower().getPosition().getCordX()-10,t.getTower().getPosition().getCordY()-dir*100+move,100,20);
        JButton second=new JButton(option2);
        second.setBounds(t.getTower().getPosition().getCordX()-10,t.getTower().getPosition().getCordY()-dir*130+move,100,20);
        ge.clearArray( ge.getUpgradeTypeOption());
        ge.getUpgradeTypeOption().add(close);
        ge.getUpgradeTypeOption().add(first);
        ge.getUpgradeTypeOption().add(second);
        close.addActionListener(new CloseActionListener(ge));
        first.addActionListener(new upgradeListener(t,1, ge));
        second.addActionListener(new upgradeListener(t,2, ge));
        ge.refreshArray( ge.getUpgradeTypeOption());

    }
}
