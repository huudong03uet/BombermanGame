package main.general;

import main.entities.bomber.Bomber;
import main.entities.enemy.Balloom;

public class CheckCollision {
    private double bottomA;
    private double bottomB;
    private double topA;
    private double topB;

    private double leftA;
    private double leftB;
    private double rightA;
    private double rightB;

    public boolean checkCollision(Object object1, Object object2) {
        if (object1 instanceof Bomber && object2 instanceof Balloom) {
            setCoordinateBomberAndBalloom((Bomber) object1, (Balloom) object2);
            return checkCollisionTwoObject();
        }
        return true;
    }

    public void setCoordinateBomberAndBalloom(Bomber bomber, Balloom balloom) {
        bottomA = bomber.getY() + bomber.getSprite().getHeight();
        bottomB = balloom.getY() + balloom.getSprite().getHeight() - 5;

        topA = bomber.getY();
        topB = balloom.getY() + 5;

        leftA = bomber.getX();
        leftB = balloom.getX() + 5;

        rightA = bomber.getX() + bomber.getSprite().getWidth();
        rightB = balloom.getX() + balloom.getSprite().getWidth() - 5;
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
