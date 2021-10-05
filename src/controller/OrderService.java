package controller;

import javafx.collections.ObservableList;
import model.Item;
import model.Order;
import model.OrderDetails;

import java.sql.SQLException;

public interface OrderService {
    public boolean deleteOrder(String o) throws SQLException, ClassNotFoundException;
    public boolean updateOrder(OrderDetails o) throws SQLException, ClassNotFoundException;
    public ObservableList<OrderDetails> getOrderItems(String id) throws SQLException, ClassNotFoundException;
    public Order getOrder(String id) throws SQLException, ClassNotFoundException;
}
