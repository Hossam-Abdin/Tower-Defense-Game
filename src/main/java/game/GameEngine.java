package game;


import game.actionListeners.SpellsListener;
import game.actionListeners.TowerButtonListener;
import game.enemies.*;
import game.towers.Tower;
import game.utils.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;

import static game.GameGui.HEART_EMOJI;
import static java.lang.System.exit;

public class GameEngine extends JPanel {
    private int level = 1;
    private int waveTime = 20000;
    private long startTime = System.currentTimeMillis() + waveTime;
    private int enemyAddTime = 700;
    private long enemyAddTimer = System.currentTimeMillis() + enemyAddTime;
    public static int coins = 100;
    private int FPS;
    private Wave waves;
    public Castle castle;
    private Image backgroundImage;
    private Image roadImage;
    private ArrayList<Enemy> enemies;
    private ArrayList<Tower> towers;
    private ArrayList<TowerButton> towerPositions;
    private ArrayList<JButton> pickTowerButtons;
    private ArrayList<pairTowerAndButton> upgradeButtons;
    private ArrayList<JButton> upgradeTypeOption;
    private ArrayList<DirectionChange> directionChanges;

    public GameEngine(int framesPerSecond) throws FileNotFoundException {
        setupLevel();
        FPS = framesPerSecond;

        // Refreshing the frames
        Timer newFrameTimer = new Timer(1000 / FPS, new NewFrameListener());
        newFrameTimer.start();
    }

    /**
     * Setting up the levels and its componenets
     * @throws FileNotFoundException
     */
    private void setupLevel() throws FileNotFoundException {
        coins=100;
        // data files
        String levelFilename = "levels/level_" + level + ".txt";
        String waveFilename = "waves/level_" + level + ".txt";

        // reading level
        FileReader fileReader = new FileReader(levelFilename);
        fileReader.read();

        // reading enemy waves
        waves = new Wave(waveFilename);

        castle = new Castle(fileReader.getCastlePosition());
        backgroundImage = new ImageIcon(fileReader.getBackground()).getImage();
        enemies = new ArrayList<>();
        towers = new ArrayList<>();
        upgradeButtons = new ArrayList<>();
        upgradeTypeOption = new ArrayList<>();
        roadImage = new ImageIcon(fileReader.getRoad()).getImage();
        directionChanges = fileReader.getDirectionChanges();

        //Read the towers Positions
        this.setLayout(null);
        this.towerPositions = fileReader.getTowerPlaceHolderButtons();
        this.pickTowerButtons = new ArrayList<>();
        for (TowerButton t : towerPositions) {
            t.addActionListener(new TowerButtonListener(t, t.getDirection(), getThis()));
            this.add(t);
        }

        //Spells buttons
        JButton frezzeb = new JButton(new ImageIcon("images/freeze.jpg"));
        frezzeb.setBounds(1190, 20, 80, 80);
        frezzeb.addActionListener(new SpellsListener("freeze", this));
        JButton meteorb = new JButton(new ImageIcon("images/meteor.jpg"));
        meteorb.setBounds(1090, 20, 80, 80);
        meteorb.addActionListener(new SpellsListener("meteor", this));
        JButton healb = new JButton(new ImageIcon("images/heal.jpg"));
        healb.setBounds(990, 20, 80, 80);
        healb.addActionListener(new SpellsListener("heal", this));
        this.add(frezzeb);
        this.add(meteorb);
        this.add(healb);
    }

    /**
     * general function to add buttons to the game engine
     * @param arr
     */
    public void refreshArray(ArrayList<JButton> arr) {
        for (JButton a : arr)
            this.add(a);
    }

    /**
     * to add the button of upgrading
     * @param arr
     */
    public void refreshPairTowerAndArray(ArrayList<pairTowerAndButton> arr) {
        try {
            for (pairTowerAndButton a : arr) {
                if (a.getTower().getLevel() == 3 && a.getTower().getUpgradeLevel() == 0)
                    this.add(a.getButton());
            }
        } catch (NullPointerException n) {

        }
    }

    /**
     * refreshing the button of the remaining places
     * @param arr
     */
    public void refreshTowerButton(ArrayList<TowerButton> arr) {
        try {
            for (TowerButton a : arr)
                this.add(a);
        } catch (NullPointerException n) {

        }
    }

    public int getCoins() {
        return this.coins;
    }

    public void setCoins(int c) {
        this.coins = c;
    }

    /**
     * increases the coins by a certain amount
     * @param c
     */
    public static void increaseCoins(int c) {
        //System.out.println("its called");
        coins += c;
    }

    /**
     * it clears the array of buttons
     * @param arr
     */
    public void clearArray(ArrayList<JButton> arr) {
        for (JButton a : arr)
            this.remove(a);
        arr.clear();
    }

