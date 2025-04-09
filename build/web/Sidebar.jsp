Đây là sidebar
<%-- 
    Document   : sider
    Created on : 6 Oct 2024, 20:30:39
    Author     : Anh Tuan
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Five Castles</title>
    </head>
    <body>
        <!-- sidebar.jsp -->
        <div class="sidebar">
            <!-- Hộp tìm kiếm -->
            <div class="search-box">
                <form action="allproduct" >
                    <input type="text" name="query" placeholder="Search for products..." required style="border-radius: 0;"> 
                    <input type="hidden" name="action" value="search"> 
                    <button type="submit" style="background-color: #007bff; color: white; border: none; border-radius: 0; padding: 3.5px 10px;">
                        <i class="fa fa-search"></i>
                    </button>
                </form>
            </div>

            <!-- Sidebar danh mục -->
            <div class="sidebar-categories">
                <div class="head">Categories</div>
                <ul class="main-categories">
                    <li class="common-filter">
                        <ul>
                            <c:forEach var="ct" items="${Category}">
                            </c:forEach>
                        </ul>
                    </li>
                </ul>
            </div>

            <!-- Sản phẩm mới nhất -->
            <div class="last-products" style="margin-top: 30px;">
                <h5>Last Products</h5>
                <ul>
                    <c:forEach var="product" items="${LastProducts}">
                        <li>
                            <a href="product_detail?id=${product.id}">${product.title}</a>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    <li class="filter-list">
        <a href="allproduct?action=filterByCategory&categoryid=${ct.id}" style="text-decoration: none; color: inherit;">
            <span class="name">${ct.name}</span>
        </a>
    </li>
</c:forEach>
</ul>
</li>
</ul>
</div>

<!-- Sản phẩm mới nhất -->
<div class="last-products" style="margin-top: 30px;">
    <h5>Last Products</h5>
    <ul>
        <c:forEach var="product" items="${LastProducts}">
            <li>
                <a href="product_detail?id=${product.id}">${product.title}</a>
            </li>
        </c:forEach>
    </ul>
</div>
</div>

</body>
</html>