package com.waremg.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.waremg.models.NotificationDBUtil;
import com.waremg.models.SupplierDBUtil;

@WebServlet("/DeleteSupplierServlet")
public class DeleteSupplierServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private SupplierDBUtil delete;
    private NotificationDBUtil notificationDBUtil;

    public void init() {
    	delete = new SupplierDBUtil();
    	notificationDBUtil = new NotificationDBUtil();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String supplierIdStr = request.getParameter("supplierId");

        try {
            int supplierId = Integer.parseInt(supplierIdStr);

            boolean deleted = delete.deleteSupplier(supplierId);

            if (deleted) {
            	
            	String notificationMessage = "A supplier has been deleted (ID: " + supplierId + ")";
                boolean notificationAdded = notificationDBUtil.insertNotification(notificationMessage);
     
                response.sendRedirect(request.getContextPath() + "/Main");
            } else {
                response.getWriter().println("Failed to delete supplier.");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.getWriter().println("Invalid supplier ID.");
        }
    }
}
