<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Marketing - Post</title>
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
                            <h1 class="mt-4">Post List</h1>
                            <ol class="breadcrumb mb-4">
                                <li class="breadcrumb-item"><a href="mkt-home.jsp">Dashboard</a></li>
                                <li class="breadcrumb-item active">Tables</li>
                            </ol>

                            <div class="card mb-4">
                                <div class="card-header">
                                    <i class="fas fa-table me-1"></i>
                                    Post List
                                    <a href="create-post" class="btn btn-success btn-sm float-end">Add New Post</a>
                                </div>
                                <div class="card-body">

                                    <div class="row mb-3">            
                                        <div class="col-md-4">
                                            <label for="categoryFilter" class="form-label">Filter by Category</label>
                                            <select id="categoryFilter" class="form-select" onchange="filterTable()">
                                                <option value="">All</option>
                                            <c:forEach items="${blogCategories}" var="c">
                                                <option value="${c.postCategoryName}">${c.postCategoryName}</option>
                                            </c:forEach>
                                        </select>
                                    </div>

                                    <div class="col-md-4">
                                        <label for="authorFilter" class="form-label">Filter by Author</label>
                                        <select id="authorFilter" class="form-select" onchange="filterTable()">
                                            <option value="">All</option>
                                            <c:forEach items="${authors}" var="a">
                                                <option value="${a.fullName}">${a.fullName}</option>
                                            </c:forEach>
                                        </select>
                                    </div>

                                    <div class="col-md-4">
                                        <label for="statusFilter" class="form-label">Filter by Status</label>
                                        <select id="statusFilter" class="form-select" onchange="filterTable()">
                                            <option value="">All</option>
                                            <option value="Active">Active</option>
                                            <option value="Inactive">Inactive</option>
                                        </select>
                                    </div>
                                </div>
                                <table id="datatablesSimple">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Thumbnail</th>
                                            <th>
                                                Title
                                            </th>
                                            <th>Category</th>
                                            <th>
                                                Author
                                            </th>
                                            <th>Featured</th>
                                            <th>
                                                Status
                                            </th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="b" items="${blogs}">
                                            <tr>
                                                <td>${b.postID}</td>
                                                <td><img src="${b.postImg}" alt="postImg" width="50" height="50"></td>
                                                <td>${b.title}</td>
                                                <td>${b.postCategoryName}</td>
                                                <td>${b.userName}</td>
                                                <td>${b.postFlag == false ? 'Show' : 'Hide'}</td>
                                                <td>
                                                    ${b.status}
                                                </td>

                                                <td>
                                                    <a href="post-detail?id=${b.postID}" class="btn btn-info btn-sm">View</a>
                                                    <a href="post-detail?id=${b.postID}" class="btn btn-warning btn-sm">Edit</a>
                                                    <a href="change-featured?id=${b.postID}" class="btn ${b.postFlag == false ? 'btn-danger' : 'btn-success'} btn-sm">${b.postFlag == false ? 'Hide' : 'Show'}</a>
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
        <script src="js/datatables-simple-post-custom.js"></script>
        <script>
                                            function filterTable() {
                                                var categoryFilter = document.getElementById("categoryFilter").value;
                                                var authorFilter = document.getElementById("authorFilter").value;
                                                var statusFilter = document.getElementById("statusFilter").value;


                                                var rows = document.querySelectorAll("#datatablesSimple tbody tr");

                                                rows.forEach(function (row) {
                                                    var category = row.querySelector('td:nth-child(4)').textContent.trim();
                                                    var author = row.querySelector('td:nth-child(5)').textContent.trim();
                                                    var status = row.querySelector('td:nth-child(7)').textContent.trim();

                                                    var matchCategory = (categoryFilter === "" || category === categoryFilter);
                                                    var matchAuthor = (authorFilter === "" || author === authorFilter);
                                                    var matchStatus = (statusFilter === "" || status === statusFilter);

                                                    if (matchCategory && matchAuthor && matchStatus) {
                                                        row.style.display = "";
                                                    } else {
                                                        row.style.display = "none";
                                                    }
                                                });
                                            }

        </script>

    </body>
</html>
