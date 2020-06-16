package game.actionListeners;

import game.GameEngine;
import game.enemies.Enemy;
import game.spells.Freeze;
import game.spells.Healing;
import game.spells.Meteor;
import game.utils.Position;


import javax.swing.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Iterator;

import static java.lang.StrictMath.abs;

public class SpellsListener implements ActionListener {

    private String spell;
    private GameEngine ge;
    private Position p;
    boolean clicked=false;
    boolean started=false;
    long startTime;

    public SpellsListener(String type,GameEngine g){
        spell=type;
        ge=g;
    }

    /**
     * Is called when the freezing button is clicked
     */
    private void freeze(){
        //System.out.println("in freeze");
        Freeze f =new Freeze(30);
        int diff = ge.getCoins() - f.get_cost();
        if (diff >= 0) {
            Timer newFrameTimer = new Timer(1000 / 24, new FreezeTimer(f, ge.getEnemies()));
            startTime = System.currentTimeMillis();
            f.freeze(ge.getEnemies());
            newFrameTimer.start();
            ge.setCoins(diff);
        }
    }

    /**
     * Is called when the meteor button is clicked
     */
    private void meteor(){

            clicked = true;
            Meteor m = new Meteor(40, p);
            int diff = ge.getCoins() - m.get_cost();
            //System.out.println(diff);
            if (diff >= 0) {
                //System.out.println(diff);
                //System.out.println("inside if");
                MyMouseListener ml = new MyMouseListener(m,ge.getEnemies());
                //System.out.println("after creating");
                ge.addMouseListener(ml);
                //System.out.println("after adding");
                //System.out.println("inside started");
            }
            else clicked=false;

    }


    /**
     * Is called when the healing button is clicked
     */
    private void heal(){
        if(ge.castle.getHealth()<100){
            Healing h= new Healing(20);
            int diff= ge.getCoins() - h.get_cost();
            if (diff>=0){
                h.affect(ge.castle);
                ge.setCoins(diff);
            }


        }


    }

    /**
     * what happens if one of the spells button is clicked
     * @param actionEvent
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        switch(spell){
            case "freeze" : freeze(); break;
            case "meteor" : meteor(); break;
            case "heal" : heal();break;
        }
    }


    class MyMouseListener extends MouseAdapter {

        Meteor spell;
        ArrayList<Enemy> enemies;
        Iterator<Enemy> itr;

        public MyMouseListener(Meteor m, ArrayList<Enemy> e){
            spell=m;
            enemies=e;
            //System.out.println("listener started");
        }

        /**
         * registers the mouse clicking for the meteor
         * @param arg0
         */
        @Override
        public void mouseClicked(MouseEvent arg0) {
            //System.out.println("I clivked");
            if(clicked){
                //System.out.println("I clicked and was registered");
            //System.out.println(arg0.getX());
                p = new Position(arg0.getX(),arg0.getY());
                spell.set_position(p);
                //System.out.println("I set position");
                spell.affect(enemies);
               // System.out.println("should have effected");
                ge.setCoins(ge.getCoins() - spell.get_cost());

                itr = enemies.iterator();
                while (itr.hasNext()) {
                    Enemy enemy = itr.next();
                    if(!enemy.isAlive()){
                        itr.remove();
                    }
                }

            }
            clicked=false;
        }
    }


    class FreezeTimer implements ActionListener{
        Freeze spell;
        ArrayList<Enemy> enemies;

        public FreezeTimer(Freeze f, ArrayList<Enemy> e){
            spell=f;
            enemies=e;

        }

        /**
         * wait two seconds after frozen to unfreeze
         * @param actionEvent
         */
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            //System.out.println("in timer");
            //System.out.println(abs(startTime - System.currentTimeMillis())/1000);
            if(abs(startTime - System.currentTimeMillis())/1000==2){
                spell.unFreeze(enemies);
            }
        }
    }


}
