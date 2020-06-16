package game.enemies.test;

import game.enemies.Armor;
import game.enemies.Soldier;
import game.towers.MagicTower;
import game.towers.RakiaTower;
import game.towers.ShootingTower;
import game.utils.Position;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ArmorTest {
    @Test
    public void test_when_shooting_tower_should_affect_by_30_percent(){
        // Given
        ShootingTower t = new ShootingTower(new Position(0, 0));
        Armor a =new Armor(new Position(0, 0));
        // When
        a.getAttacked(t);
        // Then
        assertEquals(a.getHealth(), 42);
    }

    @Test
    public void test_when_not_shooting_tower_should_affect_by_100_percent(){
        // Given
        MagicTower t = new MagicTower(new Position(0, 0));
        Armor a =new Armor(new Position(0, 0));
        // When
        a.getAttacked(t);
        // Then
        assertEquals(a.getHealth(), 25);
    }

}
