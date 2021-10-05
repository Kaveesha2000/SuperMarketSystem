package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class ManageItemsFormController {
    public AnchorPane itemContext;

    public void goToAddItemForm(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/AddItemForm.fxml");
        Parent load = FXMLLoader.load(resource);
        itemContext.getChildren().clear();
        itemContext.getChildren().add(load);
    }

    public void goToUpdateItemForm(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/UpdateItemForm.fxml");
        Parent load = FXMLLoader.load(resource);
        itemContext.getChildren().clear();
        itemContext.getChildren().add(load);
    }

    public void goToDeleteItemForm(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/DeleteItemForm.fxml");
        Parent load = FXMLLoader.load(resource);
        itemContext.getChildren().clear();
        itemContext.getChildren().add(load);
    }

    public void goToViewItemsForm(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/ViewItemsForm.fxml");
        Parent load = FXMLLoader.load(resource);
        itemContext.getChildren().clear();
        itemContext.getChildren().add(load);
    }
}
