package controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

public class CashierDashBoardFormController {
    public Label lableTime;
    public Label lableDate;
    public AnchorPane cashierContext;

    public void initialize(){
        loadDateAndTime();
    }

    private void loadDateAndTime() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        lableDate.setText(f.format(date));

        // load Time
        Timeline time = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();

            String state = null;
            if (currentTime.getHour() >= 12) {
                state = "PM";
            } else {
                state = "AM";
            }
            lableTime.setText("" + String.format("%02d",currentTime.getHour()) + ":" + String.format("%02d", currentTime.getMinute()) + ":" + String.format("%02d", currentTime.getSecond())+" "+state);

        }),
                new KeyFrame(Duration.seconds(1))
        );
        time.setCycleCount(Animation.INDEFINITE);
        time.play();

    }

    public void goToManageCustomerForm(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/ManageCustomerForm.fxml");
        Parent load = FXMLLoader.load(resource);
        cashierContext.getChildren().clear();
        cashierContext.getChildren().add(load);
    }

    public void goToManageOrdersForm(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/ManageOrdersForm.fxml");
        Parent load = FXMLLoader.load(resource);
        cashierContext.getChildren().clear();
        cashierContext.getChildren().add(load);
    }

    public void logOut(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/LogInForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) cashierContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void back(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/CashierDashBoardForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) cashierContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }
}
