package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;
import model.Item;
import model.ItemDetails;
import model.Order;
import view.tm.CartTM;

import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AddOrderFormController {
    public ComboBox<String> cmbItemId;
    public JFXTextField txtQtyOnHand;
    public TableColumn colItemId;
    public TableColumn colQuantity;
    public ComboBox<String> cmbCustomerId;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQuantity;
    public JFXTextField txtPackedSize;
    public Label txtOrderId;
    public TableColumn colUnitPrice;
    public TableView<CartTM> tblOrder;
    public JFXTextField txtDiscount;
    public Label lblTotal;
    public TableColumn colTotal;
    public Label txtOrderDate;
    public Label txtOrderTime;
    public Button placeOrderBtn;

    int cartSelectedRowForRemove = -1;

    public void initialize(){

        colItemId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        setOrderId();
       // disableAndEnable(cmbCustomerId.getValue());

        try {
            loadCustomerIds();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            loadItemIds();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        cmbCustomerId.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    placeOrderBtn.setDisable(false);
                    try {
                        setCustomerData(newValue);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    loadDateTime();
                });

        cmbItemId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setItemData(newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        tblOrder.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            cartSelectedRowForRemove = (int) newValue;
        });

    }

    //----------disable------------------------

    /*public void disableAndEnable(String customerId){
        placeOrderBtn.setDisable(false);
        for (CustomerTM temp:obList) {
            if(temp.getId().equals(customerId)){
                placeOrderBtn.setDisable(true);
            }
        }
        if(!placeOrderBtn.isDisable()){
            placeOrderBtn.setDisable(false);
        }
    }*/

    //-------------------------------------------

    private void loadDateTime(){
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        txtOrderDate.setText(java.time.LocalDateTime.now().format(dateFormatter));
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm a");
        txtOrderTime.setText(java.time.LocalDateTime.now().format(timeFormatter));
    }
    private void loadItemIds() throws SQLException, ClassNotFoundException {
        List<String> itemIds = new ItemController().getAllItemIds();
        cmbItemId.getItems().addAll(itemIds);
    }

    private void loadCustomerIds() throws SQLException, ClassNotFoundException {
        List<String> customerIds = new CustomerController().getCustomerIds();
        cmbCustomerId.getItems().addAll(customerIds);
    }

    private void setItemData(String itemId) throws SQLException, ClassNotFoundException {
        Item i1 = new ItemController().getItem(itemId);
        if (i1 == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set");
        } else {
            txtPackedSize.setText(i1.getPackSize());
            txtQtyOnHand.setText(String.valueOf(i1.getQtyOnHand()));
            txtUnitPrice.setText(String.valueOf(i1.getUnitPrice()));
        }
    }

    private void setCustomerData(String customerId) throws SQLException, ClassNotFoundException {
        Customer c1 = new CustomerController().getCustomer(customerId);
        if (c1 == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set");
        }
    }

    private void setOrderId() {
        try {

            txtOrderId.setText(new OrderController().getOrderId());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    ObservableList<CartTM> obList= FXCollections.observableArrayList();

    public void addOrder(ActionEvent actionEvent) {
        int qtyOnHand = Integer.parseInt(txtQtyOnHand.getText());
        double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        int qtyForCustomer = Integer.parseInt(txtQuantity.getText()+"");
        Double discount = Double.parseDouble(txtDiscount.getText());
        double dis=(qtyForCustomer*unitPrice)*discount/100;
        Double total=(qtyForCustomer*unitPrice)-dis;

        if (qtyOnHand<qtyForCustomer){
            new Alert(Alert.AlertType.WARNING,"Invalid Quantity").show();
            return;
        }

        CartTM tm = new CartTM(
                discount,
                cmbItemId.getValue(),
                qtyForCustomer,
                unitPrice,
                total
        );

        int rowNumber=isExists(tm);

        if (rowNumber==-1){
            // new Add
            obList.add(tm);
        }else{
            // update
            CartTM temp = obList.get(rowNumber);
            if(temp.getQuantity()<qtyOnHand){
                CartTM newTm = new CartTM(
                        temp.getDiscount(),
                        temp.getItemId(),
                        temp.getQuantity()+qtyForCustomer,
                        unitPrice,
                        total+temp.getTotal()
                );

                obList.remove(rowNumber);
                obList.add(newTm);
            }
            else{
                new Alert(Alert.AlertType.WARNING,"Invalid Quantity").show();
            }

        }
        tblOrder.setItems(obList);
        if (!obList.isEmpty()){
            placeOrderBtn.setDisable(false);
        }
        calculateCost();
    }

    private int isExists(CartTM tm){
        for (int i = 0; i < obList.size(); i++) {
            if (tm.getItemId().equals(obList.get(i).getItemId())){
                return i;
            }
        }
        return -1;
    }

    void calculateCost(){
        double ttl=0;
        for (CartTM tm:obList
        ) {
            ttl+=tm.getTotal();
        }
        lblTotal.setText(ttl+" /=");
    }

    public void placeOrderOnAction(ActionEvent actionEvent) {
        ArrayList<ItemDetails> items= new ArrayList<>();
        double total=0;
        for (CartTM tempTm:obList) {
            total+=tempTm.getTotal();

           // System.out.println(tempTm.getItemId() + " " + tempTm.getQuantity() + " " + tempTm.getUnitPrice());

            items.add(new ItemDetails(tempTm.getItemId(),tempTm.getQuantity(), tempTm.getDiscount()));
        }

        //System.out.println("order object item check " + items);

        Order order= new Order(txtOrderId.getText(), txtOrderDate.getText(),txtOrderTime.getText(),
                cmbCustomerId.getValue(),total,items);

        if (new OrderController().placeOrder(order)){
            new Alert(Alert.AlertType.CONFIRMATION, "Success").show();
            setOrderId();
        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }

    }

    public void clear(ActionEvent actionEvent) {
        if (cartSelectedRowForRemove==-1){
            new Alert(Alert.AlertType.WARNING, "Please Select a row").show();
        }else{
            obList.remove(cartSelectedRowForRemove);
            if (obList.isEmpty()){
                placeOrderBtn.setDisable(true);
            }
            calculateCost();
            tblOrder.refresh();
        }
    }
}
