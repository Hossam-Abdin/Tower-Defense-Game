package game.enemies;

import game.towers.Tower;
import game.utils.Position;

import javax.swing.*;

public class Slime extends Enemy {



    /**
     * How many xp points it brings to the tower that killed it
     * @return
     */
    @Override
    public int howManyXp() {
        return 6;
    }
    /**
     * How many coins it brings when killed
     * @return
     */
    @Override
    public int howManyCoins() {
        return 17;
    }
    public Slime(Position position) {
        super(40, 10, 40, 4, position,"images/slime2.gif");
    }

    /**
     * defines the reaction of this enemy when it is attacked by a tower
     * @param t
     */
    @Override
    public void getAttacked(Tower t) {
        boolean notRakiaTower = !t.getName().equals("Rakia");
        if(notRakiaTower){
            if(t.getAttackPower() > this._defencePower){
                int damage = t.getAttackPower() - this._defencePower;
                this._health -= damage;
                _image=t.getAttackImage();
                _BlinkTimer.start();
            }
        }

    }
}
