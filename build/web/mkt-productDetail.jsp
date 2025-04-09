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
        <title>Marketing - Feedback Detail</title>
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
                            <h1 class="mt-4">Product Detail</h1>
                            <ol class="breadcrumb mb-4">
                                <li class="breadcrumb-item"><a href="mkt-home.jsp">Dashboard</a></li>
                                <li class="breadcrumb-item"><a href="list_product_mkt">Products List</a></li>
                                <li class="breadcrumb-item active">Product Detail</li>
                            </ol>
                            <div class="card mb-4">
                                <div class="card-header">
                                    <i class="fas fa-user-edit me-1"></i>
                                    Product Information
                                </div>

                                <div class="card-body">
                                    <form action="mkt_update_product" method="post" enctype="multipart/form-data">
                                        <div class="row mb-3">
                                            <div class="col-md-12">
                                                <input type="hidden" class="form-control" id="productID" name="id" value="${productDetailforMKT.productID}" readonly >
                                            <label for="title" class="form-label"><strong>Title</strong></label>
                                            <input type="text" class="form-control" id="title" name="title" value="${productDetailforMKT.title}">
                                        </div>
                                    </div>

                                    <div class="row mb-3">
                                        <div class="col-md-12">
                                            <label for="briefInfo" class="form-label"><strong>Brief Information</strong></label>
                                            <input type="text" class="form-control" id="briefInfo" name="briefInfo" value="${productDetailforMKT.briefInfo}">
                                        </div>
                                    </div>

                                    <div class="row mb-3">
                                        <div class="col-md-12">
                                            <label for="description" class="form-label"><strong>Description</strong></label>
                                            <input type="text" class="form-control" id="description" name="description" value="${productDetailforMKT.description}">
                                        </div>
                                    </div>

                                    <div class="row mb-3">
                                        <div class="col-md-12">
                                            <label for="thumbnail" class="form-label"><strong>Thumbnail</strong></label>
                                            <img id="previewThumbnail" src="${productDetailforMKT.thumbnail}" alt="thumbnail" style="width: 100px; height: auto;">
                                            <input type="file" class="form-control" id="thumbnail" name="thumbnail" onchange="previewImage(event, 'previewThumbnail')">
                                        </div>
                                    </div>

                                    <div class="row mb-3">
                                        <div class="col-md-12">
                                            <label for="img1" class="form-label"><strong>Img 1</strong></label>
                                            <img id="previewImg1" src="${productDetailforMKT.img1}" alt="Image 1" style="width: 100px; height: auto;">
                                            <input type="file" class="form-control" id="img1" name="img1" onchange="previewImage(event, 'previewImg1')">
                                        </div>
                                    </div>

                                    <div class="row mb-3">
                                        <div class="col-md-12">
                                            <label for="img2" class="form-label"><strong>Img 2</strong></label>
                                            <img id="previewImg2" src="${productDetailforMKT.img2}" alt="Image 2" style="width: 100px; height: auto;">
                                            <input type="file" class="form-control" id="img2" name="img2" onchange="previewImage(event, 'previewImg2')">
                                        </div>
                                    </div>

                                    <div class="row mb-3">
                                        <div class="col-md-12">
                                            <label for="img3" class="form-label"><strong>Img 3</strong></label>
                                            <img id="previewImg3" src="${productDetailforMKT.img3}" alt="Image 3" style="width: 100px; height: auto;">
                                            <input type="file" class="form-control" id="img3" name="img3" onchange="previewImage(event, 'previewImg3')">
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
                                            <label for="discount" class="form-label"><strong>Discount</strong></label>
                                            <input type="number" class="form-control" id="discount" name="discount" value="${productDetailforMKT.discount}" readonly>
                                        </div>
                                    </div>

                                    <div class="row mb-3">
                                        <div class="col-md-12">
                                            <label for="feature" class="form-label"><strong>Feature</strong></label>
                                            <select class="form-control" id="feature" name="feature">
                                                <c:forEach var="f" items="${allFeature}">
                                                    <option value="${f.featureName}">${f.featureName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="row mb-3">
                                        <div class="col-md-12">
                                            <label for="size" class="form-label"><strong>Size</strong></label>
                                            <div id="size-buttons">
                                                <c:forEach var="size" items="${avalibleSizeNameByID}">
                                                    <a href="mkt_change_product_size?productID=${productDetailforMKT.productID}&size=${size.sizeName}" 
                                                       class="size-btn ${productDetailforMKT.sizeName eq size.sizeName ? 'active' : ''}">
                                                        ${size.sizeName}
                                                    </a>
                                                </c:forEach>
                                            </div>
                                            <input type="hidden" name="sizeName" id="size-input" value="${productDetailforMKT.sizeName}">
                                        </div>
                                    </div>

                                    <div class="row mb-3">
                                        <div class="col-md-12">
                                            <label for="quantity" class="form-label"><strong>Quantity</strong></label>
                                            <input type="text" class="form-control" id="quantity" name="quantity" value="${productDetailforMKT.quantity}" readonly>
                                        </div>
                                    </div>

                                    <div class="row mb-3">
                                        <div class="col-md-12">
                                            <label for="hold" class="form-label"><strong>Hold</strong></label>
                                            <input type="text" class="form-control" id="hold" name="hold" value="${productDetailforMKT.hold}" readonly>
                                        </div>

                                        <div class="row mb-3">
                                            <div class="col-md-12">
                                                <label for="category" class="form-label"><strong>Category</strong></label>
                                                <input type="text" class="form-control" id="category" name="category" value="${productDetailforMKT.category}" readonly>
                                            </div>
                                        </div>

                                        <div class="row mb-3">
                                            <div class="col-md-12">
                                                <label for="brand" class="form-label"><strong>Brand</strong></label>
                                                <input type="text" class="form-control" id="brand" name="brand" value="${productDetailforMKT.brand}" readonly>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row mb-3">
                                        <div class="col-md-12">
                                            <label for="status" class="form-label"><strong>Status</strong></label>
                                            <select class="form-control" id="status" name="status" required>
                                                <option value="Active" ${productDetailforMKT.status == 'Active' ? 'selected' : ''}>Active</option>
                                                <option value="InActive" ${productDetailforMKT.status == 'InActive' ? 'selected' : ''}>InActive</option>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="mb-3">
                                        <button type="submit" class="btn btn-primary">Save Changes</button>
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
        <script>

                                                ClassicEditor
                                                        .create(document.querySelector('#postContent'))
                                                        .catch(error => {
                                                            console.error(error);
                                                        });
        </script>
        <script>
            function previewImage(input) {
                const preview = document.getElementById('img-preview-id');

                // Kiểm tra xem có file được chọn hay không
                if (input.files && input.files[0]) {
                    // Hiển thị preview của hình ảnh
                    preview.src = window.URL.createObjectURL(input.files[0]);
                    preview.style.display = "block"; // Hiển thị ảnh
                } else {
                    preview.style.display = "none"; // Ẩn ảnh nếu không có file nào
                }
            }
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
        <script>
            function previewImage(event, previewId) {
                const preview = document.getElementById(previewId);
                preview.src = URL.createObjectURL(event.target.files[0]);
                preview.onload = function () {
                    URL.revokeObjectURL(preview.src); // Giải phóng bộ nhớ
                }
            }
        </script>
    </body>
</html>
