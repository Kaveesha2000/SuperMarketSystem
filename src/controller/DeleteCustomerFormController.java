package controller;

import com.jfoenix.controls.JFXTextField;
import db.DbConnection;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import model.Customer;

import java.sql.SQLException;

public class DeleteCustomerFormController {
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

    public void deleteCustomer(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (new CustomerController().deleteCustomer(txtId.getText())){
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
            txtId.clear();txtTitle.clear();txtName.clear();txtAddress.clear();txtCity.clear();txtProvince.clear();txtPostalCode.clear();

        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }
}
