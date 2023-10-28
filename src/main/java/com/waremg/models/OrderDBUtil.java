package com.waremg.models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.waremg.classes.DBConnect;
import com.waremg.classes.Order;

public class OrderDBUtil {
    private Connection con;
    private Statement statement;
    private ResultSet rs;

    private static final String SELECT_ALL_ORDERS_SQL = "SELECT * FROM order_supplier";

    public OrderDBUtil() {
        con = DBConnect.getConnection();
        try {
            statement = con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        try {
            rs = statement.executeQuery(SELECT_ALL_ORDERS_SQL);
            while (rs.next()) {
                int orderID = rs.getInt("orderID");
                String description = rs.getString("description");
                int quantity = rs.getInt("quantity");
                double unitPrice = rs.getDouble("unitPrice");
                orders.add(new Order(orderID, description, quantity, unitPrice));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public boolean insertOrder(String description, int quantity, double unitPrice) {
        try {
            Connection con = DBConnect.getConnection();
            Statement statement = con.createStatement();

            String insertSQL = "INSERT INTO order_supplier (description, quantity, unitPrice) VALUES ('"
                    + description + "', " + quantity + ", " + unitPrice + ");";

            int rowsAffected = statement.executeUpdate(insertSQL);
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public Order getMostRecentOrder() {
        Order order = null;
        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM order_supplier ORDER BY orderID DESC LIMIT 1");
            if (rs.next()) {
                int orderID = rs.getInt("orderID");
                String description = rs.getString("description");
                int quantity = rs.getInt("quantity");
                int unitPrice = rs.getInt("unitPrice");
                order = new Order(orderID, description, quantity, unitPrice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }
    
    public List<Order> getOrdersForSupplier(int supplierId) {
        List<Order> orders = new ArrayList<>();
        try {
            String selectSQL = "SELECT * FROM order_supplier WHERE supplier_id = " + supplierId + "";
            ResultSet rs = statement.executeQuery(selectSQL);

            while (rs.next()) {
                int orderID = rs.getInt("orderID");
                String description = rs.getString("description");
                int quantity = rs.getInt("quantity");
                double unitPrice = rs.getDouble("unit_price");
                orders.add(new Order(orderID, description, quantity, unitPrice));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }



}

