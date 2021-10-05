package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import model.Order;
import model.OrderDetails;

import java.sql.SQLException;

public class DeleteOrderFormController {
    public JFXTextField txtOrderId;
    public JFXTextField txtOrderDate;
    public JFXTextField txtOrderTime;
    public JFXTextField txtCustId;

    public void search(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        String orderId = txtOrderId.getText();

        Order o1= new OrderController().getOrder(orderId);
        if (o1==null) {
            new Alert(Alert.AlertType.WARNING, "No Order Related To This Order Id").show();
        } else {
            setData(o1);
        }
    }

    private void setData(Order o1) {
        txtOrderDate.setText(o1.getDate());
        txtOrderTime.setText(o1.getTime());
        txtCustId.setText(o1.getCustomerId());
    }

    public void deleteOrder(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (new OrderController().deleteOrder(txtOrderId.getText())){
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
            txtOrderId.clear();txtOrderDate.clear();txtOrderTime.clear();txtCustId.clear();
        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }
}
