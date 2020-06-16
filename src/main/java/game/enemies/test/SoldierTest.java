package game.enemies.test;

import game.enemies.Soldier;
import game.towers.RakiaTower;
import game.utils.Position;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SoldierTest {

    @Test
    public void test_when_soldier_can_handle_attack_should_be_alive(){
        // Given
        Soldier s = new Soldier(new Position(3, 3));
        RakiaTower t = new RakiaTower(new Position(3, 3));
        // When
        s.getAttacked(t);
        // Then
        assertTrue(s.isAlive());
    }

    @Test
    public void test_when_soldier_can_not_handle_attack_should_die(){
        // Given
        Soldier s = new Soldier(new Position(3, 3));
        RakiaTower t = new RakiaTower(new Position(3, 3));
        // When
        for (int i = 0; i < 5; i++){
            s.getAttacked(t);
        }
        // Then
        assertFalse(s.isAlive());
    }

}
