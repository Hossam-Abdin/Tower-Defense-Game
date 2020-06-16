package game.enemies.test;


import game.Castle;
import game.enemies.EnemyForTesting;
import game.enemies.Soldier;
import game.utils.Position;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class EnemyTest {
    @Test
    public void test_when_init_soldier_should_be_alive(){
        // Given
        EnemyForTesting e = new EnemyForTesting(10,0, 0, 0,
                new Position(3, 3));
        // Then
        assertTrue(e.isAlive());
    }

    @Test
    public void when_get_killed_should_be_dead(){
        // Given
        EnemyForTesting e = new EnemyForTesting(10,0, 0, 0,
                new Position(3, 3));
        // When
        e.getKilled();
        // Then
        assertFalse(e.isAlive());
    }
    //ToDo : This should be depending on the castle
    @Test
    public void when_attack_and_successful_return_true(){
        // Given
        EnemyForTesting e = new EnemyForTesting(10,10, 0, 0,
                new Position(3, 3));
        Castle c = new Castle(new Position(0,0));
        // When
        e.attack(c);
        // Then
        assertEquals(c.getHealth(), 90);
    }

    @Test
    public void when_move_should_change_the_coordinates(){
        // Given
        EnemyForTesting e = new EnemyForTesting(10,0, 0, 10,
                new Position(0, 0));
        // When
        e.move();
        // Then
        assertEquals(e.getPosition().getCordX(), 5);
        assertEquals(e.getPosition().getCordY(), 0);
    }

    @Test
    public void when_mov_while_dead_should_not_change_coordinate(){
        // Given
        EnemyForTesting e = new EnemyForTesting(0,0, 0, 5,
                new Position(0, 0));
        // When
        e.move();
        // Then
        assertEquals(e.getPosition().getCordX(), 0);
        assertEquals(e.getPosition().getCordY(), 0);
    }
}
