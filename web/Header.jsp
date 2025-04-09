<%-- 
    Document   : Header
    Created on : Sep 18, 2024, 7:41:09 AM
    Author     : sontu
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Five Castles Shop - Home</title>
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
        <header class="header_area">
            <div class="main_menu">
                <nav class="navbar navbar-expand-lg navbar-light">
                    <div class="container">
                        <a class="navbar-brand logo_h" href="home"><img src="img/logo.png" alt=""></a>
                        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <div class="collapse navbar-collapse offset" id="navbarSupportedContent">
                            <ul class="nav navbar-nav menu_nav ml-auto mr-auto">
                                <li class="nav-item active"><a class="nav-link" href="home">Home</a></li>
                                <li class="nav-item submenu dropdown">
                                    <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                                       aria-expanded="false">Shop</a>
                                    <ul class="dropdown-menu">
                                        <li class="nav-item"><a class="nav-link" href="productlist">Products</a></li>
                                        <li class="nav-item"><a class="nav-link" href="cart?service=showCart">My Cart</a></li>
                                    </ul>
                                </li>
                                <li class="nav-item"><a class="nav-link" href="blogs">Blog</a></li>
                                <li class="nav-item submenu dropdown">
                                    <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                                       aria-expanded="false">Orders</a>
                                    <ul class="dropdown-menu">
                                        <li class="nav-item"><a class="nav-link" href="order_customer">My Order</a></li>
                                        <li class="nav-item"><a class="nav-link" href="customerfeedback">Feedback</a></li>
                                    </ul>
                                </li>
                                <li class="nav-item"><a class="nav-link" href="introduction">Introduction</a></li>
                            </ul>
                            <ul class="nav-shop">
                                <li class="nav-item">
                                    <button>
                                        <a class="ti-shopping-cart" href="cart?service=showCart"></a>
                                        <span class="nav-shop__circle"></span>
                                    </button>
                                </li>
                                <li class="nav-item">
                                    <c:choose>
                                        <c:when test="${sessionScope.account != null}">
                                            <div class="dropdown">
                                                <a href="#" class="dropdown-toggle" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                    <c:if test="${sessionScope.account.avatar != null}">
                                                        <img class="ti-user" src="${sessionScope.account.avatar}" style="width: 25px; height: 25px; border-radius: 50%; object-fit: cover;"/>
                                                    </c:if> 
                                                </a>
                                                <div class="dropdown-menu" aria-labelledby="userDropdown">
                                                    <c:if test="${sessionScope.account.fullName != null}">
                                                        <a class="dropdown-item">Welcome, ${sessionScope.account.fullName}</a>
                                                    </c:if>                                                       
                                                    <a class="dropdown-item" href="changepassword">Change Password</a>                                                                                                     
                                                    <a class="dropdown-item" href="UserDetailServlet">View Profile</a>
                                                    <a class="dropdown-item" href="logout">Logout</a>
                                                </div>
                                            </div>
                                        </c:when>
                                        <c:otherwise>
                                            <a class="ti-user" href="login  "></a>
                                        </c:otherwise>
                                    </c:choose>
                                </li>
                                <li class="nav-item"><a class="button button-header" href="login">Login</a></li>
                            </ul>
                        </div>
                    </div>
                </nav>
            </div>
        </header>
    </body>
</html>
