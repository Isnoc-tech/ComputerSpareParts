package com.waremg.models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.waremg.classes.DBConnect;
import com.waremg.classes.Supplier;

public class SupplierDBUtil {
    private Connection con;
    private Statement statement;
    private ResultSet rs;
    
    private static final String SELECT_ALL_SUPPLIERS_SQL = "SELECT * FROM supplier";

    public SupplierDBUtil() {
        con = DBConnect.getConnection();
        try {
            statement = con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Supplier> getAllSuppliers() {
        List<Supplier> suppliers = new ArrayList<>();
        try {
            rs = statement.executeQuery(SELECT_ALL_SUPPLIERS_SQL);
            while (rs.next()) {
                int supplierId = rs.getInt("supplierId");
                String name = rs.getString("name");
                int staffId = rs.getInt("staffId");
                String itemType = rs.getString("itemType");
                int deliveryTime = rs.getInt("deliveryTime");
                suppliers.add(new Supplier(supplierId, name, staffId, itemType, deliveryTime));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return suppliers;
    }

    public boolean insertSupplier(String name, int staffId, String itemType, int deliveryTime) {
        try {
            Connection con = DBConnect.getConnection();
            Statement statement = con.createStatement();

            String insertSQL = "INSERT INTO supplier (name, staffId, itemType, deliveryTime) VALUES ('"
                    + name + "', " + staffId + ", '" + itemType + "', " + deliveryTime + ");";

            int rowsAffected = statement.executeUpdate(insertSQL);
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    
    public boolean updateSupplier(Supplier updatedSupplier) {
        try {
            int supplierId = updatedSupplier.getSupplierId();
            String name = updatedSupplier.getName();
            int staffId = updatedSupplier.getStaffId();
            String itemType = updatedSupplier.getItemType();
            int deliveryTime = updatedSupplier.getDeliveryTime();

            String updateSQL = "UPDATE supplier " +
                              "SET name='" + name + "', staffId=" + staffId + ", itemType='" + itemType + "', deliveryTime=" + deliveryTime +
                              " WHERE supplierId=" + supplierId;

            int rowsAffected = statement.executeUpdate(updateSQL);
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteSupplier(int supplierId) {
        try {
            String deleteSQL = "DELETE FROM supplier WHERE supplierId = " + supplierId;
            int rowsAffected = statement.executeUpdate(deleteSQL);
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Supplier> searchSuppliers(String searchQuery) {
        List<Supplier> searchResults = new ArrayList<>();
        try {
        	String searchSQL = "SELECT * FROM supplier WHERE " +
                    "name LIKE '%" + searchQuery + "%' " +
                    "OR itemType LIKE '%" + searchQuery + "%' " +
                    "OR CAST(deliveryTime AS CHAR) LIKE '%" + searchQuery + "%' " +
                    "OR staffId = '" + searchQuery + "'" +
                   "OR CAST(supplierId AS CHAR) = '" + searchQuery + "'";


            rs = statement.executeQuery(searchSQL);
            while (rs.next()) {
                int supplierId = rs.getInt("supplierId");
                String name = rs.getString("name");
                int staffId = rs.getInt("staffId");
                String itemType = rs.getString("itemType");
                int deliveryTime = rs.getInt("deliveryTime");
                searchResults.add(new Supplier(supplierId, name, staffId, itemType, deliveryTime));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return searchResults;
    }

}
