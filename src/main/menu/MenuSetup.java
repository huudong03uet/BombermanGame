package main.menu;

import javafx.scene.Group;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;


public class MenuSetup {
    MenuBar menuBar = new MenuBar();

    Menu fileMenu = new Menu("File");
    MenuItem fileMenu1 = new MenuItem("new");
    MenuItem fileMenu2 = new MenuItem("Pause");
    MenuItem fileMenu3 = new MenuItem("Exit");

    Menu HelpMenu = new Menu("Help");
    MenuItem helpMenu1 = new MenuItem("Instruction");

    public MenuSetup() {

    }


    public void addMenu() {
        fileMenu.getItems().addAll(fileMenu1, fileMenu2, fileMenu3);
        helpMenu1.setOnAction(e -> {
            System.out.println("Help");
        });
        HelpMenu.getItems().addAll(helpMenu1);

        menuBar.getMenus().addAll(fileMenu, HelpMenu);

    }

    public void setMenuBar(Group root) {
        addMenu();
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(menuBar);
        root.getChildren().add(borderPane);
    }
}
