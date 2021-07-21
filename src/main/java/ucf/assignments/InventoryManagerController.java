package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.fxml.Initializable;

import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class InventoryManagerController implements Initializable {
    @FXML
    Button addItemButton;

    @FXML
    TableView<InventoryItem> table;

    @FXML
    VBox vbMenu;

    @FXML
    TextField price;

    @FXML
    TextField serial;

    @FXML
    TextField name;

    @FXML
    FileChooser fileChooser = new FileChooser();

    @FXML
    private TextArea theDisplayTextArea;

    @FXML
    ListView<InventoryItem> itemList;

    static ObservableList<InventoryItem> list = FXCollections.observableArrayList();

    @FXML
    public void addItem(Event e){
        // gets information from the three textfields and creates new item
        // adds item to the list
        int noSerialFound = 0;
        noSerialFound = searchForSerial(list, serial.getText());
        int serialFormatCheck = 0;
        serialFormatCheck = checkSerialFormat(serial.getText());
        int nameLengthCheck = 0;
        nameLengthCheck = checkNameLength(name.getText());
        String fixedPrice = price.getText();
        fixedPrice = makeDollar(fixedPrice);
        String errorMessage = "";
        if(noSerialFound == 2) {
            errorMessage += ("SERIAL NUMBER ALREADY IN LIST! TRY ANOTHER SERIAL NUMBER!!!\n");
        }
        else if(noSerialFound == 2 && serialFormatCheck == 2) {
            errorMessage += ("SERIAL NUMBER IS NOT IN XXXXXXXXXX FORMAT! TRY A DIFFERENT FORMAT!!!\n");
        }
        else if(nameLengthCheck == 2) {
            errorMessage += ("NAME LENGTH IS NOT BETWEEN 2 AND 256 CHARACTERS! TRY ANOTHER NAME!!!");
        }
        if(noSerialFound == 2 || serialFormatCheck == 2 || nameLengthCheck == 2) {
           // this.theDisplayTextArea.setText(errorMessage);
        }
        else
        {
            InventoryItem theItem = new InventoryItem(fixedPrice, serial.getText(), name.getText());
            list.add(theItem);
            itemList.setItems(list); // prints the item
           //this.theDisplayTextArea.setText("");
        }
        refresh();
    }

    public int searchForSerial(ObservableList<InventoryItem> list, String theSerial){
        if(list.contains(theSerial))
        {
            return 1;
        }
        else
        {
            return 2;
        }
    }

    public int checkSerialFormat(String theSerial){
        if(theSerial.length() == 10)
        {
            return 2;
        }
        else
        {
            return 1;
        }
    }

    public int checkNameLength(String theName){
        if(theName.length() >= 2 && theName.length() <= 256)
        {
            return 2;
        }
        else
        {
            return 1;
        }
    }


    public String makeDollar(String fixedPrice)
    {
        int intIndex = fixedPrice.indexOf("$");
        if(intIndex == - 1) {
            fixedPrice = ("$" + fixedPrice);
        }
        int dotIndex = fixedPrice.indexOf(".");
        if(dotIndex == - 1) {
            fixedPrice = (fixedPrice + ".00");
        }
        return fixedPrice;
    }
/*
    @FXML
    public void markComplete(Event e) {
        isComplete.setSelected(true);
        // go to item's boolean complete and make it true
        // fix screen to display checkmark under complete column
    }

    @FXML
    public void getHelp(Event e) {
        Stage secondStage = new Stage();
        secondStage.setScene(new Scene(new AnchorPane(new Label("TodoListHelp.fxml"))));
        secondStage.show();
        // opens a stage with help text
    }

    @FXML
    public void displayAll(Event e) {
        String allString = "";
        for( Object item : list)
        {
            String curString = String.format("%s%n\n", item);
            allString += curString;
        }
        this.theDisplayTextArea.setText(allString);
        // adds each item to one big string of items
        // displays the big string in the right side of the application
    }

    @FXML
    public void displayIncomplete(Event e) {
        String incompleteString = "";
        for( Object item : list)
        {
            String curString = String.format("%s%n\n", item);
            if(curString.contains("Not Completed"))
            {
                incompleteString += curString;
            }
        }
        this.theDisplayTextArea.setText(incompleteString);
        // goes through every item in list
        // if marked incomplete it is converted to a string and to one big string of incompleted items
        // displays the big string in the right side of the application
    }

    @FXML
    public void displayComplete(Event e) {
        String CompleteString = "";
        for( Object item : list)
        {
            String curString = String.format("%s%n\n", item);
            if(!(curString.contains("Not Completed")))
            {
                CompleteString += curString;
            }
        }
        this.theDisplayTextArea.setText(CompleteString);
        // goes through every item in list
        // if marked complete it is converted to a string and to one big string of completed items
        // displays the big string in the right side of the application
    }

    @FXML
    public void saveItems(Event event){
        // opens up a window to be able save list into a text file
        // text file is then converted from a list full of todolist items
        // toString() is used to make the items in the list into Strings
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Dialog");
        fileChooser.setInitialFileName("mysave");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("text file", "*.txt"));
        File selectedFile = null;
        while(selectedFile== null){
            selectedFile = fileChooser.showSaveDialog(null);
        }

        File file = new File(String.valueOf(selectedFile));
        PrintWriter outFile = null;
        try {
            outFile = new PrintWriter(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        outFile.println("Your Todo List");
        for(int i = 0; i < itemList.getItems().size(); i++){
            outFile.println(itemList.getItems().get(i).toString());
        }
        outFile.close();
    }

    @FXML
    public void loadItems(Event e) {
        // opens up a window to be able load a text file
        // text file is then converted into a list full of todolist items
        // extractTitle, extractDueDate, and extractCompletion are key to creating each item in the observable list that is being loaded
        Window stage = vbMenu.getScene().getWindow();
        fileChooser.setTitle("Load Dialog");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("text file", "*.txt"));
        try {
            File file = fileChooser.showOpenDialog(stage);
            fileChooser.setInitialDirectory(file.getParentFile());
            Scanner myReader = new Scanner(file);
            list.clear();
            try {
                while (myReader.hasNextLine()) {
                    String theTitle = "", firstDueDate = "";
                    int completeNum = -1;
                    String curLine = myReader.nextLine();
                    completeNum = extractCompletion(curLine);
                    firstDueDate = extractDueDate(curLine);
                    theTitle = extractTitle(curLine);
                    if(completeNum == 0)
                    {
                        TodoListItem theItem = new TodoListItem(theTitle, LocalDate.parse(firstDueDate), true, "Completed");
                        list.add(theItem);
                        itemList.setItems(list);
                    }
                    else if(completeNum == 1)
                    {
                        TodoListItem theItem = new TodoListItem(theTitle, LocalDate.parse(firstDueDate), false, "Not Completed");
                        list.add(theItem);
                        itemList.setItems(list);
                    }
                    else
                    {
                        continue;
                    }
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
    }

    public int extractCompletion(String curLine){
        // searches for the word Not Completed or Completed to determine the current line's completion status
        int theCompletionCheck = -1;
        int intIndex = curLine.indexOf("Not Completed");
        if(intIndex == - 1) {
            int comIndex = curLine.indexOf("Completed");
            if(comIndex == - 1) {
                theCompletionCheck = -2; // Neither
            } else {
                theCompletionCheck = 1;  // Completed
            }
        } else {
            theCompletionCheck = 0;  // Not Completed
        }
        return theCompletionCheck;
    }

    public String extractDueDate(String curLine) {
        // searches through the current line to take out the specific location of the due date
        String firstDueDate = "";
        int dueDateCounter = 0;
        for (char c : curLine.toCharArray()) {
            if (Character.isDigit(c) || c == '-') {
                firstDueDate += c;
                dueDateCounter++;
            }
            if (dueDateCounter == 10) {
                break;
            }
        }
        return firstDueDate;
    }

    public String extractTitle(String curLine){
        // searches through the current line to take out the specific location of the task description
        String theTitle = "";
        int dueDateCounter = 0;
        for (char c : curLine.toCharArray()) {
            if (Character.isDigit(c) || c == '-') {
                dueDateCounter++;
            }
            if (dueDateCounter >= 12) {
                if (c != '.') {
                    theTitle += c;
                }
            }

            if (dueDateCounter >= 10) {
                dueDateCounter = dueDateCounter + 1; // skip the space between due date and task description
            }
            if (c == '.') {
                break;
            }
        }
        return theTitle;
    }
*/
    public void refresh()
    {
        // sets all the variables to a beginning form
        price.setText(null);
        serial.setText(null);
        name.setText(null);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // gives the todolist program something to start off with
        //theDisplayTextArea.setText("ready");
    }
/*
    public String addItemTest(String theList, String nameInput, String dateInput, boolean isComplete){
        // gets information from textfield, datepicker, and checkbox and creates new item
        // adds item to the list
        TodoListItem theItem = new TodoListItem();
        theItem.setTitle(nameInput);
        theItem.setDueDate(LocalDate.parse(dateInput));
        if(isComplete == true)
        {
            theItem.setCompletionCheck("Completed");
        }
        else
        {
            theItem.setCompletionCheck("Not completed");
        }
        theList += theItem.toString();
        return theList;
    }

    public String deleteItemTest(String theList, String nameInput, String dateInput, boolean isComplete){
        // removes the selected item from list
        TodoListItem theItem = new TodoListItem();
        theItem.setTitle(nameInput);
        theItem.setDueDate(LocalDate.parse(dateInput));
        if(isComplete == true)
        {
            theItem.setCompletionCheck("Completed");
        }
        else
        {
            theItem.setCompletionCheck("Not completed");
        }
        theList = theList.replaceAll(theItem.toString(), "");
        return theList;
    }

    public String editItemTest(String theList, String nameInput, String dateInput, boolean isComplete, String newName, String newDate, boolean newComplete){
        // searches for the item in the list and then edits it to the user's liking
        // first item input information is what is being edited and second item input information is the new edit
        TodoListItem theItem = new TodoListItem();
        theItem.setTitle(nameInput);
        theItem.setDueDate(LocalDate.parse(dateInput));
        if(isComplete == true)
        {
            theItem.setCompletionCheck("Completed");
        }
        else
        {
            theItem.setCompletionCheck("Not completed");
        }
        TodoListItem newItem = new TodoListItem();
        newItem.setTitle(newName);
        newItem.setDueDate(LocalDate.parse(newDate));
        if(newComplete == true)
        {
            newItem.setCompletionCheck("Completed");
        }
        else
        {
            newItem.setCompletionCheck("Not completed");
        }
        theList = theList.replaceAll(theItem.toString(), newItem.toString());
        return theList;
    }

    public String clearAllTest(String theList) {
        // clears the list
        theList = "";
        return theList;
    }

    public int isItemComplete(String nameInput, String dateInput, boolean isComplete){
        // returns 1 if item is completed and returns 0 if item is incompleted
        TodoListItem theItem = new TodoListItem();
        theItem.setTitle(nameInput);
        theItem.setDueDate(LocalDate.parse(dateInput));
        if(isComplete == true)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }

    @FXML
    public void addListButtonClicked(ActionEvent actionEvent) {
        // clear current page
        // ask for title of new list
        // create new list with the new title
    }

    @FXML
    public void removeListButtonClicked(ActionEvent actionEvent) {
        // clear current page
        // delete the title from the collection of todolist
        // return to previous todolist
    }

    @FXML
    public void editTitleButtonClicked(ActionEvent actionEvent) {
        // delete current title of todolist
        // ask for updated title from user
        // return the new title entered by the user
    }

    @FXML
    public void addItemButtonClicked(ActionEvent actionEvent) {
        // prints in terminal not display?
        //label.setText("Hello World");
        // go to final item on list and open a new spot
        // ask user for due date and task description
        // create new item with information inputted from the user
        // place new item underneath previous item on screen
    }

    @FXML
    public void removeItemButtonClicked(ActionEvent actionEvent) {
        // clear current line containing item on screen
        // delete the item from the collection of todolist
        // move up to the item above
    }

    @FXML
    public void editDescriptionButtonClicked(ActionEvent actionEvent) {
        // clear current description
        // ask for description of new item
        // create new list with the new title
    }

    @FXML
    public void editDueDateButtonClicked(ActionEvent actionEvent) {
        // clear current due date
        // ask for due date of new item
        // create new list with the new title
    }

    @FXML
    public void markCompleteButtonClicked(ActionEvent actionEvent) {
        // go to item's boolean complete and make it true
        // fix screen to display checkmark under complete column
    }

    @FXML
    public void displayAllItemsButtonClicked(ActionEvent actionEvent) {
        // create a new screen
        // print every item
    }

    @FXML
    public void displayIncompleteItemsButtonClicked(ActionEvent actionEvent) {
        // go item by item to check if boolean complete is marked false
        // create a new screen
        // print every item marked false
    }

    @FXML
    public void displayCompleteItemsButtonClicked(ActionEvent actionEvent) {
        // go item by item to check if boolean complete is marked true
        // create a new screen
        // print every item marked true
    }

    @FXML
    public void saveItemsSingleListButtonClicked(ActionEvent actionEvent) {
        // save each item in the one list
        // research how to save in javafx
    }

    @FXML
    public void saveItemsAllListButtonClicked(ActionEvent actionEvent) {
        // save each item in the list
        // go to the next list and repeat process until every list is saved
    }

    @FXML
    public void loadSingleListButtonClicked(ActionEvent actionEvent) {
        // ask user for name of list they want
        // go one by one through all the todolist until a title matches the user's input
    }

    @FXML
    public void loadMultipleListsButtonClicked(ActionEvent actionEvent) {
        // ask user for name of list they want
        // go one by one through all the todolist until a title matches the user's input
        // repeat process until every list user has inputted has been found
    }

    public void addListLen() {
        // number of todolist should increase by one
    }

    public void subtractListLen() {
        // number of todolist should decrease by one
    }

    public void newTitleCheck() {
        // actual should equal the user's input for the title
    }

    public void addItemLen() {
        // number of todolistitem should increase by one
    }

    public void subtractItemLen() {
        // number of todolistitem should decrease by one
    }

    public void newDescriptionCheck() {
        // actual should equal the user's input for the description
    }

    public void newDueDateCheck() {
        // actual should equal the user's input for the due date
    }

    public void isCompleteCheck() {
        // check if the boolean complete in item is true
    }

    public void isIncompleteCheck(){
        // check if the boolean complete in item is false
    }

    public void didItemInOneListSave(){
        // return 1 if every list item successfully saved
    }

    public void didItemInEveryListSave(){
        // return 1 if every list item successfully saved
        // go to the next list and repeat process
        // end entire process by returning 0 if item didnt save
    }

    public void loadTitle(){
        // check if the user's input equals the actual
    }

    public void loadMultipleTitles(){
        // check if every title in the user's input equals the actual
    }

 */
}
