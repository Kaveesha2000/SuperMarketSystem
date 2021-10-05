package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Item;
import view.tm.ItemTM;

import java.sql.SQLException;
import java.util.ArrayList;

public class ViewItemsFormController {
    public TableView<ItemTM> tblItem;
    public TableColumn colItemCode;
    public TableColumn colDescription;
    public TableColumn colPackSize;
    public TableColumn colQtyOnHand;
    public TableColumn colUnitPrice;

    public void initialize() {
        try {

            colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
            colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
            colPackSize.setCellValueFactory(new PropertyValueFactory<>("packSize"));
            colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
            colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));

            setItemsToTable(new ItemController().getAllItems());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void setItemsToTable(ArrayList<Item> allItems) {
        ObservableList<ItemTM> obList = FXCollections.observableArrayList();
        allItems.forEach(e->{
            obList.add(
                    new ItemTM(e.getItemCode(),e.getDescription(),e.getPackSize(),e.getUnitPrice(),e.getQtyOnHand()));
        });
        tblItem.setItems(obList);
    }
}
