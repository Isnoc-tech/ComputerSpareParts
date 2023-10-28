package com.waremg.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.waremg.classes.Supplier;
import com.waremg.models.NotificationDBUtil;
import com.waremg.models.SupplierDBUtil;

@WebServlet("/EditSupplierServlet")
public class EditSupplierServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private SupplierDBUtil supplierDBUtil;
    private NotificationDBUtil notificationDBUtil;

    public void init() {
        supplierDBUtil = new SupplierDBUtil();
        notificationDBUtil = new NotificationDBUtil();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Retrieve updated data from the form
        String supplierIdStr = request.getParameter("supplierId");
        String name = request.getParameter("name");
        String staffIdStr = request.getParameter("staffId");
        String itemType = request.getParameter("itemType");
        String deliveryTimeStr = request.getParameter("deliveryTime");

        try {
            int supplierId = Integer.parseInt(supplierIdStr);
            int staffId = Integer.parseInt(staffIdStr);
            int deliveryTime = Integer.parseInt(deliveryTimeStr);

            
            Supplier updatedSupplier = new Supplier(supplierId, name, staffId, itemType, deliveryTime);

            boolean updated = supplierDBUtil.updateSupplier(updatedSupplier);

            if (updated) {
            	
            	 String notificationMessage = "Supplier details have been updated: " + updatedSupplier.getName();
            	 boolean notificationAdded = notificationDBUtil.insertNotification(notificationMessage);
                
                response.sendRedirect(request.getContextPath() + "/Main");
            } else {
                
                response.sendRedirect("EditSupplierForm.jsp?error=Update failed");
            }
        } catch (NumberFormatException e) {
            
            e.printStackTrace();
            response.sendRedirect("EditSupplierForm.jsp?error=Invalid supplier ID, staff ID, or delivery time.");
        }
    }
}





