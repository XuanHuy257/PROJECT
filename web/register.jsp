<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Aroma - Register</title>
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
        <script>
            function validateEmail() {
                var email = document.getElementById("email").value;
                var emailPattern = /^[a-zA-Z0-9._%+-]+@company\.com$/;
                var errorMessage = document.getElementById("error-message");

                if (!emailPattern.test(email)) {
                    errorMessage.textContent = "";
                    return true;
                } else {
                    errorMessage.textContent = "Invalid email format.Email must be different from the company email.";
                    return false;
                }
            }
        </script>
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
                        <h1>Register</h1>
                        <nav aria-label="breadcrumb" class="banner-breadcrumb">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item"><a href="home">Home</a></li>
                                <li class="breadcrumb-item active" aria-current="page">Register</li>
                            </ol>
                        </nav>
                    </div>
                </div>
            </div>
        </section>
        <!-- ================ end banner area ================= -->

        <!--================Login Box Area =================-->
        <section class="login_box_area section-margin">
            <div class="container">
                <div class="row">
                    <div class="col-lg-6">
                        <div class="login_box_img">
                            <div class="hover">
                                <h4>Already have an account?</h4>
                                <p>Welcome back! It's great to see you again. Ready to find your next favorite outfit? Check out our latest arrivals!</p>
                                <a class="button button-account" href="login">Login Now</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="login_form_inner register_form_inner">
                            <h3>Create an account</h3>
                            <form class="row login_form" action="register" method="Post" id="register_form" onsubmit="return validateEmail()">
                                <div class="col-md-12 form-group">
                                    <input type="text" class="form-control" id="name" name="fullname" value="${requestScope.fullname}" placeholder="Full Name" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Full Name'" required>
                                </div>
                                <div class="col-md-12 form-group">
                                    <input type="email" class="form-control" id="email" name="email" value="${requestScope.email}" placeholder="Email" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Email'" required>
                                </div>
                                <div style="color: #4B5CED; margin-left: 20px; font-weight: bold;" id="error-message">
                                    ${ErrorEmail}
                                </div>
                                <div class="col-md-12 form-group">
                                    <input type="password" class="form-control" id="password" name="password" value="${requestScope.rawpassword}" placeholder="Password" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Password'" required>
                                </div>
                                <div class="col-md-12 form-group">
                                    <select  id="gender" name="gender" required>
                                        <option value="" disabled selected>Select your gender</option>
                                        <option value="Male" ${"Male".equals(requestScope.gender) ? "selected" : ""}>Male</option>
                                        <option value="Female" ${"Female".equals(requestScope.gender) ? "selected" : ""} >Female</option>
                                    </select>

                                </div>
                                <div class="col-md-12 form-group">
                                    <input type="tel" pattern="^[0-9]{10}$" class="form-control" id="phonenumber" name="phonenumber" value="${requestScope.phonenumber}" placeholder="Mobile" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Phone Number'" required>
                                </div>
                                <div class="col-md-12 form-group">
                                    <input type="text" class="form-control" id="Address" name="address" value="${requestScope.address}" placeholder="Address" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Address'" required=""> 
                                </div>                               
                                <div class="col-md-12 form-group">
                                    <button type="submit" class="button button-register w-100">Register</button>
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