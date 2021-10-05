package controller;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Order;

import java.sql.SQLException;

public class ViewOrdersFormController {
    public TableView<Order> tblOrderDetails;
    public TableColumn colOrderId;
    public TableColumn colDate;
    public TableColumn colTime;
    public TableColumn colCustId;
    public TableColumn colCost;

    public void initialize() throws SQLException, ClassNotFoundException {
        tblOrderDetails.setItems(new OrderController().getAllOrders());
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colCustId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colCost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
    }
}
