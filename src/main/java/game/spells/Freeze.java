package game.spells;
import game.enemies.Enemy;

import java.util.ArrayList;

public class Freeze extends Spells {

    public Freeze(int c) {
        super(c);
    }


    /**It freezes the enemies for some seconds
     * @param enemies -> ArrayList<Enemy>
     */
    public void freeze(ArrayList<Enemy> enemies){

        if(!this._inUse) {
            this._inUse = true;
            for (Enemy enemy : enemies) {
                enemy.setSpeed(0);

            }
            //this._inUse=false;
        }
    }

    /**
     * After the seconds are done it unfreezes the living enemies by giving the original speed
     * @param enemies : ArrayList<Enemy>
     */
    public void unFreeze(ArrayList<Enemy> enemies){
        for(Enemy enemy : enemies ) {
            if(enemy.isAlive()) {
                enemy.setSpeed(enemy.getOriginalSpeed());
            }
        }
        this._inUse=false;

    }

}

