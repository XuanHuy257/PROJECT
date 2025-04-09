<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Edit User - Five Castles Shop</title>
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
                
                border-radius: 50%; /* Circular border */
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
            .form-group {
                margin-bottom: 1rem; /* Space between form groups */
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
            <section class="edit-user section-margin">
                <div class="container">
                    <div class="section-intro pb-60px">
                        <h2>Edit <span class="section-intro__style">User Profile</span></h2>
                    </div>
                    <div class="row justify-content-center">
                        <div class="col-md-8">
                            <div class="card">
                                <div class="card-body text-left">
                                    <h4 class="card-title text-center">Edit User Information</h4>


<form id="avatarForm" enctype="multipart/form-data" method="post" action="uploadAvatarServlet">
    <div class="user-avatar mb-4 text-center position-relative">
        <label for="avatarInput" class="position-relative">
            <img id="avatarPreview" src="${user.avatar}" alt="User Avatar" class="img-fluid rounded-circle" 
                 style="width: 150px; height: 150px; border: 4px solid #007bff; object-fit: cover; cursor: pointer;">
            <span class="edit-text-overlay">Click to edit avatar</span>
        </label>
        <input type="file" id="avatarInput" name="avatar" style="display: none;" accept="image/*" onchange="previewImage(event)">
    </div>
    
<!--                 <button type="submit" class="btn btn-primary">Update Avatar</button>-->
</form>



             <style>
.user-avatar {
    position: relative;
}

.edit-text-overlay {
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    color: white;
    background-color: rgba(0, 0, 0, 0.6);
    padding: 5px 10px;
    border-radius: 8px;
    font-size: 14px;
    opacity: 0;
    transition: opacity 0.3s ease-in-out;
    pointer-events: none;
}

.user-avatar label:hover .edit-text-overlay {
    opacity: 1;
}
</style>





<!-- Form to upload the new avatar -->

<script>
    
    function previewImage(event) {
    const avatarPreview = document.getElementById('avatarPreview');
    avatarPreview.src = URL.createObjectURL(event.target.files[0]);
}

</script>


                                    <form action="UpdateUserInfo" method="post">
                                        <input type="hidden" name="customerID" value="${user.customerID}">
                                        <div class="form-group">
                                            <label for="fullName">Full Name</label>
                                            <input type="text" class="form-control" id="fullName" name="fullName" value="${user.fullName}" maxlength="25" required>
                                        </div>
                                        <div class="form-group">
                                            <label for="email">Email</label>
                                            <input type="email" class="form-control" id="email" name="email" value="${user.email}" required readonly>
                                            <small class="form-text text-muted">Note: you are not allowed to change the email.</small>
                                        </div>


<div class="form-group">
    <label for="phoneNumber">Phone Number</label>
    <input type="text" class="form-control" id="phoneNumber" name="phoneNumber" value="${user.phoneNumber}" maxlength="11" required>
    <small id="phoneError" class="form-text text-danger" style="display:none;">Phone number must be 11 digits or less.</small>
</div>
  


                                        <div class="form-group">
                                            <label for="gender">Gender</label>
                                            <select class="form-control" id="gender" name="gender" required>
                                                <option value="Male" ${user.gender == 'Male' ? 'selected' : ''}>Male</option>
                                                <option value="Female" ${user.gender == 'Female' ? 'selected' : ''}>Female</option>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label for="address">Address</label>
                                            <input type="text" class="form-control" id="address" name="address" value="${user.address}" maxlength="50" required>
                                        </div>

                                        <div class="mt-4 text-center">
                                            <button type="submit" class="btn btn-primary btn-lg">Update User</button>
                                            <a href="UserDetailServlet?id=${sessionScope.account.customerID}" class="btn btn-secondary btn-lg">Cancel</a>
                                        </div>
                                    </form>
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
