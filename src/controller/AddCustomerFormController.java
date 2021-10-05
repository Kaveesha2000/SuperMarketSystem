package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import model.Customer;

import java.sql.SQLException;

public class AddCustomerFormController{
    public JFXTextField txtId;
    public JFXTextField txtTitle;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtCity;
    public JFXTextField txtProvince;
    public JFXTextField txtPostalCode;

    public void addCustomer(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Customer c1 = new Customer(
                txtId.getText(),txtTitle.getText(),txtName.getText(),txtAddress.getText(),
                txtCity.getText(),txtProvince.getText(),txtPostalCode.getText()
        );
        if(new CustomerController().saveCustomer(c1)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
            txtId.clear();txtTitle.clear();txtName.clear();txtAddress.clear();txtCity.clear();txtProvince.clear();txtPostalCode.clear();

        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
        }
    }
}
