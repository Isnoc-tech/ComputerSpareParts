package com.waremg.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import com.waremg.classes.WarehouseManager;
import com.waremg.models.WareMGDBUtil;


@WebServlet("/WareMGLoginServlet")
public class WareMGLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String errorMessage = "";
		
		List<WarehouseManager> mgr = WareMGDBUtil.validateWarehouseManager(username, password);
		
		if(!mgr.isEmpty()) {
			
			HttpSession session = request.getSession();
			
			session.setAttribute("mgr", mgr);
			
			response.sendRedirect(request.getContextPath() + "/Main");
		}else {
			
			errorMessage += "username or password incorrect!!<br>";
			request.setAttribute("errorMessage", errorMessage);
		    request.getRequestDispatcher("WarehouseManagerLogin.jsp").forward(request, response);
		    return;
		}
	}

}
