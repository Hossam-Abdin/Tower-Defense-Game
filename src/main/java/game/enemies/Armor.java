package game.enemies;

import game.towers.Tower;
import game.utils.Position;

import javax.swing.*;

public class Armor extends Enemy {


    /**
     * How many xp points it brings to the tower that killed it
     * @return
     */
    @Override
    public int howManyXp() {
        return 4;
    }
    /**
     * How many coins it brings when killed
     * @return
     */
    @Override
    public int howManyCoins() {
        return 12;
    }
    public Armor(Position position) {
        super(50, 40, 30, 5, position,"images/armor.gif");
    }
    /**
     * defines the reaction of this enemy when it is attacked by a tower
     * @param t
     */
    @Override
    public void getAttacked(Tower t) {
        if(t.getAttackPower() > this._defencePower){
            boolean notShootingTower = !t.getName().equals("Shooting");
            int damage = t.getAttackPower() - this._defencePower;
            if(notShootingTower){
                this._health -= damage;
            }else{
                this._health -= damage * 0.3;
            }
             _image=t.getAttackImage();
            _BlinkTimer.start();
        }
    }

}
