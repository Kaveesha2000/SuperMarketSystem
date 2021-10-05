package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class ManageCustomerFormController {
    public AnchorPane customerContext;

    public void goToAddCustomerForm(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/AddCustomerForm.fxml");
        Parent load = FXMLLoader.load(resource);
        customerContext.getChildren().clear();
        customerContext.getChildren().add(load);
    }

    public void goToViewCustomerForm(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/ViewCustomerForm.fxml");
        Parent load = FXMLLoader.load(resource);
        customerContext.getChildren().clear();
        customerContext.getChildren().add(load);
    }

    public void goToUpdateCustomerForm(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/UpdateCustomerForm.fxml");
        Parent load = FXMLLoader.load(resource);
        customerContext.getChildren().clear();
        customerContext.getChildren().add(load);
    }

    public void goToDeleteCustomerForm(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/DeleteCustomerForm.fxml");
        Parent load = FXMLLoader.load(resource);
        customerContext.getChildren().clear();
        customerContext.getChildren().add(load);
    }
}
