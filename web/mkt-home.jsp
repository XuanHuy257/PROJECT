<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Dashboard - SB Admin</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
        <link href="css/styles.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
    </head>
    <body class="sb-nav-fixed">
        <jsp:include page="mkt-header.jsp"></jsp:include>
            <div id="layoutSidenav">
            <jsp:include page="mkt-sidenav.jsp"></jsp:include>
            <div id="layoutSidenav_content">
                 <main>
                <div class="container-fluid px-4">
                    <h1 class="mt-4">Dashboard</h1>
                    <ol class="breadcrumb mb-4">
                        <li class="breadcrumb-item active">Dashboard</li>
                    </ol>
                    
                    <!-- Statistics Section -->
                    <div class="row">
    <div class="col-lg-3 col-md-6">
        <div class="card bg-primary text-white mb-4">
            <div class="card-body">Total Posts: ${postCount}</div>
        </div>
    </div>
    <div class="col-lg-3 col-md-6">
        <div class="card bg-warning text-white mb-4">
            <div class="card-body">Total Products: ${productCount}</div>
        </div>
    </div>
    <div class="col-lg-3 col-md-6">
        <div class="card bg-success text-white mb-4">
            <div class="card-body">Total Customers: ${customerCount}</div>
        </div>
    </div>
    <div class="col-lg-3 col-md-6">
        <div class="card bg-danger text-white mb-4">
            <div class="card-body">Total Feedbacks: ${feedbackCount}</div>
        </div>
    </div>
</div>

                    
                    <!-- Customer Trend Chart Section -->
                     <div class="row">
        <div class="col-xl-12">
            <div class="card mb-4">
                <div class="card-header">
                    <i class="fas fa-chart-area me-1"></i>
                    New Customers Trend (Last 7 Days)
                </div>
                <div class="card-body">
                    <canvas id="customerTrendChart" width="100%" height="40"></canvas>
                </div>
            </div>
        </div>
    </div>
                </div>
    </main>
    <footer class="py-4 bg-light mt-auto">
        <div class="container-fluid px-4">
            <div class="d-flex align-items-center justify-content-between small">
                <div class="text-muted">Copyright &copy; Your Website 2023</div>
                <div>
                    <a href="#">Privacy Policy</a>
                    &middot;
                    <a href="#">Terms &amp; Conditions</a>
                </div>
            </div>
        </div>
    </footer>
</div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
<script src="js/scripts.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
<script src="assets/demo/chart-area-demo.js"></script>
<script src="assets/demo/chart-bar-demo.js"></script>
<script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
<script src="js/datatables-simple-demo.js"></script>
</body>

<script>
        document.addEventListener("DOMContentLoaded", function() {
            console.log("DOM fully loaded and parsed.");
            const canvas = document.getElementById('customerTrendChart');
            console.log(canvas); // Log canvas

            if (!canvas) {
                console.error('Canvas element not found!');
                return;
            }

            const ctx = canvas.getContext('2d');
            if (!ctx) {
                console.error('Could not get canvas context. Check the canvas element.');
                return;
            }

            const newCustomerCounts = [<c:forEach var="count" items="${newCustomerCounts}">${count},</c:forEach>];
            console.log('New Customer Counts:', newCustomerCounts); // Debug log

            if (newCustomerCounts.length === 0) {
                console.error('No data available for the chart.');
                return; // Prevent chart creation if no data
            }

            const chart = new Chart(ctx, {
                type: 'bar',
                data: {
                    labels: ['Day 1', 'Day 2', 'Day 3', 'Day 4', 'Day 5', 'Day 6', 'Day 7'],
                    datasets: [{
                        label: 'New Customers',
                        data: newCustomerCounts,
                        borderColor: 'rgba(75, 192, 192, 1)',
                        borderWidth: 2,
                        fill: false
                    }]
                },
                options: {
                    responsive: true,
                    scales: {
                        y: {
                            beginAtZero: true
                        }
                    }
                }
            });
        });
    </script>

</html>
