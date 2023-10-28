package com.waremg.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.waremg.classes.Notification;
import com.waremg.models.NotificationDBUtil;

@WebServlet("/NotificationServlet")
public class NotificationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private NotificationDBUtil notificationDBUtil;

    public void init() {
        notificationDBUtil = new NotificationDBUtil();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve all notifications
        List<Notification> notifications = notificationDBUtil.getAllNotifications();

        // You can pass the notifications to your JSP for rendering
        request.setAttribute("notifications", notifications);
        request.getRequestDispatcher("/notifications.jsp").forward(request, response);
    }
}
