package controller;

import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.ItemDetails;
import model.Order;
import model.OrderDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderController implements OrderService{

    public String getOrderId() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance()
                .getConnection().prepareStatement(
                        "SELECT OrderId FROM Orders ORDER BY OrderId DESC LIMIT 1"
                ).executeQuery();
        if (rst.next()){

            int tempId = Integer.
                    parseInt(rst.getString(1).split("-")[1]);
            tempId=tempId+1;
            if (tempId<=9){
                return "O-00"+tempId;
            }else if(tempId<99){
                return "O-0"+tempId;
            }else{
                return "O-"+tempId;
            }

        }else{
            return "O-001";
        }
    }

    public boolean placeOrder(Order order) {
        Connection con=null;
        try {
            con= DbConnection.getInstance().getConnection();
            con.setAutoCommit(false);
            PreparedStatement stm =con.
                    prepareStatement("INSERT INTO Orders VALUES(?,?,?,?,?)");

            stm.setObject(1, order.getOrderId());
            stm.setObject(2, order.getDate());
            stm.setObject(3, order.getTime());
            stm.setObject(4, order.getCustomerId());
            stm.setObject(5, order.getCost());

            //System.out.println("this is item check " + order.getItems());

            if (stm.executeUpdate() > 0){
                if (saveOrderDetail(order.getOrderId(), order.getItems())){
                    con.commit();
                    return true;
                }else{
                    con.rollback();
                    return false;
                }
            }else{
                con.rollback();
                return false;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {

                con.setAutoCommit(true);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return false;
    }

    private boolean saveOrderDetail(String orderId, ArrayList<ItemDetails> items) throws SQLException, ClassNotFoundException {

        //System.out.println(items);

        for (ItemDetails temp : items) {
            PreparedStatement stm = DbConnection.getInstance().getConnection().
                    prepareStatement("INSERT INTO OrderDetail VALUES(?,?,?,?)");
            stm.setObject(1, orderId);
            stm.setObject(2, temp.getItemId());
            stm.setObject(3, temp.getQuantity());
            stm.setObject(4, temp.getDiscount());

            if (stm.executeUpdate() > 0) {

                if (updateQty("I001", 12)){

                }else{
                    return false;
                }

            } else {
                return false;
            }
       }
        return true;
    }
    private boolean updateQty(String itemId, Integer quantity) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection()
                .prepareStatement
                        ("UPDATE Item SET QtyOnHand=(QtyOnHand-" + quantity
                                + ") WHERE ItemCode='" + itemId + "'");
        return stm.executeUpdate()>0;
    }

    @Override
    public boolean deleteOrder(String o) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Orders WHERE orderId='"+o+"'").executeUpdate()>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean updateOrder(OrderDetails o) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().
                prepareStatement("DELETE FROM orderdetail WHERE OrderId=? && ItemCode=?");

        stm.setObject(1, o.getOrderId());
        stm.setObject(2, o.getItemCode());

        if (stm.executeUpdate()>0){
            PreparedStatement stm1 = DbConnection.getInstance().getConnection().
                    prepareStatement("INSERT INTO orderdetail VALUES (?,?,?,?)");

            stm1.setObject(1,o.getOrderId());
            stm1.setObject(2,o.getItemCode());
            stm1.setObject(3,o.getQuantity());
            stm1.setObject(4,o.getDiscount());

            return stm1.executeUpdate()>0;
        }
        return false;
    }

    public boolean addOrderItem(OrderDetails o) throws SQLException, ClassNotFoundException {
        PreparedStatement stm1 = DbConnection.getInstance().getConnection().
                prepareStatement("INSERT INTO orderdetail VALUES (?,?,?,?)");

        stm1.setObject(1,o.getOrderId());
        stm1.setObject(2,o.getItemCode());
        stm1.setObject(3,o.getQuantity());
        stm1.setObject(4,o.getDiscount());

        return stm1.executeUpdate()>0;
    }


    public ObservableList<OrderDetails> getOrderItems(String orderId) throws SQLException, ClassNotFoundException {

        ObservableList<OrderDetails> orderDetails = FXCollections.observableArrayList();

        PreparedStatement stm = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT * FROM orderdetail WHERE orderId=?");

        stm.setObject(1, orderId);
        ResultSet rst = stm.executeQuery();

        while (rst.next()){
            OrderDetails temp = new OrderDetails(rst.getString(1),rst.getString(2),rst.getInt(3),rst.getDouble(4));
            orderDetails.add(temp);
        }
        return orderDetails;
    }

    @Override
    public Order getOrder(String id) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT * FROM orders WHERE orderId=?");

        stm.setObject(1, id);
        ResultSet rst = stm.executeQuery();

        if (rst.next()) {
            Order order = new Order(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getDouble(5),new ArrayList<>());
            return order;
        }
        return null;
    }

    public ObservableList<Order> getAllOrders() throws SQLException, ClassNotFoundException {
        ObservableList<Order> orderObservableList = FXCollections.observableArrayList();
        PreparedStatement stm = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT * FROM orders");

        ResultSet rst = stm.executeQuery();

        while(rst.next()){
            Order order = new Order(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getDouble(5),new ArrayList<>());
            orderObservableList.add(order);
        }
        return orderObservableList;
    }
}
