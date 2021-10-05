package controller;

import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import view.tm.IncomeReportTM;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MonthlyIncomeFormController {
    public TableView tblReport;
    public TableColumn colMonth;
    public TableColumn colOrderCount;
    public TableColumn colItemSoldQty;
    public TableColumn colCost;

    public void initialize() throws ClassNotFoundException, SQLException {
//        System.out.println(obListMonthly);
//        System.out.println(obList);

        colMonth.setCellValueFactory(new PropertyValueFactory<>("monthOrYear"));
        colOrderCount.setCellValueFactory(new PropertyValueFactory<>("numberOfOrders"));
        colItemSoldQty.setCellValueFactory(new PropertyValueFactory<>("numberOfSoldItem"));
        colCost.setCellValueFactory(new PropertyValueFactory<>("finalCost"));

        loadMonthlyIncomeReport();

    }

    ObservableList<IncomeReportTM> obListMonthly= FXCollections.observableArrayList();
    ObservableList<IncomeReportTM> obList= FXCollections.observableArrayList();

    private void loadMonthlyIncomeReport() throws SQLException, ClassNotFoundException {

        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT extract(MONTH FROM(OrderDate)) ,sum(orderdetail.OrderQty),count(orders.OrderId),sum(orders.Cost)  FROM orders INNER JOIN orderdetail ON orders.OrderId = orderdetail.OrderId GROUP BY OrderDate");

        ResultSet rst = stm.executeQuery();
        if (rst.next()){
            int monthNo= rst.getInt(1);
            int sumOfQty= rst.getInt(2);
            int order= rst.getInt(3);
            double total= rst.getDouble(4);

            int x= ifExists(monthNo);

            if (x==-1){
               // System.out.println("if cluase");
            }else {

                IncomeReportTM temp=obList.get(x);
                IncomeReportTM newTm=new IncomeReportTM(
                        temp.getDate(),order, sumOfQty, total,String.valueOf(monthNo));
                obListMonthly.add(newTm);

                System.out.println(obListMonthly);

            }
        }
        tblReport.setItems(obListMonthly);
    }

    private int ifExists(int monthNo) {
        for (int i = 0; i < obList.size(); i++) {
            int tempMonth=Integer.parseInt(String.valueOf(obList.get(i).getDate()).split("-")[1]);
            if (monthNo==tempMonth) {
                return i;
            }
        }
        return -1;
    }
}
