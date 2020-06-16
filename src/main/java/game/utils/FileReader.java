package game.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReader {
    public FileReader(String level) throws FileNotFoundException {
        File levelFile = new File(level);
        reader = new Scanner(levelFile);
    }


    /**
     * Reading all the components, should be called once at the beginning
     */
    public void read() {
        background = reader.next();
        road = reader.next();
        this.readTowerPlaceHolderButtons();
        castlePosition = this.readCastlePositon();
        this.readDirectionChanges();
    }



    /**
     * Reading all the tower place holder
     */
    public void readTowerPlaceHolderButtons() {
        towerPlaceHolderButtons = new ArrayList<TowerButton>();
        int numberOfPlaceHolders = Integer.parseInt(reader.next());
        readTowerPlaceHolderButton(numberOfPlaceHolders);
    }

    /**
     * Reading a tower place holder
     * @param numberOfPlaceHolders
     */
    private void readTowerPlaceHolderButton(int numberOfPlaceHolders) {
        for (int i = 0; i < numberOfPlaceHolders; i++) {
            int coordX = Integer.parseInt(reader.next());
            int coordY = Integer.parseInt(reader.next());
            int direction = Integer.parseInt(reader.next());
            towerPlaceHolderButtons.add(new TowerButton(coordX, coordY, direction));
        }
    }


    /**
     * Reading the castle positon (width, height)
     * @return Position
     */
    private Position readCastlePositon() {
        int coordX = Integer.parseInt(reader.next());
        int coordY = Integer.parseInt(reader.next());
        return new Position(coordX, coordY);
    }


    /**
     * Reading all direction changes
     */
    public void readDirectionChanges() {
        directionChanges = new ArrayList<DirectionChange>();
        int numberOfChanges = Integer.parseInt(reader.next());
        readDirectionChange(numberOfChanges);
    }


    /**
     * Reading a direction change
     * @param numberOfChanges
     */
    private void readDirectionChange(int numberOfChanges) {
        for (int i = 0; i < numberOfChanges; i++) {
            int coordX = Integer.parseInt(reader.next());
            int coordY = Integer.parseInt(reader.next());
            int dirX = Integer.parseInt(reader.next());
            int dirY = Integer.parseInt(reader.next());
            directionChanges.add(new DirectionChange(new Direction(dirX, dirY), new Position(coordX, coordY)));
        }
    }


    public String getBackground() {
        return background;
    }

    public String getRoad() {
        return road;
    }

    public ArrayList<TowerButton> getTowerPlaceHolderButtons() {
        return towerPlaceHolderButtons;
    }

    public Position getCastlePosition() {
        return castlePosition;
    }

    public ArrayList<DirectionChange> getDirectionChanges() {
        return directionChanges;
    }

    private Scanner reader;
    private String background;
    private String road;
    private ArrayList<TowerButton> towerPlaceHolderButtons;
    private ArrayList<DirectionChange> directionChanges;
    private Position castlePosition;
}