    public void clearPairTowerAndButton(ArrayList<pairTowerAndButton> arr) {
        for (pairTowerAndButton a : arr)
            this.remove(a.getButton());
        arr.clear();
    }

    public GameEngine getThis() {
        return this;
    }

    public ArrayList<JButton> getUpgradeTypeOption() {
        return this.upgradeTypeOption;
    }

    public ArrayList<pairTowerAndButton> getUpgradeButtons() {
        return this.upgradeButtons;
    }

    public ArrayList<JButton> getPickTowerButtons() {
        return this.pickTowerButtons;
    }

    public ArrayList<TowerButton> getTowerPositions() {
        return this.towerPositions;
    }

    public ArrayList<Tower> getTowers() {
        return this.towers;
    }

    public ArrayList<Enemy> getEnemies() {
        return this.enemies;
    }

    public void removeTowerButton(TowerButton a) {
        this.remove(a);
    }

    public int getH() {
        return this.getHeight();
    }

    void removeButtonFromFrame(JButton a) {
        this.remove(a);
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        // Setting up the images of the map

        grphcs.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        grphcs.drawImage(roadImage, 0, getHeight() / 4, getWidth(), getHeight() / 2, this);
        // Updating the top panel

        GameGui.coinsLabel.setText("$: " + coins);
        GameGui.timeLabel.setText("Next Wave : " + (startTime - System.currentTimeMillis()) / 1000);
        GameGui.healthLabel.setText(HEART_EMOJI + " :" + castle.getHealth());

        // Drawing the different components of the game
        //first towers
        castle.draw(grphcs);
        try {
            for (Tower m : towers) {
                m.draw(grphcs);
            }
            ;
        } catch (NullPointerException n) {

        }

        for (Enemy a : enemies) {
            //if (a.isAlive())
            a.draw(grphcs);
        }

        if (startTime - System.currentTimeMillis() <= 1000 / FPS) {
            startTime = System.currentTimeMillis() + waveTime;
            if (!waves.isOver())
                waves.nextWave();
        }

        if (enemyAddTimer - System.currentTimeMillis() <= 10) {
            enemyAddTimer = System.currentTimeMillis() + enemyAddTime;
            if (!waves.endOfWave()) {
                enemies.add(waves.getNextEnemy());
            }
        }

    }


    class NewFrameListener implements ActionListener {

        /**
         * Describing what will happen in every frame
         * @param ae
         */
        @Override
        public void actionPerformed(ActionEvent ae) {

            Iterator<Enemy> itr = enemies.iterator();
            while (itr.hasNext()) {
                Enemy e = itr.next();
                if (castle.collides(e)) {
                    castle.getAttacked(e.getAttackPower());
                    e.getKilled();
                    itr.remove();

                    try {
                        checkGameOver();
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }

                }
            }


            try {
                for (Tower m : towers) {
                    m.tillNextShootDecrease();
                    if (m.getTillNextShout() == 0) {
                        m.attack(enemies);
                        m.RefreshTillNextShoot();
//                        checkWin();
                    }

                }
                ;
                for (pairTowerAndButton p : upgradeButtons) {
                    if (p.getTower().getUpgradeLevel() == 0 && p.getTower().getLevel() == 3) {
                        //  getUpgradeButtons().add(p);
                        getThis().add(p.getButton());
                    }
                }
            } catch (NullPointerException ignored) {

            }
            for (Enemy e : enemies) {
                e.checkDirection(directionChanges);
                e.move();
            }

            try {
                checkWin();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            repaint();

        }

    }


    /**
     * Is called each frame to check if all enemies are killed
     * @throws FileNotFoundException
     */
    public void checkWin() throws FileNotFoundException {
        if (enemies.isEmpty() && waves.isOver() && castle.isSafe()) {
            if (level < 5) {
                JOptionPane.showMessageDialog(null,
                        "level Passed ",
                        "You Won",
                        JOptionPane.WARNING_MESSAGE);

                level += 1;
                this.removeAll();
                setupLevel();
            } else {
                JOptionPane.showMessageDialog(null,
                        "Congratulations \nYou won the game",
                        "You Won",
                        JOptionPane.WARNING_MESSAGE);

                exit(0);
            }
        }
    }

    /**
     * is called every frame to check if we lost(castle's health reaches zero)
     * @throws FileNotFoundException
     */
    public void checkGameOver() throws FileNotFoundException {
        if (!castle.isSafe()) {
            JOptionPane.showMessageDialog(null,
                    "You Lost",
                    "Game Over",
                    JOptionPane.WARNING_MESSAGE);

            level = 1;
            this.removeAll();
            setupLevel();
        }
    }

}
