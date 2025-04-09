<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Tables - SB Admin</title>
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
                            <h1 class="mt-4">Customer List</h1>
                            <ol class="breadcrumb mb-4">
                                <li class="breadcrumb-item"><a href="mkt-home">Dashboard</a></li>
                                <li class="breadcrumb-item active">Tables</li>
                            </ol>

                            <div class="card mb-4">
                                <div class="card-body">
                                    <div class="mb-3">
                                        <label for="statusFilter" class="form-label">Filter by Status</label>
                                        <select id="statusFilter" class="form-select" onchange="filterByStatus()">
                                            <option value="">All</option>
                                            <option value="Active">Active</option>
                                            <option value="Inactive">Inactive</option>
                                            <option value="Pending">Pending</option>
                                            <!-- Add more statuses as needed -->
                                        </select>
                                    </div>

                                    <table id="datatablesSimple">
                                        <thead>
                                            <tr>
                                                <th>ID</th>
                                                <th>
                                                    <a href="?sortBy=name&order=${param.order == 'asc' ? 'desc' : 'asc'}">FullName</a>
                                            </th>
                                            <th>
                                                <a href="?sortBy=email&order=${param.order == 'asc' ? 'desc' : 'asc'}">Email</a>
                                            </th>
                                            <th>Gender</th>
                                            <th>
                                                <a href="?sortBy=phoneNumber&order=${param.order == 'asc' ? 'desc' : 'asc'}">PhoneNumber</a>
                                            </th>
                                            <th>Address</th>
                                            <th>Avatar</th>
                                            <th>Action</th>
                                            <th>
                                                <a href="?sortBy=status&order=${param.order == 'asc' ? 'desc' : 'asc'}">Status</a>
                                            </th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="customer" items="${customerList}">
                                            <tr>
                                                <td>${customer.customerID}</td>
                                                <td>${customer.fullName}</td>
                                                <td>${customer.email}</td>
                                                <td>${customer.gender}</td>
                                                <td>${customer.phoneNumber}</td>
                                                <td>${customer.address}</td>
                                                <td><img src="${customer.avatar}" alt="Avatar" width="50" height="50"></td>

                                                <td>
                                                    <a href="viewCustomerDetail?cid=${customer.customerID}" class="btn btn-info btn-sm">View</a>
                                                    <a href="editCustomerForm?customerID=${customer.customerID}" class="btn btn-warning btn-sm">Update</a>
                                                    <form action="deleteCustomer" method="post" style="display:inline;">
                                                        <input type="hidden" name="id" value="${customer.customerID}">
                                                        <button type="submit" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete this customer?');">Delete</button>
                                                    </form>
                                                </td>

                                                <td>
                                                    ${customer.status}
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
        <script>
                                                            function searchTable() {
                                                                var input, filter, table, tr, td, i, txtValue;
                                                                input = document.getElementById("searchInput");
                                                                filter = input.value.toLowerCase();
                                                                table = document.getElementById("datatablesSimple");
                                                                tr = table.getElementsByTagName("tr");
                                                                for (i = 1; i < tr.length; i++) {
                                                                    tr[i].style.display = "none";
                                                                    td = tr[i].getElementsByTagName("td");
                                                                    for (var j = 0; j < td.length; j++) {
                                                                        if (td[j]) {
                                                                            txtValue = td[j].textContent || td[j].innerText;
                                                                            if (txtValue.toLowerCase().indexOf(filter) > -1) {
                                                                                tr[i].style.display = "";
                                                                                break;
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
        </script>
        <script>
            function filterByStatus() {
                var select = document.getElementById("statusFilter");
                var filter = select.value;
                var rows = document.querySelectorAll(".customerRow");

                rows.forEach(function (row) {
                    var status = row.getAttribute("data-status");
                    if (filter === "" || status === filter) {
                        row.style.display = ""; // Show the row
                    } else {
                        row.style.display = "none"; // Hide the row
                    }
                });
            }
        </script>

    </body>
</html>
