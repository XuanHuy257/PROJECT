<%-- 
    Document   : mkt-sidenav
    Created on : Oct 1, 2024, 11:01:38 AM
    Author     : 
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div id="layoutSidenav_nav">
            <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion" style="background-color: #384aeb;">
                <div class="sb-sidenav-menu">
                    <div class="nav">
                        <div class="sb-sidenav-menu-heading" style="color: white">Core</div>
                        <a class="nav-link" href="list_product_mkt" style="color: white">
                            <div class="sb-nav-link-icon" style="color: white"><i class="fas fa-table"></i></div>
                            List of Products
                        </a>
                        <a class="nav-link" href="customer" style="color: white">
                            <div class="sb-nav-link-icon" style="color: white"><i class="fas fa-table"></i></div>
                            Customer
                        </a>
                         <a class="nav-link" href="posts" style="color: white">
                            <div class="sb-nav-link-icon" style="color: white"><i class="fas fa-table"></i></div>
                            Post
                        </a>
                        <a class="nav-link" href="mkt-feedbacks" style="color: white">
                            <div class="sb-nav-link-icon" style="color: white"><i class="fas fa-table"></i></div>
                            Feedback
                        </a>
                        <a class="nav-link" href="slider_controller" style="color: white">
                            <div class="sb-nav-link-icon" style="color: white"><i class="fas fa-table"></i></div>
                            Slider
                        </a>
                        
                    </div>
                </div>
                <div class="sb-sidenav-footer" style="background-color: #384aeb; color: white">
                    <div class="small">Logged in as:</div>
                    ${sessionScope.account.fullName}
                </div>
            </nav>
        </div>
    </body>
</html>
