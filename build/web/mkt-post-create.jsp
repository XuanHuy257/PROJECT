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
        <title>Marketing - Post Add</title>
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
                            <h1 class="mt-4">Create Post</h1>
                            <ol class="breadcrumb mb-4">
                                <li class="breadcrumb-item"><a href="mkt-home.jsp">Dashboard</a></li>
                                <li class="breadcrumb-item"><a href="posts">Post List</a></li>
                                <li class="breadcrumb-item active">Create Post</li>
                            </ol>
                            <div class="card mb-4">
                                <div class="card-header">
                                    <i class="fas fa-user-edit me-1"></i>
                                    Create Post
                                </div>

                                <div class="card-body">
                                    <form action="create-post" method="post" enctype="multipart/form-data">
                                        <div class="row mb-3">
                                            <div class="col-md-12">
                                            <label for="title" class="form-label"><strong>Title</strong></label>
                                            <input type="text" class="form-control" id="title" name="title" required="" >
                                        </div>

                                    </div>
                                    <div class="row mb-3">
                                        <div class="col-md-12">
                                            <label for="category" class="form-label"><strong> Post Category</strong></label>
                                            <select class="form-control" id="category" name="category" required>
                                                <c:forEach items="${blogCategories}" var="bc">
                                                    <option value="${bc.postCategoryID}" ${bc.postCategoryID == post.postCategoryID ? 'selected' : ''}>${bc.postCategoryName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="row mb-3">

                                        <div class="col-md-12">
                                            <label for="thumbnail" class="form-label"><strong>Thumbnail</strong> </label>
                                            <input type="file" class="form-control" id="thumbnail" name="thumbnail" required onchange="previewImage(this)">
                                        </div>
                                        <div class="form-group">
                                            <img src="" alt="Image" class="img-preview" id="img-preview-id"
                                                 style="width: 200px; border: 1px solid #ddd; border-radius: 5px;" />
                                        </div>
                                    </div>
                                    <div class="row mb-3">  
                                        <div class="col-md-12">
                                            <label for="postBrief" class="form-label"> <strong> Post Brief </strong></label>
                                            <textarea type="text" class="form-control" id="postBrief" name="postBrief" rows="3" required></textarea>
                                        </div>

                                    </div>

                                    <div class="row mb-3">  
                                        <div class="col-md-12">
                                            <label for="postContent" class="form-label"> <strong> Post Content </strong></label>
                                            <textarea type="text" class="form-control" id="postContent" name="postContent" rows="30"></textarea>
                                        </div>

                                    </div>

                                    <div class="mb-3">
                                        <button type="submit" class="btn btn-success">Create</button>
                                        <a href="posts" class="btn btn-secondary">Cancel</a>
                                    </div>
                                </form>
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
            document.addEventListener('DOMContentLoaded', function () {
                const preview = document.getElementById('img-preview-id');
                preview.style.display = "none";
            });
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
        </script>
    </body>
</html>
