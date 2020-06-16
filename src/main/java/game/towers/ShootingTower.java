package game.towers;

import game.GameEngine;
import game.enemies.Enemy;
import game.utils.Position;

import java.util.ArrayList;


public class ShootingTower extends Tower {

    public ShootingTower(Position a) {
        super(a,"images/stoneTower1.jpg","images/shootingTowerUpgrade1.JPG","images/shootingTowerUpgrade2.JPG",1,"images/shoot.jpg");
        this.price = 30;
        this.name = "Shooting";
        this.attack = 55;
    }
    /**
     * Defines the attacking behavior of the ShootingTower, taking in consideration the upgrade level
     * @param enemies
     */
    @Override
    public void attack(ArrayList<Enemy> enemies) {
try{
        for (int i = 0; i < enemies.size(); i++) {
            if (inRange(enemies.get(i).getPosition()) && enemies.get(i).isAlive()) {
                indexesOfEnemiesInRange.add(i);
            }
        }
        if (indexesOfEnemiesInRange.size() > 0) {
            int randomIndex;
            int enemyToAttackIndex;
            if (this.upgradeLevel == 0 || this.upgradeLevel == 2) {
                randomIndex = (int) (Math.random() * indexesOfEnemiesInRange.size());
                enemyToAttackIndex = indexesOfEnemiesInRange.get(randomIndex);
                enemies.get(enemyToAttackIndex).getAttacked(this);
                if (this.upgradeLevel == 2) {
                    enemies.get(enemyToAttackIndex).getKilled();
                }
                if (!enemies.get(enemyToAttackIndex).isAlive()) {
                    this.xp+= enemies.get(enemyToAttackIndex).howManyXp();
                    GameEngine.increaseCoins(enemies.get(enemyToAttackIndex).howManyCoins());
                    if(this.xp>=10 && this.getLevel()<3){
                        this.levelUp();
                        //System.out.println("Shooting tower level up");

                    }
                    enemies.remove(enemyToAttackIndex);

                    //System.out.println("shoul have");
                }
            }

            if (this.upgradeLevel == 1) {
                int howmanyAttack;
                if(indexesOfEnemiesInRange.size()>=3)
                {
                    howmanyAttack=3;
                }
                else {howmanyAttack=indexesOfEnemiesInRange.size();}
                for (int i = 0; i < howmanyAttack; i++) {
                    randomIndex = (int) (Math.random() * indexesOfEnemiesInRange.size()-1);
                    enemyToAttackIndex = indexesOfEnemiesInRange.get(randomIndex);
                    enemies.get(enemyToAttackIndex).getAttacked(this);
                   // indexesOfEnemiesInRange.remove(randomIndex);
                   // howmanyAttack--;
                    if (!enemies.get(enemyToAttackIndex).isAlive()) {
                        this.xp+= enemies.get(enemyToAttackIndex).howManyXp();
                        if(this.xp>=10 && this.getLevel()<3){
                            this.levelUp();
                            //System.out.println("Shooting tower level up");

                        }

                        enemies.remove(enemyToAttackIndex);
                        GameEngine.increaseCoins(1);
                        //System.out.println("shoul have");
                    }
                }
            }


        }
        indexesOfEnemiesInRange.clear();
}
catch (NullPointerException n){

}
    }

}
