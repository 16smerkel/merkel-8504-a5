package ucf.assignments;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class InventoryManager extends Application {
    @FXML
    private TableView<InventoryItem> table;

    TextField priceInput;
    TextField serialInput;
    TextField nameInput;

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
/*
    private void addItem(){
        // gets information from textfield, datepicker, and checkbox and creates new item
        // adds item to the list
        TodoListItem theItem = new TodoListItem();
        theItem.setTitle(nameInput.getText());
        theItem.setDueDate(dateInput.getValue());
        if(completeInput.isSelected() == true)
        {
            theItem.setCompletionCheck("Completed");
            theItem.isComplete = true;
        }
        else
        {
            theItem.setCompletionCheck("Not completed");
        }
        table.getItems().add(theItem);
        refresh();
    }

    private void deleteItem(){
        // removes the selected item from list
        ObservableList<TodoListItem> TodoListItemSelected, allTodoListItems;
        allTodoListItems = table.getItems();
        TodoListItemSelected = table.getSelectionModel().getSelectedItems();
        for(TodoListItem p : TodoListItemSelected)
        {
            allTodoListItems.remove(p);
        }
    }

    private void editItem(){
        // gets information from textfield, datepicker, and checkbox (whichever ones were filled out) and edits chosen item
        ObservableList<TodoListItem> allTodoListItems;
        allTodoListItems = table.getItems();
        TodoListItem Selected = table.getSelectionModel().getSelectedItem();
        allTodoListItems.remove(Selected);
        TodoListItem theItem = new TodoListItem();
        if(nameInput.getText() == "")
        {
            theItem.setTitle(Selected.title);
        }
        else
        {
            theItem.setTitle(nameInput.getText());
        }
        if(dateInput.getValue().equals(""))
        {
            theItem.setDueDate(Selected.dueDate);
        }
        else
        {
            theItem.setDueDate(dateInput.getValue());
        }
        if(completeInput.isSelected() == true)
        {
            theItem.setCompletionCheck("Completed");
            theItem.isComplete = true;
        }
        else
        {
            theItem.setCompletionCheck("Not completed");
        }
        table.getItems().add(theItem);
        refresh();
    }

    private void clearAll(){
        // removes everything from the list
        ObservableList<TodoListItem> allTodoListItems;
        allTodoListItems = table.getItems();
        allTodoListItems.clear();
    }
 */
    private void refresh()
    // sets all the variables to a beginning form
    {
        nameInput.clear();
    }

}