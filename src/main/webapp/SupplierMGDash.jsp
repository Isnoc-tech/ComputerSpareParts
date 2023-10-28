<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Supplier Management</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    
    <link rel="stylesheet" href="./css/mgwdash.css">
    
      <!-- Google Fonts -->
  <link rel="preconnect" href="https://fonts.googleapis.com" />
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
  <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;500;700&display=swap" rel="stylesheet" />
    
      <!-- Font Awesome -->
  <script defer src="https://use.fontawesome.com/releases/v5.0.7/js/all.js"></script>
  <script src="https://kit.fontawesome.com/38f42574c5.js" crossorigin="anonymous"></script>
  
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
  
    
</head>
<body>


<nav class="navbar navbar-expand-lg navbar-dark">
    <a class="navbar-brand" href="#" style="margin-left: 40%" >Supplier Management</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav" style="margin-right: 295px">
        <ul class="navbar-nav ml-auto"> 
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="notificationDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <i class="fas fa-bell"></i> 
                </a>
                <div class="dropdown-menu dropdown-menu-right" aria-labelledby="notificationDropdown">
                    <c:forEach var="notification" items="${notifications}">
                        <a class="dropdown-item" href="#"><c:out value="${notification.message}" /></a>
                    </c:forEach>
                </div>
            </li>
            <li class="nav-item">
	                <c:if test="${not empty sessionScope.mgr}">
					  <form method="post" action="WareMGLogoutServlet">
					  	<input type="hidden" name="log" value="logout"/>
					 	<input type="submit" value="Log out" class="btn btn-danger ms-2">
				 	
					</form>
					</c:if>
            </li>
        </ul>
    </div>
</nav>



    <div class="container-fluid">
        

        <!-- Search Suppliers -->
 		<div class="mt-4">
            <div class="search-bar">
                <h2 style="color: white">Search Suppliers</h2>
                <form action="Main" method="get">
                    <div class="input-group">
                        <input type="text" class="form-control search-input" name="searchQuery" id="searchQuery">
                        <div class="input-group-append">
                            <button class="btn btn-primary search-button" type="submit">
                                <i class="fa fa-search"></i>
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>

        <!-- Display All Suppliers -->
        <div class="mt-4">
            <div class="overflow-hidden card table-nowrap table-card">
                <div class="card-header d-flex justify-content-between align-items-center">
                    <h5 class="mb-0">All Suppliers</h5>
                </div>
                <div class="table-responsive">
                    <table class="table table-striped" >
                        <thead class="thead-dark">
                            <tr>
                                <th>ID</th>
                                <th>Name</th>
                                <th>Manager ID</th>
                                <th>Item Type</th>
                                <th>Delivery Time</th>
                                <th>Order</th>
                                <th>Report </th>
                                <th>Update</th>
                                <th>Delete</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="supplier" items="${suppliers}">
                                <tr>
                                    <td>${supplier.supplierId}</td>
                                    <td>${supplier.name}</td>
                                    <td>${supplier.staffId}</td>
                                    <td>${supplier.itemType}</td>
                                    <td>${supplier.deliveryTime}</td>
                                    
                                    <td>
	                                    <form method="get" action="PlaceOrder.jsp">
										    <input type="hidden" name="supplierId" value="${supplier.supplierId}" />
										    <input type="submit" value="Place Order" class="btn btn-success" />
										</form>
                                    </td>
                                    
                                    <td>
                                    
                                    <form method="get" action="GenerateOrderReportServlet">
									    <input type="hidden" name="supplierId" value="${supplier.supplierId}" />
									    <input type="submit" value="PDF Report" class="btn btn-primary" />
									</form>
                                    
                                    </td>
                                    
                                    <td>
                                    
                                        <form method="get" action="EditSupplierForm.jsp">
                                            <input type="hidden" name="supplierId" value="${supplier.supplierId}" />
                                            <input type="submit" value="Update" class="btn btn-primary">
                                        </form>
									</td>
									<td>
                                        <form method="get" action="DeleteSupplierServlet">
                                            <input type="hidden" name="supplierId" value="${supplier.supplierId}" />
                                            <input type="submit" value="Delete" class="btn btn-danger">
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

		<div class="card mt-4">
		    <div class="card-body">
		        <h2 class="card-title">Add New Supplier</h2>
		        <form action="SupplierInsertServlet" method="post">
		            <div class="form-group">
		                <label for="name">Name:</label>
		                <input type="text" class="form-control" name="name" id="name" required>
		            </div>
		            <div class="form-group">
		                <label for="staffId">Staff ID:</label>
		                <input type="text" class="form-control" name="staffId" id="staffId" required>
		            </div>
					<div class="form-group">
					    <label for="itemType">Item Type:</label>
					    <select class="form-control" name="itemType" id="itemType" required>
					        <option value="VGA">VGA</option>
					        <option value="RAM">RAM</option>
					        <option value="CPU">CPU</option>
					        <option value="Hard">Hard Disk</option>
					        <option value="Motherboard">Motherboard</option>
					        <option value="Powersupply">Power Supply</option>
					        <option value="Fan">Fan</option>
					    </select>
					</div>
		            <div class="form-group">
		                <label for="deliveryTime">Delivery Time in Days:</label>
		                <input type="text" class="form-control" name="deliveryTime" id="deliveryTime" required>
		            </div><br>
		            <button type="submit" class="btn btn-success">Add Supplier</button>
		        </form>
		    </div>
		</div>

        
    </div>
         <footer class="text-white text-center py-4" style = "margin-top: 35px">
            <p>&copy; 2023 OOP Project. All rights reserved.</p>
        </footer>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
   <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</body>
</html>
