package game.spells.test;

import game.Castle;
import game.spells.Healing;
import game.spells.Spells;
import game.utils.Position;
import org.junit.Test;
import static org.junit.Assert.*;

public class HealingTest {
    @Test
    public void when_healed_should_health_of_castle_increased(){
        //Given
        Castle c= new Castle(new Position(50,600));
        c.getAttacked(40);
        assertEquals(c.getHealth(),60);
        Healing s = new Healing(300);
        //When
        s.affect(c);
        //Then
        assertEquals(c.getHealth(),70);

    }
}
