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
        <title>Marketing - Add New Size</title>
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
                            <h1 class="mt-4">Add Product Size</h1>
                            <ol class="breadcrumb mb-4">
                                <li class="breadcrumb-item"><a href="mkt-home.jsp">Dashboard</a></li>
                                <li class="breadcrumb-item"><a href="list_product_mkt">Products List</a></li>
                                <li class="breadcrumb-item active">Add Product Size</li>
                            </ol>
                            <div class="card mb-4">
                                <div class="card-header">
                                    <i class="fas fa-user-edit me-1"></i>
                                    Add New Product
                                </div>

                                <div class="card-body">
                                    <form action="mkt-add-new-size" method="post">
                                        <input type="hidden" name="productID" value="${productID}">
                                    <div class="row mb-3">
                                        <div class="row mb-3">
                                            <div class="col-md-12">
                                                <p for="sizeName" class="form-label"><strong>Available Size</strong></p>                                                
                                                <p>${avalibleSizeName}</p>                                               
                                            </div>
                                        </div>                                     
                                    </div>
                                    <div class="row mb-3">
                                        <div class="col-md-12">
                                            <label for="sizeName" class="form-label"><strong>Size</strong></label>
                                            <select class="form-control" id="size" name="size">
                                                <c:forEach var="s" items="${allSize}">
                                                    <option value="${s.sizeName}">${s.sizeName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="mb-3">
                                        <button type="submit" class="btn btn-primary">Add Size</button>
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
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="js/scripts.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
        <script src="js/datatables-simple-demo.js"></script>
        <script src="https://cdn.ckeditor.com/ckeditor5/36.0.1/classic/ckeditor.js"></script>       
    </body>
</html>

