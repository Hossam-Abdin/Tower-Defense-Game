package game.enemies;

import game.towers.Tower;
import game.utils.Position;

import javax.swing.*;

public class Witch extends Enemy {




    /**
     * How many xp points it brings to the tower that killed it
     * @return
     */
    public Witch(Position position) {
        super(20, 30, 10, 7, position,"images/witch.gif");
    }
    @Override
    public int howManyXp() {
        return 5;
    }
    /**
     * How many coins it brings when killed
     * @return
     */
    @Override
    public int howManyCoins() {
        return 14;
    }
    /**
     * defines the reaction of this enemy when it is attacked by a tower
     * @param t
     */
    @Override
    public void getAttacked(Tower t) {
        if(t.getAttackPower() > this._defencePower){
            boolean notMagicTower = !t.getName().equals("Magic");
            int damage = t.getAttackPower() - this._defencePower;
            if(notMagicTower){
                this._health -= damage;
            }else{
                this._health -= damage * 0.2;
            }
            _image=t.getAttackImage();
            _BlinkTimer.start();
        }
    }
}
