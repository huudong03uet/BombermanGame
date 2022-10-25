package main.entities.bomber;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import main.entities.CanMoveEntity;
import main.entities.Entity;
import main.graphics.Sprite;
import main.soundSetting.Sound;

import java.util.List;

import static java.lang.Math.abs;
import static main.map.MapGame.level;
import static main.settings.PropertiesConstant.*;
import static main.settings.PropertiesStatic.*;
import static main.settings.StatusGame.GAME_CHANGE_LEVEL;
import static main.settings.StatusGame.status;


public class Bomber extends CanMoveEntity {
    protected int speed = SPEED_BOMBER * 2;
    protected Image[][] imagesTwoWay = new Image[4][BOMBER_SPRITE];
    protected Image[] imagesDead = new Image[BOMBER_DEAD_SPRITE];
    private KeyCode keyCode = null;
    private Sound bomberDie = new Sound();
    private int directionPrevious = 0;
    private Image imageRender = img;
    private int indexAnimate = 0;
    protected boolean isDead = false;
    protected boolean canPassWall = false;
    protected int timeRemainPassWall = TIME_REMAIN * 2 * 10;

    protected boolean canProtected = false;
    protected int timeRemainProtected = TIME_REMAIN * 2 * 10;
    int countDead = 0;
    protected Sound sound = new Sound();

