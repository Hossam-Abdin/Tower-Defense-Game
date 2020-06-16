package game.utils;

import javax.swing.*;

public class TowerButton extends JButton  {
    public TowerButton(int coordX, int coordY, int direction){
        super();
        this.setBounds(coordX, coordY,80,80);
        this.position = new Position(coordX, coordY);
        this.direction = direction;
    }

    public Position getPosition() {
        return position;
    }
    public int getDirection() { return direction; }

    private Position position;
    private int direction;
}



