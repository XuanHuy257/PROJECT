<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Update Customer - SB Admin</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
        <link href="css/styles.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
    </head>
    <body class="sb-nav-fixed">
         <jsp:include page="mkt-header.jsp"></jsp:include>
        <div id="layoutSidenav">
 <jsp:include page="mkt-sidenav.jsp"></jsp:include>
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <h1 class="mt-4">Update Customer</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item"><a href="mkt-home.jsp">Dashboard</a></li>
                            <li class="breadcrumb-item"><a href="customer">Customer List</a></li>
                            <li class="breadcrumb-item active">Update Customer</li>
                        </ol>
                        <div class="card mb-4">
                            <div class="card-header">
                                <i class="fas fa-user-edit me-1"></i>
                                Update Customer Information
                            </div>
                            
                              <c:if test="${not empty message}">
        <p style="color:green;">${message}</p>
    </c:if>
                            <div class="card-body">
                                <form action="editCustomerForm" method="post">
                                    <div class="row mb-3">
                                        <div class="col-md-6">
                                            <label for="customerID" class="form-label"><strong>Customer ID</strong></label>
                                            <input type="text" class="form-control" id="customerID" name="customerID" value="${customer.customerID}" readonly >
                                        </div>
                                        <div class="col-md-6">
                                            <label for="userName" class="form-label"><strong>Full Name</strong> </label>
                                            <input type="text" class="form-control" id="fullName" name="fullName" value="${customer.fullName}" required>
                                        </div>
                                    </div>
                                        <div class="row mb-3">  
                                        <div class="col-md-6">
                                            <label for="email" class="form-label"> <strong> Email </strong></label>
                                            <input type="email" class="form-control" id="email" name="email" value="${customer.email}" required>
                                        </div>
                                        <div class="col-md-6">
                                            <label for="phoneNumber" class="form-label">   <strong> Phone Number </strong></label>
                                            <input type="text" class="form-control" id="phoneNumber" name="phoneNumber" value="${customer.phoneNumber}" required>
                                        </div>
                                    </div>
                                    <div class="row mb-3">
                                        <div class="col-md-6">
                                            <label for="gender" class="form-label"><strong> Gender</strong></label>
                                            <select class="form-control" id="gender" name="gender" required>
                                                <option value="Male" ${customer.gender == 'Male' ? 'selected' : ''}>Male</option>
                                                <option value="Female" ${customer.gender == 'Female' ? 'selected' : ''}>Female</option>
                                                <option value="Other" ${customer.gender == 'Other' ? 'selected' : ''}>Other</option>
                                            </select>
                                        </div>

                                        <div class="col-md-6">
                                            <label for="address" class="form-label"> <strong> Address</strong></label>
                                            <input type="text" class="form-control" id="address" name="address" value="${customer.address}" required>
                                        </div>

                                    </div>
                                    


                                    <div class="mb-3">
                                        <button type="submit" class="btn btn-primary">Save Changes</button>
                                        <a href="customer" class="btn btn-secondary">Cancel</a>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </main>
                <footer class="py-4 bg-light mt-auto">
                    <div class="container-fluid px-4">
                        <div class="d-flex align-items-center justify-content-between small">
                            <div class="text-muted">Copyright &copy; Your Website 2023</div>
                            <div>
                                <a href="#">Privacy Policy</a>
                                &middot;
                                <a href="#">Terms &amp; Conditions</a>
                            </div>
                        </div>
                    </div>
                </footer>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="js/scripts.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
        <script src="js/datatables-simple-demo.js"></script>
    </body>
</html>
