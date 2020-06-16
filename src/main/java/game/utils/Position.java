package game.utils;

public class Position {
    private int cordX,cordY;
    public Position(int x,int y){
        cordX=x;
        cordY=y;
    }
    public int getCordX() {
        return this.cordX;
    }
    public int getCordY() {
        return this.cordY;
    }

    public void setCordX(int cordX) {
        this.cordX = cordX;
    }

    public void setCordY(int cordY) {
        this.cordY = cordY;
    }
}
