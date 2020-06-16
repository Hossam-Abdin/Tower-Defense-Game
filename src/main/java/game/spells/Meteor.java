package game.spells;
import game.enemies.Enemy;
import game.utils.Position;

import java.util.ArrayList;

import static java.lang.Math.*;

public class Meteor extends Spells {

    private int _range=200;
    private Position _position;

    public Meteor(int c,Position pos) {
        super(c);
        _position=pos;
    }

    public void set_position(Position p){
        _position=p;
    }

    /**
     * Finds if the enemies are in range by finding the distance between the enemies and the meteor and checking if
     * smaller then range
     * @param enemyPosition of type Position
     * @return boolean
     */
    public boolean inRange(Position enemyPosition) {
        return sqrt(pow(abs(this._position.getCordX() - enemyPosition.getCordX()), 2) +
                pow(abs(this._position.getCordY() - enemyPosition.getCordY()), 2)) <= this._range;
    };

    /**
     * If no other spell is being used then it goes through the list of enemies and applies the getMeteored which damages
     * the health of the enemies
     * @param enemies
     */
    public void affect(ArrayList<Enemy> enemies){
        if(!this._inUse){
            for (Enemy enemy:enemies){
                if(inRange(enemy.getPosition())){
                    System.out.println("Inside meteored");
                    enemy.getMeteored(0.9f);

                }
            }

            this._inUse=false;
        }

    }
}
