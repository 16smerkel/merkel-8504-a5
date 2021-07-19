package ucf.assignments;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

public class MainWindowController {
    @FXML
    private TableView<?> itemsTableView;

    @FXML
    private TableColumn<?, ?> itemsSerialNumberColumn;

    @FXML
    private TableColumn<?, ?> itemsNameColumn;

    @FXML
    private TableColumn<?, ?> itemsValueColumn;

    @FXML
    private TextField itemSerialNumberTextField;

    @FXML
    private TextField itemNameText;

    @FXML
    private TextField itemPriceTextField;


    @FXML
    void addNewItemButtonClicked(ActionEvent event) {
        String sn = itemSerialNumberTextField.getText();
        String name = itemNameText.getText();
        double value = Double.parseDouble(itemPriceTextField.getText());

        Item item = addNewItem(sn, name, value);

        ItemModel.add(item);
    }

    public void addNewItem(String sn, String name, double value) {
        return new Item(sn, name, value);
    }

    void saveAsButtonClicked(ActionEvent event){
        filename = FileChooser.getName();
        filetype = FileChooser.getType();

        if(filetype == 'CSV'){
            saveAsCsv(flename + filetype);
        }
    }

    public void saveAsCSV(String filename){
        /*
        open up filename
        for each item in the item model
            write the item to file as sn, name, price
        close file
         */
    }
}
