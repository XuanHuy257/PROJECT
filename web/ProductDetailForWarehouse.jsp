<%-- 
    Document   : OrderForSales
    Created on : 15 Oct 2024, 21:16:52
    Author     : Anh Tuan
--%>
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
            .body {
                font-family: 'Arial', sans-serif;
                background-color: #f1f3f4;
                padding: 30px;
            }
            .container {
                max-width: 1200px;
                margin: auto;
                background-color: #fff;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            }
            h3 {
                text-align: center;
                color: #333;
                margin-bottom: 20px;
            }
            .order-header {
                display: flex;
                justify-content: space-between;
                align-items: center;
                margin-bottom: 20px;
            }
            .order-header h4 {
                margin: 0;
                font-size: 1.2em;
            }
            .order-header .status {
                color: #28a745;
                font-weight: bold;
                font-size: 1em;
            }
            .info-section {
                display: flex;
                justify-content: space-between;
                flex-wrap: wrap;
                margin-bottom: 30px;
            }
            .info-box {
                flex: 1;
                padding: 20px;
                border: 1px solid #ced4da;
                border-radius: 8px;
                margin-bottom: 20px;
                background-color: #f9f9f9;
                min-width: 300px;
                margin-right: 10px;
                transition: box-shadow 0.3s ease;
            }
            .info-box:hover {
                box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
            }
            .info-box:last-child {
                margin-right: 0;
            }
            .info-box h5 {
                font-size: 1.1em;
                margin-bottom: 10px;
                color: #333;
                display: flex;
                align-items: center;
                gap: 10px;
            }
            .info-box p {
                margin: 5px 0;
                font-size: 1em;
            }
            .notes-box textarea {
                width: 100%;
                height: 100px;
                border-radius: 5px;
                border: 1px solid #ced4da;
                padding: 10px;
                font-size: 1em;
            }
            .btn-save {
                margin-top: 10px;
                background-color: #007bff;
                color: #fff;
                border: none;
                border-radius: 5px;
                padding: 10px 20px;
                font-size: 1em;
                transition: background-color 0.3s ease, transform 0.3s ease;
            }

            .btn-save:hover {
                background-color: #0056b3;
                transform: translateY(-2px);
            }

            .btn-save:active {
                transform: translateY(0);
            }
        </style>
    </head>
    <body class="sb-nav-fixed">
        <div id="layoutSidenav">
            <%@ include file="components/WarehouseComponents.jsp" %>
            <div id="layoutSidenav_content">
                <main>
                    <div class="container">
                        <div class="order-header">
                            <div>
                                <p><a href="productforwarehouse" style="
                                      display: inline-block;
                                      padding: 8px 16px;
                                      background-color: #007bff;
                                      color: white;
                                      text-decoration: none;
                                      border-radius: 4px;
                                      transition: background-color 0.3s ease;
                                      " onmouseover="this.style.backgroundColor = '#0056b3'"
                                      onmouseout="this.style.backgroundColor = '#007bff'">
                                        ← Back to List
                                    </a></p>
                                <h4>Product ID: #${product.productID}</h4>
                            </div>
                        </div>
                        <!-- Customer, Order Info, Receiver Info -->
                        <div class="info-section">
                            <!-- Order Info -->
                            <div class="info-box">
                                <h5><i class="fas fa-box"></i> Product Info</h5>
                                <img src="${product.thumbnail}" alt="Product Thumbnail" class="img-thumbnail" style="width: 150px; height: auto;">
                                <p>Title: ${product.title}</p>
                                <p>Release Date: ${product.releaseDate}</p>
                            </div>
                            <div class="info-box col-md-12">
                                <div class="table-responsive" style="flex: 1; margin-left: 10px;">
                                    <table class="table table-bordered " 
                                           style="border-collapse: separate; border-spacing: 0; border-top-left-radius: 10px; border-top-right-radius: 10px;">
                                        <thead>
                                            <tr>
                                                <th style="border-top-left-radius: 10px;">Size</th>
                                                <th style="border-top-right-radius: 10px;">Quantity</th>
                                                <th>Original Price</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="l" items="${listsizeqty}">
                                                <tr>
                                                    <td>${l.sizeName}</td>
                                                    <td style="text-align: center;">${l.quantity}</td>
                                                    <td style="text-align: center;">${l.originalPrice} $</td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                    <div <c:if test="${mess != null}"> style="padding: 15px; background-color: #e7f3fe; border-left: 6px solid #0056b3; margin: 20px 0;"</c:if>>
                                        <strong style="color: #0056b3;">${mess}</strong>
                                    </div>
                                </div>
                            </div>
                            <div class="info-box notes-box">
                                <h5><i class="fas fa-warehouse"></i>Inventory Restocking</h5>
                                <form action="productdetailforwarehouse" method="POST" style="max-width: 400px; margin: 20px auto; padding: 20px; border: 1px solid #ccc; border-radius: 8px; background-color: #f9f9f9; box-shadow: 0 2px 10px rgba(0,0,0,0.1);">
                                    <input type="hidden" name="productid" value="${product.productID}"/>

                                    <label for="size" style="font-weight: bold; margin-bottom: 5px; display: block;">Size:</label>
                                    <select name="sizeid" required="" id="size" style="padding: 10px; font-size: 1em; border-radius: 5px; border: 1px solid #ced4da; background-color: #fff; color: #333; cursor: pointer; transition: border-color 0.3s; width: 100%; margin-bottom: 15px;">
                                        <option value="" disabled selected>Size</option>
                                        <c:forEach items="${listsizeqty}" var="c">
                                            <option value="${c.sizeID}">${c.sizeName}</option>
                                        </c:forEach>
                                    </select>

                                    <label for="quantity" style="font-weight: bold; margin-bottom: 5px; display: block;">Quantity:</label>
                                    <input type="number" min="1" name="quantity" id="quantity" placeholder="Enter Quntity" required style="padding: 10px; font-size: 1em; border-radius: 5px; border: 1px solid #ced4da; background-color: #fff; color: #333; width: 100%; margin-bottom: 15px;" />

                                    <label for="oriprice" style="font-weight: bold; margin-bottom: 5px; display: block;">Original Price:</label>
                                    <input type="number" min="1" name="oriprice" id="oriprice" placeholder="Enter Original Price" required style="padding: 10px; font-size: 1em; border-radius: 5px; border: 1px solid #ced4da; background-color: #fff; color: #333; width: 100%; margin-bottom: 15px;" />

                                    <button type="submit" class="btn-save" style="padding: 10px 15px; font-size: 1em; border-radius: 5px; border: none; background-color: #007bff; color: white; cursor: pointer; transition: background-color 0.3s; width: 100%;">
                                        Add Quantity To Inventory
                                    </button>
                                </form>

                            </div>
                        </div>
                    </div>
                </main>
            </div>
        </div><script src="vendors/jquery/jquery-3.2.1.min.js"></script>
        <script src="vendors/bootstrap/bootstrap.bundle.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
        <script src="js/scripts.js"></script>        
    </body>
</html>