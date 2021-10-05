package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import model.Customer;
import model.Item;
import sun.font.AttributeValues;

import java.sql.SQLException;

public class UpdateItemFormController {
    public JFXTextField txtItemDescription;
    public JFXTextField txtPackSize;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQtyOnHand;
    public JFXTextField txtItemCode;

    public void search(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String itemId = txtItemCode.getText();

        Item i1= new ItemController().getItem(itemId);
        if (i1==null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        } else {
            setData(i1);
        }
    }

    private void setData(Item i1) {
        txtItemCode.setText(i1.getItemCode());
        txtItemDescription.setText(i1.getDescription());
        txtPackSize.setText(i1.getPackSize());
        txtUnitPrice.setText(String.valueOf(i1.getUnitPrice()));
        txtQtyOnHand.setText(String.valueOf(i1.getQtyOnHand()));
    }

    public void updateItem(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Item i1 = new Item(
                txtItemCode.getText(),txtItemDescription.getText(),txtPackSize.getText(),
                Double.parseDouble(txtUnitPrice.getText()),Integer.parseInt(txtQtyOnHand.getText())
        );
        if(new ItemController().updateItem(i1)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
            txtItemCode.clear();txtItemDescription.clear();txtPackSize.clear();txtUnitPrice.clear();txtQtyOnHand.clear();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
        }
    }
}
