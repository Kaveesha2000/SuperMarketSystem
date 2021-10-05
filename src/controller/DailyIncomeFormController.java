package controller;

import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.IncomeReport;
import view.tm.IncomeReportTM;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DailyIncomeFormController {
    public TableColumn colDate;
    public TableColumn colOrderCost;
    public TableColumn colItemQty;
    public TableColumn colCost;
    public TableView tblReport;

    public void initialize() throws SQLException, ClassNotFoundException {

        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colOrderCost.setCellValueFactory(new PropertyValueFactory<>("numberOfOrders"));
        colItemQty.setCellValueFactory(new PropertyValueFactory<>("numberOfSoldItem"));
        colCost.setCellValueFactory(new PropertyValueFactory<>("finalCost"));

        loadDalyIncomeReport();

    }

    private void loadDalyIncomeReport() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT OrderDate,count(OrderId),sum(cost) from orders group by OrderDate");
        ResultSet rst = stm.executeQuery();
        if (rst.next()){
            String date= String.valueOf(rst.getString(1));
            int countOrderId=rst.getInt(2);
            double sumOfTotal= rst.getDouble(3);
            ArrayList<IncomeReport> data=getCountItems();
            loadDalyIncomeReportTableToData(date,countOrderId,sumOfTotal,data);
        }
    }

    ObservableList<IncomeReportTM> obList= FXCollections.observableArrayList();

    private void loadDalyIncomeReportTableToData(String date, int countOrderId, double sumOfTotal, ArrayList<IncomeReport> data) {
        for (IncomeReport temp:data
        ) {
            obList.add(new IncomeReportTM(
                    date,countOrderId, temp.getNumberOfSoldItem(), sumOfTotal, data
                        ));
        }
        tblReport.setItems(obList);
    }

    private ArrayList<IncomeReport> getCountItems() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT orders.OrderDate,sum(orderdetail.OrderQty) FROM orders INNER JOIN orderdetail ON orders.OrderId = orderdetail.OrderId GROUP BY orderDate");
        ResultSet rst = stm.executeQuery();
        ArrayList<IncomeReport> temp=new ArrayList<>();
        while (rst.next()){
            String orderDate=rst.getString(1);
            int sumOfItem= rst.getInt(2);
            temp.add(new IncomeReport(
                    orderDate,sumOfItem
            ));
        }
        //System.out.println(temp);
        return temp;
    }
}
