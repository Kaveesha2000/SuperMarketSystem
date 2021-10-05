package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Order;
import view.tm.PaymentTM;

import java.sql.SQLException;

public class PaymentFormController {
    public TableView<Order> tblPayment;
    public TableColumn colOrderId;
    public TableColumn colCustId;
    public TableColumn colDate;
    public TableColumn colTime;
    public TableColumn colTotal;
    public Label lblTotal;

    public void initialize() throws SQLException, ClassNotFoundException {
        tblPayment.setItems(new OrderController().getAllOrders());
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colCustId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("cost"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));

        lblTotal.setText(String.valueOf(colTotal));

        addToTable();
    }

    //-------------------------------------------
    ObservableList<PaymentTM> obList = FXCollections.observableArrayList();
    public void addToTable() throws SQLException, ClassNotFoundException {
        String orderId = colOrderId.getText();
        String custId = colCustId.getText();
        String total = colTotal.getText();
        String date = colDate.getText();
        String time = colTime.getText();

        PaymentTM tm = new PaymentTM(
                orderId,
                custId,date,time,total
        );

        int rowNumber=isExists(tm);

        if (rowNumber==-1){// new Add
            obList.add(tm);
        }else {
            double total1 = 0;

            PaymentTM temp = obList.get(rowNumber);

            PaymentTM newTm = new PaymentTM(
                    temp.getOrderId(),
                    temp.getCustId(),
                    temp.getDate(),
                    temp.getTime(),
                    total1+temp.getCost()
            );

            obList.remove(rowNumber);
            obList.add(newTm);
        }
    }
    private int isExists(PaymentTM tm){
        for (int i = 0; i < obList.size(); i++) {
            if (tm.getOrderId().equals(obList.get(i).getOrderId())){
                return i;
            }
        }
        return -1;
    }

    //-------------------------------------------
}
