<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>User Detail - Five Castles Shop</title>
    <link rel="icon" href="img2/FIcon.png" type="image/png">
    <link rel="stylesheet" href="vendors/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" href="vendors/fontawesome/css/all.min.css">
    <link rel="stylesheet" href="css/style.css">
    <style>
        body {
            background-color: #f8f9fa; /* Light background for contrast */
        }
        .card {
            border: none; /* Remove card border */
            border-radius: 10px; /* Rounded corners */
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1); /* Soft shadow */
        }
        .card-title {
            font-size: 1.5rem; /* Slightly larger title */
            font-weight: 600; /* Bold title */
            margin-bottom: 1rem; /* Space below the title */
        }
        .user-avatar {
            border: 4px solid #007bff; /* Blue border for avatar */
            border-radius: 50%; /* Circular border */
            background: linear-gradient(135deg, rgba(255, 255, 255, 0.8), rgba(0, 123, 255, 0.2)); /* Gradient background */
            padding: 5px; /* Padding around the image */
            display: inline-block; /* Center the avatar */
            transition: transform 0.3s ease; /* Animation effect */
        }
        .user-avatar:hover {
            transform: scale(1.05); /* Slightly enlarge on hover */
        }
        .user-avatar img {
            border-radius: 50%; /* Ensure the image is circular */
            width: 150px; /* Fixed width */
            height: 150px; /* Fixed height */
        }
        .list-unstyled li {
            margin-bottom: 10px; /* Space between list items */
            font-size: 1rem; /* Larger font size for better readability */
        }
        .btn {
            border-radius: 50px; /* Rounded buttons */
            padding: 10px 20px; /* Button padding */
        }
    </style>
</head>
<body>
    <%@include file="Header.jsp" %>

    <main class="site-main">
        <section class="user-detail section-margin">
            <div class="container">
                <div class="section-intro pb-60px">
                    <h2>User <span class="section-intro__style">Details</span></h2>
                </div>
                <div class="row justify-content-center">
    <div class="col-md-8">
        <div class="card">
            <div class="card-body text-left">
                <h4 class="card-title text-center">User Information</h4>
               
                <% String successMessage = (String) request.getAttribute("successMessage"); %>
<% if (successMessage != null) { %>
    <div class="alert alert-success" role="alert">
        <%= successMessage %>
    </div>
<% } %>

                <div class="user-avatar mb-4 text-center">
                    <img src="${user.avatar}" alt="User Avatar" class="img-fluid rounded-circle" style="width: 150px; height: 150px; border: 4px solid #007bff;">
                </div>
                <ul class="list-unstyled">
                    <li class="mb-2"><strong>Full Name:</strong> ${user.fullName}</li>
                    <li class="mb-2"><strong>Email:</strong> ${user.email}</li>
                    <li class="mb-2"><strong>Phone Number:</strong> ${user.phoneNumber}</li>
                    <li class="mb-2"><strong>Gender:</strong> ${user.gender}</li>
                    <li class="mb-2"><strong>Address:</strong> ${user.address}</li>
                </ul>

                <div class="mt-4 text-center">
                  <a href="UpdateUserInfo?id=${user.customerID}" class="btn btn-primary btn-lg">Edit User</a>


                    <a href="home" class="btn btn-secondary btn-lg">Back to Home</a>
                </div>
            </div>
        </div>
    </div>
</div>

            </div>
        </section>
    </main>

    <%@include file="Footer.jsp" %>

    <script src="vendors/jquery/jquery-3.2.1.min.js"></script>
    <script src="vendors/bootstrap/bootstrap.bundle.min.js"></script>
    <script src="js/main.js"></script>
</body>
</html>
