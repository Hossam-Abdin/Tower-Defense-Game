package game.utils.test;

import game.utils.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class TestFileReader {
    FileReader reader = new FileReader("levels/level_for_test.txt");

    public TestFileReader() throws FileNotFoundException {
    }

    @Before
    public void setUp(){
        reader.read();
    }

    @Test
    public void check_for_getBackground(){
        Assert.assertEquals(reader.getBackground(), "images/map.png");
    }

    @Test
    public void check_for_getRoad(){
        Assert.assertEquals(reader.getRoad(), "images/road_2.png");
    }

    @Test
    public void check_for_towerPlaceHolderButtons(){
        // Given
        ArrayList<TowerButton> towerPlaceHolderButtons = new ArrayList<>();
        TowerButton t1 = new TowerButton(50, 90, -1);
        TowerButton t2 = new TowerButton(350, 90,   1);
        towerPlaceHolderButtons.add(t1);
        towerPlaceHolderButtons.add(t2);
        // Then
        for (int i = 0; i < towerPlaceHolderButtons.size(); i++){
            Assert.assertEquals(reader.getTowerPlaceHolderButtons().get(i).getDirection(),
                    towerPlaceHolderButtons.get(i).getDirection());
        }
    }

    @Test
    public void check_for_getCastlePosition(){
        Assert.assertEquals(reader.getCastlePosition().getCordX(), 0);
        Assert.assertEquals(reader.getCastlePosition().getCordY(), 0);
    }

    @Test
    public void check_for_getDirectionChanges(){
        // Given
        ArrayList<DirectionChange> directionChanges = new ArrayList<>();
        directionChanges.add(new DirectionChange(new Direction(0, -1), new Position(625, 430)));
        directionChanges.add(new DirectionChange(new Direction(0, 1), new Position(640, 170)));
        // Then
        for (int i = 0; i < directionChanges.size(); i++){
            Assert.assertEquals(reader.getDirectionChanges().get(i).direction.getDirectionX(),
                    directionChanges.get(i).direction.getDirectionX());
            Assert.assertEquals(reader.getDirectionChanges().get(i).direction.getDirectionY(),
                    directionChanges.get(i).direction.getDirectionY());
        }
    }

}
