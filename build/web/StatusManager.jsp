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
        <title>Orders For Sales</title>       
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
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">

        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f1f3f4;
            }

            .container {
                margin-top: 50px;
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
                font-size: 20px;
                margin-right: 5px;
            }
            .filters div {
                margin-bottom: 10px;
                flex: 1;
                margin-right: 10px;
            }

            .filters div:last-child {
                margin-right: 0;
            }
            .overlay {
                position: fixed;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                background-color: rgba(0, 0, 0, 0.5);
                z-index: 8;
            }
            .form-popup {
                position: fixed;
                bottom: 0;
                right: 15px;
                border: 3px solid #f1f1f1;
                z-index: 9;
                background-color: white;
                padding: 20px;
                box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
                border-radius: 10px;
                width: 300px;
            }

            .form-container {
                display: flex;
                flex-direction: column;
            }

            .form-container input[type="text"],
            .form-container textarea {
                width: 100%;
                padding: 10px;
                margin: 5px 0 15px 0;
                border: 1px solid #ccc;
                border-radius: 5px;
                box-sizing: border-box;
            }

            .form-container button {
                padding: 10px;
                border: none;
                border-radius: 5px;
                cursor: pointer;
            }

            .btn-primary {
                background-color: #007bff;
                color: white;
                margin-bottom: 10px;
            }

            .btn-secondary {
                background-color: #6c757d;
                color: white;
            }
        </style>
        <script>
            function addStatus() {
                document.getElementById('overlay').style.display = 'block';
                document.getElementById('addStatusForm').style.display = 'block';
            }

            function editStatus(id, name, description) {
                document.getElementById('overlay').style.display = 'block';
                document.getElementById('editStatusId').value = id;
                document.getElementById('editStatusName').value = name;
                document.getElementById('editStatusDescription').value = description;
                document.getElementById('editStatusForm').style.display = 'block';
            }

            function closeAddForms() {
                document.getElementById('overlay').style.display = 'none';
                document.getElementById('addStatusForm').style.display = 'none';
            }
            function closeEditForms() {
                document.getElementById('overlay').style.display = 'none';
                document.getElementById('editStatusForm').style.display = 'none';
            }
        </script>
    </head>
    <body>
        <div class="container col-lg-10">
            <h3>Status Management</h3>
            <div >
                <button class="btn btn-success" onclick="addStatus()">
                    <i class="fas fa-plus-circle"></i> Add Status
                </button>
            </div>
            <div id="overlay" class="overlay" style="display: none;"></div>

            <div id="addStatusForm" class="form-popup" style="display: none;">
                <form class="form-container" action="managerstatus" method="post">
                    <strong><p style="color: #007bff;">Add New Status</p></strong>

                    <label for="statusName"><a>Status Name</a></label>
                    <input type="text" id="statusName" name="statusname" placeholder="Enter Status Name" required>

                    <label for="statusDescription"><a>Status Description</a></label>
                    <textarea id="statusDescription" name="statusdescription" placeholder="Enter Status Description" required></textarea>

                    <button type="submit" class="btn btn-primary" >Save</button>
                    <button type="button" class="btn btn-secondary" onclick="closeAddForms()">Close</button>
                </form>
            </div>
            <div id="editStatusForm" class="form-popup" style="display: none;">
                <form class="form-container" action="managerstatus" method="post">
                    <strong><p style="color: #007bff;">Edit Status</p></strong>

                    <input type="hidden" id="editStatusId" name="statusid" > 
                    <input type="hidden" name="action" value="edit" > 
                    <label for="editStatusName"><b>Status Name</b></label>
                    <input type="text" id="editStatusName" name="statusname" placeholder="Enter Status Name" required>

                    <label for="editStatusDescription"><b>Status Description</b></label>
                    <textarea id="editStatusDescription" name="statusdescription" placeholder="Enter Status Description" required></textarea>

                    <button type="submit" class="btn btn-primary" >Save</button>
                    <button type="button" class="btn btn-secondary" onclick="closeEditForms()">Close</button>
                </form>
            </div>
            <!-- Orders Table -->
            <table class="table table-bordered" id="orderTable">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Status Name</th>
                        <th>Status Description</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="s" items="${liststatus}">
                        <tr>
                            <td>${s.attributeID}</td>
                            <td>${s.attributeName}</td>                         
                            <td>${s.attributeDescription}</td>
                            <td>
                                <a style="color: #007bff; cursor: pointer;" onclick="editStatus('${s.attributeID}', '${s.attributeName}', '${s.attributeDescription}')">
                                    <i class="fas fa-edit"></i> Edit
                                </a>
                                <form action="managerstatus" method="post" style="display: inline;">
                                    <input type="hidden" name="statusid" value="${s.attributeID}">
                                    <input type="hidden" name="action" value="delete">
                                    <button type="submit" style="color: red; border: none; background: none; cursor: pointer; margin-left: 10px;">
                                        <i class="fas fa-trash-alt"></i> Delete
                                    </button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
        <script src="vendors/jquery/jquery-3.2.1.min.js"></script>
        <script src="vendors/bootstrap/bootstrap.bundle.min.js"></script>
        <script>
                                    window.addEventListener('DOMContentLoaded', event => {
                                        const datatablesSimple = document.getElementById('orderTable');
                                        if (datatablesSimple) {
                                            const dataTable = new simpleDatatables.DataTable(datatablesSimple, {
                                                searchable: true,
                                                perPage: 5,
                                                perPageSelect: false,
                                                labels: {
                                                    info: "" // Tùy chỉnh thông báo dòng hiện tại
                                                },
                                                columns: [
                                                    {select: 0, sortable: true, searchable: true},
                                                    {select: 1, sortable: true, searchable: true},
                                                    {select: 2, sortable: false, searchable: true},
                                                    {select: 3, sortable: false, searchable: false},
                                                ],
                                            });
                                        }
                                    });
        </script>
    </body>
</html>
