/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Sean Merkel
 */
package ucf.assignments;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class InventoryManager extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try{
            AnchorPane root = FXMLLoader.load(getClass().getResource("InventoryManager.fxml"));

            Scene scene = new Scene(root);

            primaryStage.setScene(scene);
            primaryStage.setTitle("Inventory");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ObservableList<InventoryItem> getProduct() {
        ObservableList<InventoryItem> thisList = InventoryManagerController.list;
        return thisList;
    }
}