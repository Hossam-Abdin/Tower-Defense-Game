package game.utils;

import game.enemies.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Wave {
    public Wave(String level) throws FileNotFoundException {
        File levelFile = new File(level);
        reader = new Scanner(levelFile);
        this.readEnemyStartPositions();
        this.readEnemyWaves();
    }

    /**
     * Reading all enemy start positions
     */
    public void readEnemyStartPositions() {
        enemyStartPositions = new ArrayList<Position>();
        int numberOfPositions = Integer.parseInt(reader.next());
        readEnemyStartPosition(numberOfPositions);
    }


    /**
     *  Reading a Start Position
     * @param numberOfPositions
     */
    private void readEnemyStartPosition(int numberOfPositions) {
        for (int i = 0; i < numberOfPositions; i++) {
            int coordX = Integer.parseInt(reader.next());
            int coordY = Integer.parseInt(reader.next());
            enemyStartPositions.add(new Position(coordX, coordY));
        }
    }


    // Reading all enemy Waves
    private void readEnemyWaves() {
        int numberOfWaves = Integer.parseInt(reader.next());
        Waves = new ArrayList<ArrayList<Enemy>>(numberOfWaves);
        readWaves(numberOfWaves);
    }

    // Reading  enemy Waves
    private void readWaves(int numberOfWaves) {
        for (int i = 0; i < numberOfWaves; i++) {
            ArrayList<Enemy> wave = readWave();
            Waves.add(wave);
        }
    }

    // Reading each enemy Wave
    private ArrayList<Enemy> readWave() {
        ArrayList<Enemy> wave = new ArrayList<Enemy>();
        int count;
        for (int j = 0; j < 5; j++) {
            count = Integer.parseInt(reader.next());
            if (j == 0) {
                for (int c = 0; c < count; c++) {
                    Position tmp = getRandomStart();
                    wave.add(new Soldier(new Position(tmp.getCordX(), tmp.getCordY())));
                }
            } else if (j == 1) {
                for (int c = 0; c < count; c++) {
                    Position tmp = getRandomStart();
                    wave.add(new Armor(new Position(tmp.getCordX(), tmp.getCordY())));
                }
            } else if (j == 2) {
                for (int c = 0; c < count; c++) {
                    Position tmp = getRandomStart();
                    wave.add(new Witch(new Position(tmp.getCordX(), tmp.getCordY())));
                }
            } else if (j == 3) {
                for (int c = 0; c < count; c++) {
                    Position tmp = getRandomStart();
                    wave.add(new Slime(new Position(tmp.getCordX(), tmp.getCordY())));
                }
            } else {
                for (int c = 0; c < count; c++) {
                    Position tmp = getRandomStart();
                    wave.add(new Devil(new Position(tmp.getCordX(), tmp.getCordY())));
                }
            }
        }
        return wave;
    }

    // return random start position
    private Position getRandomStart() {
        Random rand = new Random();
        return enemyStartPositions.get(rand.nextInt(enemyStartPositions.size()));
    }

    // prepares the next wave after finishing the wave before
    public void nextWave() {
        Waves.remove(0);
    }

    // returns each enemy from the wave
    public Enemy getNextEnemy() {
        Enemy enemy = Waves.get(0).get(0);
        Waves.get(0).remove(0);
        return enemy;
    }

    // checks if wave is over
    public boolean endOfWave() {
        if (Waves.size() != 0)
            return Waves.get(0).size() == 0;
        return true;
    }

    // checks if all the waves are over
    public boolean isOver() {
        if (Waves.size() == 1)
            return Waves.get(0).size() == 0;
        return Waves.size() == 0;
    }


    private Scanner reader;
    private ArrayList<Position> enemyStartPositions;
    private ArrayList<ArrayList<Enemy>> Waves;
}
