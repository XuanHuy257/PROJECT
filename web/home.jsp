<%-- 
    Document   : home
    Created on : Sep 14, 2024, 1:27:12 AM
    Author     : sontu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Aroma Shop - Home</title>
        <link rel="icon" href="img2/FIcon.png" type="image/png">
        <link rel="stylesheet" href="vendors/bootstrap/bootstrap.min.css">
        <link rel="stylesheet" href="vendors/fontawesome/css/all.min.css">
        <link rel="stylesheet" href="vendors/themify-icons/themify-icons.css">
        <link rel="stylesheet" href="vendors/nice-select/nice-select.css">
        <link rel="stylesheet" href="vendors/owl-carousel/owl.theme.default.min.css">
        <link rel="stylesheet" href="vendors/owl-carousel/owl.carousel.min.css">
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <!--================ Start Header Menu Area =================-->
        <%@include file="Header.jsp" %>
        <!--================ End Header Menu Area =================-->

        <main class="site-main">

            <!--================ Hero banner start =================-->
            <div id="heroSlider" class="carousel slide" data-ride="carousel">
                <!-- Indicators (tuỳ chọn) -->
                <ol class="carousel-indicators">
                    <c:forEach var="p" items="${listActiveSlider}" varStatus="status">
                        <li data-target="#heroSlider" data-slide-to="${status.index}" class="${status.first ? 'active' : ''}"></li>
                        </c:forEach>
                </ol>

                <!-- Slide nội dung -->
                <div class="carousel-inner">
                    <c:forEach var="p" items="${listActiveSlider}" varStatus="status">
                        <div class="carousel-item ${status.first ? 'active' : ''}">
                            <div class="row no-gutters align-items-center pt-60px">
                                <div class="col-12"> <!-- Sửa col để chiếm toàn bộ màn hình -->
                                    <div class="hero-banner__img position-relative" style="text-align: center;">
                                        <img class="img-fluid" src="${p.img}" style="height: 100%; width: auto; " alt="image">
                                        <div class="hero-banner__content">
                                            <h4 class="hero-title">${p.title}</h4>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>

                <style>
                    .carousel-item img {
                        width: 100%; /* Chiều rộng giữ nguyên */
                        height: 50vh; /* Chiều cao chỉ chiếm 50% màn hình */
                        object-fit: cover; /* Đảm bảo ảnh không bị méo */
                    }

                    .hero-banner__content {
                        position: absolute;
                        top: 50%; /* Căn giữa theo trục dọc */
                        left: 50%; /* Căn giữa theo trục ngang */
                        transform: translate(-50%, -50%); /* Đảm bảo nội dung được căn chính giữa */
                        color: white; /* Đổi màu chữ cho dễ nhìn */
                        text-shadow: 2px 2px 8px rgba(0, 0, 0, 0.7); /* Bóng mờ để chữ nổi bật hơn trên ảnh */
                        z-index: 2; /* Đảm bảo chữ nằm trên ảnh */
                    }

                    .hero-title {
                        font-size: 2rem; /* Tăng kích thước chữ */
                        font-weight: bold; /* In đậm */
                        color: #333; /* Màu sắc tiêu đề */
                        text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.3); /* Thêm bóng cho chữ */
                        line-height: 1.2; /* Tăng khoảng cách giữa các dòng */
                    }
                    .hero-title {
                        font-size: 2rem;
                        font-weight: bold;
                        color: #333;
                        animation: fadeIn 1.5s ease-in-out;
                    }

                    @keyframes fadeIn {
                        0% {
                            opacity: 0;
                            transform: translateY(20px);
                        }
                        100% {
                            opacity: 1;
                            transform: translateY(0);
                        }

                    }

                    /* Định dạng nút bấm */
                    .btn-custom {
                        display: inline-block;
                        background-color: #007bff; /* Màu nền xanh dương */
                        color: white; /* Màu chữ */
                        padding: 10px 20px; /* Kích thước padding */
                        border-radius: 25px; /* Bo tròn góc */
                        font-size: 14px;
                        font-weight: bold;
                        text-transform: uppercase;
                        text-align: center;
                        transition: background-color 0.3s ease, transform 0.3s ease; /* Hiệu ứng */
                        text-decoration: none; /* Bỏ gạch chân */
                    }

                    /* Hiệu ứng khi di chuột vào nút */
                    .btn-custom:hover {
                        background-color: #0056b3; /* Thay đổi màu nền khi di chuột */
                        transform: scale(1.05); /* Phóng to nhẹ khi di chuột */
                        color: #fff; /* Đảm bảo màu chữ vẫn là trắng */
                    }
                </style>
                <!-- Điều hướng -->
                <a class="carousel-control-prev" href="#heroSlider" role="button" data-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="carousel-control-next" href="#heroSlider" role="button" data-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>

            <!--================ Hero banner start =================-->

            <!-- ================ trending product section start ================= -->  
            <section class="section-margin calc-60px">
                <div class="container">
                    <div class="section-intro pb-60px">
                        <p>Popular Item in the market</p>
                        <h2>Newest <span class="section-intro__style">Awesome Product</span></h2>
                    </div>
                    <div class="row">
                        <c:forEach items="${list8ExcellentProductNewest}" var="p">
                            <div class="col-md-6 col-lg-4 col-xl-3">
                                <div class="card text-center card-product">
                                    <div class="card-product__img">
                                        <a href="product_detail?id=${p.productID}">
                                            <img class="card-img" style="height: 200px; width: 200px; display: block; margin: 0 auto;" src="${p.thumbnail}" alt="image">
                                        </a>
                                        <ul class="card-product__imgOverlay">                       
                                            <li>
                                                <a class="btn-custom" href="product_detail?id=${p.productID}">More Info</a>
                                            </li>
                                        </ul>
                                    </div>
                                    <div class="card-body">                                               
                                        <h4 class="card-product__title">
                                            <a href="product_detail?id=${p.productID}">${p.title}</a>
                                        </h4>
                                        <p class="card-product__info">${p.briefInfo}</p>
                                        <p class="card-product__price">
                                            <c:if test="${p.totalQuantity == 0 && p.featureID != 1}">
                                                <span class="out-of-stock" style="color: red;">Out of Stock</span>
                                            </c:if>
                                            <c:if test="${p.featureID == 3}">
                                                <span class="coming-soon" style="color: #FFA500;">Haven't Released Yet</span>
                                            </c:if>
                                            <c:if test="${p.totalQuantity > 0 && p.featureID != 3}">
                                                <c:if test="${p.firstPrice != (p.firstPrice * (1 - p.discount / 100))}">
                                                    <span class="original-price" style="color: red;"><del>${p.firstPrice}$</del></span><br>
                                                    <span class="sale-price" style="color: #00AB55;">${p.firstPrice * (1 - p.discount / 100)}$</span>
                                                </c:if>
                                                <c:if test="${p.firstPrice == (p.firstPrice * (1 - p.discount / 100))}">
                                                    <span style="color: #00AB55;" class="sale-price">${p.firstPrice}$</span>
                                                </c:if>
                                            </c:if>
                                        </p>
                                    </div>  
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </section>
            <style>
                .card-product__img img {
                    width: 100%;
                    height: 280px; /* Chiều cao cố định, bạn có thể điều chỉnh */
                    object-fit: cover; /* Đảm bảo hình ảnh không bị méo */
                }

            </style>
            <!-- ================ trending product section end ================= -->  


            <!-- ================ offer section start ================= --> 
            <section class="offer" id="parallax-1" data-anchor-target="#parallax-1" data-300-top="background-position: 20px 30px" data-top-bottom="background-position: 0 20px">
                <div class="container">
                    <div class="row">
                        <div class="col-xl-5">
                            <div class="offer__content text-center">
                                <h3>Big sale on this September</h3>
                                <h4>Winter Sale</h4>
                                <p>Go here to get newest products</p>
                                <a class="button button--active mt-3 mt-xl-4" href="productlist?action=null">Shop Now</a>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <!-- ================ offer section end ================= --> 

            <!-- ================ Best Selling item  carousel ================= --> 
            <section class="section-margin calc-60px">
                <div class="container">
                    <div class="section-intro pb-60px">
                        <p>Popular Item in the market</p>
                        <h2>Incoming <span class="section-intro__style">Product!</span></h2>
                    </div>
                    <div class="owl-carousel owl-theme" id="bestSellerCarousel">
                        <c:forEach items="${list4NotReleasedProductNewest}" var="p">
                            <div class="card text-center card-product">
                                <div class="card-product__img">
                                    <a href="product_detail?id=${p.productID}">
                                        <img class="img-fluid" src="${p.thumbnail}" style="height: 200px; width: 200px; display: block; margin: 0 auto;" alt="imagez">
                                    </a>
                                    <ul class="card-product__imgOverlay">                       
                                        <li>
                                            <a class="btn-custom" href="product_detail?id=${p.productID}">More Info</a>
                                        </li>
                                    </ul>
                                </div>
                                <div class="card-body">                                               
                                    <h4 class="card-product__title">
                                        <a href="product_detail?id=${p.productID}">${p.title}</a>
                                    </h4>
                                    <p class="card-product__info">${p.briefInfo}</p>
                                    <p class="card-product__price">
                                        <c:if test="${p.totalQuantity == 0 && p.featureID != 3}">
                                            <span class="out-of-stock" style="color: red;">Out of Stock</span>
                                        </c:if>
                                        <c:if test="${p.featureID == 3}">
                                            <span class="coming-soon" style="color: #FFA500;">Haven't Released Yet</span>
                                        </c:if>
                                        <c:if test="${p.totalQuantity > 0 && p.featureID != 3}">
                                            <c:if test="${p.firstPrice != (p.firstPrice * (1 - p.discount / 100))}">
                                                <span class="original-price" style="color: red;"><del>${p.firstPrice}$</del></span><br>
                                                <span class="sale-price" style="color: #00AB55;">${p.firstPrice * (1 - p.discount / 100)}$</span>
                                            </c:if>
                                            <c:if test="${p.firstPrice == (p.firstPrice * (1 - p.discount / 100))}">
                                                <span style="color: #00AB55;" class="sale-price">${p.firstPrice}$</span>
                                            </c:if>
                                        </c:if>
                                    </p>
                                </div> 
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </section>
            <style>
                .btn-custom {
                    font-size: 14px;
                    padding: 8px 12px;
                    border-radius: 5px;
                    color: #fff;
                    transition: 0.3s;
                    cursor: pointer;
                }
                .btn-danger {
                    background-color: #d9534f;
                    box-shadow: 0 4px 8px rgba(217, 83, 79, 0.4);
                }
                .btn-danger:hover {
                    background-color: #c9302c;
                    box-shadow: 0 6px 12px rgba(217, 83, 79, 0.6);
                }
                .btn-info {
                    background-color: #5bc0de;
                    box-shadow: 0 4px 8px rgba(91, 192, 222, 0.4);
                }
                .btn-info:hover {
                    background-color: #31b0d5;
                    box-shadow: 0 6px 12px rgba(91, 192, 222, 0.6);
                }
            </style>
            <!-- ================ Best Selling item  carousel end ================= --> 

            <!-- ================ Blog section start ================= -->  
            <section class="blog">
                <div class="container">
                    <div class="section-intro pb-60px">
                        <p>Popular Item in the market</p>
                        <h2>Latest <span class="section-intro__style">News</span></h2>
                    </div>

                    <div class="row">
                        <c:forEach items="${top3LastestBlog}" var="p">
                            <div class="col-md-6 col-lg-4 mb-4 mb-lg-0">
                                <div class="card card-blog">
                                    <div class="card-blog__img">
                                        <a href="blog-detail?id=${p.postID}"><img class="card-img rounded-0" src="${p.postImg}" style="display: block; margin: 0 auto; " alt=""></a>
                                    </div>
                                    <div class="card-body">
                                        <ul class="card-blog__info">
                                            <li><a href="#">By ${p.userName}</a></li>
                                            <li><a href="#"><i class="ti-comments-smiley"></i> 2 Comments</a></li>
                                        </ul>
                                        <h4 class="card-blog__title"><a href="blog-detail?id=${p.postID}">${p.title}</a></h4>
                                        <p>${p.postBrief}</p>
                                        <a class="card-blog__link" href="blog-detail?id=${p.postID}">Read More <i class="ti-arrow-right"></i></a>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </section>
            <!-- ================ Blog section end ================= -->  
        </main>


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
