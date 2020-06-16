package game;

import game.enemies.*;
import game.utils.Position;

import javax.swing.*;
import java.awt.*;

public class Castle {

    public Castle (Position position) {
        this.position = position;
        safe = true;
    }

    /**
     * increaseHealth method to increase castle health with a spell
     * @param extra_health
     */
    public void increaseHealth(int extra_health) {
        if(safe) {
            health += extra_health;
        }
        if(health > 100)
            health = 100;
    }
    

    /**
     * getAttacked method to decrease the castle health when any enemy attacks
     * @param attack
     */
    public void getAttacked(int attack){
        if(safe) {
            health -= attack;
        }
        if(health <= 0)
            safe = false;
    }

    public Position getPosition() {
        return position;
    }


    /**
     * To check if castle is still alive and has health
     * @return Boolean
     */
    public Boolean isSafe(){
        return safe;
    }

    public int getHealth(){ return health;}


    /**
     *  draw method to draw the castle in the panel
     * @param g
     */
    public void draw(Graphics g){
        g.drawImage( image, position.getCordX(), position.getCordY(), 120, 120, null);
    }


    /**
     * collides method to check if enemy is attacking
     * @param s
     * @return Boolean
     */
    public boolean collides(Enemy s){
        Rectangle rect = new Rectangle(position.getCordX(), position.getCordY(), 80, 80);
        Rectangle otherRect = new Rectangle(s.getPosition().getCordX(), s.getPosition().getCordY(), 80, 80);
        return rect.intersects(otherRect);
    }


    private Position position;
    private int health = 100;
    private boolean safe;
    private Image image = new ImageIcon("images/castle.png").getImage();
}
