package main.menu;


import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

import javax.swing.*;

public class MenuSetup {
    public MenuBar menuBar = new MenuBar();
    public MenuSelectionManager menuSelectionManager = new MenuSelectionManager();
    public Menu game = new Menu(" Game ");
    public MenuItem gameMenuNewGame = new MenuItem("New Game");
    public MenuItem gameMenuPause = new MenuItem("Pause");
    public MenuItem gameMenuExit = new MenuItem("Exit");

    public Menu player = new Menu(" Player ");
    public MenuItem playerMenuManual = new MenuItem("Manual");
    public MenuItem playerMenuAuto = new MenuItem("Auto");

    public Menu level = new Menu(" Level ");
    public MenuItem level1 = new MenuItem("Level 1");

    public MenuItem level2 = new MenuItem("Level 2");
    public MenuItem level3 = new MenuItem("Level 3");
    public MenuItem level4 = new MenuItem("Level 4");
    public MenuItem level5 = new MenuItem("Level 5");
    public Label label6 = new Label("Level 6");

    public Menu options = new Menu(" Options ");
    public MenuItem optionsMenuSound = new MenuItem("Sound");
    public MenuItem optionsMenuMusic = new MenuItem("Music");
    public MenuItem optionsMenuControl = new MenuItem("Control");

    public Menu helpMenu = new Menu(" Help ");
    public MenuItem helpMenuInstruction = new MenuItem("Instruction");


    public MenuSetup() {

    }


    public void addGameMenu() {
        game.getItems().addAll(gameMenuNewGame, gameMenuPause, gameMenuExit);
        gameMenuNewGame.setOnAction(e -> {
            System.out.println("New");
        });
        gameMenuPause.setOnAction(e -> {
            System.out.println("Pause");
        });
        gameMenuExit.setOnAction(e -> {
            System.out.println("Exit");
        });
        menuBar.getMenus().add(game);
    }

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

    public void addLevelMenu() {
        level.getItems().addAll(level1, level2, level3, level4, level5);
        level1.setOnAction(e -> {
            System.out.println("Level 1");
        });
        level2.setOnAction(e -> {
            System.out.println("Level 2");
        });
        level3.setOnAction(e -> {
            System.out.println("Level 3");
        });
        level4.setOnAction(e -> {
            System.out.println("Level 4");
        });
        level5.setOnAction(e -> {
            System.out.println("Level 5");
        });
        menuBar.getMenus().add(level);
    }

    public void addHelpMenu() {
        helpMenu.getItems().addAll(helpMenuInstruction);
        helpMenuInstruction.setOnAction(e -> {
            System.out.println("Instruction");
        });
        menuBar.getMenus().add(helpMenu);
    }

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
