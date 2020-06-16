package game.towers;

import game.GameEngine;
import game.enemies.Enemy;
import game.utils.Position;

import java.util.ArrayList;

public class RakiaTower extends Tower {

    public RakiaTower(Position a) {
        super(a, "images/rakiaTower1.jpg", "images/rakiaUpgrade1.JPG", "images/rakiaUpgrade2.JPG", 2, "images/rakia.jpg");
        this.price = 25;
        this.name = "Rakia";
        this.attack = 40;
    }

    /**
     * Defines the attacking behavior of the RakiaTower, taking in consideration the upgrade level
     *
     * @param enemies
     */
    @Override
    public void attack(ArrayList<Enemy> enemies) {
        try {
            for (int i = 0; i < enemies.size(); i++) {
                if (this.inRange(enemies.get(i).getPosition()) && enemies.get(i).isAlive()) {
                    indexesOfEnemiesInRange.add(i);
                }
            }

            if (indexesOfEnemiesInRange.size() > 0) {

                int randomIndex = (int) (Math.random() * indexesOfEnemiesInRange.size());
                int enemyToAttackIndex = indexesOfEnemiesInRange.get(randomIndex);

                if (this.upgradeLevel == 1) {

                    int x = enemies.get(enemyToAttackIndex).getDirection().getDirectionX();
                    int y = enemies.get(enemyToAttackIndex).getDirection().getDirectionY();
                    enemies.get(enemyToAttackIndex).setDirection((-1) * x, (-1) * y);
                    enemies.get(enemyToAttackIndex).startRakija1();

                }
                if (this.upgradeLevel == 2) {
                    enemies.get(enemyToAttackIndex).setDefence(0);
                    enemies.get(enemyToAttackIndex).startRakija2();

                }
                enemies.get(enemyToAttackIndex).getAttacked(this);

                if (!enemies.get(enemyToAttackIndex).isAlive()) {
                    GameEngine.increaseCoins(enemies.get(enemyToAttackIndex).howManyCoins());
                    this.xp+= enemies.get(enemyToAttackIndex).howManyXp();
                    if (this.xp >= 10 && this.getLevel() < 3) {
                        this.levelUp();
                        //System.out.println("Rakia tower level up");

                    }

                    enemies.remove(enemyToAttackIndex);

                }
            }
            indexesOfEnemiesInRange.clear();
        } catch (NullPointerException n) {

        }
    }
}
