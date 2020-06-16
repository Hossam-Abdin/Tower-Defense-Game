package game.spells.test;

import game.enemies.Enemy;
import game.enemies.Slime;
import game.enemies.Soldier;
import game.spells.Freeze;
import game.spells.Spells;
import game.utils.Position;
import org.junit.Test;

import java.util.ArrayList;
import static org.junit.Assert.*;

public class FreezeTest {
    @Test
    public void when_frozen_should_all_have_speed_zero(){
        //Given
        Freeze s=new Freeze(100);
        Soldier e1=new Soldier(new Position(200,100));
        Enemy e2 = new Slime(new Position(500,500));
        ArrayList<Enemy> enemies=new ArrayList<>();
        enemies.add(e1);
        enemies.add(e2);
        //When
        s.freeze(enemies);
        //Then
        assertEquals(e1.getCurrentSpeed(),0);
        assertEquals(e2.getCurrentSpeed(),0);

    }

    @Test
    public void when_un_frozen_should_all_have_original_speed(){
        //Given
        Freeze s=new Freeze(100);
        Enemy e1=new Soldier(new Position(200,100));
        Enemy e2 = new Slime(new Position(500,500));
        ArrayList<Enemy> enemies=new ArrayList<>();
        enemies.add(e1);
        enemies.add(e2);
        //When
        s.freeze(enemies);
        s.unFreeze(enemies);
        //Then
        assertEquals(e1.getCurrentSpeed(),e1.getOriginalSpeed());
        assertEquals(e2.getCurrentSpeed(),e2.getOriginalSpeed());

    }
}
