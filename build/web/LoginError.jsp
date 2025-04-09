<%-- 
    Document   : LoginError
    Created on : 8 Nov 2024, 12:34:51
    Author     : Anh Tuan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
        <title>Login Error</title>
    <style>
            :root {
                --primary-color: #0A68FF;
                --white-color: #ffffff;
            }
            .cart_contact_area {
                max-width: 1000px;
                width: 100%;
            }

            .error-card {
                padding: 50px;
                border-radius: 10px;
                text-align: center;
                width: 100%; 
                max-width: 800px; 
                margin: 0 auto; 
            }

            .error-icon {
                font-size: 40px;
                color: var(--primary-color);
                margin-bottom: 10px;
            }

            .error-title {
                font-size: 24px;
                font-weight: bold;
                color: var(--primary-color);
                margin-bottom: 15px;
            }

            .error-message {
                font-size: 16px;
                color: #333;
                margin-bottom: 20px;
            }

            .btn-primary-custom {
                display: inline-block;
                padding: 10px 20px;
                font-size: 16px;
                color: var(--white-color);
                background-color: var(--primary-color);
                border-radius: 5px;
                text-decoration: none;
                transition: background-color 0.3s ease;
            }

            .btn-primary-custom:hover {
                background-color: #084bbf;
            }

            .footer-note {
                font-size: 14px;
                color: #666;
                margin-top: 20px;
            }
        </style>
    </head>
    <body>
        <%@include file="Header.jsp" %>
        <div style="display: flex;
             flex-direction: column;
             justify-content: center;
             align-items: center;
             margin-top: 20px;
             margin-bottom: 20px;">

            <section class="cart_contact_area">
                <div class="error-card" style="padding: 20px; border: 2px solid #ff0000; border-radius: 10px; background-color: #fff3f3; box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1); height: 500px; justify-content: center;">
                    <div class="error-icon" style="font-size: 50px; margin-bottom: 10px; margin-top: 60px;"><i class="fas fa-user-lock"></i></i></div>
                    <div class="error-title" style="font-size: 24px; font-weight: bold; color: #d9534f;">Please Log In to Continue</div>
                    <p class="error-message" style="font-size: 18px; line-height: 1.5; color: #555;">
                        This feature is only accessible to logged-in users. Please log in.
                    </p>
                    <a href="login" class="btn btn-primary btn-primary-custom" style="padding: 10px 20px; font-size: 18px; background-color: #007bff; color: white; border-radius: 5px; text-decoration: none; margin-top: 25px;">
                        Go to Login
                    </a>
                    <div class="footer-note" style="margin-top: 85px; font-size: 16px; color: #777;">
                        We apologize for the inconvenience and thank you for your understanding.
                    </div>
                </div>
            </section>
        </div>

        <%@include file="Footer.jsp" %>
    </body>
</html>