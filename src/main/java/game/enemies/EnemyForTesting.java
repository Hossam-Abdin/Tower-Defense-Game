package game.enemies;

import game.towers.Tower;
import game.utils.Position;

public class EnemyForTesting extends Enemy {
    public EnemyForTesting(int health, int attackPower, int defencePower, int speed, Position position) {
        super(health, attackPower, defencePower, speed, position,"images/soldier.jpg");
    }
    @Override
    public int howManyXp() {return 0;}
    @Override
    public int howManyCoins() {return 0;}
    @Override
    public void getAttacked(Tower t) {}
}
