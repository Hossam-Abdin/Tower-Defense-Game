package game.enemies;

import game.Castle;
import game.towers.Tower;
import game.utils.Direction;
import game.utils.DirectionChange;
import game.utils.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public abstract class Enemy {


    protected int _health;
    protected int _originalDef;
    protected int _defencePower;
    private  int _attackPower;
    private int _currentSpeed;
    private int _originalSpeed;
    protected Image _image;
    protected String _img;
    private Position _position;
    protected Direction _direction;
    protected Timer _BlinkTimer;
    protected Timer _RakijaTimer1;
    protected Timer _RakijaTimer2;
    public Enemy(int health, int attackPower, int defencePower, int speed , Position position,String img){
        this._originalDef=defencePower;
        this._health = health;
        this._attackPower = attackPower;
        this._defencePower = defencePower;
        this._currentSpeed = speed;
        this._originalSpeed = speed;
        this._position = position;
        this._image=new ImageIcon(img).getImage();
        this._img=img;
        this._direction=new Direction(1,0);
        this._BlinkTimer = new Timer(3000/ 5, new Enemy.BlinkImageListener());
        this._RakijaTimer1= new Timer(2000,new RakijaTimer1Listener());
        this._RakijaTimer2= new Timer(2000,new RakijaTimer2Listener());
    }

    public boolean isAlive(){
        return _health > 0;
    }

    /**
     * Attack the castle and the castle should be attacked using the visitor design pattern
     * @param c
     */
    public void attack(Castle c){
        if(this.isAlive()){
            c.getAttacked(this._attackPower);
        }
    }

    /**
     * Used by the meteor spell
     * @param damage
     */
    public void getMeteored(float damage){

        //this.getKilled();
        _image=new ImageIcon("images/meteorAttack.jpg").getImage();
        _BlinkTimer.start();
        this._health -= this._health * damage;

    }

    public abstract void getAttacked(Tower t);

    /**
     * The move is done by adding the coordinates after multiplying by the current speed
     */
    public void move(){
        if(this.isAlive()){
            this._position.setCordX(this._position.getCordX() + this._direction.getDirectionX() * this._currentSpeed/2);
            this._position.setCordY(this._position.getCordY() + this._direction.getDirectionY() * this._currentSpeed/2);
        }
    }
    //This is an old version(the function below) of move but i left it here because it is used in the test cases
    public void move(int cordX,int cordY){
        if(this.isAlive()){
            this._position.setCordX(this._position.getCordX() + cordX * this._currentSpeed);
            this._position.setCordY(this._position.getCordY() + cordY* this._currentSpeed);
        }
    }

    /**
     * this function draws the enemy on the screen
     * @param g
     */
    public void draw(Graphics g){
        if(this.isAlive()) {
            g.drawImage(_image, _position.getCordX(), _position.getCordY(), 80, 80, null);
        }
    }

    /**
     *  Change the direction of the movements
     * @param directionChanges
     */
    public void checkDirection(ArrayList<DirectionChange> directionChanges){
        for (DirectionChange directionChange  : directionChanges ) {
            if (directionChange.inRange(this._position)){
                this._direction.setDirectionXandY(directionChange.direction.getDirectionX(),directionChange.direction.getDirectionY());
            }
        }
    }

    public void setSpeed(int speed){this._currentSpeed = speed;}

    public void getKilled(){this._health = 0; this._attackPower=0;}

    public void setDefence(int defence) {this._defencePower = defence;}

    public int getCurrentSpeed(){return this._currentSpeed;}

    public int getOriginalSpeed(){return this._originalSpeed;}

    public int getAttackPower() {return this._attackPower;}

    public int getDefencePower() {return  this._defencePower;}

    public int getHealth() {return this._health;}
    public Direction getDirection() { return this._direction;}
    public void setDirection(int x,int y){this._direction.setDirectionXandY(x,y);}
    public void startRakija1(){this._RakijaTimer1.start();}
    public void startRakija2(){this._RakijaTimer2.start();}

    public abstract int howManyXp();
    public abstract int howManyCoins();

    public Position getPosition(){
        return _position;
    };
    class BlinkImageListener implements ActionListener{
        /**
         * changing the enemy icon when some towers attacks it
         * @param actionEvent
         */
        @Override
        public void actionPerformed(ActionEvent actionEvent) {

            _BlinkTimer.stop();
            _image=new ImageIcon(_img).getImage();

        }
    }
    class RakijaTimer1Listener implements ActionListener{
        /**
         * changing the enemy icon when some towers attacks it
         * @param actionEvent
         */
        @Override
        public void actionPerformed(ActionEvent actionEvent) {

            _RakijaTimer1.stop();
            _direction.setDirectionXandY(_direction.getDirectionX()*(-1),_direction.getDirectionY()*(-1));

        }
    }
    class RakijaTimer2Listener implements ActionListener{
        /**
         * changing the enemy icon when some towers attacks it
         * @param actionEvent
         */
        @Override
        public void actionPerformed(ActionEvent actionEvent) {

            _RakijaTimer2.stop();
            setDefence(_originalDef);

        }
    }
}
