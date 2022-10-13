package main.menu;


import javafx.scene.Group;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import main.general.GeneralStatic;

import javax.swing.*;

import static main.settings.StatusGame.*;

public class MenuSetup {
    public MenuBar menuBar = new MenuBar();
    public MenuSelectionManager menuSelectionManager = new MenuSelectionManager();

    /**
     * Set up control menu
     */
    public Menu game = new Menu(" Game ");
    public MenuItem gameMenuReturn = new MenuItem(" Open Menu ");
    public MenuItem gameMenuNewGame = new MenuItem("New Game");
    public MenuItem gameMenuPause = new MenuItem("Pause");

    public MenuItem gameMenuContinue = new MenuItem("Continue");
    public MenuItem gameMenuExit = new MenuItem("Exit");

    /**
     * Set up auto menu.
     */
    public Menu player = new Menu(" Player ");
    public MenuItem playerMenuManual = new MenuItem("Manual");
    public MenuItem playerMenuAuto = new MenuItem("Auto");

    /**
     * Set up level menu.
     */
    public Menu level = new Menu(" Level ");
    public MenuItem level1 = new MenuItem("Level 1");

    public MenuItem level2 = new MenuItem("Level 2");
    public MenuItem level3 = new MenuItem("Level 3");
    public MenuItem level4 = new MenuItem("Level 4");
    public MenuItem level5 = new MenuItem("Level 5");

    /**
     * Set up sound menu.
     */
    public Menu options = new Menu(" Options ");
    public MenuItem optionsMenuSound = new MenuItem("Sound");
    public MenuItem optionsMenuMusic = new MenuItem("Music");
    public MenuItem optionsMenuControl = new MenuItem("Control");

    /**
     * Set up help menu.
     */
    public Menu helpMenu = new Menu(" Help ");
    public MenuItem helpMenuInstruction = new MenuItem("Instruction");


    public MenuSetup() {

    }


    /**
     * Game menu:
     * - Menu
     * - New Game
     * - Pause
     * - Continue
     * - Exit
     */
    public void addGameMenu() {
        game.getItems().addAll(gameMenuReturn, gameMenuNewGame, gameMenuPause, gameMenuContinue, gameMenuExit);
        gameMenuReturn.setOnAction(event -> {
            status = GAME_MENU;
        });
        gameMenuNewGame.setOnAction(event -> {
            status = GAME_RESTART_LEVEL;
        });
        gameMenuPause.setOnAction(e -> {
            status = GAME_PAUSE;
        });

        gameMenuContinue.setOnAction(e -> {
            status = GAME_PLAY;
        });

        gameMenuExit.setOnAction(e -> {
            status = GAME_EXIT;
        });
        menuBar.getMenus().add(game);
    }

    /**
     * Player menu:
     * - Manual
     * - Auto
     */
    public void addPlayerMenu() {
        player.getItems().addAll(playerMenuManual, playerMenuAuto);
        playerMenuManual.setOnAction(e -> {
            System.out.println("Manual");
        });
        playerMenuAuto.setOnAction(e -> {
            System.out.println("Auto");
        });
        menuBar.getMenus().add(player);
    }

    /**
     * Options menu:
     * - Sound
     * - Music
     * - Control
     */
    public void addOptionsMenu() {
        options.getItems().addAll(optionsMenuSound, optionsMenuMusic, optionsMenuControl);
        optionsMenuSound.setOnAction(e -> {
            System.out.println("Sound");
        });
        optionsMenuMusic.setOnAction(e -> {
            System.out.println("Music");
        });
        optionsMenuControl.setOnAction(e -> {
            System.out.println("Control");
        });
        menuBar.getMenus().add(options);
    }

    /**
     * Level menu:
     * - Level 1
     * - Level 2
     * - Level 3
     * - Level 4
     * - Level 5
     */
    public void addLevelMenu() {
        level.getItems().addAll(level1, level2, level3, level4, level5);
        level1.setOnAction(e -> {
            GeneralStatic.changeLevel(1);
        });
        level2.setOnAction(e -> {
            GeneralStatic.changeLevel(2);
        });
        level3.setOnAction(e -> {
            GeneralStatic.changeLevel(3);
        });
        level4.setOnAction(e -> {
            GeneralStatic.changeLevel(4);
        });
        level5.setOnAction(e -> {
            GeneralStatic.changeLevel(5);
        });
        menuBar.getMenus().add(level);
    }

    /**
     * Help menu:
     * - Instruction
     */
    public void addHelpMenu() {
        helpMenu.getItems().addAll(helpMenuInstruction);
        helpMenuInstruction.setOnAction(e -> {
            System.out.println("Instruction");
        });
        menuBar.getMenus().add(helpMenu);
    }

    /**
     * Menu bar:
     * - Game
     * - Player
     * - Level
     * - Options
     * - Help
     */
    public void addMenu() {
        addGameMenu();
        addPlayerMenu();
        addLevelMenu();
        addOptionsMenu();
        addHelpMenu();
    }

    public void setMenuBar(Group root) {
        addMenu();
        BorderPane borderPane = new BorderPane();

        borderPane.setTop(menuBar);
        root.getChildren().add(borderPane);
    }
}
