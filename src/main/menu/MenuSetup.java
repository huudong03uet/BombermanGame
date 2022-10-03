package main.menu;


import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javax.swing.*;

public class MenuSetup {
    public MenuBar menuBar = new MenuBar();

    public Menu fileMenu = new Menu("File");
    public MenuItem fileMenu1 = new MenuItem("new");
    public MenuSelectionManager menuSelectionManager = new MenuSelectionManager();
    public MenuItem fileMenu2 = new MenuItem("Pause");
    public MenuItem fileMenu3 = new MenuItem("Exit");

    public Menu HelpMenu = new Menu("Help");
    public MenuItem helpMenu1 = new MenuItem("Instruction");

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
        // render border pane without insert into image

        root.getChildren().add(borderPane);
    }
}
