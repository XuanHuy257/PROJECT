<%-- 
    Document   : ProductsInOrderOfCustomer
    Created on : Oct 15, 2024, 1:15:28 AM
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
        <title>Five Castles Shop - Cart</title>
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
            }
        </style>
        <section class="cart_area">
            <div class="container">
                <div class="cart_inner">
                    <div class="table-responsive">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th scope="col">Product Name</th>
                                    <th scope="col">Image</th>
                                    <th scope="col">Quantity</th>
                                    <th scope="col">Price</th>
                                    <th scope="col">Size Name</th>
                                    <th scope="col">Re-buy Product</th>
                                </tr>                                    
                            </thead>
                            <tbody>
                                <c:forEach items="${listOrderDetail}" var="l">
                                    <tr>
                                        <td>${l.title}</td>
                                        <td><div class="media">
                                                <div class="d-flex">
                                                    <img src="${l.thumbnail}" alt="a">
                                                </div>
                                            </div></td>
                                        <td>${l.quantity}</td>
                                        <td>$${l.price}</td>
                                        <td>${l.sizeName}</td>
                                        <td><a class="ti-shopping-cart" href="product_detail?id=${l.productID}"></a></td>
                                    </tr>
                                </c:forEach>
                                <tr class="out_button_area">
                                    <td class="d-none-l">
                                    </td>
                                    <td class="">

                                    </td>
                                    <td>

                                    </td>
                                    <td>
                                        <div class="checkout_btn_inner d-flex align-items-center">
                                            <a class="primary-btn ml-2" href="order_customer">Back to Order List</a>
                                        </div>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </section>
        <!--================End Cart Area =================-->



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
