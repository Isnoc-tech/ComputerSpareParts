<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Order Details</title>
    <!-- Add Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- Add your custom CSS (style.css) if needed -->
    <link rel="stylesheet" href="./css/mgwplaceorder.css">
</head>
<body>
    <div class="container mt-5">
        <div class="card">
            <div class="card-header">
                <h1 class="text-center">Recent Order Details</h1>
            </div>
            <div class="card-body">
                <c:choose>
                    <c:when test="${empty recentOrder}">
                        <p class="text-center">No recent orders available.</p>
                    </c:when>
                    <c:otherwise>
                        <p><strong>Order ID:</strong> ${recentOrder.orderID}</p>
                        <p><strong>Description:</strong> ${recentOrder.description}</p>
                        <p><strong>Quantity:</strong> ${recentOrder.quantity}</p>
                        <p><strong>Unit Price:</strong> ${recentOrder.unitPrice}</p>
                        <p><strong>Total Price:</strong> ${recentOrder.quantity * recentOrder.unitPrice}</p>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
