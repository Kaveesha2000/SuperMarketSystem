package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class ManageOrdersFormController {
    public AnchorPane orderContext;

    public void goToAddOrderForm(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/AddOrderForm.fxml");
        Parent load = FXMLLoader.load(resource);
        orderContext.getChildren().clear();
        orderContext.getChildren().add(load);
    }

    public void goToViewOrderForm(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/ViewOrdersForm.fxml");
        Parent load = FXMLLoader.load(resource);
        orderContext.getChildren().clear();
        orderContext.getChildren().add(load);
    }

    public void goToUpdateOrderForm(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/UpdateOrderForm.fxml");
        Parent load = FXMLLoader.load(resource);
        orderContext.getChildren().clear();
        orderContext.getChildren().add(load);
    }

    public void goToDeleteOrderForm(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/DeleteOrderForm.fxml");
        Parent load = FXMLLoader.load(resource);
        orderContext.getChildren().clear();
        orderContext.getChildren().add(load);
    }

    public void goToPaymentFormForm(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/PaymentForm.fxml");
        Parent load = FXMLLoader.load(resource);
        orderContext.getChildren().clear();
        orderContext.getChildren().add(load);
    }
}
