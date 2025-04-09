<%-- 
    Document   : mkt-update-sell-price
    Created on : Nov 6, 2024, 9:32:01 AM
    Author     : sontu
--%>

<%-- 
    Document   : mkt-add-new-size
    Created on : Oct 27, 2024, 10:42:54 PM
    Author     : sontu
--%>

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
        <title>Marketing - Update Sell Price</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
        <link href="css/styles.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
        <style>
            .ck-editor__editable_inline {
                min-height: 200px; /* Chiều cao tối thiểu */
            }

        </style>
    </head>
    <body class="sb-nav-fixed">
        <jsp:include page="mkt-header.jsp"></jsp:include>
            <div id="layoutSidenav">
            <jsp:include page="mkt-sidenav.jsp"></jsp:include>
                <div id="layoutSidenav_content">
                    <main>
                        <div class="container-fluid px-4">
                            <h1 class="mt-4">Update Sell Price</h1>
                            <ol class="breadcrumb mb-4">
                                <li class="breadcrumb-item"><a href="mkt-home.jsp">Dashboard</a></li>
                                <li class="breadcrumb-item"><a href="list_product_mkt">Products List</a></li>
                                <li class="breadcrumb-item active">Update Sell Price</li>
                            </ol>
                            <div class="card mb-4">
                                <div class="card-header">
                                    <i class="fas fa-user-edit me-1"></i>
                                    Update Sell Price
                                </div>

                                <div class="card-body">
                                    <form action="mkt-update-sell-price" method="post">
                                        <input type="hidden" name="productDetailID" value="${productDetailforMKT.productDetailID}">
                                    <input type="hidden" name="productID" value="${productDetailforMKT.productID}">
                                    <input type="hidden" name="sizeName" value="${productDetailforMKT.sizeName}">
                                    <div class="row mb-3">
                                        <div class="row mb-3">
                                            <div class="col-md-12">
                                                <label for="size" class="form-label"><strong>Size</strong></label>
                                                <div id="size-buttons">
                                                    <c:forEach var="size" items="${avalibleSizeNameByID}">
                                                        <a href="mkt-change-size-update-sell-price?productID=${productDetailforMKT.productID}&size=${size.sizeName}" 
                                                           class="size-btn ${productDetailforMKT.sizeName eq size.sizeName ? 'active' : ''}">
                                                            ${size.sizeName}
                                                        </a>
                                                    </c:forEach>
                                                </div>
                                                <input type="hidden" name="sizeName" id="size-input" value="${productDetailforMKT.sizeName}">                                            
                                            </div>
                                        </div>                                     
                                    </div>
                                    <div class="row mb-3">
                                        <div class="col-md-12">
                                            <label for="originalPrice" class="form-label"><strong>Original Price</strong></label>
                                            <input type="number" class="form-control" id="originalPrice" name="originalPrice" value="${productDetailforMKT.originalPrice}" readonly>
                                        </div>
                                    </div>
                                    <div class="row mb-3">
                                        <div class="col-md-12">
                                            <label for="sellPrice" class="form-label"><strong>Sell Price</strong></label>
                                            <input type="number" class="form-control" id="sellPrice" name="sellPrice" value="${productDetailforMKT.sellPrice}" readonly>
                                        </div>
                                    </div>
                                    <div class="row mb-3">
                                        <div class="col-md-12">
                                            <label for="newSellPrice" class="form-label"><strong> New Sell Price</strong></label>
                                            <input type="number" class="form-control" id="newSellPrice" name="newSellPrice" value="newSellPrice">
                                        </div>
                                    </div>
                                    <div class="mb-3">
                                        <button type="submit" class="btn btn-primary">Update</button>
                                        <a href="list_product_mkt" class="btn btn-secondary">Cancel</a>
                                    </div>
                                </form>
                            </div>

                        </div>
                    </div>
                </main>
                <style>
                    .button.primary-btn {
                        background-color: #384AEB; /* Màu nền */
                        margin-top: 10px;
                        color: white; /* Màu chữ */
                        border: none; /* Loại bỏ viền */
                        padding: 10px 20px; /* Khoảng cách bên trong nút */
                        border-radius: 5px; /* Bo góc */
                        font-size: 16px; /* Kích thước chữ */
                        cursor: pointer; /* Đổi con trỏ khi hover */
                        transition: background-color 0.3s ease; /* Hiệu ứng khi hover */
                    }

                    .button.primary-btn:hover {
                        background-color: #2b3bb1; /* Màu nền khi hover */
                    }
                    .size-btn {
                        border: 1px solid #ddd;
                        padding: 10px;
                        cursor: pointer;
                        margin-right: 5px;
                        background-color: #f9f9f9;
                    }

                    .size-btn.active {
                        background-color: #4CAF50;
                        color: white;
                        border-color: #4CAF50;
                    }

                </style>
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
        <script>
            document.querySelectorAll('.size-btn').forEach(function (button) {
                button.addEventListener('click', function () {
                    // Xóa class 'active' khỏi tất cả các nút
                    document.querySelectorAll('.size-btn').forEach(function (btn) {
                        btn.classList.remove('active');
                    });
                    // Thêm class 'active' vào nút được chọn
                    button.classList.add('active');
                    // Cập nhật giá trị của size vào input ẩn
                    document.getElementById('size-input').value = button.getAttribute('data-size');
                });
            });

        </script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="js/scripts.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
        <script src="js/datatables-simple-demo.js"></script>
        <script src="https://cdn.ckeditor.com/ckeditor5/36.0.1/classic/ckeditor.js"></script>       
    </body>
</html>


