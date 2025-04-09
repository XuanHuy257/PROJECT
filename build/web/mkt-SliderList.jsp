<%-- 
    Document   : mkt-SliderList
    Created on : Nov 2, 2024, 6:35:55 AM
    Author     : sontu
--%>

<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Marketing - Products</title>
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
                            <h1 class="mt-4">List of Products</h1>
                            <ol class="breadcrumb mb-4">
                                <li class="breadcrumb-item"><a href="mkt-home.jsp">Dashboard</a></li>
                                <li class="breadcrumb-item active">Sliders</li>
                            </ol>

                            <div class="card mb-4">
                                <div class="card-header">
                                    <i class="fas fa-table me-1"></i>
                                    List of Sliders
                                    <h3 style="color: red">${mess}</h3>
                                <a href="mkt-option-add-product" class="btn btn-success btn-sm float-end">Add New Slider</a>
                            </div>
                            <div class="card-body">
                                <table id="sliderTable">
                                    <thead>
                                        <tr>
                                            <th>Slider ID</th>
                                            <th>Image</th>
                                            <th>
                                                Title
                                            </th>
                                            <th>Status</th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="l" items="${listAllSlider}">
                                            <tr>
                                                <td>${l.sliderID}</td>
                                                <td><img src="${l.img}" alt="alt"/></td>

                                                <td>${l.title}</td>
                                                <td><c:choose>
                                                        <c:when test="${l.status == 1}">
                                                            Active
                                                        </c:when>
                                                        <c:otherwise>
                                                            Inactive
                                                        </c:otherwise>
                                                    </c:choose></td>
                                                <td><a href="changeSliderStatus?sliderID=${l.sliderID}&status=${l.status}" class="btn btn-info">Change Status</a> <br>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <style>
                                /* Đặt kích thước cố định cho ảnh và canh giữa */
                                table img {
                                    width: 80px; /* hoặc tùy chỉnh kích thước phù hợp */
                                    height: auto;
                                    display: block;
                                    margin-left: auto;
                                    margin-right: auto;
                                    border-radius: 8px; /* Bo góc hình ảnh */
                                    object-fit: cover; /* Đảm bảo hình ảnh luôn giữ tỉ lệ và vừa với khung */
                                }

                                /* Điều chỉnh bảng để không bị tràn */
                                table {
                                    width: 100%;
                                    border-collapse: collapse;
                                }

                                table th, table td {
                                    padding: 12px;
                                    text-align: center; /* Căn giữa các nội dung trong ô */
                                    vertical-align: middle; /* Đảm bảo nội dung căn giữa theo chiều dọc */
                                }

                                table th {
                                    background-color: #f8f9fa; /* Màu nền cho tiêu đề */
                                }

                                table td {
                                    border-bottom: 1px solid #ddd; /* Đường viền giữa các hàng */
                                }

                                /* Tạo hiệu ứng khi hover vào hàng */
                                table tbody tr:hover {
                                    background-color: #f1f1f1;
                                }

                                /* Tạo style cho các nút */
                                .btn {
                                    padding: 5px 10px;
                                    border-radius: 5px;
                                    text-decoration: none;
                                    font-size: 14px;
                                }

                                /* Style cho nút View */
                                .btn-info {
                                    background-color: #17a2b8;
                                    color: white;
                                }

                                /* Style cho nút Active/Inactive */
                                .btn-success {
                                    background-color: #28a745;
                                    color: white;
                                }

                                .btn-danger {
                                    background-color: #dc3545;
                                    color: white;
                                }

                            </style>
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
        <script src="js/sliderTable.js"></script>
        <script>
            function filterTable() {
                var statusFilter = document.getElementById("statusFilter").value;
                var categoryFilter = document.getElementById("categoryFilter").value;

                window.location.href = "list_product_mkt?status=" + statusFilter + "&category=" + categoryFilter;
            }

        </script>

    </body>
</html>

