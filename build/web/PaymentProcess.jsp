<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Aroma Shop - Payment</title>
        <link rel="icon" href="img/Fevicon.png" type="image/png">
        <link rel="stylesheet" href="vendors/bootstrap/bootstrap.min.css">
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <%@include file="Header.jsp" %>

        <div class="container" style="margin-top: 20px;">
            <h2>Payment Information</h2>          
            <hr>
            <div class="transaction-info" style="border-radius: 5px; padding: 15px; color: black;">
                <p><strong>Transaction ID:</strong> ${vnp_TxnRef}</p>
                <p><strong>Amount:</strong> ${vnp_Amount} VNĐ</p>
                <p><strong>Bank Code:</strong> ${vnp_BankCode}</p>
                <p><strong>Order Information:</strong> ${vnp_OrderInfo}</p>
                <p><strong>Transaction Status:</strong> 
                    <c:choose>
                        <c:when test="${vnp_TransactionStatus == '00'}">
                            <span style="color: #00AB55;">Transaction Successful</span>
                        </c:when>
                        <c:otherwise>
                            <span style="color: red;">Transaction Failed</span>
                        </c:otherwise>
                    </c:choose>
                </p> 
                <c:if test="${vnp_TransactionStatus != '00'}">
                    <div class="alert alert-danger">
                        <strong style="color: red;">Notice:</strong> Your order has been <strong>Rejected</strong> due to a payment failure.
                        <br>
                        Please check your payment information to ensure your account has sufficient balance or that your credit card details are accurate.                      
                    </div>
                    <span style="color: green; font-weight: bold;">We encourage you to reorder the product so you don't miss out on this fantastic item!</span>
                </c:if>
            </div>
            <hr>
            <div style="margin-top: 20px;">
                <c:choose>
                    <c:when test="${vnp_TransactionStatus == '00'}">
                        <button onclick="window.location.href = 'cartcompletion'" class="btn btn-primary" style="width: 25%; margin-bottom: 20px;">Continue</button>
                    </c:when>
                    <c:otherwise>
                        <button onclick="window.location.href = 'order_customer'" class="btn btn-primary" style="width: 25%; margin-bottom: 20px;">Continue</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>

        <%@include file="Footer.jsp" %>

        <script src="vendors/jquery/jquery-3.2.1.min.js"></script>
        <script src="vendors/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>
