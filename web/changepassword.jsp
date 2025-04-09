<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Change Password - Change Password</title>
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
            function validateForm() {
                var password = document.getElementById("newpassword").value;
                var confirmPassword = document.getElementById("confirmnewpassword").value;

                if (password !== confirmPassword) {
                    document.getElementById("Fail1").innerHTML = "Confirm passwords do not match!";
                    return false;
                } else {
                    document.getElementById("Fail1").innerHTML = "";
                    return true;
                }
            }

            function duplicateForm() {
                var oldpassword = document.getElementById("currentpassword").value;
                var newpassword = document.getElementById("newpassword").value;

                if (oldpassword === newpassword) {
                    document.getElementById("Fail2").innerHTML = "New password cannot be the same as old password!";
                    return false;
                } else {
                    document.getElementById("Fail2").innerHTML = "";
                    return true;
                }
            }

            function onSubmitForm() {
                var isValid = validateForm();
                var isDuplicate = duplicateForm();
                return isValid && isDuplicate;
            }
        </script>
    </head>
    <body>
        <!--================ Start Header Menu Area =================-->
        <%@include file="Header.jsp" %>
        <!--================ End Header Menu Area =================-->
        <!--================Login Box Area =================-->
        <section class="login_box_area section-margin">
            <div class="container">
                <div class="row">                    
                    <div class="col-lg-12">
                        <div class="login_form_inner">
                            <h3>${sessionScope.account.fullName}</h3>
                            <h3>Change Password</h3>
                            <form class="row login_form" action="changepassword" method="Post" onsubmit="return onSubmitForm()" id="contactForm" >
                                <div class="col-md-12 form-group">
                                    <input type="password" class="form-control" id="currentpassword" name="currentpassword" placeholder="Current Password" onfocus="this.placeholder = ''" value="${currentp}" onblur="this.placeholder = 'Current Password'" required="">
                                </div>
                                <div class="col-md-12 form-group">
                                    <input type="password" class="form-control" id="newpassword" name="newpassword" placeholder="New Password" onfocus="this.placeholder = ''" value="${newp}" onblur="this.placeholder = 'New Password'" required="">
                                </div>
                                <div class="col-md-12 form-group">
                                    <input type="password" class="form-control" id="confirmnewpassword" name="confirmnewpassword" placeholder="Confirm New Password" onfocus="this.placeholder = ''" value="${confirmp}" onblur="this.placeholder = 'Confirm New Password'" required="">
                                </div>                              
                                <div class="col-md-12 form-group" id="Fail1" style="color: #384AEB; font-weight: bold;">
                                </div>
                                <div class="col-md-12 form-group" id="Fail2" style="color: #384AEB; font-weight: bold;">
                                </div>
                                <div class="col-md-12 form-group" style="color: #384AEB; font-weight: bold;">${mess}</div>                               
                                <div class="col-md-12 form-group">
                                    <button type="submit" value="submit" class="button button-login w-100">Save Changes</button>
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