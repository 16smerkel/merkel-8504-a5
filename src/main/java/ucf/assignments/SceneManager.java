package ucf.assignments;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SceneManager {
    Map<String, Scene> scenes = new HashMap<>();

    void load(){
        ListModel listModel = new ListModel();

        ListManagerController listManagerController = new ListManagerController(listModel, this);
        ListManagerController addItemController = new AddItemController(listModel, this);

        Parent root;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListManager.fxml"));
        loader.setController(listManagerController);

        try{
            root = loader.load();
            scenes.put("ListManager", new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }

        loader = new FXMLLoader(getClass().getResource("AddItem.fxml"));
        loader.setController(addItemController);

        try{
            root = loader.load();
            scenes.put("AddItem", new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Scene getScene(String name){
        return scenes.get(name);
    }
}
