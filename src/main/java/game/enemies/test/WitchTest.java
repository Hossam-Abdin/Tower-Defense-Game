package game.enemies.test;

import game.enemies.Witch;
import game.towers.MagicTower;
import game.towers.ShootingTower;
import game.utils.Position;
import org.junit.Assert;
import org.junit.Test;

public class WitchTest {
    @Test
    public void when_magic_tower_should_be_affected_by_20_percent(){
        // Given
        MagicTower t = new MagicTower(new Position(0, 0));
        Witch w = new Witch(new Position(0, 0));
        // When
        w.getAttacked(t);
        // Then
        Assert.assertEquals(w.getHealth(), 11);
    }

    @Test
    public void when_not_magic_tower_should_be_affected_by_100_percent(){
        // Given
        ShootingTower t = new ShootingTower(new Position(0, 0));
        Witch w = new Witch(new Position(0, 0));
        // When
        w.getAttacked(t);
        // Then
        Assert.assertFalse(w.isAlive());
    }
}
