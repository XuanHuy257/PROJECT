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
        <title>Dashboard - Admin</title>

        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
        <link href="css/styles.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    </head>
    <body class="sb-nav-fixed">
        <nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
            <!-- Navbar Brand-->
            <a class="navbar-brand ps-3" href="AdminDashBoard">Admin</a>
            <!-- Sidebar Toggle-->
            <button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle"><i class="fas fa-bars"></i></button>        <!-- Navbar-->
            <form class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0">
                <div class="input-group">


                </div>
            </form>
            <ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="settinglist">Settings</a></li>
                        <li><a class="dropdown-item" href="#!">Activity Log</a></li>
                        <li><hr class="dropdown-divider" /></li>
                        <li><a class="dropdown-item" href="logout">Logout</a></li>
                    </ul>
                </li>
            </ul>
        </nav>

        <div id="layoutSidenav">
            <div id="layoutSidenav_nav">
                <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
                    <div class="sb-sidenav-menu">
                        <div class="nav">
                            <div class="sb-sidenav-menu-heading">Core</div>
                            <a class="nav-link" href="userlist">
                                <div class="sb-nav-link-icon"><i class="fas fa-table"></i></div>
                                User
                            </a>
                            <a class="nav-link" href="settinglist">
                                <div class="sb-nav-link-icon"><i class="fas fa-cog"></i></div>
                                Settings
                            </a>
                        </div>
                    </div>
                    <div class="sb-sidenav-footer">
                        <div class="small">Logged in as :</div>
                         ${sessionScope.account.fullName}
                    </div>
                </nav>
            </div>
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <h1 class="mt-4">Dashboard</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item active">Dashboard</li>
                        </ol>

                        <!-- Order Statistics -->
                        <div class="row">
                            <div class="col-xl-4 col-md-6">
                                <div class="card bg-primary text-white mb-4">
                                    <div class="card-body">
                                        Success Orders: ${orderStats.successOrders}
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-4 col-md-6">
                                <div class="card bg-warning text-white mb-4">
                                    <div class="card-body">
                                        Cancelled Orders: ${orderStats.cancelledOrders}
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-4 col-md-6">
                                <div class="card bg-success text-white mb-4">
                                    <div class="card-body">
                                        Submitted Orders: ${orderStats.submittedOrders}
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Revenue Statistics -->
                        <div class="row">

                            <div class="col-xl-6">
                                <div class="card mb-4">
                                    <div class="card-header">
                                        <i class="fas fa-chart-area me-1"></i>
                                        Revenue by Category
                                    </div>

                                    <div class="card-body">
                                        <!-- Safely handle the revenue attribute, providing a default value of 0 if it's null -->
                                        <h5 style="color: green;">Total Revenue: $<c:out value="${totalRevenue}"/></h5>

                                        <canvas id="categoryRevenueChart" width="50 %" height="42"></canvas> <!-- Canvas for the chart -->
                                    </div>
                                </div>
                            </div>

                            <!-- Customer Statistics -->
                            <div class="col-xl-6">
                                <div class="card mb-4">
                                    <div class="card-header">
                                        <i class="fas fa-users me-1"></i>
                                        Customers
                                    </div>
                                    <div class="card-body">
                                        <p>Newly Registered: ${newCustomers}</p>
                                        <p>Newly Bought: ${newlyBoughtCustomers}</p>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Feedback Statistics -->
                       <div class="row">
    <!-- Feedback Statistics -->
    <div class="col-xl-6">
        <div class="card mb-4">
            <div class="card-header d-flex align-items-center">
                <i class="fas fa-star me-2"></i>
                <span>Feedback Statistics</span>
            </div>
            <div class="card-body">
                <canvas id="feedbackChart" width="100%" height="40"></canvas>
            </div>
        </div>
    </div>

    <!-- Order Trend -->
    <div class="col-xl-6">
        <div class="card mb-4">
            <div class="card-header d-flex align-items-center">
                <i class="fas fa-chart-line me-2"></i>
                <span>Order Trend</span>
            </div>
            <div class="card-body">
                <canvas id="orderTrendChart" width="100%" height="40"></canvas>
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

        <!-- Chart.js Scripts -->

        <!-- Feedback Chart -->
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <script>
            document.addEventListener('DOMContentLoaded', function () {
                // Prepare data for the Feedback chart
                const categories = [];
                const averageStars = [];
                const totalFeedbacks = [];

            <c:forEach var="feedbackStat" items="${feedbackStatsList}">
                categories.push('${feedbackStat.categoryName}');
                averageStars.push(${feedbackStat.averageStar});
                totalFeedbacks.push(${feedbackStat.totalFeedback});
            </c:forEach>

                // Chart configuration for Feedback Statistics
                const ctxFeedback = document.getElementById('feedbackChart').getContext('2d');
                const feedbackChart = new Chart(ctxFeedback, {
                    type: 'bar', // Change this to 'line' for a line chart
                    data: {
                        labels: categories,
                        datasets: [
                            {
                                label: 'Average Star Rating',
                                data: averageStars,
                                backgroundColor: 'rgba(75, 192, 192, 0.6)',
                                borderColor: 'rgba(75, 192, 192, 1)',
                                borderWidth: 1
                            },
                            {
                                label: 'Total Feedback Count',
                                data: totalFeedbacks,
                                backgroundColor: 'rgba(153, 102, 255, 0.6)',
                                borderColor: 'rgba(153, 102, 255, 1)',
                                borderWidth: 1
                            }
                        ]
                    },
                    options: {
                        responsive: true,
                        scales: {
                            y: {
                                beginAtZero: true,
                                title: {
                                    display: true,
                                    text: 'Counts'
                                }
                            }
                        },
                        plugins: {
                            legend: {
                                position: 'top'
                            },
                            title: {
                                display: true,
                                text: 'Feedback Statistics'
                            }
                        }
                    }
                });


            });

        </script>

        <script>
            const ctx = document.getElementById('orderTrendChart').getContext('2d');
            const labels = [];
            const totalOrders = [];
            const successfulOrders = [];

            <c:forEach var="trend" items="${orderTrends}">
            labels.push('${trend.orderDate}');
            totalOrders.push(${trend.totalOrders});
            successfulOrders.push(${trend.successfulOrders});
            </c:forEach>

            const orderTrendChart = new Chart(ctx, {
                type: 'bar',
                data: {
                    labels: labels,
                    datasets: [{
                            label: 'Total Orders',
                            data: totalOrders,
                            backgroundColor: 'rgba(75, 192, 192, 0.6)', // Set background color for total orders
                            borderColor: 'rgba(75, 192, 192, 1)',
                            borderWidth: 1,
                            fill: true, // Fill the bars
                        }, {
                            label: 'Successful Orders',
                            data: successfulOrders,
                            backgroundColor: 'rgba(255, 99, 132, 0.6)', // Set background color for successful orders
                            borderColor: 'rgba(255, 99, 132, 1)',
                            borderWidth: 1,
                            fill: true, // Fill the bars
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
        </script>


        <script>
            // Retrieve revenue data from JSP (parse it to a JavaScript object)
            const revenueData = JSON.parse('${revenueJson}');

            // Debugging line: Log the parsed data to console
            console.log(revenueData);

            // Extract category names and revenues
            const categories = revenueData.map(data => data.categoryName);
            const revenues = revenueData.map(data => data.revenue);

            // Create a bar chart using Chart.js
            const ctx = document.getElementById('categoryRevenueChart').getContext('2d');
            const categoryRevenueChart = new Chart(ctx, {
                type: 'bar',
                data: {
                    labels: categories,
                    datasets: [{
                            label: 'Revenue',
                            data: revenues,
                            backgroundColor: 'rgba(54, 162, 235, 0.2)',
                            borderColor: 'rgba(54, 162, 235, 1)',
                            borderWidth: 1
                        }]
                },
                options: {
                    scales: {
                        y: {
                            beginAtZero: true,
                            title: {
                                display: true,
                                text: 'Revenue (Currency)'
                            }
                        },
                        x: {
                            title: {
                                display: true,
                                text: 'Categories'
                            }
                        }
                    },
                    responsive: true,
                    maintainAspectRatio: false
                }
            });
        </script>
        <!-- Bootstrap JS and dependencies -->
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js"></script>
        <script src="js/scripts.js"></script>
    </body>
</html>