    public Bomber(int x, int y) {
        super(x, y, Sprite.player_right.getFxImage(Sprite.player_right.get_realWidth(), Sprite.player_right.get_realHeight()));

        imagesTwoWay[RIGHT][0] = Sprite.player_right.getFxImage(Sprite.player_right.get_realWidth(), Sprite.player_right.get_realHeight());
        imagesTwoWay[DOWN][0] = Sprite.player_down.getFxImage(Sprite.player_down.get_realWidth(), Sprite.player_down.get_realHeight());
        imagesTwoWay[LEFT][0] = Sprite.player_left.getFxImage(Sprite.player_left.get_realWidth(), Sprite.player_left.get_realHeight());
        imagesTwoWay[UP][0] = Sprite.player_up.getFxImage(Sprite.player_up.get_realWidth(), Sprite.player_up.get_realHeight());

        imagesTwoWay[RIGHT][1] = Sprite.player_right_1.getFxImage(Sprite.player_right_1.get_realWidth(), Sprite.player_right_1.get_realHeight());
        imagesTwoWay[DOWN][1] = Sprite.player_down_1.getFxImage(Sprite.player_down_1.get_realWidth(), Sprite.player_down_1.get_realHeight());
        imagesTwoWay[LEFT][1] = Sprite.player_left_1.getFxImage(Sprite.player_left_1.get_realWidth(), Sprite.player_left_1.get_realHeight());
        imagesTwoWay[UP][1] = Sprite.player_up_1.getFxImage(Sprite.player_up_1.get_realWidth(), Sprite.player_up_1.get_realHeight());

        imagesTwoWay[RIGHT][2] = Sprite.player_right_2.getFxImage(Sprite.player_right_2.get_realWidth(), Sprite.player_right_2.get_realHeight());
        imagesTwoWay[DOWN][2] = Sprite.player_down_2.getFxImage(Sprite.player_down_2.get_realWidth(), Sprite.player_down_2.get_realHeight());
        imagesTwoWay[LEFT][2] = Sprite.player_left_2.getFxImage(Sprite.player_left_2.get_realWidth(), Sprite.player_left_2.get_realHeight());
        imagesTwoWay[UP][2] = Sprite.player_up_2.getFxImage(Sprite.player_up_2.get_realWidth(), Sprite.player_up_2.get_realHeight());

        imagesDead[0] = Sprite.player_dead1.getFxImage(Sprite.player_dead1.get_realWidth(), Sprite.player_dead1.get_realHeight());
        imagesDead[1] = Sprite.player_dead2.getFxImage(Sprite.player_dead2.get_realWidth(), Sprite.player_dead2.get_realHeight());
        imagesDead[2] = Sprite.player_dead3.getFxImage(Sprite.player_dead3.get_realWidth(), Sprite.player_dead3.get_realHeight());
    }

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
    }

    public void updateProtected() {
        if(canProtected) {
            timeRemainProtected--;
            if(timeRemainProtected == 0) {
                canProtected = false;
                timeRemainProtected = TIME_REMAIN * 2 * 10;
            }
        }
    }

    public void setCoordinate(char[][] mapGame) {
        updateProtected();

        setKeyCodeFromDirection();
        setCoordinateAfterMove();

        if (canPassWall == true) {
            updateTimePassWall();
            if(getXCenter() <= 0 || getXCenter() >= WIDTH_TILE - 1 || getYCenter() <= 0 || getYCenter() >= HEIGHT_TILE - 1) {
                setCoordinateAfterMoveReverse();
            }
            setCoordinatesRenderMap();
            return;
        }

        if (checkWallCollision(mapGame)) {
            boolean isResult = false;
            if (direction == 0 || direction == 2) {
                isResult = optimizationCoordinateY(mapGame);
            } else {
                isResult = optimizationCoordinateX(mapGame);
            }
            if (isResult == false) {
                setCoordinateAfterMoveReverse();
            }
        }
        setCoordinatesRenderMap();
    }

    public void updateTimePassWall() {
        if (timeRemainPassWall > 0) {
            timeRemainPassWall--;
        } else {
            if (checkWallCollision(map) == true) {
                return;
            }
            canPassWall = false;
            timeRemainPassWall = TIME_REMAIN * 2 * 10;
        }
    }

    @Override
    public void setCoordinate(char[][] mapGame, Entity player) {

    }

    private boolean optimizationCoordinateX(char[][] mapGame) {
        for (int i = -16; i <= 16; i++) {
            x += i;
            if (!checkWallCollision(mapGame)) {
                if (i > 3 || i < -3) {
                    x -= i;
                    x += i / abs(i) * 3;
                }
                return true;
            }
            x -= i;
        }
        return false;
    }

    public boolean optimizationCoordinateY(char[][] mapGame) {
        for (int j = -24; j <= 24; j++) {
            y += j;
            if (!checkWallCollision(mapGame)) {
                if (j > 3 || j < -3) {
                    y -= j;
                    y += j / abs(j) * 3;
                }
                return true;
            }
            y -= j;
        }
        return false;
    }

    public void updateSprite() {
        if (direction == STOP) {
            numberSprite = 0;
        } else {
            numberSprite = indexAnimate / (FRAME_PER_ONE_BOMBER / (BOMBER_SPRITE - 1)) + 1;
            indexAnimate += 1;
            if (indexAnimate >= FRAME_PER_ONE_BOMBER) {
                indexAnimate = 0;
            }
        }
    }

    public void getItem(char[][] map, List<Entity> items) {
        boolean hasIsItem = false;

        if (map[getYCenter()][getXCenter()] == SPEED_ITEM) {
            hasIsItem = true;
            map[getYCenter()][getXCenter()] = CHAR_GRASS;
            speed += 2;

        }

        if (map[getYCenter()][getXCenter()] == FLAME_ITEM) {
            hasIsItem = true;
            map[getYCenter()][getXCenter()] = CHAR_GRASS;
            lengthFlameDefault = 2;
        }

        if (map[y / (TILE_SIZE * SCALE)][x / (TILE_SIZE * SCALE)] == FLAME_PASS_ITEM) {
            hasIsItem = true;
            map[getYCenter()][getXCenter()] = CHAR_GRASS;
            isHavingFlame = true;
        }

        if (map[getYCenter()][getXCenter()] == BOMB_ITEM) {
            hasIsItem = true;
            map[getYCenter()][getXCenter()] = CHAR_GRASS;
            numberBombDefault += 1;
        }

        if (map[getYCenter()][getXCenter()] == WALL_PASS_ITEM) {
            hasIsItem = true;
            map[getYCenter()][getXCenter()] = CHAR_GRASS;
            canPassWall = true;
        }

        if (map[getYCenter()][getXCenter()] == DETONATOR_ITEM) {
            hasIsItem = true;
            map[getYCenter()][getXCenter()] = CHAR_GRASS;
            canProtected = true;
        }

        if(map[getYCenter()][getXCenter()] == CHAR_PORTAL) {
            hasIsItem = true;
            level++;
            if(level == 6) {
                level = 5;
            }
            status = GAME_CHANGE_LEVEL;
        }

        if (hasIsItem == true) {
            sound.playSE(5);
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i).getXCenter() == getXCenter() && items.get(i).getYCenter() == getYCenter()) {
                    items.remove(i);
                    break;
                }
            }

        }
    }

    @Override
    public void update() {
        xUnit = x / (TILE_SIZE * SCALE);
        yUnit = y / (TILE_SIZE * SCALE);
    }

    public void update(List<Entity> items) {
        xUnit = x / (TILE_SIZE * SCALE);
        yUnit = y / (TILE_SIZE * SCALE);
        getItem(map, items);
    }

    public void renderIsDead(GraphicsContext gc) {
        int spriteNumber = countDead / (FRAME_PER_SECOND / BOMBER_DEAD_SPRITE);
        gc.drawImage(imagesDead[spriteNumber], x - xHide, y - yHide);
        countDead += 1;
        if (countDead >= FRAME_PER_SECOND) {
            countDead = 0;
            isRemove = true;
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        updateSprite();
        if (isDead) {
            renderIsDead(gc);
            return;
        }

        if (direction == STOP) {
            imageRender = imagesTwoWay[directionPrevious][0];
        } else {
            imageRender = imagesTwoWay[direction][numberSprite];
            directionPrevious = direction;
        }
        gc.drawImage(imageRender, x - xHide, y - yHide);
    }

    public void setKeyCodeFromDirection() {
        if (direction == RIGHT) {
            keyCode = KeyCode.RIGHT;
        } else if (direction == DOWN) {
            keyCode = KeyCode.DOWN;
        } else if (direction == LEFT) {
            keyCode = KeyCode.LEFT;
        } else if (direction == UP) {
            keyCode = KeyCode.UP;
        } else if (direction == STOP) {
            keyCode = null;
        }
    }

    @Override
    public void setCoordinateAfterMoveReverse() {
        if (keyCode == KeyCode.RIGHT) {
            this.x -= speed;
        } else if (keyCode == KeyCode.LEFT) {
            this.x += speed;
        } else if (keyCode == KeyCode.UP) {
            this.y += speed;
        } else if (keyCode == KeyCode.DOWN) {
            this.y -= speed;
        }
    }

    @Override
    public void setCoordinateAfterMove() {
        if (keyCode == KeyCode.RIGHT) {
            this.x += speed;
        } else if (keyCode == KeyCode.LEFT) {
            this.x -= speed;
        } else if (keyCode == KeyCode.UP) {
            this.y -= speed;
        } else if (keyCode == KeyCode.DOWN) {
            this.y += speed;
        }
    }

    public void setIsDead(boolean isDead) {
        if (canProtected == true) {
            return;
        }
        bomberDie.playSE(2);
        this.isDead = isDead;
    }

    @Override
    public void setIsExploded(boolean isExploded) {
        if (canProtected == true) {
            return;
        }
        directionAnimate = STOP;
        super.setIsExploded(isExploded);
    }

    @Override
    public void increaseScore() {
        score -= 0;
        if(score < 0) {
            score = 0;
        }
    }
}