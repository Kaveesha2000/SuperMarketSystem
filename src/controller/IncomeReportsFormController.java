package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class IncomeReportsFormController {
    public ComboBox cmbReport;
    public AnchorPane context;

    public void initialize() {
        cmbReport.getItems().addAll("Daily Income","Monthly Income" ,"Annually Income");

        cmbReport.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            try{

                if(newValue.equals("Daily Income")){
                    URL resource = getClass().getResource("../view/DailyIncomeForm.fxml");
                    Parent load = null;
                    try {
                        load = FXMLLoader.load(resource);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    context.getChildren().clear();
                    context.getChildren().add(load);

                }
                else if(newValue.equals("Monthly Income")){
                    URL resource = getClass().getResource("../view/MonthlyIncomeForm.fxml");
                    Parent load = null;
                    try {
                        load = FXMLLoader.load(resource);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    context.getChildren().clear();
                    context.getChildren().add(load);
                }

            }catch (NullPointerException n){

            }

        });
    }
}
