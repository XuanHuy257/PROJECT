<%-- 
    Document   : Introduction
    Created on : Nov 2, 2024, 7:01:28 PM
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
        <title>Aroma - Introduction</title>
        <link rel="icon" href="img/Fevicon.png" type="image/png">
        <link rel="stylesheet" href="vendors/bootstrap/bootstrap.min.css">
        <link rel="stylesheet" href="vendors/fontawesome/css/all.min.css">
        <link rel="stylesheet" href="vendors/themify-icons/themify-icons.css">
        <link rel="stylesheet" href="vendors/owl-carousel/owl.theme.default.min.css">
        <link rel="stylesheet" href="vendors/owl-carousel/owl.carousel.min.css">
        <link rel="stylesheet" href="vendors/nice-select/nice-select.css">
        <link rel="stylesheet" href="vendors/nouislider/nouislider.min.css">
        <link rel="stylesheet" href="css/style.css">
        <style>
            /* Background and Text Styling for Banner */
            .blog-banner-area {
                padding: 80px 0;
                background: linear-gradient(rgba(0, 64, 128, 0.7), rgba(0, 64, 128, 0.7)), url('img/banner-bg.jpg') no-repeat center center;
                background-size: cover;
                text-align: center;
                color: #ffffff;
            }

            .blog-banner-area h1 {
                font-size: 36px;
                font-weight: bold;
                color: #ffffff;
                margin-bottom: 15px;
            }

            .banner-breadcrumb {
                font-size: 14px;
                display: inline-block;
                background: rgba(255, 255, 255, 0.2);
                padding: 5px 10px;
                border-radius: 5px;
            }

            .breadcrumb-item a {
                color: #00aaff;
                text-decoration: none;
            }

            .breadcrumb-item a:hover {
                color: #ffffff;
            }

            /* Styling for Introduction Section */
            .introduction-text {
                padding: 40px;
                background: #ffffff;
                border-radius: 10px;
                margin: 30px auto;
                max-width: 800px;
                text-align: justify;
                font-size: 17px;
                line-height: 1.8;
                color: #333;
                box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
                border: 2px solid #00aaff;
            }

            .introduction-text p {
                margin-bottom: 1.8em;
            }

            /* Link Hover Effects */
            a, .btn {
                transition: all 0.3s ease;
            }

            a:hover, .btn:hover {
                color: #00aaff;
                text-decoration: none;
                transform: translateY(-2px);
            }
        </style>
    </head>
    <body>
        <!--================ Start Header Menu Area =================-->
        <%@include file="Header.jsp" %>
        <!--================ End Header Menu Area =================-->

        <!--================ Start Banner Area =================-->  
        <section class="blog-banner-area" id="category">
            <div class="container h-100">
                <div class="blog-banner">
                    <h1>Introduction</h1>
                    <nav aria-label="breadcrumb" class="banner-breadcrumb">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="home">Home</a></li>
                            <li class="breadcrumb-item active" aria-current="page">Introduction</li>
                        </ol>
                    </nav>
                </div>
            </div>
        </section>
        <!--================ End Banner Area =================-->

        <!--================ Start Introduction Section =================-->
        <section class="introduction-text">
            <p>Welcome to <strong>[Aroma Shop]</strong> – your ultimate destination for discovering stylish, high-quality clothing tailored to fit every personality and occasion! We bring you a wide range of apparel, from sophisticated, elegant pieces to trendy, casual wear that lets you express your unique style. Our collection is thoughtfully curated to ensure each item meets our high standards for quality, comfort, and durability, making sure you feel confident and stylish with every purchase.</p>

            <p>At <strong>[Aroma Shop]</strong>, we believe shopping should be effortless and enjoyable. Our user-friendly interface, detailed product descriptions, and size guides make finding the perfect outfit easy. Plus, with new arrivals constantly hitting our shelves, you’ll always have fresh, inspiring choices to explore.</p>

            <p>Step into the world of fashion with us and redefine your wardrobe with pieces that speak to you. Join us today and experience a seamless blend of fashion, quality, and convenience at <strong>[Aroma Shop]</strong> – where every look is crafted to make you feel your best!</p>
        </section>
        <!--================ End Introduction Section =================-->

        <!--================ Start footer Area  =================-->    
        <%@include file="Footer.jsp" %>
        <!--================ End footer Area  =================-->

        <script src="vendors/jquery/jquery-3.2.1.min.js"></script>
        <script src="vendors/bootstrap/bootstrap.bundle.min.js"></script>
        <script src="vendors/owl-carousel/owl.carousel.min.js"></script>
        <script src="vendors/nice-select/jquery.nice-select.min.js"></script>
        <script src="js/main.js"></script>
    </body>
</html>
