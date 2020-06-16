package game.enemies.test;

import game.enemies.Devil;
import game.towers.ShootingTower;
import game.utils.Position;
import org.junit.Assert;
import org.junit.Test;

public class DevilTest {
    @Test
    public void when_any_tower_should_be_affected_by_20_percent(){
        // Given
        ShootingTower t = new ShootingTower(new Position(0, 0));
        Devil d = new Devil(new Position(0, 0));
        // When
        d.getAttacked(t);
        // Then
        Assert.assertEquals(d.getHealth(), 50);
    }
}
