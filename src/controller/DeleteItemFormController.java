package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import model.Item;

import java.sql.SQLException;

public class DeleteItemFormController {
    public JFXTextField txtItemCode;
    public JFXTextField txtDescription;
    public JFXTextField txtPackSize;
    public JFXTextField txtUnitPrize;

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
        txtDescription.setText(i1.getDescription());
        txtPackSize.setText(i1.getPackSize());
        txtUnitPrize.setText(String.valueOf(i1.getUnitPrice()));
    }

    public void deleteItem(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (new ItemController().deleteItem(txtItemCode.getText())){
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
            txtItemCode.clear();txtPackSize.clear();txtDescription.clear();txtUnitPrize.clear();
        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }
}
