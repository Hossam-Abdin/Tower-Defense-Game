package game.enemies;

import game.towers.Tower;
import game.utils.Position;

import javax.swing.*;

public class Soldier extends Enemy {



    public Soldier(Position position) {
        super(50, 20, 20, 6, position,"images/soldier.gif");
    }
    /**
     * How many xp points it brings to the tower that killed it
     * @return
     */
    @Override
    public int howManyXp() {
        return 2;
    }
    /**
     * How many coins it brings when killed
     * @return
     */
    @Override
    public int howManyCoins() {
        return 7;
    }


    /**
     * defines the reaction of this enemy when it is attacked by a tower
     * @param t
     */
    @Override
    public void getAttacked(Tower t) {
        if(t.getAttackPower() > this._defencePower){
            int damage = t.getAttackPower() - this._defencePower;
                this._health -= damage;
                _image=t.getAttackImage();
                _BlinkTimer.start();
            }


        }
}

