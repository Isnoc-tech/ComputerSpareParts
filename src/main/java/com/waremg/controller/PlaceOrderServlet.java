package com.waremg.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.waremg.models.NotificationDBUtil;
import com.waremg.models.OrderDBUtil;

@WebServlet("/PlaceOrderServlet")
public class PlaceOrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private OrderDBUtil orderDBUtil;
    private NotificationDBUtil notificationDBUtil;

    public void init() {
        orderDBUtil = new OrderDBUtil();
        notificationDBUtil = new NotificationDBUtil();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String description = request.getParameter("description");
        String quantityStr = request.getParameter("quantity");
        String unitPriceStr = request.getParameter("unitPrice");
        String supplierId = request.getParameter("supplierId");
        
        

        try {
            int quantity = Integer.parseInt(quantityStr);
            double unitPrice = Double.parseDouble(unitPriceStr);
            
            String errorMessage = "";
            
            if (unitPrice > 0 && quantity > 0) {
                // If unit price is negative, convert it to a positive number
            	 // Insert the order into the database
                boolean isSuccess = orderDBUtil.insertOrder(description, quantity, unitPrice);
                
               

                if (isSuccess) {
                    // Create a notification message
                    String notificationMessage = "Order has been placed " + description;

                    // Save the notification
                    boolean notificationSaved = notificationDBUtil.insertNotification(notificationMessage);

                    if (notificationSaved) {
                        response.sendRedirect(request.getContextPath() + "/ProcessOrderServlet");
                    } else {
                        response.sendRedirect(request.getContextPath() + "/OrderDetails.jsp");
                    }
                } else {
                    response.sendRedirect(request.getContextPath() + "/OrderDetails.jsp");
                }
            }else {
            	
            	
            	
            	errorMessage += "Minus value error!!<br>";
    			request.setAttribute("errorMessage", errorMessage);
    		    request.getRequestDispatcher("PlaceOrder.jsp").forward(request, response);
    		    return;
            }

           
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/OrderDetails.jsp");
        }
    }
}
