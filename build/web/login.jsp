<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Aroma - Login</title>
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
                height: 200px;
                border: 2px solid #384AEB;
                text-align: center;
            }

            .popup h4 {
                color: #384AEB; /* Màu tiêu ?? */
                margin-bottom: 10px; /* Kho?ng cách d??i tiêu ?? */
            }

            .popup p {
                margin-bottom: 20px; /* Kho?ng cách d??i ?o?n v?n */
            }

            /* ??t ki?u cho liên k?t kích ho?t tài kho?n */
            .popup a {
                display: inline-block; /* Hi?n th? link nh? nút */
                padding: 10px 20px; /* Padding cho nút */
                background-color: #384AEB; /* N?n màu ch? ??o */
                color: white; /* Màu ch? tr?ng */
                text-decoration: none; /* Không có g?ch chân */
                border-radius: 5px;
                transition: background-color 0.3s;
            }

            .popup a:hover {
                background-color: white;
                color: #384AEB;
                border-radius: 8px;
                border: 2px solid #384AEB;
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
    </style>
</head>
<body>
    <!--================ Start Header Menu Area =================-->
    <%@include file="Header.jsp" %>
    <!--================ End Header Menu Area =================-->

    <!-- ================ start banner area ================= -->	
    <section class="blog-banner-area" id="category">
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
    <div class="popup" style="${mess == 'Fail' ? 'height: 150px;' : ''}" id="activationPopup">
        <button id="closePopup" class="close-button">
            <i class="fas fa-times"></i> 
        </button>
        <h4>Your account is not activated!</h4>
        <p style="margin-top: 20px;">Please activate your account to continue using our services.</p>
        <a href="activecustomer" id="needcancel" class="activation-link">Click here to activate your account</a>
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
                                <h4>Are you an staff?</h4>
                                <p>Welcome back, Staff! We're excited to have you on board again. Let's make today a great day for our customers</p>
                                <a class="button button-account" href="employeelogin">Login as Staff</a>
                            </div>
                        </div>

                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="login_form_inner">
                        <h3>Log in to enter</h3>
                        <form class="row login_form" action="login" method="Post" id="contactForm" >
                            <div class="col-md-12 form-group">
                                <input type="email" class="form-control" id="name" name="cemail" placeholder="Email" onfocus="this.placeholder = ''" value="${cookie.cemail.value}" onblur="this.placeholder = 'Email'">
                            </div>
                            <div class="col-md-12 form-group">
                                <input type="password" class="form-control" id="name" name="cpassword" placeholder="Password" onfocus="this.placeholder = ''" value="${cookie.cpass.value}" onblur="this.placeholder = 'Password'">
                            </div>
                            <div class="col-md-12 form-group">
                                <div class="creat_account">
                                    <input type="checkbox" id="f-option2" ${(cookie.crem!=null?'checked':'')} name="cremember">
                                    <label for="f-option2">Keep me logged in</label>
                                </div>
                            </div>
                            <div class="col-md-12 form-group" style="color: #384AEB; font-weight: bold;">${mess}</div>
                            <div class="col-md-12 form-group">
                                <button type="submit" value="submit" class="button button-login w-100">Log In</button>
                                <a href="forgotpassword">Forgot Password?</a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!--================End Login Box Area =================-->
    <!--================ Start footer Area  =================-->	
    <%@include file="Footer.jsp" %>
    <!--================ End footer Area  =================-->
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
    <script>
        document.getElementById("needcancel").onclick = function (event) {
            this.style.pointerEvents = "none";
        };
    </script>
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