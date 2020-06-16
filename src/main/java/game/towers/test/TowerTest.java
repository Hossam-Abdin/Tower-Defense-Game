package game.towers.test;

import game.towers.RakiaTower;
import game.utils.Position;
import org.junit.Test;

import static org.junit.Assert.*;

public class TowerTest {

    @Test
    public void test_when_the_tower_levels_up() throws Exception {
        // Given
        RakiaTower tower=new RakiaTower(new Position(0,0));
        tower.setXP(11);
         // When 
        tower.levelUp();
        // Then
        assertEquals(tower.getXP(),1);
       assertEquals(tower.getLevel(),1);
        assertEquals(tower.getAttackPower(),50);
        assertEquals(tower.getFrequency(),3);
        assertEquals(tower.getRange(),280);
    }

    @Test
    public void test_when_the_tower_is_upgraded() throws Exception {
        // Given
        RakiaTower tower=new RakiaTower(new Position(0,0));
        // When
        tower.upgrade(1);
        // Then
        assertEquals(tower.getUpgradeLevel(),1);
    }

    @Test
    public void test_when_the_inRange_function_is_True() throws Exception{
        //  Given
        RakiaTower tower=new RakiaTower(new Position(0,0));
        // Then
        assertTrue(tower.inRange(new Position(0,250)));
        assertTrue(tower.inRange(new Position(0,180)));
    }
    @Test
    public void test_when_the_inRange_function_is_False() throws Exception{
        //  Given
        RakiaTower tower=new RakiaTower(new Position(0,0));
        // Then
        assertFalse(tower.inRange(new Position(0,251)));
        assertFalse(tower.inRange(new Position(0,349)));

    }
}