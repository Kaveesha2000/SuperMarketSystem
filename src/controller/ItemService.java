package controller;

import model.Customer;
import model.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemService {
    public boolean saveItem(Item i) throws SQLException, ClassNotFoundException;
    public boolean deleteItem(String i) throws SQLException, ClassNotFoundException;
    public boolean updateItem(Item i) throws SQLException, ClassNotFoundException;
    public Item getItem(String id) throws SQLException, ClassNotFoundException;
    public ArrayList<Item> getAllItems() throws SQLException, ClassNotFoundException;

}
