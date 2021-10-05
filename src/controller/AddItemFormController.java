package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import model.Item;

import java.sql.SQLException;

public class AddItemFormController {
    public JFXTextField txtItemId;
    public JFXTextField txtItemDescrption;
    public JFXTextField txtPackSize;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQtyOnHand;

    public void addItem(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Item i1 = new Item(
                txtItemId.getText(),txtItemDescrption.getText(),txtPackSize.getText(),
                Double.parseDouble(txtUnitPrice.getText()),Integer.parseInt(txtQtyOnHand.getText())
        );

        if(new ItemController().saveItem(i1)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
            txtItemId.clear();txtItemDescrption.clear();txtPackSize.clear();txtUnitPrice.clear();txtQtyOnHand.clear();

        }else {
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
        }
    }

}
