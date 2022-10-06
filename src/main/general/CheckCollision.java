package main.general;

import main.entities.Entity;
import main.entities.bomb.Bomb;
import main.entities.bomb.Flame;

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
        int distance = 0;
        if(entity1 instanceof Bomb || entity2 instanceof Bomb) {
            distance = 10;
         }
        bottomA = entity1.getY() + entity1.getSprite().getHeight() + distance;
        bottomB = entity2.getY() + entity2.getSprite().getHeight() ;

        topA = entity1.getY() - distance;
        topB = entity2.getY();

        leftA = entity1.getX() - distance;
        leftB = entity2.getX();

        rightA = entity1.getX() + entity1.getSprite().getWidth() + distance; ;
        rightB = entity2.getX() + entity2.getSprite().getWidth() ;
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
        if (xCenter == xFlame && (yCenter == yFlame + 1 || yCenter == yFlame - 1)) {
            return true;
        }

        if (yCenter == yFlame && (xCenter == xFlame + 1 || xCenter == xFlame - 1)) {
            return true;
        }
        return false;
    }
}
