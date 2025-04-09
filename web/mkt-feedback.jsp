<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Marketing - Feedback</title>
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
                            <h1 class="mt-4">Feedback List</h1>
                            <ol class="breadcrumb mb-4">
                                <li class="breadcrumb-item"><a href="mkt-home.jsp">Dashboard</a></li>
                                <li class="breadcrumb-item active">Tables</li>
                            </ol>

                            <div class="card mb-4">
                                <div class="card-header">
                                    <i class="fas fa-table me-1"></i>
                                    Feedback List
                                </div>
                                <div class="card-body">

                                    <div class="row mb-3">            
                                        <div class="col-md-4">
                                            <label for="statusFilter" class="form-label">Filter by Status</label>
                                            <select id="statusFilter" class="form-select" name="status" onchange="filterTable()">
                                                <option value="">All</option>
                                                <option value="1" <c:if test="${status == '1'}">selected</c:if>>Active</option>
                                            <option value="0" <c:if test="${status == '0'}">selected</c:if>>InActive</option>
                                            </select>
                                        </div>

                                        <div class="col-md-4">
                                            <label for="productFilter" class="form-label">Filter by Product</label>
                                            <select id="productFilter" class="form-select" name="product" onchange="filterTable()">
                                                <option value="">All</option>
                                            <c:forEach items="${products}" var="a">
                                                <option value="${a.productID}" <c:if test="${a.productID == product}">selected</c:if>>${a.title}</option>

                                            </c:forEach>
                                        </select>
                                    </div>

                                    <div class="col-md-4">
                                        <label for="starFilter" class="form-label">Filter by Rated Star</label>
                                        <select id="starFilter" class="form-select" name="star" onchange="filterTable()">
                                            <option value="">All</option>
                                            <option value="1" <c:if test="${star == '1'}">selected</c:if>>1</option>
                                            <option value="2" <c:if test="${star == '2'}">selected</c:if>>2</option>
                                            <option value="3" <c:if test="${star == '3'}">selected</c:if>>3</option>
                                            <option value="4" <c:if test="${star == '4'}">selected</c:if>>4</option>
                                            <option value="5" <c:if test="${star == '5'}">selected</c:if>>5</option>
                                            </select>
                                        </div>
                                    </div>
                                    <table id="datatablesSimple">
                                        <thead>
                                            <tr>
                                                <th>Full Name</th>
                                                <th>Product Name</th>
                                                
                                                <th>
                                                    Rated Star
                                                </th>
                                                 <th>Content</th>
                                                <th>Status</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="b" items="${feedbacks}">
                                            <tr>
                                                <td>${b.fullName}</td>
                                                <td>${b.productName}</td>
                                               
                                                <td>${b.rating}</td>
                                                 <td>${b.feedbackText}</td>
                                                <td>${b.status == false ? 'InActive' : 'Active'}</td>


                                                <td>
                                                    <a href="mkt-feedback-detail?id=${b.feedbackID}" class="btn btn-info btn-sm">View</a>
                                                    <a href="mkt-change-status-feedback?id=${b.feedbackID}" class="btn ${b.status == true ? 'btn-danger' : 'btn-success'} btn-sm">${b.status == true ? 'InActive' : 'Active'}</a>
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
                            <div class="text-muted">Copyright &copy; Your Website 2024</div>
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
        <script src="js/datatables-simple-feedback-custom.js"></script>
        <script>
                                            function filterTable() {
                                                var statusFilter = document.getElementById("statusFilter").value;
                                                var productFilter = document.getElementById("productFilter").value;
                                                var starFilter = document.getElementById("starFilter").value;

                                                window.location.href = "mkt-feedbacks?status=" + statusFilter + "&product=" + productFilter + "&star=" + starFilter;
                                            }

        </script>

    </body>
</html>
