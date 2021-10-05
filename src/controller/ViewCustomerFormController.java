package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;
import view.tm.CustomerTM;

import java.sql.SQLException;
import java.util.ArrayList;

public class ViewCustomerFormController {
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colCity;
    public TableColumn colProvince;
    public TableColumn colPostalCode;
    public TableView<CustomerTM> tblCustomer;

    public void initialize() {
        try {

            colId.setCellValueFactory(new PropertyValueFactory<>("id"));
            colName.setCellValueFactory(new PropertyValueFactory<>("name"));
            colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
            colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
            colProvince.setCellValueFactory(new PropertyValueFactory<>("province"));
            colPostalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));

            setCustomersToTable(new CustomerController().getAllCustomers());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void setCustomersToTable(ArrayList<Customer> allCustomers) {
        ObservableList<CustomerTM> obList = FXCollections.observableArrayList();
        allCustomers.forEach(e->{
            obList.add(
                    new CustomerTM(e.getId(),e.getTitle(),e.getName(),e.getAddress(),e.getCity(),e.getProvince(),e.getPostalCode()));
        });
        tblCustomer.setItems(obList);
    }
}
