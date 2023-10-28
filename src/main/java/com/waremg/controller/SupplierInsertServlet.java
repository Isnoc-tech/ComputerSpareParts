package com.waremg.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.waremg.models.NotificationDBUtil;
import com.waremg.models.SupplierDBUtil;


@WebServlet("/SupplierInsertServlet")
public class SupplierInsertServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private SupplierDBUtil supplierDBUtil;
    private NotificationDBUtil notificationDBUtil;

    public void init() {
        supplierDBUtil = new SupplierDBUtil();
        notificationDBUtil = new NotificationDBUtil();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
        String name = request.getParameter("name");
        String staffIdStr = request.getParameter("staffId");
        String itemType = request.getParameter("itemType");
        String deliveryTimeStr = request.getParameter("deliveryTime");

        try {
            int staffId = Integer.parseInt(staffIdStr);
            int deliveryTime = Integer.parseInt(deliveryTimeStr);

            boolean isSuccess = supplierDBUtil.insertSupplier(name, staffId, itemType, deliveryTime);

            if (isSuccess) {
            	
            	String notificationMessage = "A new supplier has been added: " + name;
            	boolean notificationAdded = notificationDBUtil.insertNotification(notificationMessage);
                response.sendRedirect(request.getContextPath() + "/Main");
            } else {

                response.getWriter().println("Failed to insert supplier.");
            }
        } catch (NumberFormatException e) {
            
            e.printStackTrace();
            response.getWriter().println("Invalid staff ID or delivery time.");
        } 
    }
}
