package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class SystemReportsFormController {
    public AnchorPane ReportContext;

    public void goToIncomeReports(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/IncomeReportsForm.fxml");
        Parent load = FXMLLoader.load(resource);
        ReportContext.getChildren().clear();
        ReportContext.getChildren().add(load);
    }

    public void goToMostMovableItem(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/MostMovableItemForm.fxml");
        Parent load = FXMLLoader.load(resource);
        ReportContext.getChildren().clear();
        ReportContext.getChildren().add(load);
    }

    public void goToCustomerWiseIncome(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/CustomerWiseIncome.fxml");
        Parent load = FXMLLoader.load(resource);
        ReportContext.getChildren().clear();
        ReportContext.getChildren().add(load);
    }
}
