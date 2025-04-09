<%-- 
    Document   : OrderCustomer
    Created on : Oct 14, 2024, 5:10:44 PM
    Author     : sontu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Five Castles Shop - Order</title>
        <link rel="icon" href="img/Fevicon.png" type="image/png">
        <link rel="stylesheet" href="vendors/bootstrap/bootstrap.min.css">
        <link rel="stylesheet" href="vendors/fontawesome/css/all.min.css">
        <link rel="stylesheet" href="vendors/themify-icons/themify-icons.css">
        <link rel="stylesheet" href="vendors/linericon/style.css">
        <link rel="stylesheet" href="vendors/owl-carousel/owl.theme.default.min.css">
        <link rel="stylesheet" href="vendors/owl-carousel/owl.carousel.min.css">
        <link rel="stylesheet" href="vendors/nice-select/nice-select.css">
        <link rel="stylesheet" href="vendors/nouislider/nouislider.min.css">
        <link rel="stylesheet" href="css/style.css">
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.css">

    </head>
    <body>
        <!--================ Start Header Menu Area =================-->
        <%@include  file="Header.jsp" %>
        <!--================ End Header Menu Area =================-->

        <!-- ================ start banner area ================= -->	
        <section class="blog-banner-area" id="category">
            <div class="container h-100">
                <div class="blog-banner">
                    <div class="text-center">
                        <h1>Orders</h1>
                        <nav aria-label="breadcrumb" class="banner-breadcrumb">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item"><a href="home">Home</a></li>
                            </ol>
                        </nav>
                    </div>
                </div>
            </div>
        </section>
        <!-- ================ end banner area ================= -->



        <!--================Cart Area =================-->
        <c:if test="${empty listOrderNow}">
            <div class="empty-cart-message">
                <p>You haven't bought anything yet</p>
            </div>
        </c:if>
        <style>
            .blog-banner-area {
                padding: 50px 0 !important; /* Giảm tối đa khoảng cách trên và dưới */
                height: auto !important; /* Đặt lại chiều cao auto để phù hợp với nội dung */
            }

            .blog-banner-area .blog-banner {
                height: 50px !important; /* Giảm thêm chiều cao tổng thể của banner */
            }

            .blog-banner-area h1 {
                font-size: 20px !important; /* Giảm kích thước chữ tiêu đề */
                margin-bottom: 5px !important; /* Giảm khoảng cách giữa tiêu đề và breadcrumb */
            }

            .banner-breadcrumb {
                font-size: 12px !important; /* Giảm kích thước chữ của breadcrumb */
            }

            .banner-breadcrumb .breadcrumb {
                margin-bottom: 0 !important; /* Loại bỏ khoảng cách dưới của breadcrumb */
            }


            .empty-cart-message {
                text-align: center;         /* Căn giữa chữ và nút */
                font-size: 36px;            /* Kích thước chữ lớn */
                font-weight: bold;          /* In đậm chữ */
                color: #333;                /* Màu chữ */
                margin-top: 50px;           /* Khoảng cách từ đầu trang */
                display: flex;
                flex-direction: column;     /* Căn theo chiều dọc */
                justify-content: center;    /* Căn giữa ngang */
                align-items: center;        /* Căn giữa dọc */
                height: 80vh;               /* Chiều cao phần thông báo */
            }

            .empty-cart-message a {
                display: inline-block;
                margin-top: 20px;           /* Khoảng cách giữa chữ và nút */
                padding: 15px 30px;         /* Kích thước nút */
                background-color: #007bff;  /* Màu nền của nút */
                color: white;               /* Màu chữ của nút */
                text-decoration: none;      /* Bỏ gạch chân */
                border-radius: 5px;         /* Bo tròn các góc nút */
                font-size: 18px;            /* Kích thước chữ trong nút */
                font-weight: bold;          /* In đậm chữ trong nút */
                transition: background-color 0.3s ease; /* Hiệu ứng chuyển màu khi hover */
            }

            .empty-cart-message a:hover {
                background-color: #0056b3;  /* Màu nền khi di chuột qua nút */
            }
            table img {
                width: 100px; /* Chiều rộng mong muốn, có thể thay đổi */
                height: auto; /* Đảm bảo giữ tỷ lệ ảnh */
                object-fit: cover; /* Giúp ảnh vừa với khung mà không bị méo */
            }
            .order_area {
                padding: 20px 0;
            }

            .order_inner {
                box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
                padding: 20px;
                background-color: #fff;
                border-radius: 10px;
            }

            .table {
                width: 100%;
                margin-bottom: 20px;
                border-collapse: collapse;
            }

            .table thead {
                background-color: #f8f9fa;
            }

            .table thead th {
                padding: 15px;
                text-align: left;
                font-weight: bold;
                border-bottom: 2px solid #dee2e6;
            }

            .table tbody td {
                padding: 15px;
                border-bottom: 1px solid #dee2e6;
                vertical-align: middle;
            }

            .table tbody tr:hover {
                background-color: #f1f1f1;
            }

            .table tbody tr:last-child td {
                border-bottom: none;
            }

            p {
                margin: 0;
            }

            @media (max-width: 768px) {
                .table thead {
                    display: none;
                }

                .table, .table tbody, .table tr, .table td {
                    display: block;
                    width: 100%;
                }

                .table td {
                    text-align: right;
                    padding-left: 50%;
                    position: relative;
                }

                .table td:before {
                    content: attr(data-label);
                    position: absolute;
                    left: 15px;
                    text-align: left;
                    font-weight: bold;
                }
                .dataTable-info,
                .dataTable-empty {
                    display: none !important; /* Ẩn dòng thông báo số lượng bản ghi và thông báo không có dữ liệu */
                }
            </style>

            }
        </style>
        <c:if test="${!empty listOrderNow}">
            <section class="cart_area">
                <div class="container">
                    <div class="cart_inner">
                        <div class="table-responsive">
                            <!-- Thêm id cho bảng -->
                            <table id="orderTable" class="table">
                                <thead>
                                    <tr>
                                        <th scope="col">ID</th>
                                        <th scope="col">Ordered Date</th>
                                        <th scope="col" style="text-align: center;">Products</th>
                                        <th scope="col">Order Status</th>
                                        <th scope="col" style="text-align: center;">Cancel Order</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${listOrderNow}" var="l">
                                        <tr>
                                            <td><a href="order_detail_customer?orderID=${l.orderID}" style="color: black">${l.orderID}</a></td>
                                            <td>${l.createdOrder}</td>
                                            <td><a href="product_order_customer?orderID=${l.orderID}" style="color: black">See All Products in this Orders</a></td>
                                            <td style="text-align: center;">${l.statusName}</td>
                                            <td style="text-align: center;">
                                                <c:if test="${l.statusName eq 'Pending'}">
                                                    <a href="cancel_order?orderID=${l.orderID}" class="btn btn-dark"> Cancel Order
                                                    </a>
                                                </c:if>
                                                <c:if test="${l.statusName ne 'Pending'}">
                                                    <p>You can only cancel if it's Pending!</p>
                                                </c:if>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </section>
        </c:if>

        <!-- Nhúng Simple Datatables và cấu hình phân trang -->
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
        <script>
            document.addEventListener('DOMContentLoaded', function () {
                const orderTable = document.querySelector("#orderTable");
                const dataTable = new simpleDatatables.DataTable(orderTable, {
                    perPage: 5,
                    perPageSelect: false,
                    searchable: false,
                    sortable: false,
                    labels: {
                        perPage: "{select} entries per page", // Tùy chỉnh số lượng dòng hiển thị
                        noRows: "No entries found", // Thông báo khi không có dữ liệu
                        info:    "" // Tùy chỉnh thông báo dòng hiện tại
                    }
                });
            });
        </script>




        <!--================ Start footer Area  =================-->	
        <%@include file="Footer.jsp" %>
        <!--================ End footer Area  =================-->



        <script src="vendors/jquery/jquery-3.2.1.min.js"></script>
        <script src="vendors/bootstrap/bootstrap.bundle.min.js"></script>
        <script src="vendors/skrollr.min.js"></script>
        <script src="vendors/owl-carousel/owl.carousel.min.js"></script>
        <script src="vendors/nice-select/jquery.nice-select.min.js"></script>
        <script src="vendors/jquery.ajaxchimp.min.js"></script>
        <script src="vendors/mail-script.js"></script>
        <script src="js/main.js"></script>
    </body>
</html>

