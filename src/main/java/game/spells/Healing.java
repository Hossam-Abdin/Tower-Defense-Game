package game.spells;

import game.Castle;

public class Healing extends Spells{
    private int _heal=20;
    public Healing(int c) {
        super(c);
    }

    /** If no other spell is being used eals the castle by increasing it health
     * @param c
     */
    public void affect(Castle c){
        if(!this._inUse){
            this._inUse=true;
            c.increaseHealth(_heal);
            this._inUse=false;
        }
    }
}
