package com.waremg.models;

import java.util.List;

import com.waremg.classes.Notification;

public interface NotificationDAO {

    List<Notification> getAllNotifications();

    boolean insertNotification(String message);
}
