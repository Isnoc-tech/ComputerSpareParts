<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="./css/warehousemglogin.css"/>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
	
	<div class="mainContainer">
	
		<div class="loginContainer">
		
			<img alt="illustration" src="./images/pic.svg">
		
			<form action="WareMGLoginServlet" method="post">
				<h1>Warehouse Manager Login</h1>
				
				<c:if test="${not empty requestScope.errorMessage}">
			        <div class="alert alert-danger" role="alert">
					  ${requestScope.errorMessage}
					</div>
				</c:if>
				
				<div>
					<h4>Username: </h4>
					<div class="inputContainer">
						<input class="inputField" type="text" name="username"> <br>
					</div>
				</div>
				<div>
					<h4>Password: </h4>
					<div class="inputContainer">
						<input class="inputField" type="password" name="password"> <br>
					</div>
				</div>
				
				<input type="submit" name="submit" value="Login" class="loginBtn">
			</form>
		
		</div>
		
	</div>

</body>

</html>