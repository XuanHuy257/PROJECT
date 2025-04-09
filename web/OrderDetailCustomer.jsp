<%-- 
    Document   : OrderDetailCustomer
    Created on : Oct 15, 2024, 1:29:48 AM
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
        <title>Five Castles Shop - Order Detail</title>
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

        </style>
        <section class="cart_area">
            <div class="container">
                <div class="cart_inner">
                    <div class="table-responsive">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th scope="col">ID</th>
                                    <th scope="col">Receiver Name</th>
                                    <th scope="col">Receiver Gender</th>
                                    <th scope="col">Receiver Email</th>
                                    <th scope="col">Receiver Mobile</th>
                                    <th scope="col">Receiver Address</th>
                                    <th scope="col">Receiver Notes</th>
                                    <th scope="col">Order Status</th>
                                    <th scope="col">Payment Method</th>
                                    <th scope="col">Created Date</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${ListOrderDetail}" var="l">
                                    <tr>
                                        <td>
                                            ${l.orderID}
                                        </td>
                                        <td><p>${l.receiverName}</p></td>
                                    <td>
                                            <h5>${l.receiverGender}</h5>
                                        </td>
                                        <td>
                                            <h5>${l.receiverEmail}</h5>
                                        </td>
                                        <td>
                                            ${l.receiverMobile}
                                        </td>
                                        <td>
                                            <h5>${l.receiverAddress}</h5>
                                        </td>
                                        <td>${l.receiverNotes}</td>
                                        <td>${l.statusName}</td>
                                        <td>${l.paymentMethod}</td>
                                        <td>${l.createdOrder}</td>
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
                                    </td>
                                    <td>
                                    </td>
                                    <td>
                                    </td>
                                    <td>
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

