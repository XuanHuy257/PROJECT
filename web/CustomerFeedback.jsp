<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Orders For Warehouse Staff</title>       
        <link href="css/styles.css" rel="stylesheet" />
        <link rel="stylesheet" href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.css">
        <link rel="stylesheet" href="vendors/bootstrap/bootstrap.min.css">
        <link rel="stylesheet" href="vendors/fontawesome/css/all.min.css">
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                background-color: #f4f4f4;
            }

            .container {
                width: 80%;
                margin: auto;
                overflow: hidden;
            }

            .product-list {
                display: grid;
                grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
                gap: 20px;
                padding: 20px;
            }

            .product {
                background: #fff;
                padding: 20px;
                border-radius: 5px;
                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
                transition: transform 0.3s;
            }

            .product:hover {
                transform: scale(1.05);
            }

            h2 {
                color: #333;
            }

            .price {
                font-weight: bold;
                color: #e91e63;
            }

            label {
                display: block;
                margin-top: 10px;
            }

            .btn {
                background: #0A68FF;
                color: #fff;
                border: none;
                padding: 10px 15px;
                border-radius: 5px;
                cursor: pointer;
                margin-top: 10px;
            }

            .btn:hover {
                background: #0A68FF;
                color: white;
            }
        </style>
    </head>
    <body>
        <%@include  file="Header.jsp" %>
        <div class="container" style="min-height: 300px; margin-top: 25px;">
            <c:choose>
                <c:when test="${empty listfb}">
                    <div class="no-feedback" style="text-align: center; padding: 20px; font-size: 24px; color: #0A68FF; margin-top: 30px; display: flex; flex-direction: column; justify-content: center; align-items: center;">
                        <i class="fas fa-info-circle" style="font-size: 80px; color: #0A68FF; margin-bottom: 10px;"></i>
                        <p>No Products Need Feedback!</p>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="product-list">
                        <c:forEach items="${listfb}" var="f">
                            <div class="product">
                                <div class="product-info">
                                    <img src="${f.thumbnail}" alt="${f.title}" class="product-image" style="height: 300px; width: auto; display: block; margin-left: auto; margin-right: auto;">
                                    <div class="product-details">
                                        <a href="product_detail?id=${f.productID}" style="color: #333; font-size: 22px; font-weight: bold; text-decoration: none; margin-top: 10px;">${f.title}</a>
                                        <p style="margin: 5px 0; font-size: 14px; color: #555;">Latest <strong>OrderID</strong> for This Product: ${f.orderID}</p>
                                        <p style="margin: 5px 0; font-size: 14px; color: #555;">Total Quantity Purchased: ${f.quantity}</p>
                                        <button class="btn" onclick="window.location.href = 'product_detail?id=${f.productID}'" style="padding: 10px 15px; background-color: #4B5CED; color: white; border: none; border-radius: 5px; cursor: pointer;">
                                            Send Feedback
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
        <%@include file="Footer.jsp" %>
    </body>
</html>
