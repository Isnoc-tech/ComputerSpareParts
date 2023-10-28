<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <meta charset="UTF-8">
    <title>Edit Supplier</title>
    <link rel="stylesheet" href="./css/mgweditform.css">
    
          <!-- Google Fonts -->
  <link rel="preconnect" href="https://fonts.googleapis.com" />
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
  <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;500;700&display=swap" rel="stylesheet" />
    
    
</head>
<body>

<section class="vh-100 gradient-custom">
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                <div class="card bg-dark text-white">
                    <div class="card-body p-5 text-center">

                        <div class="mb-md-5 mt-md-4 pb-2">
                            <h2 class="fw-bold mb-2 text-uppercase">Edit Supplier</h2>

                            <form action="EditSupplierServlet" method="post">
                                <div class="form-outline form-white mb-4">
                                    <label for="supplierId" class="form-label">Supplier ID:</label>
                                    <input type="text" class="form-control form-control-lg" name="supplierId" id="supplierId" value="${param.supplierId}" readonly>
                                </div>
                                <div class="form-outline form-white mb-4">
                                    <label for = "name" class="form-label">Name:</label>
                                    <input type="text" class="form-control form-control-lg" name="name" id="name" value="${param.name}" required>
                                </div>
                                <div class="form-outline form-white mb-4">
                                    <label for="staffId" class="form-label">Manager ID:</label>
                                    <input type="text" class="form-control form-control-lg" name="staffId" id="staffId" value="${param.staffId}" required>
                                </div>
                                <div class="form-outline form-white mb-4">
                                    <label for="itemType" class="form-label">Item Type:</label>
                                    
                                     <select class="form-select form-select-lg" name="itemType" id="itemType" required>
								        <option value="VGA" ${param.itemType == 'VGA' ? 'selected' : ''}>VGA</option>
								        <option value="RAM" ${param.itemType == 'RAM' ? 'selected' : ''}>RAM</option>
								        <option value="CPU" ${param.itemType == 'CPU' ? 'selected' : ''}>CPU </option>
								        <option value="Motherboard" ${param.itemType == 'Motherboard' ? 'selected' : ''}>Motherboard</option>
								        <option value="Power Supply" ${param.itemType == 'Power Supply' ? 'selected' : ''}>Power Supply</option>
								        <option value="Hard" ${param.itemType == 'Hard' ? 'selected' : ''}>Hard</option>
								        <option value="Fan" ${param.itemType == 'Fan' ? 'selected' : ''}>Fan</option>
								    </select>
                                </div>
                                <div class="form-outline form-white mb-4">
                                    <label for="deliveryTime" class="form-label">Delivery Time:</label>
                                    <input type="number" class="form-control form-control-lg" name="deliveryTime" id="deliveryTime" value="${param.deliveryTime}" required>
                                </div>
                                <button type="submit" class="btn btn-primary btn-outline-light px-5">Save Changes</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

</body>
</html>
