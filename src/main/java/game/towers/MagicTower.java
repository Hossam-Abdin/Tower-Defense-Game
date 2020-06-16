package game.towers;

import game.GameEngine;
import game.enemies.Enemy;
import game.utils.Position;

import java.util.ArrayList;

public class MagicTower extends Tower{

    public MagicTower(Position a) {
        super(a,"images/magicTower1.jpg","images/magicUpgrade1.JPG","images/magicUpgrade2.JPG",2,"images/magic.jpg");
        this.price=40;
        this.name="Magic";
        this.attack=55;
    }

    /**
     * Defines the attacking behavior of the MagicTower, taking in consideration the upgrade level
     * @param enemies
     */
    public void attack( ArrayList<Enemy> enemies)
    {
        try{
            for (int i = 0; i < enemies.size(); i++) {
                if (this.inRange(enemies.get(i).getPosition()) && enemies.get(i).isAlive()) {
                    indexesOfEnemiesInRange.add(i);

                }
            }

            if(indexesOfEnemiesInRange.size()>0)
            {

                int randomIndex = (int) (Math.random() * indexesOfEnemiesInRange.size());
                int enemyToAttackIndex = indexesOfEnemiesInRange.get(randomIndex);

                if(this.upgradeLevel==1)
                {
                    int enemySpeed= enemies.get(enemyToAttackIndex).getCurrentSpeed();
                    enemies.get(enemyToAttackIndex).setSpeed(enemySpeed/2+1);//CHECK THE SPEED
                }
                if(this.upgradeLevel==2)
                {
                    int enemyDefence= enemies.get(enemyToAttackIndex).getDefencePower();
                    enemies.get(enemyToAttackIndex).setDefence(enemyDefence/2);//CHECK THE DEFENCE VALUE
                }
                enemies.get(enemyToAttackIndex).getAttacked(this);
                if (!enemies.get(enemyToAttackIndex).isAlive()) {
                    GameEngine.increaseCoins(enemies.get(enemyToAttackIndex).howManyCoins());
                    this.xp+= enemies.get(enemyToAttackIndex).howManyXp();
                    if(this.xp>=10 && this.getLevel()<3){
                        this.levelUp();

                    }

                    enemies.remove(enemyToAttackIndex);

                }
            }
            indexesOfEnemiesInRange.clear();
        }
        catch (NullPointerException n){

        }
    }




}

