<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <style>
        .custom-card {
            border-radius: 25px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.5);

        }
        .submit-button {
            text-align: center;
        }
        .btn-green {
            background-color: #28a745;
            color: #fff; 
        }
    </style>
    
    <link rel="stylesheet" href="./css/mgwplaceorder.css">
</head>
<body>
    <div class="container d-flex justify-content-center align-items-center vh-100">
        <div class="card custom-card col-md-5">
            <div class="card-header">
                <h1 class="text-center">Place Order</h1>
            </div>
            <div class="card-body">
                <form action="PlaceOrderServlet" method="get">
                <c:if test="${not empty requestScope.errorMessage}">
			        <div class="alert alert-danger" role="alert">
					  ${requestScope.errorMessage}
					</div>
				</c:if>
                    <div class="form-group">
                        <label for="description">Description:</label>
                        <input type="text" class="form-control" id="description" name="description" required><br>
                    </div>
                    <div class="form-group">
                        <label for="quantity">Quantity:</label>
                        <input type="number" class="form-control" id="quantity" name="quantity" required><br>
                    </div>
                    <div class="form-group">
                        <label for="unitPrice">Unit Price:</label>
                        <input type="number" class="form-control" id="unitPrice" name="unitPrice" required><br>
                    </div>
                    <div class="submit-button">
                        <button type="submit" class="btn btn-green btn-block">Place Order</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
