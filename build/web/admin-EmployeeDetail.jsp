<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Customer Details - SB Admin</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
        <link href="css/styles.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
    </head>
    <body class="sb-nav-fixed">
        <nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
            <a class="navbar-brand ps-3" href="AdminDashBoard">Admin</a>
            <button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle" href="#!"><i class="fas fa-bars"></i></button>
            <form class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0">
                <div class="input-group"></div>
            </form>
            <ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="#!">Settings</a></li>
                        <li><a class="dropdown-item" href="#!">Activity Log</a></li>
                        <li><hr class="dropdown-divider" /></li>
                        <li><a class="dropdown-item" href="logout">Logout</a></li>
                    </ul>
                </li>
            </ul>
        </nav>
        <div id="layoutSidenav">
            <div id="layoutSidenav_nav">
                <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
                    <div class="sb-sidenav-menu">
                        <div class="nav">
                            <div class="sb-sidenav-menu-heading">Core</div>
                            <a class="nav-link" href="userlist">
                                <div class="sb-nav-link-icon"><i class="fas fa-table"></i></div>
                                User
                            </a>
                        </div>
                    </div>
                    <div class="sb-sidenav-footer">
                        <div class="small">Logged in as:</div>
                         ${sessionScope.account.fullName}
                    </div>
                </nav>
            </div>
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <h1 class="mt-4">User Detail</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item"><a href="AdminDashBoard">Dashboard</a></li>
                            <li class="breadcrumb-item active">User Detail</li>
                        </ol>
                        <div class="card mb-4">
                            <div class="card-header">
                                <i class="fas fa-user me-1"></i>
                                User Information
                            </div>
                            <div class="card-body">
                                <div class="col-md-6">
                                    <label for="avatar" class="form-label"></label>
                                    <div>
                                        <img src="${employee.avatar}" alt="Avatar" width="100" height="100">
                                    </div>
                                </div>
                                <div class="row mb-3">
                                    <div class="col-md-6">
                                        <label for="employeeID" class="form-label"><strong>Customer ID</strong></label>
                                        <input type="text" class="form-control" id="employeeID" value="${employee.employeeID}" readonly>
                                    </div>
                                    <div class="col-md-6">
                                        <label for="userName" class="form-label"><strong>Full Name</strong></label>
                                        <input type="text" class="form-control" id="userName" value="${employee.fullName}" readonly>
                                    </div>
                                </div>
                                <div class="row mb-3">
                                    <div class="col-md-6">
                                        <label for="email" class="form-label"><strong>Email</strong></label>
                                        <input type="email" class="form-control" id="email" value="${employee.email}" readonly>
                                    </div>
                                    <div class="col-md-6">
                                        <label for="phoneNumber" class="form-label"><strong>Phone Number</strong></label>
                                        <input type="text" class="form-control" id="phoneNumber" value="${employee.phoneNumber}" readonly>
                                    </div>
                                </div>
                                <div class="row mb-3">
                                    <div class="col-md-6">
                                        <label for="gender" class="form-label"><strong>Gender</strong></label>
                                        <input type="text" class="form-control" id="gender" value="${employee.gender}" readonly>
                                    </div>
                                    <div class="col-md-6">
                                        <label for="address" class="form-label"><strong>Address</strong></label>
                                        <input type="text" class="form-control" id="address" value="${employee.address}" readonly>
                                    </div>
                                </div>
                                <div class="row mb-3">
                                    <div class="col-md-2">
                                        <label for="status" class="form-label"><strong>Status</strong></label>
                                        <input type="text" class="form-control" id="status" value="${employee.status}" readonly>
                                    </div>
                                    <div class="col-md-2">
    <label for="roleName" class="form-label"><strong>Role</strong></label>
    
    <c:choose>
        <c:when test="${employee.roleId == 1}">
            <c:set var="roleName" value="Admin" />
        </c:when>
        <c:when test="${employee.roleId == 2}">
            <c:set var="roleName" value="Sales" />
        </c:when>
        <c:when test="${employee.roleId == 3}">
            <c:set var="roleName" value="Marketing" />
        </c:when>
        <c:when test="${employee.roleId == 4}">
            <c:set var="roleName" value="Sale Manager" />
        </c:when>
        <c:when test="${employee.roleId == 5}">
            <c:set var="roleName" value="Warehouse Staff" />
        </c:when>
        <c:otherwise>
            <c:set var="roleName" value="Unknown Role" />
        </c:otherwise>
    </c:choose>
    
    <input type="text" class="form-control" id="roleName" value="${roleName}" readonly>
</div>
    
                                </div>
                                     

                                <div class="mb-3">
                                    <a href="userlist" class="btn btn-secondary">Back to employee List</a>
                                </div>
                                <div class="mb-3">
                                    <a href="editUserForm?employeeID=${employee.employeeID}" class="btn btn-warning btn-sm">Update</a>
                                </div>
                            </div>
                        </div>

                        <!-- Changes History Table -->
                        
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
