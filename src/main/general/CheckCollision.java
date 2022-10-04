package main.general;

import main.entities.Entity;

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
        bottomA = entity1.getY() + entity1.getSprite().getHeight();
        bottomB = entity2.getY() + entity2.getSprite().getHeight() - 5;

        topA = entity1.getY();
        topB = entity2.getY() + 5;

        leftA = entity1.getX();
        leftB = entity2.getX() + 5;

        rightA = entity1.getX() + entity1.getSprite().getWidth();
        rightB = entity2.getX() + entity2.getSprite().getWidth() - 5;

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
}
