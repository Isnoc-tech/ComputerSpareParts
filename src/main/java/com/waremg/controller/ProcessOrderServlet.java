package com.waremg.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.waremg.classes.Order;
import com.waremg.models.OrderDBUtil;

@WebServlet("/ProcessOrderServlet")
public class ProcessOrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private OrderDBUtil orderDBUtil;

    public void init() {
        orderDBUtil = new OrderDBUtil();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Order recentOrder = orderDBUtil.getMostRecentOrder();

        request.setAttribute("recentOrder", recentOrder);

        request.getRequestDispatcher("/OrderDetails.jsp").forward(request, response);
    }
}

