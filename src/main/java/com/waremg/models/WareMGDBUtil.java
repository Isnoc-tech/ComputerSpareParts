package com.waremg.models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.waremg.classes.DBConnect;
import com.waremg.classes.WarehouseManager;

public class WareMGDBUtil {

    public static List<WarehouseManager> validateWarehouseManager(String username, String password) {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<WarehouseManager> managers = new ArrayList<>();

        try {
            con = DBConnect.getConnection();
            stmt = con.createStatement();
            String sql = "SELECT * FROM warehouse_staff WHERE username = '" + username + "' AND password = '" + password + "'";            
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int mid = rs.getInt("staffId");
                String uname = rs.getString("username");
                String name = rs.getString("name");
                String pwd = rs.getString("password");


                WarehouseManager mgr = new WarehouseManager(mid, uname, name, pwd);
                managers.add(mgr);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } 
        

        return managers;
    }
}
