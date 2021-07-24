/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Sean Merkel
 */
package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.Initializable;

import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.*;

public class InventoryManagerController implements Initializable {
    @FXML
    Button addItemButton;

    @FXML
    TableView<InventoryItem> table;

    @FXML
    TextField price;

    @FXML
    TextField serial;

    @FXML
    TextField name;

    @FXML
    TextField searchSerial;

    @FXML
    TextField searchName;

    @FXML
    TableColumn<InventoryItem, String> priceColumn;

    @FXML
    TableColumn<InventoryItem, String> serialColumn;

    @FXML
    TableColumn<InventoryItem, String> nameColumn;

    @FXML
    FileChooser fileChooser = new FileChooser();

    @FXML
    private TextArea theDisplayTextArea;

    @FXML
    VBox vbMenu;

    static ObservableList<InventoryItem> list = FXCollections.observableArrayList();

    @FXML
    public void addItem(Event e){
        // gets information from the three textfields and creates new item
        // adds item to the list
        this.theDisplayTextArea.setText("");
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
            errorMessage += ("SERIAL NUMBER ALREADY IN LIST! \nTRY ANOTHER SERIAL NUMBER!!!\n");
        }
        else if(serialFormatCheck == 2) {
            errorMessage += ("SERIAL NUMBER IS NOT IN XXXXXXXXXX FORMAT! \nTRY A DIFFERENT FORMAT!!!\n");
        }
        else if(nameLengthCheck == 2) {
            errorMessage += ("NAME LENGTH IS NOT BETWEEN 2 AND 256 CHARACTERS! \nTRY ANOTHER NAME!!!\n");
        }
        if(noSerialFound == 2 || serialFormatCheck == 2 || nameLengthCheck == 2) {
            this.theDisplayTextArea.setText(errorMessage);
        }
        else
        {
            InventoryItem theItem = new InventoryItem(fixedPrice, serial.getText(), name.getText());
            list.add(theItem);
            this.theDisplayTextArea.setText("");
        }
        refresh();
    }

    @FXML
    public void deleteItem(Event e){
        // removes the selected item from list
        this.theDisplayTextArea.setText("");
        ObservableList<InventoryItem> InventoryItemSelected, allInventoryItems;
        allInventoryItems = table.getItems();
        InventoryItemSelected = table.getSelectionModel().getSelectedItems();
        for(InventoryItem p : InventoryItemSelected)
        {
            allInventoryItems.remove(p);
        }
    }

    @FXML
    public void editItem(Event E){
        // gets information from the three textfields (whichever ones were filled out) and edits chosen item
        this.theDisplayTextArea.setText("");
        ObservableList<InventoryItem> allInventoryItems;
        allInventoryItems = table.getItems();
        InventoryItem Selected = table.getSelectionModel().getSelectedItem();
        InventoryItem theItem = new InventoryItem();
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
            errorMessage += ("SERIAL NUMBER ALREADY IN LIST! \nTRY ANOTHER SERIAL NUMBER!!!\n");
        }
        else if(serialFormatCheck == 2) {
            errorMessage += ("SERIAL NUMBER IS NOT IN XXXXXXXXXX FORMAT! \nTRY A DIFFERENT FORMAT!!!\n");
        }
        else if(nameLengthCheck == 2 && !(name.getText().equals(""))) {
            errorMessage += ("NAME LENGTH IS NOT BETWEEN 2 AND 256 CHARACTERS! \nTRY ANOTHER NAME!!!\n");
        }
        if(noSerialFound == 2 || serialFormatCheck == 2 || nameLengthCheck == 2) {
            this.theDisplayTextArea.setText(errorMessage);
        }
        if(noSerialFound == 2)
        {
            this.theDisplayTextArea.setText("SERIAL NUMBER ALREADY IN LIST! \nTRY ANOTHER SERIAL NUMBER!!!\n");
        }
        else
        {
            if (price.getText().equals("")) {
                theItem.setThePrice(Selected.thePrice.get());
            }
            else {
                theItem.setThePrice(fixedPrice);
            }
            if (serial.getText().equals("")) {
                theItem.setTheSerial(Selected.theSerial.get());
            }
            else {
                theItem.setTheSerial(serial.getText());
            }
            if (name.getText().equals("")) {
                theItem.setTheName(Selected.theName.get());
            }
            else {
                theItem.setTheName(name.getText());
            }
            allInventoryItems.remove(Selected);
            list.add(theItem);
        }
        refresh();
    }

    @FXML
    public void editItemPrice(Event E){
        // gets information from the price textfield and edits chosen item
        this.theDisplayTextArea.setText("");
        ObservableList<InventoryItem> allInventoryItems;
        allInventoryItems = table.getItems();
        InventoryItem Selected = table.getSelectionModel().getSelectedItem();
        InventoryItem theItem = new InventoryItem();
        String fixedPrice = price.getText();
        fixedPrice = makeDollar(fixedPrice);
        theItem.setThePrice(fixedPrice);
        theItem.setTheSerial(Selected.theSerial.get());
        theItem.setTheName(Selected.theName.get());
        allInventoryItems.remove(Selected);
        list.add(theItem);
        refresh();
    }

    @FXML
    public void editItemSerial(Event E){
        // gets information from the serial textfield and edits chosen item
        this.theDisplayTextArea.setText("");
        ObservableList<InventoryItem> allInventoryItems;
        allInventoryItems = table.getItems();
        InventoryItem Selected = table.getSelectionModel().getSelectedItem();
        InventoryItem theItem = new InventoryItem();
        int noSerialFound = 0;
        noSerialFound = searchForSerial(list, serial.getText());
        int serialFormatCheck = 0;
        serialFormatCheck = checkSerialFormat(serial.getText());
        String errorMessage = "";
        if(serialFormatCheck == 2) {
            errorMessage += ("SERIAL NUMBER IS NOT IN XXXXXXXXXX FORMAT! \nTRY A DIFFERENT FORMAT!!!\n");
        }
        else if(noSerialFound == 2) {
            errorMessage += ("SERIAL NUMBER ALREADY IN LIST! \nTRY ANOTHER SERIAL NUMBER!!!\n");
        }
        if(noSerialFound == 2 || serialFormatCheck == 2) {
            this.theDisplayTextArea.setText(errorMessage);
        }
        else
        {
            theItem.setTheSerial(serial.getText());
            theItem.setThePrice(Selected.thePrice.get());
            theItem.setTheName(Selected.theName.get());
            allInventoryItems.remove(Selected);
            list.add(theItem);
        }
        refresh();
    }

    @FXML
    public void editItemName(Event E){
        // gets information from the name textfield and edits chosen item
        this.theDisplayTextArea.setText("");
        ObservableList<InventoryItem> allInventoryItems;
        allInventoryItems = table.getItems();
        InventoryItem Selected = table.getSelectionModel().getSelectedItem();
        InventoryItem theItem = new InventoryItem();
        int nameLengthCheck = 0;
        nameLengthCheck = checkNameLength(name.getText());
        String errorMessage = "";
        if(nameLengthCheck == 2) {
            errorMessage += ("NAME LENGTH IS NOT BETWEEN 2 AND 256 CHARACTERS! \nTRY ANOTHER NAME!!!\n");
            this.theDisplayTextArea.setText(errorMessage);
        }
        else
        {
            theItem.setThePrice(Selected.thePrice.get());
            theItem.setTheSerial(Selected.theSerial.get());
            theItem.setTheName(name.getText());
            allInventoryItems.remove(Selected);
            list.add(theItem);
        }
        refresh();
    }

    @FXML
    public void sortPrice(Event e)
    // sorts inventory from most expensive item to least expensive item
    // removes $ and converts double to make the comparison process more precise
    {
        int i,j;
        for(i = 0; i < list.size() - 1; i++)
        {
            for(j = i + 1; j < list.size(); j++)
            {
                String jItem = list.get(j).getThePrice();
                String iItem = list.get(i).getThePrice();
                jItem = jItem.replace("$","");
                iItem = iItem.replace("$","");
                double jNum = Double.parseDouble(jItem);
                double iNum = Double.parseDouble(iItem);
                if (jNum > iNum) {
                    InventoryItem temp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, temp);
                }
            }
        }
    }

    @FXML
    public void sortSerial(Event e)
    // sorts inventory by serial number alphabetically with the items with serial numbers starting with A being on top while the items with serial numbers with Z being on bottom
    {
        int i,j;
        for(i = 0; i < list.size() - 1; i++)
        {
            for(j = i + 1; j < list.size(); j++)
            {
                String jItem = list.get(j).getTheSerial();
                String iItem = list.get(i).getTheSerial();
                if (jItem.compareTo(iItem) < 0) {
                    InventoryItem temp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, temp);
                }
            }
        }
    }

    @FXML
    public void sortName(Event e)
    // sorts inventory by name alphabetically with the items with name starting with A being on top while the items with name with Z being on bottom
    {
        int i,j;
        for(i = 0; i < list.size() - 1; i++)
        {
            for(j = i + 1; j < list.size(); j++)
            {
                String jItem = list.get(j).getTheName();
                String iItem = list.get(i).getTheName();
                if (jItem.compareTo(iItem) < 0) {
                    InventoryItem temp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, temp);
                }
            }
        }
    }

    @FXML
    public void searchSerial(Event e)
    // goes through list to find item with desired serial number
    // if serial number is in none of the items in the inventory it displays an error message
    {
        String curSearch = searchSerial.getText();
        this.theDisplayTextArea.setText("");
        int theSerialCount = 0;
        ObservableList<InventoryItem> allInventoryItems = table.getItems();
        InventoryItem found = new InventoryItem();
        for(InventoryItem p : allInventoryItems)
        {
           if(p.getTheSerial().equals(curSearch))
           {
               theSerialCount++;
               found = p;
           }
        }
        if(theSerialCount == 0)
        {
            theDisplayTextArea.setText("THE SERIAL NUMBER " + curSearch + " WAS NOT FOUND IN YOUR INVENTORY! PLEASE TRY A DIFFERENT SERIAL NUMBER!!!");
        }
        else {
            theDisplayTextArea.setText("the serial number " + curSearch + " was found in your inventory\n" + found.toString() );
        }
    }

    @FXML
    public void searchName(Event e)
    // goes through list to find item with desired name
    // if name is in none of the items in the inventory it displays an error message
    {
        String curSearch = searchName.getText();
        this.theDisplayTextArea.setText("");
        int theNameCount = 0;
        ObservableList<InventoryItem> allInventoryItems = table.getItems();
        InventoryItem found = new InventoryItem();
        for(InventoryItem p : allInventoryItems)
        {
            if(p.getTheName().equals(curSearch))
            {
                theNameCount++;
                found = p;
            }
        }
        if(theNameCount == 0)
        {
            theDisplayTextArea.setText("THE NAME " + curSearch + " WAS NOT FOUND IN YOUR INVENTORY! PLEASE TRY A DIFFERENT NAME!!!");
        }
        else {
            theDisplayTextArea.setText("the name " + curSearch + " was found in your inventory\n" + found.toString() );
        }
    }

    @FXML
    public void saveInventoryTSV(Event event){
    // opens up a window to be able save list into a text file
    // text file is then converted from a list full of inventory items
    // toTab() is used to make the items in the list into Strings that are properly spaced out
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
        for(int i = 0; i < table.getItems().size(); i++){
            outFile.println(table.getItems().get(i).toTab());
        }
        outFile.close();
    }

    @FXML
    public void saveInventoryHTML(Event event){
    // opens up a window to be able save list into a text file
    // HTML file is then converted from a list full of inventory items
    // <table border> is key to making html file a table format
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Dialog");
        fileChooser.setInitialFileName("mysave");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("html file", "*.html"));
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
        ObservableList<InventoryItem> allInventoryItems;
        allInventoryItems = table.getItems();
        assert outFile != null;
        outFile.write(" <tr bgcolor=\"yellow\">" + "<td>");
        outFile.write("<html>" +
                "<body>" +
                "<table border ='1'>" +
                "<tr>" +
                "<td>Price</td>" +
                "<td>Serial Number</td>" +
                "<td>Name</td>" +
                "</tr>");
        for(InventoryItem p : allInventoryItems)
        {
            outFile.write("<html>" +
                    "<body>" +
                    "<table border ='1'>" +
                    "<tr>" +
                    "</tr>");
            outFile.write(" <tr bgcolor=\"yellow\">" + "<td>");
            outFile.write( "<td>");
            outFile.write(p.getThePrice());
            outFile.write("</td><td>");
            outFile.write(p.getTheSerial() + "\t");
            outFile.write("</td><td>");
            outFile.write(p.getTheName() + "\t");
            outFile.write("</table>" +
                    "</body>" +
                    "</html>");
        }
        outFile.close();
    }

    @FXML
    public void saveInventoryJSON(Event event){
    // opens up a window to be able save list into a text file
    // JSON file is then converted from a list full of inventory items
    // toTab() is used to make the items in the list into Strings that are properly spaced out
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Dialog");
        fileChooser.setInitialFileName("mysave");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JSON file", "*.json"));
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
        for(int i = 0; i < table.getItems().size(); i++){
            outFile.println(table.getItems().get(i).toTab());
        }
        outFile.close();
    }

    public void loadInventory(Event e){
        // opens up a window to be able load a text file
        // text file is then converted into a list full of inventory items
        // extractPrice, extractSerial, and extractName are key to creating each item in the observable list that is being loaded
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
                    String curLine = myReader.nextLine();
                    String thePrice = extractPrice(curLine);
                    String theSerial = extractSerial(curLine);
                    String theName = extractName(curLine);
                    InventoryItem theItem = new InventoryItem(thePrice, theSerial, theName);
                    list.add(theItem);
                    table.setItems(list);
                    }
                } catch (Exception exception) {
                exception.printStackTrace();
            }
        } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

    public String extractPrice(String curLine){
        // searches through the current line to take out the specific location of the price
        String thePrice = "";
        int decimalCount = 0;
        for (char c : curLine.toCharArray()) {
            if (decimalCount > 0) {
                decimalCount++;
            }
            if (c == '.') {
                decimalCount = 1;
            }
            if(decimalCount == 4)
            {
                break;
            }
            thePrice += c;
        }
        return thePrice;
    }

    public String extractSerial(String curLine){
        // searches through the current line to take out the specific location of the serial number
        String theSerial = "";
        int decimalCount = 0;
        for (char c : curLine.toCharArray()) {
            if (decimalCount > 0) {
                decimalCount++;
            }
            if (c == '.') {
                decimalCount = 1;
            }
            if(decimalCount > 4)
            {
                theSerial += c;
            }
            if(decimalCount == 14)
            {
                break;
            }
        }
        return theSerial;
    }

    public String extractName(String curLine){
        // searches through the current line to take out the specific location of the name
        String theName = "";
        int decimalCount = 0;
        for (char c : curLine.toCharArray()) {
            if(c == '\n')
            {
                break;
            }
            if (decimalCount > 0) {
                decimalCount++;
            }
            if (c == '.') {
                decimalCount = 1;
            }
            if(decimalCount > 14)
            {
                theName += c;
            }
        }
        return theName;
    }

    public int searchForSerial(ObservableList<InventoryItem> list, String theSerial){
        // goes through list to make sure an item is not in the list with the same serial number
        for( Object item : list)
        {
            int intIndex = item.toString().indexOf(theSerial);
            if(intIndex != - 1)
            {
                return 2;
            }
        }
            return 1;
    }

    public int checkSerialFormat(String theSerial){
        // checks to see if serial number is in XXXXXXXXXX format
        if(theSerial.length() == 10)
        {
            return 1;
        }
        else
        {
            return 2;
        }
    }

    public int checkNameLength(String theName){
        // checks to see if length of name is between 2 and 256
        if(theName.length() >= 2 && theName.length() <= 256)
        {
            return 1;
        }
        else
        {
            return 2;
        }
    }

    public String makeDollar(String fixedPrice)
            // edits price input to have a dollar sign and at least zero cents if no decimal is entered
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

    public void refresh()
    {
        // sets all the variables to a beginning form
        price.setText("");
        serial.setText("");
        name.setText("");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // create table to view list of inventory
        priceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty());
        serialColumn.setCellValueFactory(cellData -> cellData.getValue().serialProperty());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        table.setItems(getProduct());
    }

    private ObservableList<InventoryItem> getProduct() {
        ObservableList<InventoryItem> thisList = InventoryManagerController.list;
        return thisList;
    }

    public String addItemTest(String theList, String priceInput, String serialInput, String nameInput){
        // gets information from textfield, datepicker, and checkbox and creates new item
        // adds item to the list
        InventoryItem theItem = new InventoryItem();
        theItem.setThePrice(priceInput);
        theItem.setTheSerial(serialInput);
        theItem.setTheName(nameInput);
        theList += theItem.toString();
        return theList;
    }

    public String deleteItemTest(String theList, String priceInput, String serialInput, String nameInput){
        // removes the selected item from list
        InventoryItem theItem = new InventoryItem();
        theItem.setThePrice(priceInput);
        theItem.setTheSerial(serialInput);
        theItem.setTheName(nameInput);
        theList = theList.replace(theItem.toString(), "");
        return theList;
    }

    public String editItemTest(String theList, String priceInput, String serialInput, String nameInput, String newPriceInput, String newSerialInput, String newNameInput){
        // searches for the item in the list and then edits it to the user's liking
        // first item input information is what is being edited and second item input information is the new edit
        InventoryItem theItem = new InventoryItem();
        theItem.setThePrice(priceInput);
        theItem.setTheSerial(serialInput);
        theItem.setTheName(nameInput);
        InventoryItem newItem = new InventoryItem();
        newItem.setThePrice(newPriceInput);
        newItem.setTheSerial(newSerialInput);
        newItem.setTheName(newNameInput);
        theList = theList.replace(theItem.toString(), newItem.toString());
        return theList;
    }

    public String editItemPriceTest(String theList, String priceInput, String serialInput, String nameInput, String newPriceInput){
        // searches for the item in the list and then edits it to the user's liking
        // first item input information is what is being edited and second item input information is the new edit
        InventoryItem theItem = new InventoryItem();
        theItem.setThePrice(priceInput);
        theItem.setTheSerial(serialInput);
        theItem.setTheName(nameInput);
        InventoryItem newItem = new InventoryItem();
        newItem.setThePrice(newPriceInput);
        newItem.setTheSerial(serialInput);
        newItem.setTheName(nameInput);
        theList = theList.replace(theItem.toString(), newItem.toString());
        return theList;
    }

    public String editItemSerialTest(String theList, String priceInput, String serialInput, String nameInput, String newSerialInput){
        // searches for the item in the list and then edits it to the user's liking
        // first item input information is what is being edited and second item input information is the new edit
        InventoryItem theItem = new InventoryItem();
        theItem.setThePrice(priceInput);
        theItem.setTheSerial(serialInput);
        theItem.setTheName(nameInput);
        InventoryItem newItem = new InventoryItem();
        newItem.setThePrice(priceInput);
        newItem.setTheSerial(newSerialInput);
        newItem.setTheName(nameInput);
        theList = theList.replace(theItem.toString(), newItem.toString());
        return theList;
    }

    public String editItemNameTest(String theList, String priceInput, String serialInput, String nameInput, String newNameInput){
        // searches for the item in the list and then edits it to the user's liking
        // first item input information is what is being edited and second item input information is the new edit
        InventoryItem theItem = new InventoryItem();
        theItem.setThePrice(priceInput);
        theItem.setTheSerial(serialInput);
        theItem.setTheName(nameInput);
        InventoryItem newItem = new InventoryItem();
        newItem.setThePrice(priceInput);
        newItem.setTheSerial(serialInput);
        newItem.setTheName(newNameInput);
        theList = theList.replace(theItem.toString(), newItem.toString());
        return theList;
    }

    public int saveAsTSV(String file)
    // checks to make sure correct TSV file was selected to be saved successfully
    {
        if(file.equals("TSV"))
        {
            return 1;
        }
        return 2;
    }

    public int saveAsHTML(String file)
    // checks to make sure correct HTML file was selected to be saved successfully
    {
        if(file.equals("HTML"))
        {
            return 1;
        }
        return 2;
    }

    public int saveAsJSON(String file)
    // checks to make sure correct JSON file was selected to be saved successfully
    {
        if(file.equals("JSON"))
        {
            return 1;
        }
        return 2;
    }

    public int loadSuccess(String file)
    // checks to make sure correct file was processed and loaded successfully
    {
        if(file.equals("TSV") || file.equals("HTML") || file.equals("JSON"))
        {
            return 1;
        }
        return 2;
    }

}
