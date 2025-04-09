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

        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f1f3f4;
            }

            .container {
                max-width: 100%;
                margin: 0 auto;
                padding: 20px;
            }

            h3 {
                color: #333;
                text-align: center;
                margin-bottom: 30px;
            }

            .filters {
                display: flex;
                flex-wrap: wrap;
                justify-content: space-between;
                margin-bottom: 20px;
                background-color: #fff;
                padding: 15px;
                border-radius: 8px;
                box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            }

            .filters div {
                margin-bottom: 10px;
            }

            .table {
                background-color: #fff;
                border-radius: 8px;
                box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
                font-size: 1.1em;
                border-collapse: collapse;
            }

            .table thead {
                background-color: #007bff;
                color: white;
            }

            .table th,
            .table td {
                vertical-align: middle;
                text-align: center;
            }

            .table td a {
                color: #007bff;
                text-decoration: none;
            }

            .table td a:hover {
                text-decoration: underline;
            }

            .pagination {
                justify-content: center;
                margin-top: 20px;
            }

            .order-status-icon {
                font-size: 24px;
                margin-right: 5px;
                transition: transform 0.2s;
            }

            .order-status {
                display: flex;
                align-items: center;
                padding: 10px;
                border-radius: 5px;
                transition: background-color 0.3s;
            }

            .order-status:hover {
                background-color: rgba(0, 0, 0, 0.05);
            }
            .filters div {
                margin-bottom: 10px;
                flex: 1;
                margin-right: 10px;
            }

            .filters div:last-child {
                margin-right: 0;
            }

        </style>
    </head>
    <body class="sb-nav-fixed">
        <div id="layoutSidenav">
            <%@ include file="components/WarehouseComponents.jsp"%>
            <div id="layoutSidenav_content">
                <main>
                    <div class="container col-11">
                        <div class="container-fluid" style="margin-top: 20px;">
                            <div class="row">
                                <div class="col-xl-6 col-md-6">
                                    <div class="card text-white mb-4" style="background-color: #DC3545; cursor: pointer;" onclick="window.location.href = 'productforwarehouse?status=low';">
                                        <div class="card-body">
                                            <i class="fas fa-upload"></i> Low Stock Products: ${producttype.type1}<strong></strong>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xl-6 col-md-6">
                                    <div class="card text-white mb-4" style="background-color: #28A745; cursor: pointer;" onclick="window.location.href = 'productforwarehouse?status=new';">
                                        <div class="card-body">
                                            <i class="fas fa-star"></i> New Products Added to Stock: ${producttype.type2}<strong></strong>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- Filters Section -->
                        <form action="productforwarehouse" method="get">
                            <div class="filters">               
                                <div>
                                    <label for="date-from">Date From</label>
                                    <input type="date" name="datefrom" id="date-from" class="form-control" value="${datefrom}">
                                </div>
                                <div>
                                    <label for="date-to">Date To</label>
                                    <input type="date" name="dateto" id="date-to" class="form-control" value="${dateto}">
                                </div>
                                <c:if test="${status != null}">
                                    <input type="hidden" name="status" value="${status}">
                                </c:if>
                                <div>
                                    <label for="customer-name">Product Title</label>
                                    <input type="text" id="sale-name" name="title" class="form-control" value="${title}" placeholder="Enter Product Title">
                                </div>       
                                <div>
                                    <button type="Submit" style="margin-top: 25px; margin-right: 10px;" id="filter-btn" class="btn btn-primary">Filter</button>                
                                    <button type="Reset" style="margin-top: 25px;" id="filter-btn" class="btn btn-primary" onclick="window.location.href = 'productforwarehouse'">Reset</button>
                                </div>                
                            </div>
                        </form>                   
                        <table class="table table-bordered" id="proTable">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Thumbnail</th>
                                    <th>Title</th>
                                    <th>Total Quantity</th>
                                    <th>Total Hold</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="l" items="${listproduct}">
                                    <tr>
                                        <td>${l.productID}</td>
                                        <td><img src="${l.thumbnail}" alt="Product Thumbnail" class="img-thumbnail" style="width: 55px; height: auto;"></td>
                                        <td>${l.title} 
                                        <td>${l.totalQuantity}</td>
                                        <td>${l.totalHold}</td>
                                        <td>
                                            <button class="btn btn-sm update-status" style="background-color: gold; color: #FFFFFF; white-space: nowrap; padding: 5px 10px;" 
                                                    onclick="window.location.href = 'productdetailforwarehouse?productid=${l.productID}'">
                                                <i class="fas fa-eye"></i> View
                                            </button>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </main>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js"></script>
        <script src="js/scripts.js"></script>
        <script>
                                                        window.addEventListener('DOMContentLoaded', event => {
                                                            const orderTable = document.getElementById('proTable');
                                                            if (orderTable) {
                                                                const savedPage = localStorage.getItem("currentPage");
                                                                const savedSearch = localStorage.getItem('dataTableSearch');
                                                                const savedSort = localStorage.getItem('dataTableSort');
                                                                const dataTable = new simpleDatatables.DataTable(orderTable, {
                                                                    searchable: true,
                                                                    perPage: 5,
                                                                    perPageSelect: false,
                                                                    columns: [
                                                                        {select: 0, sortable: true, searchable: true},
                                                                        {select: 1, sortable: false, searchable: false},
                                                                        {select: 2, sortable: true, searchable: true},
                                                                        {select: 3, sortable: true, searchable: true},
                                                                        {select: 4, sortable: true, searchable: true},
                                                                        {select: 5, sortable: false, searchable: false},
                                                                    ],
                                                                    labels: {
                                                                        noRows: "No results found.",
                                                                        info: "",
                                                                    }
                                                                });

                                                                if (savedPage) {
                                                                    dataTable.page(parseInt(savedPage, 10));
                                                                }

                                                                dataTable.on("datatable.page", function (page) {
                                                                    localStorage.setItem("currentPage", page);
                                                                });

                                                            }
                                                        });
        </script>
    </body>
</html>

