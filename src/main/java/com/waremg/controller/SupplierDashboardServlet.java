package com.waremg.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.waremg.classes.Notification;
import com.waremg.classes.Supplier;
import com.waremg.models.NotificationDBUtil;
import com.waremg.models.SupplierDBUtil;

@WebServlet("/Main")
public class SupplierDashboardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private SupplierDBUtil supplierDBUtil;
    private NotificationDBUtil notificationDBUtil;

    public void init() {
        supplierDBUtil = new SupplierDBUtil();
        notificationDBUtil = new NotificationDBUtil();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String searchQuery = request.getParameter("searchQuery");


            if (searchQuery != null) {
                
            	
            	List<Supplier> suppliers = supplierDBUtil.searchSuppliers(searchQuery);
            	request.setAttribute("suppliers", suppliers);
            	request.getRequestDispatcher("SupplierMGDash.jsp").forward(request, response);

            } else {

            	List<Supplier> suppliers = supplierDBUtil.getAllSuppliers();
            	List<Notification> notifications = notificationDBUtil.getAllNotifications();
            	request.setAttribute("notifications", notifications);
            	request.setAttribute("suppliers", suppliers);
            	request.getRequestDispatcher("SupplierMGDash.jsp").forward(request, response);
     	
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String supplierIdStr = request.getParameter("PlaceOrder");

        if (supplierIdStr != null) {
            request.setAttribute("supplierId", supplierIdStr);
            request.getRequestDispatcher("OrderDeatils.jsp").forward(request, response);
        }
    }
}


