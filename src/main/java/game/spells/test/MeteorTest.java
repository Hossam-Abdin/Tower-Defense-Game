package game.spells.test;
import game.enemies.Enemy;
import game.enemies.Soldier;
import game.spells.Meteor;
import game.utils.Position;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class MeteorTest {
    @Test
    public void when_attack_should_all_in_range_be_damaged(){
        // Given
        Meteor m =new Meteor(50, new Position(50,50));
        Soldier s1=new Soldier(new Position(0,0));
        Soldier s2=new Soldier(new Position(45,45));
        ArrayList<Enemy> arr = new ArrayList<>();
        arr.add(s1);
        arr.add(s2);
        // When
        m.affect(arr);
        // Then
        assertEquals(s1.getHealth(), 50);
        assertEquals(s2.getHealth(),5);
    }

    @Test
    public void when_no_one_in_range_should_do_nothing(){
        // Given
        Meteor m =new Meteor(50, new Position(300,300));
        Soldier s1=new Soldier(new Position(0,0));
        Soldier s2=new Soldier(new Position(50,50));
        Soldier s3=new Soldier(new Position(25,25));
        ArrayList<Enemy> arr = new ArrayList<>();
        arr.add(s1);
        arr.add(s2);
        arr.add(s3);
        // When
        m.affect(arr);
        // Then
        assertEquals(s1.getHealth(), 50);
        assertEquals(s2.getHealth(),50);
        assertEquals(s3.getHealth(),50);
    }
}