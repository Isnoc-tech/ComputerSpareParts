package com.waremg.models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.waremg.classes.DBConnect;
import com.waremg.classes.Notification;

public class NotificationDBUtil implements NotificationDAO{
    private Connection con;
    private Statement statement;
    private ResultSet rs;
    private static NotificationDBUtil NotificationInstance;

    
    private static final String SELECT_ALL_NOTIFICATIONS_SQL = "SELECT * FROM notification";

    public NotificationDBUtil() {
        con = DBConnect.getConnection();
        try {
            statement = con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static NotificationDBUtil getInstance() {
        if (NotificationInstance == null) {
            synchronized (NotificationDBUtil.class) {
                if (NotificationInstance == null) {
                	NotificationInstance = new NotificationDBUtil();
                }
            }
        }
        return NotificationInstance;
    }
    
    @Override
    public List<Notification> getAllNotifications() {
        List<Notification> notifications = new ArrayList<>();
        try {
            rs = statement.executeQuery(SELECT_ALL_NOTIFICATIONS_SQL);
            while (rs.next()) {
                int id = rs.getInt(1);
                String message = rs.getString(2);
                notifications.add(new Notification(id, message));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notifications;
    }
    
    @Override
    public boolean insertNotification(String message) {
        try {
            String insertSQL = "INSERT INTO notification (message) VALUES ('" + message + "');";
            int rowsAffected = statement.executeUpdate(insertSQL);
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
