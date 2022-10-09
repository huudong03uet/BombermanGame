package main.general;

import main.entities.Entity;
import main.entities.bomb.Bomb;
import main.entities.bomb.Flame;

import static java.lang.Math.abs;
import static main.PropertiesStatic.lengthFlameDefault;
import static main.PropertiesStatic.map;
public class CheckCollision {
    private double bottomA;
    private double bottomB;
    private double topA;
    private double topB;

    private double leftA;
    private double leftB;
    private double rightA;
    private double rightB;


    public boolean checkCollision(Entity entity1, Entity entity2) {
        int distance = 0; int notDistance = 10;
        if (entity1 instanceof Bomb) {
            distance = 10;
            notDistance = 0;
        }
        bottomA = entity1.getY() + entity1.getSprite().getHeight() - notDistance;
        bottomB = entity2.getY() + entity2.getSprite().getHeight() - notDistance;

        topA = entity1.getY() + notDistance;
        topB = entity2.getY() + notDistance;

        leftA = entity1.getX() + notDistance;
        leftB = entity2.getX() + notDistance;

        rightA = entity1.getX() + entity1.getSprite().getWidth() + distance - notDistance;
        rightB = entity2.getX() + entity2.getSprite().getWidth() - notDistance;
        return checkCollisionTwoObject();
    }

    public boolean checkCollisionTwoObject() {
        if (bottomA <= topB) {
            return false;
        }

        if (topA >= bottomB) {
            return false;
        }

        if (rightA <= leftB) {
            return false;
        }

        if (leftA >= rightB) {
            return false;
        }

        return true;
    }

    public boolean checkCollisionWithFlame(Entity entity1, Flame flame) {
        int xCenter = entity1.getXCenter();
        int yCenter = entity1.getYCenter();

        int xFlame = flame.getXCenter();
        int yFlame = flame.getYCenter();

        if (xCenter == xFlame && yCenter == yFlame) {
            return true;
        }

        if(abs(xCenter - xFlame) <= 1 && abs(yCenter - yFlame) <= 1) {
            if (xCenter == xFlame && (yCenter == yFlame + 1 || yCenter == yFlame - 1)) {
                return true;
            }

            if (yCenter == yFlame && (xCenter == xFlame + 1 || xCenter == xFlame - 1)) {
                return true;
            }
            return false;
        }


        if(yCenter == yFlame && xCenter == xFlame + 2 && lengthFlameDefault == 2
                && map[yCenter][xCenter - 1] != '#' && map[yCenter][xCenter - 1] != '*') {
            return true;
        }

        if(yCenter == yFlame && xCenter == xFlame - 2 && lengthFlameDefault == 2
                && map[yCenter][xCenter + 1] != '#' && map[yCenter][xCenter + 1] != '*') {
            return true;
        }

        if(xCenter == xFlame && yCenter == yFlame + 2 && lengthFlameDefault == 2
                && map[yCenter - 1][xCenter] != '#' && map[yCenter - 1][xCenter] != '*') {
            return true;
        }

        if(xCenter == xFlame && yCenter == yFlame - 2 && lengthFlameDefault == 2
                && map[yCenter + 1][xCenter] != '#' && map[yCenter + 1][xCenter] != '*') {
            return true;
        }

        return false;
    }
}
