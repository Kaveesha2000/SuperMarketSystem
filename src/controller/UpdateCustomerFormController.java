package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import model.Customer;

import java.sql.SQLException;

public class UpdateCustomerFormController {
    public JFXTextField txtTitle;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtCity;
    public JFXTextField txtProvince;
    public JFXTextField txtPostalCode;
    public JFXTextField txtId;

    public void search(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String customerId = txtId.getText();

        Customer c1= new CustomerController().getCustomer(customerId);
        if (c1==null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        } else {
            setData(c1);
        }
    }

    private void setData(Customer c1) {
        txtId.setText(c1.getId());
        txtTitle.setText(c1.getTitle());
        txtName.setText(c1.getName());
        txtAddress.setText(c1.getAddress());
        txtCity.setText(c1.getCity());
        txtProvince.setText(c1.getProvince());
        txtPostalCode.setText(c1.getPostalCode());
    }

    public void updateCustomer(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Customer c1 = new Customer(
                txtId.getText(),txtTitle.getText(),txtName.getText(),txtAddress.getText(),
                txtCity.getText(),txtProvince.getText(),txtPostalCode.getText()
        );
        if(new CustomerController().updateCustomer(c1)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
            txtId.clear();txtTitle.clear();txtName.clear();txtAddress.clear();txtCity.clear();txtProvince.clear();txtPostalCode.clear();

        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
        }

    }
}
