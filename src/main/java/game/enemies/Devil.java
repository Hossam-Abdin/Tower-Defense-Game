package game.enemies;

import game.towers.Tower;
import game.utils.Position;

import javax.swing.*;

public class Devil extends Enemy {
    /**
     * How many xp points it brings to the tower that killed it
     * @return
     */
    @Override
    public int howManyXp() {
        return 200;
    }
    /**
     * How many coins it brings when killed
     * @return
     */
    @Override
    public int howManyCoins() {
        return 1000;
    }
    public static int dyingCoins = 5000;


    public Devil(Position position) {
        super(50, 1000, 200, 10, position,"images/devil.gif");
    }
    /**
     * defines the reaction of this enemy when it is attacked by a tower
     * @param t
     */
    @Override
    public void getAttacked(Tower t) {
        if(t.getAttackPower() > this._defencePower){
            int damage = t.getAttackPower() - this._defencePower;
            this._health -= damage * 2.0;
            _image=t.getAttackImage();
            _BlinkTimer.start();
        }

    }

}
