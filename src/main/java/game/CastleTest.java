package game;

import game.utils.Position;
import org.junit.Test;

import static org.junit.Assert.*;

public class CastleTest {

    @Test
    public void test_when_castle_get_attacked_should_have_lower_health() throws Exception {
        // Given
        Castle castle = new Castle(new Position(0,0));
        // When
        castle.getAttacked(50);
        // Then
        assertEquals(castle.getHealth(),50);
    }

    @Test
    public void test_when_castle_get_attacked_by_more_than_health_should_be_unsafe() throws Exception {
        // Given
        Castle castle = new Castle(new Position(0,0));
        // When
        castle.getAttacked(100);
        // Then
        assertFalse(castle.isSafe());
    }

    @Test
    public void test_when_increase_health_castle_should_get_more_health() throws Exception{
        //  Given
        Castle castle = new Castle(new Position(0,0));
        // When
        castle.getAttacked(50);
        castle.increaseHealth(20);
        // Then
        assertEquals(castle.getHealth(),70);
    }
}