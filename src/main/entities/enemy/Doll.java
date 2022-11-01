package main.entities.enemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import main.entities.Entity;
import main.entities.enemy.AI.AStar;

import static main.graphics.Sprite.*;
import static main.settings.PropertiesConstant.*;
import static main.settings.PropertiesStatic.score;

public class Doll extends Enemy {
    private final int FRAME_PER_ONE = FRAME_PER_SECOND / 2;
    protected Image[][] imagesTwoWay = new Image[4][DOLL_SPRITE];

    AStar aStar = new AStar();

    public Doll(int x, int y, Image img) {
        super(x, y, img);

    }

    public Doll(int x, int y) {
        super(x, y, doll_right1.getFxImage(doll_right1.get_realWidth(), doll_right1.get_realHeight()));
        imagesTwoWay[RIGHT][0] = doll_right1.getFxImage(doll_right1.get_realWidth(), doll_right1.get_realHeight());
        imagesTwoWay[DOWN][0] = doll_left1.getFxImage(doll_left1.get_realWidth(), doll_left1.get_realHeight());
        imagesTwoWay[LEFT][0] = doll_right1.getFxImage(doll_right1.get_realWidth(), doll_right1.get_realHeight());
        imagesTwoWay[UP][0] = doll_left1.getFxImage(doll_left1.get_realWidth(), doll_left1.get_realHeight());

        imagesTwoWay[RIGHT][1] = doll_right2.getFxImage(doll_right2.get_realWidth(), doll_right2.get_realHeight());
        imagesTwoWay[DOWN][1] = doll_left2.getFxImage(doll_left2.get_realWidth(), doll_left2.get_realHeight());
        imagesTwoWay[LEFT][1] = doll_right2.getFxImage(doll_right2.get_realWidth(), doll_right2.get_realHeight());
        imagesTwoWay[UP][1] = doll_left2.getFxImage(doll_left2.get_realWidth(), doll_left2.get_realHeight());

        imagesTwoWay[RIGHT][2] = doll_right3.getFxImage(doll_right3.get_realWidth(), doll_right3.get_realHeight());
        imagesTwoWay[DOWN][2] = doll_left3.getFxImage(doll_left3.get_realWidth(), doll_left3.get_realHeight());
        imagesTwoWay[LEFT][2] = doll_right3.getFxImage(doll_right3.get_realWidth(), doll_right3.get_realHeight());
        imagesTwoWay[UP][2] = doll_left3.getFxImage(doll_left3.get_realWidth(), doll_left3.get_realHeight());

        imagesExploded[0] = doll_dead.getFxImage(doll_dead.get_realWidth(), doll_dead.get_realHeight());
    }

    public void setCoordinate(char[][] mapGame, Entity player) {
        if (directionAnimate == STOP) {
            return;
        }
        setDirection(mapGame, player);
        setCoordinateAfterMove();
    }

    public void setDirection(char[][] mapGame, Entity player) {
        aStar.setNodes(this.getXCenter(), this.getYCenter(), player.getXCenter(), player.getYCenter());
        if (aStar.searchPath()) {
            int nextX = aStar.getPathArrayList().get(0).getColumn();
            int nextY = aStar.getPathArrayList().get(0).getRow();

            int xTile = (x + 1) / SCALED_SIZE;
            int yTile = (y + 1) / SCALED_SIZE;

            int endRightX = (x + SCALED_SIZE - 1) / SCALED_SIZE;
            int endBottomY = (y + SCALED_SIZE - 1) / SCALED_SIZE;

            if (endRightX == xTile && endBottomY == yTile) {
                if (nextX > xTile) {
                    directionAnimate = RIGHT;
                } else if (nextX < xTile) {
                    directionAnimate = LEFT;
                } else if (nextY > yTile) {
                    directionAnimate = DOWN;
                } else if (nextY < yTile) {
                    directionAnimate = UP;
                }
            }
        } else {
            setDirection(mapGame);
        }

    }

    public double distanceObject(Entity entity1, Entity entity2) {
        return Math.sqrt(Math.pow(entity1.getX() - entity2.getX(), 2) + Math.pow(entity1.getY() - entity2.getY(), 2));
    }

    @Override
    public void setCoordinateAfterMove() {
        if (directionAnimate == 0) {
            x += speed;
        } else if (directionAnimate == 1) {
            y += speed;
        } else if (directionAnimate == 2) {
            x -= speed;
        } else if (directionAnimate == 3) {
            y -= speed;
        }
    }


    @Override
    public void update() {
        updateSprite();
    }

    public void updateSprite() {
        indexAnimate += 1;
        if (indexAnimate >= FRAME_PER_ONE) {
            indexAnimate = 0;
        }
    }

    @Override
    public void setCoordinateAfterMoveReverse() {
        // 0: right, 1: down, 2: left, 3: up
        if (directionAnimate == RIGHT) {
            x -= speed;
        } else if (directionAnimate == DOWN) {
            y -= speed;
        } else if (directionAnimate == LEFT) {
            x += speed;
        } else if (directionAnimate == UP) {
            y += speed;
        }
    }


    @Override
    public void render(GraphicsContext gc) {
        if (isExploded == true) {
            int frame = timeRemain % TIME_EXPLOYED / (TIME_EXPLOYED / BOMBER_SPRITE);
            timeRemain++;
            if (timeRemain >= TIME_EXPLOYED) {
                timeRemain = 0;
                isRemove = true;
            } else {
                img = imagesExploded[frame];
            }

        } else {
            int frame = indexAnimate % FRAME_PER_ONE / (FRAME_PER_ONE / BOMBER_SPRITE);
            indexAnimate++;
            if (directionAnimate == RIGHT) {
                img = imagesTwoWay[RIGHT][frame];
            } else if (directionAnimate == DOWN) {
                img = imagesTwoWay[DOWN][frame];
            } else if (directionAnimate == LEFT) {
                img = imagesTwoWay[LEFT][frame];
            } else if (directionAnimate == UP) {
                img = imagesTwoWay[UP][frame];
            }

        }
        super.render(gc);

    }

    public void findCoordinatesRenderFromMap(char[][] mapGame) {
        for (int i = 0; i < HEIGHT_TILE; i++) {
            for (int j = 0; j < WIDTH_TILE; j++) {
                if (mapGame[i][j] == CHAR_DOLL) {
                    y = i * TILE_SIZE * SCALE;
                    x = j * TILE_SIZE * SCALE;
                    mapGame[i][j] = CHAR_BLANK;
                    return;
                }
            }
        }
    }

    public void increaseScore() {
        score += 25;
    }
}
