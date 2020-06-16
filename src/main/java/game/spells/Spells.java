package game.spells;

public abstract class Spells {
    protected int _cost;
    protected boolean _inUse = false;

    public int get_cost() {
        return _cost;
    }

    public Spells(int c){
        _cost=c;
    }

}

