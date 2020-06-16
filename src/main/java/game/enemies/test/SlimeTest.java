package game.enemies.test;

import game.enemies.Slime;
import game.towers.MagicTower;
import game.towers.RakiaTower;
import game.utils.Position;
import org.junit.Assert;
import org.junit.Test;

public class SlimeTest {
    @Test
    public void test_when_rakjia_tower_should_not_be_affected(){
        // Given
        RakiaTower r = new RakiaTower(new Position(0, 0));
        Slime s = new Slime(new Position(0, 0));
        // When
        s.getAttacked(r);
        // Then
        Assert.assertEquals(s.getHealth(), 40);
    }

    @Test
    public void test_when_not_rakjia_tower_should_not_be_affected(){
        // Given
        MagicTower t = new MagicTower(new Position(0, 0));
        Slime s = new Slime(new Position(0, 0));
        // When
        s.getAttacked(t);
        // Then
        Assert.assertEquals(s.getHealth(), 25);
    }


}
