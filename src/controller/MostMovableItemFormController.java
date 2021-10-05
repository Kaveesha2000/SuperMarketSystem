package controller;

import com.jfoenix.controls.JFXTextField;
import db.DbConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MostMovableItemFormController {
    public JFXTextField txtItemCode;
    public JFXTextField txtQuantity;
    public JFXTextField txtDiscount;

    public String ItemCode;
    public String ItemQuantity;
    public double ItemDiscount;

    public JFXTextField txtQuantity1;
    public JFXTextField txtDiscount1;
    public JFXTextField txtItemCode1;

    public void initialize() throws SQLException, ClassNotFoundException {
        setData();
        setData2();
    }

    private void setData() throws SQLException, ClassNotFoundException {
        PreparedStatement stm  = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM orderdetail ORDER BY OrderQty ASC");
        ResultSet rst = stm.executeQuery();

        while (rst.next()){
            ItemCode=(rst.getString(2));
            ItemQuantity=(rst.getString(3));
            ItemDiscount=(rst.getDouble(4));
        }
        txtItemCode.setText(ItemCode);
        txtQuantity.setText(ItemQuantity);
        txtDiscount.setText(String.valueOf(ItemDiscount));
    }

    private void setData2() throws SQLException, ClassNotFoundException {
        PreparedStatement stm  = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM orderdetail ORDER BY OrderQty DESC ");
        ResultSet rst = stm.executeQuery();

        while (rst.next()){
            ItemCode=(rst.getString(2));
            ItemQuantity=(rst.getString(3));
            ItemDiscount=(rst.getDouble(4));
        }
        txtItemCode1.setText(ItemCode);
        txtQuantity1.setText(ItemQuantity);
        txtDiscount1.setText(String.valueOf(ItemDiscount));
    }
    /*private OrderDetails getData() throws SQLException, ClassNotFoundException {
        ResultSet stm = DbConnection.getInstance().getConnection()
                .prepareStatement("SELECT * FROM orderdetail ORDER BY OrderQty ASC;").executeQuery();

        return new OrderDetails(stm.getString(1),stm.getString(stm.getString(2)),stm.getString(3),stm.getString(4),
                Integer.parseInt(stm.getString(5)));
    }*/
}
