<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Settings - Admin Panel</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
        <link href="css/styles.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
    </head>
    <body class="sb-nav-fixed">
        <nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
            <!-- Navbar Brand-->
            <a class="navbar-brand ps-3" href="AdminDashBoard">Admin</a>
            <!-- Sidebar Toggle-->
            <button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle"><i class="fas fa-bars"></i></button>
            <!-- Navbar-->
            <form class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0">
                <div class="input-group">


                </div>
            </form>
            <ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="settinglist">Settings</a></li>
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
                            <a class="nav-link" href="settinglist">
                                <div class="sb-nav-link-icon"><i class="fas fa-cog"></i></div>
                                Settings
                            </a>
                        </div>
                    </div>
                    <div class="sb-sidenav-footer">
                        <div class="small">Logged in as:</div>
                        Admin
                    </div>
                </nav>
            </div>

            <div id="layoutSidenav_content">
                <main>
                    
                    <div class="container-fluid px-4">
                        <h1 class="mt-4">Settings List</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item"><a href="AdminDashBoard">Dashboard</a></li>
                            <li class="breadcrumb-item active">Settings</li>
                        </ol>

                        <div class="card mb-4">
                            <div class="card-header">
                                <i class="fas fa-cog me-1"></i>
                                Settings List
                                <!-- Add New Setting Button -->
                                <a href="addSetting" class="btn btn-success btn-sm float-end">Add New Setting</a>
                            </div>

                            <div class="card-body">
                                <table id="datatablesSimple">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>
                                                <a href="?sortBy=type&order=${param.order == 'asc' ? 'desc' : 'asc'}">Type</a>
                                            </th>
                                            <th>
                                                <a href="?sortBy=value&order=${param.order == 'asc' ? 'desc' : 'asc'}">Value</a>
                                            </th>
                                            <th>
                                                <a href="?sortBy=order&order=${param.order == 'asc' ? 'desc' : 'asc'}">Order</a>
                                            </th>
                                            <th>Status</th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <!-- Loop through the settings list -->
                                        <c:forEach var="setting" items="${settingsList}">
                                            <tr>
                                                <td>${setting.id}</td>
                                                <td>${setting.type}</td>
                                                <td>${setting.value}</td>
                                                <td>${setting.order}</td>
                                                <td>
                                                    <c:choose>
                                                        <c:when test="${setting.status}">
                                                            Active
                                                        </c:when>
                                                        <c:otherwise>
                                                            Inactive
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                                <td>
                                                    <!-- View Button -->
                                                    <a href="ViewDetailSetting?sid=${setting.id}" class="btn btn-info btn-sm">View</a>

                                                    <!-- Edit/Update Button -->
                                                    <a href="editSettingForm?sid=${setting.id}" class="btn btn-warning btn-sm">Update</a>
<form action="DeleteSetting" method="post" style="display:inline;">
        <input type="hidden" name="id" value="${setting.id}">
        <button type="submit" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete this setting?');">
            Delete
        </button>
    </form>

                                                    <!-- Activate/Deactivate Button -->
                                                    <form action="ToggleSettingStatusServlet" method="post" style="display:inline;">
                                                        <input type="hidden" name="id" value="${setting.id}">
                                                        <button type="submit" class="btn btn-secondary btn-sm">
                                                            <c:choose>
                                                                <c:when test="${setting.status}">
                                                                    Deactivate
                                                                </c:when>
                                                                <c:otherwise>
                                                                    Activate
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </button>
                                                    </form>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
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
