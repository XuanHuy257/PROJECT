<%-- 
    Document   : ShopProduct
    Created on : 19 Oct 2024, 12:13:04
    Author     : Anh Tuan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Five Castles Shop - Shop</title>
        <style>
            .sidebar-categories {
                background-color: #f8f9fa;
                border: 1px solid #e0e0e0;
                border-radius: 8px;
                padding: 15px;
            }

            .sidebar-categories .head {
                font-size: 1.2em;
                font-weight: bold;
                margin-bottom: 15px;
                color: #333;
                text-align: left;
            }

            .main-categories {
                padding-left: 0;
            }

            .filter-list {
                margin: 10px 0;
                transition: background-color 0.3s, color 0.3s;
            }

            .filter-list:hover {
                background-color: #384AEB;
                color: #FFFFFF;
            }

            .filter-list a {
                color: inherit;
                text-decoration: none;
                display: block;
                height: 100%;
            }

            .filter-list:hover a {
                color: #FFFFFF;
            }

            .name {
                margin-left: 10px;
            }
            label.selected {
                background-color: #384AEB;
                color: #FFFFFF;
                display: block;
                height: 100%;
            }
            #sortSelect {
                padding: 8px;
                border: 1px solid #ccc;
                border-radius: 4px;
                background-color: #fff;
                color: #333;
                font-size: 14px;
                cursor: pointer;
                transition: border 0.3s;
            }

            #sortSelect:hover {
                border-color: #384AEB;
            }
            #sortSelect option {
                background-color: #fff;
                color: #333;
            }
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
            .card-product__info {
                max-height: 100px;
                overflow: hidden;
                text-overflow: ellipsis;
                display: -webkit-box;
                -webkit-line-clamp: 2;
                -webkit-box-orient: vertical;
            }
            .card {
                display: flex;
                flex-direction: column;
                height: 100%;
            }

            .card-product__img {
                flex-shrink: 0;
            }

            .card-body {
                flex: 1;
                display: flex;
                flex-direction: column;
                justify-content: space-between;
            }
        </style>
    </head>
    <body>
        <%@include file="Header.jsp" %>
        <section class="section-margin--small mb-5">
            <div class="container">
                <div class="row">
                    <div class="col-xl-3 col-lg-4 col-md-5">

                        <div class="sidebar-categories">
                            <div class="head">Brands</div>
                            <ul class="main-categories" style="list-style: none; padding: 0;">
                                <li class="common-filter">
                                    <ul style="padding: 0;">
                                        <c:forEach var="br" items="${listbrand}">
                                            <li class="filter-list" style="margin: 10px 0;">       
                                                <a href="productlist?brand=${br.attributeID}">
                                                    <label class="${br.attributeID == brand ? 'selected' : ''}">
                                                        <span class="name" style="padding: 10px; font-weight: 500;">${br.attributeName}</span>
                                                    </label>
                                                </a>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </li>
                            </ul>
                        </div>
                        <div>
                            <!--space-->
                        </div>

                        <div class="sidebar-categories" style="margin-top: 10px;">
                            <div class="head">Category</div>
                            <ul class="main-categories" style="list-style: none; padding: 0;">
                                <li class="common-filter">
                                    <ul style="padding: 0;">
                                        <c:forEach var="ct" items="${listcategory}">
                                            <li class="filter-list">
                                                <a href="productlist?category=${ct.attributeID}" style="text-decoration: none; cursor: pointer;">
                                                    <label class="${ct.attributeID == category ? 'selected' : ''}">
                                                        <span class="name">${ct.attributeName}</span>
                                                    </label>
                                                </a>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </li>
                            </ul>
                        </div>
                        <div class="sidebar-categories">
                            <div class="head">Last Product</div>
                            <div class="card text-center card-product">
                                <div class="card-product__img">
                                    <img class="" src="${pro.thumbnail}" alt="image" style="height: auto; width: 100%;">
                                    <ul class="card-product__imgOverlay">
                                        <ul class="card-product__imgOverlay">
                                            <li>
                                                <a class="btn-custom" href="product_detail?id=${p.productID}">More Info</a>
                                            </li>
                                        </ul>
                                    </ul>
                                </div>
                                <div class="card-body">                                               
                                    <h4 class="card-product__title">
                                        <a href="product_detail?id=${pro.productID}">${pro.title}</a>
                                    </h4>
                                    <p class="card-product__info">${pro.briefInfo}</p>
                                    <p class="card-product__price">
                                        <c:if test="${pro.firstPrice != (pro.firstPrice * (1 - pro.discount / 100))}">
                                            <span class="original-price" style="color: red;"><del>${pro.firstPrice}$</del></span><br>
                                            <span class="sale-price" style="color: #00AB55;">${pro.firstPrice * (1 - pro.discount / 100)}$</span>
                                        </c:if>
                                        <c:if test="${pro.firstPrice == (pro.firstPrice * (1 - pro.discount / 100))}">
                                            <span style="color: #00AB55;"class="sale-price">${pro.firstPrice}$</span>
                                        </c:if>
                                    </p>
                                </div>                                   
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-9 col-lg-8 col-md-7" style="background-color: #f8f9fa;border: 1px solid #e0e0e0;border-radius: 8px; padding: 15px;">
                        <!-- Start Filter Bar -->
                        <div class="filter-bar d-flex flex-wrap align-items-center">
                            <div class="sorting">
                                <form action="productlist">
                                    <c:if test="${not empty category}">
                                        <input type="hidden" name="category" value="${category}">
                                    </c:if>
                                    <c:if test="${not empty brand}">
                                        <input type="hidden" name="brand" value="${brand}">
                                    </c:if>
                                    <c:if test="${not empty search}">
                                        <input type="hidden" name="search" value="${search}">
                                    </c:if>
                                    <select id="sortSelect" name="sort" onchange="this.form.submit()">
                                        <option value="">NONE</option>
                                        <option ${"DESC".equals(sort) ? "selected" : ""} value="DESC">PRICE (HIGH-LOW)</option>
                                        <option ${"ASC".equals(sort) ? "selected" : ""} value="ASC">PRICE (LOW-HIGH)</option>
                                    </select>
                                </form>
                            </div>
                            <div>
                                <form action="productlist">
                                    <div class="input-group filter-bar-search">
                                        <c:if test="${not empty category}">
                                            <input type="hidden" name="category" value="${category}">
                                        </c:if>
                                        <c:if test="${not empty brand}">
                                            <input type="hidden" name="brand" value="${brand}">
                                        </c:if>
                                        <c:if test="${not empty sort}">
                                            <input type="hidden" name="sort" value="${sort}">
                                        </c:if>
                                        <input style="border-color: #384AEB;" type="text" name="search" id="searchInput" value="${search}" placeholder="Search" >
                                        <div class="input-group-append">
                                            <button style="border-color: #384AEB;" type="submit" ><i class="ti-search"></i></button>
                                        </div>
                                        <div class="input-group-append">
                                            <button type="Reset" onclick="window.location.href = 'productlist'" style="border: none; margin-left: 20px; cursor: pointer; color: #384AEB;" ><i class="fas fa-broom"></i> Clear</button>
                                        </div>
                                    </div>
                                </form>
                                <!-- Container to display suggestions -->
                                <div id="suggestions" class="suggestions-box" style="display:none;">
                                </div>
                            </div> 
                        </div>
                        <section class="lattest-product-area pb-40 category-list">
                            <div class="row">
                                <c:forEach items="${productlist}" var="p">
                                    <div class="col-md-6 col-lg-4" style="margin-bottom: 20px;">
                                        <div class="card text-center card-product">
                                            <div class="card-product__img">
                                                <img class="" src="${p.thumbnail}" alt="image" style="height: 255px; width: 100%;">
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
                        </section>

                        <!-- End Best Seller -->
                    </div>
                </div>
            </div>
        </section>       
        <nav class="custome-pagination" style="margin-bottom: 50px;">
            <ul class="d-flex justify-content-center pagination-md">
                <c:if test="${page > 1}">
                    <li class="page-item">
                        <a href="productlist?page=1&category=${category}&brand=${brand}&search=${search}&sort=${sort}" class="page-link" style="color: var(--theme-color);"> Begin </a>
                    </li>
                    <li class="page-item">
                        <a href="productlist?page=${page - 1}&category=${category}&brand=${brand}&search=${search}&sort=${sort}" class="page-link" style="color: var(--theme-color);"> Previous </a>
                    </li>
                </c:if>

                <c:set var="startPage" value="${page - 2 > 0 ? page - 2 : 1}" />
                <c:set var="endPage" value="${page + 2 <= total ? page + 2 : total}" />

                <c:forEach var="p" begin="${startPage}" end="${endPage}">
                    <li class="page-item ${p == page ? 'active' : ''}">
                        <a href="productlist?page=${p}&category=${category}&brand=${brand}&search=${search}&sort=${sort}" class="page-link" style="color: var(--theme-color);"> ${p} </a>
                    </li>
                </c:forEach>

                <c:if test="${page < total}">
                    <li class="page-item">
                        <a href="productlist?page=${page + 1}&category=${category}&brand=${brand}&search=${search}&sort=${sort}" class="page-link" style="color: var(--theme-color);"> Next </a>
                    </li>
                    <li class="page-item">
                        <a href="productlist?page=${total}&category=${category}&brand=${brand}&search=${search}&sort=${sort}" class="page-link" style="color: var(--theme-color);"> End </a>
                    </li>
                </c:if>
            </ul>
        </nav>
        <%@include file="Footer.jsp"%>
    </body>
</html>
