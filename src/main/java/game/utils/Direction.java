package game.utils;

public class Direction {
    private int directionX;
    private int directionY;
    public Direction(int x, int y)
    {
        this.directionX=x;
        this.directionY=y;
    }
    public int getDirectionX(){return directionX;}
    public int getDirectionY(){return directionY;}
    public void setDirectionX(int x){this.directionX=x;}
    public void setDirectionY(int y){this.directionY=y;}
    public void setDirectionXandY(int x,int y){this.directionX=x; this.directionY=y;}
}
