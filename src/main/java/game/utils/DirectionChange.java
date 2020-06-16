package game.utils;

import static java.lang.StrictMath.*;

public class DirectionChange{
    public Direction direction;
    public Position position;

    public DirectionChange(Direction direction, Position position) {
        this.direction = direction;
        this.position = position;
    }

    /**
     * Check if enemy is in range
     * @param enemyPosition
     * @return Boolean
     */
    public boolean inRange(Position enemyPosition) {
        return sqrt(pow(abs(this.position.getCordX() - enemyPosition.getCordX()), 2) + pow(abs(this.position.getCordY() - enemyPosition.getCordY()), 2)) <= 50;
    };
}
