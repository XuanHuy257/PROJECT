<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Five Castles Shop - Employee Login</title>
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
        <style>
            .div-container {
                margin-bottom: 10%;
            }
            .popup-overlay {
                display: none;
                position: fixed;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                background: rgba(0, 0, 0, 0.1);
                z-index: 10000;
                pointer-events: all;
            }

            .popup {
                display: none;
                position: fixed;
                top: 50%;
                left: 50%;
                transform: translate(-50%, -50%);
                background: white;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
                z-index: 10001;
                width: 450px;
                height: 150px;
                border: 2px solid #384AEB;
                text-align: center;
            }

            .popup p {
                color: #384AEB; /* Màu tiêu ?? */
                margin-bottom: 10px; /* Kho?ng cách d??i tiêu ?? */
            }

            .close-button {
                position: absolute;
                top: 10px;
                right: 10px;
                background-color: transparent;
                border: none;
                font-size: 18px;
                color: #384AEB;
                cursor: pointer;
            }

            .close-button:hover {
                color: #2e3b9a;
            }
        </style>
        <script>
            function validateEmail() {
                var email = document.getElementById("eemail").value;
                var emailPattern = /^[a-zA-Z0-9._%+-]+@company\.com$/;
                var errorMessage = document.getElementById("error-message");

                if (!emailPattern.test(email)) {
                    errorMessage.textContent = "Invalid email format. Must be a company email.";
                    return false;
                } else {
                    errorMessage.textContent = "";
                    return true;
                }
            }
        </script>
    </head>
    <body>
        <!--================ Start Header Menu Area =================-->
        <%@include file="Header.jsp" %>
        <!--================ End Header Menu Area =================-->
        <!-- ================ start banner area ================= -->	
        <section class="blog-banner-area">
            <div class="container h-100">
                <div class="blog-banner">
                    <div class="text-center">
                        <h1>Login / Register</h1>
                        <nav aria-label="breadcrumb" class="banner-breadcrumb">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item"><a href="#">Home</a></li>
                                <li class="breadcrumb-item active" aria-current="page">Login/Register</li>
                            </ol>
                        </nav>
                    </div>
                </div>
            </div>
        </section>
        <!-- ================ end banner area ================= -->
        <div class="popup-overlay" id="popupOverlay"></div>
        <div class="popup" id="activationPopup">
            <button id="closePopup" class="close-button">
                <i class="fas fa-times"></i> 
            </button>
            <p style="color: #4B5CED; font-weight: bold; font-size: 15px;">Account Suspended!</p>
            <strong style="margin-top: 55px;">Your account is suspended!. Please contact Admin to reactivate your account!.</strong><br>
            <a href="mailto:sontug9xx@gmail.com" style="color: #4B5CED; text-decoration: underline; margin-top: 5px; font-weight: bold;">Contact to Admin</a>

        </div>
        <!--================Login Box Area =================-->
        <section class="login_box_area section-margin">
            <div class="container">
                <div class="row">
                    <div class="col-lg-6">
                        <div class="login_box_img">
                            <div class="hover">
                                <div class="div-container">
                                    <h4>New to our website?</h4>
                                    <p>Welcome, Thank you for joining our fashion family. We can't wait for you to explore our latest collections and exclusive offers</p>
                                    <a class="button button-account" href="register">Create an Account</a>
                                </div>
                                <div>
                                    <h4>Are you a customer?</h4>
                                    <p>Welcome back! It's great to see you again. Ready to find your next favorite outfit? Check out our latest arrivals!</p>
                                    <a class="button button-account" href="login">Login as Customer</a>
                                </div>
                            </div>

                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="login_form_inner">
                            <h3>Hello Staff</h3>
                            <form class="row login_form" action="employeelogin" method="Post" id="contactForm" onsubmit="return validateEmail()">
                                <div class="col-md-12 form-group">
                                    <input type="email" class="form-control" id="eemail" name="eemail" placeholder="Email" onfocus="this.placeholder = ''" value="${cookie.eemail.value}" onblur="this.placeholder = 'Email'" required="">
                                </div>
                                <div class="col-md-12 form-group">
                                    <input type="password" class="form-control" id="name" name="epassword" placeholder="Password" onfocus="this.placeholder = ''" value="${cookie.epass.value}" onblur="this.placeholder = 'Password'" required="">
                                </div>
                                <div class="col-md-12 form-group">
                                </div>
                                <div class="col-md-12 form-group">
                                    <div class="creat_account">
                                        <input type="checkbox" id="f-option2" ${(cookie.erem!=null?'checked':'')} name="eremember">
                                        <label for="f-option2">Keep me logged in</label>
                                    </div>
                                </div>
                                <div class="col-md-12 form-group" id="error-message" style="color: #384AEB; font-weight: bold;">${mess}</div>
                                <div class="col-md-12 form-group">
                                    <button type="submit" value="submit" class="button button-login w-100">Log In</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!--================End Login Box Area =================-->
        <c:if test="${not empty Inactive}">
            <script>
                document.getElementById("popupOverlay").style.display = "block";
                document.getElementById("activationPopup").style.display = "block";
            </script>
        </c:if>
        <script>
            document.getElementById('closePopup').onclick = function () {
                document.getElementById('popupOverlay').style.display = 'none';
                document.getElementById('activationPopup').style.display = 'none';
            };
        </script>
        <!--================ Start footer Area  =================-->	
        <%@include file="Footer.jsp" %>
        <!--================ End footer Area  =================-->
        <script src="vendors/jquery/jquery-3.2.1.min.js"></script>
        <script src="vendors/bootstrap/bootstrap.bundle.min.js"></script>
        <script src="vendors/skrollr.min.js"></script>
        <script src="vendors/owl-carousel/owl.carousel.min.js"></script>
        <script src="vendors/nice-select/jquery.nice-select.min.js"></script>
        <script src="vendors/jquery.ajaxchimp.min.js"></script>
        <script src="vendors/mail-script.js"></script>
        <script src="js/main.js"></script>
    </body>
</html>