package game.towers;

import game.GameEngine;
import game.enemies.Enemy;
import game.utils.Position;

import javax.swing.*;

import static java.lang.StrictMath.*;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.*;


public abstract class Tower {
    private int level;
    protected int attack;
    private int frequency;//shouts every frequency seconds
    private int tillNextShoot;
    protected int range;
    protected int upgradeLevel;
    protected int price;
    protected int xp;
    protected Image attackImage;
    protected String name;
    private Position position;
    protected Image image;
    protected Image upgrade1;
    protected Image upgrade2;
    protected int enemiesKilled;
    protected ArrayList<Integer>indexesOfEnemiesInRange;
    //protected GameEngine ge;

    public Tower(Position a,String img,String upgrade1,String upgrade2,int fre,String attPic) {
        this.position = a;
        this.level = 1;
        this.xp = 0;
        this.upgradeLevel=0;
        this.frequency=fre;
        this.tillNextShoot=fre*25;
        this.range=220;//IDK IF THIS VALUE IS OKAY
        this.indexesOfEnemiesInRange=new ArrayList<>();
        this.attackImage=new ImageIcon(attPic).getImage();
        image = new ImageIcon(img).getImage();
        this.upgrade1=new ImageIcon(upgrade1).getImage();
        this.upgrade2=new ImageIcon(upgrade2).getImage();


    };

    /**
     * increases the level of the tower, and changes the appropriate parameters(attack,frequency,range)
     */
    public void levelUp() {

            level++;
            attack += 10;
            range+=30;//IDK ABOUT THIS VALUE
            xp -= 10;

    };
    public abstract void attack(ArrayList<Enemy> enemies);

    /**
     * changes the upgradeLevel of the tower
     * @param typeOfUpgrade
     */
    /**
     * changes the upgradeLevel of the tower
     * @param typeOfUpgrade
     */
    public  void upgrade(int typeOfUpgrade){
        if (typeOfUpgrade==1)
            image=upgrade1;
        else if (typeOfUpgrade==2)
            image=upgrade2;

        this.upgradeLevel=typeOfUpgrade;
    };

    

    /**
     * Tells if the enemy is in he tower's range
     * @param enemyPosition
     * @return
     */
    public boolean inRange(Position enemyPosition) {
        return sqrt(pow(abs(this.position.getCordX() - enemyPosition.getCordX()), 2) + pow(abs(this.position.getCordY() - enemyPosition.getCordY()), 2)) <= this.range;
    };
    public int getAttackPower()
    {
        return this.attack;
    }
    public String getName()
    {
        return this.name;
    }
    public void setXP(int xp)
    {
        this.xp=xp;
    }
    public int getXP()
    {
        return this.xp;
    }
    public int getLevel()
    {
        return this.level;
    }
    public int getFrequency()
    {
        return this.frequency;
    }
    public int getTillNextShout(){return this.tillNextShoot;}
    public int getRange()
    {
        return this.range;
    }
    public int getUpgradeLevel()
    {
        return this.upgradeLevel;
    }
    public Image getAttackImage() { return this.attackImage;}
    public int getPrice(){return this.price;}
public Position getPosition(){return this.position;}
    /**
     * decreases the tillNextShoot counter
     */
    public void tillNextShootDecrease(){this.tillNextShoot--;}

    /**
     * refreshes the tillNextShoot counter
     */
    public void RefreshTillNextShoot(){this.tillNextShoot=25*this.frequency;}
    public void draw(Graphics g){
        g.drawImage( image, position.getCordX(), position.getCordY(), 80, 80, null);
    }

}
