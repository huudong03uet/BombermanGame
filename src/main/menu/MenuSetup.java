package main.menu;


import javafx.scene.Group;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

import javax.swing.*;

public class MenuSetup {
    public MenuBar menuBar = new MenuBar();

    public Menu fileMenu = new Menu("File");
    public MenuItem fileMenuNew = new MenuItem("New");
    public MenuSelectionManager menuSelectionManager = new MenuSelectionManager();
    public MenuItem fileMenuPause = new MenuItem("Pause");
    public MenuItem fileMenuExit = new MenuItem("Exit");


    public Menu helpMenu = new Menu("Help");
    public MenuItem helpMenuInstruction = new MenuItem("Instruction");

    public MenuSetup() {

    }


    public void addMenu() {
        fileMenu.getItems().addAll(fileMenuNew, fileMenuPause, fileMenuExit);
        helpMenuInstruction.setOnAction(e -> {
            System.out.println("Instruction");
        });
        fileMenuNew.setOnAction(e -> {
            System.out.println("New");
        });
        fileMenuPause.setOnAction(e -> {
            System.out.println("Pause");
        });
        fileMenuExit.setOnAction(e -> {
            System.out.println("Exit");
        });
        helpMenu.getItems().addAll(helpMenuInstruction);
        menuBar.getMenus().addAll(fileMenu, helpMenu);
    }

    public void setMenuBar(Group root) {
        addMenu();
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(menuBar);
        root.getChildren().add(borderPane);
    }
}
