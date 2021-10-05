package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Item;
import model.Order;
import model.OrderDetails;
import view.tm.OrderDetailTM;

import java.sql.SQLException;

public class UpdateOrderFormController extends OrderDetails {
    public TableColumn colItemId;
    public TableColumn colQuantity;
    public JFXTextField txtOrderId;
    public JFXTextField txtTime;
    public JFXTextField txtDate;
    public TableView<OrderDetails> tblOrderDetails;
    public JFXTextField txtQty;
    public JFXTextField txtQtyOnHand;
    public ComboBox<String> cmbItemId;
    public JFXTextField txtDiscount;


    ObservableList<OrderDetails> order;

    public void initialize() throws SQLException, ClassNotFoundException {

        cmbItemId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {

                if (newValue!=null){
                    Item item = new ItemController().getItem(newValue);
                    txtQtyOnHand.setText(String.valueOf(item.getQtyOnHand()));
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        tblOrderDetails.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            try {

                if (newValue!=null){
                    Item item = new ItemController().getItem(newValue.getItemCode());
                    txtQtyOnHand.setText(String.valueOf(item.getQtyOnHand()));
                    txtQty.setText(String.valueOf(newValue.getQuantity()));
                    cmbItemId.setValue(newValue.getItemCode());
                }


            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        cmbItemId.getItems().addAll(new ItemController().getAllItemIds());

        colItemId.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    }

    public void updateOrder(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        for (int i=0;i<order.size();i++){
            if (order.get(i).getItemCode().equals(cmbItemId.getValue())){
                OrderDetails orderDetails = new OrderDetails(order.get(i).getOrderId(),order.get(i).getItemCode(),
                        Integer.parseInt(txtQty.getText()),order.get(i).getDiscount());
                order.set(i,orderDetails);

                new OrderController().updateOrder(orderDetails);
            }
        }
        tblOrderDetails.setItems(order);
        txtQty.clear();txtQtyOnHand.clear();cmbItemId.setValue(null);
    }

    public void search(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        order = new OrderController().getOrderItems(txtOrderId.getText());
        tblOrderDetails.setItems(order);

        Order order1 = new OrderController().getOrder(txtOrderId.getText());
        txtDate.setText(order1.getDate());
        txtTime.setText(order1.getTime());
    }

    public void addItem(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        OrderDetails orderDetails = new OrderDetails(txtOrderId.getText(),cmbItemId.getValue(),Integer.parseInt(txtQty.getText()),
                Double.parseDouble(txtDiscount.getText()));
        order.add(orderDetails);
        tblOrderDetails.setItems(order);

        new OrderController().addOrderItem(orderDetails);
    }
}
