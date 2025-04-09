<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Aroma - Reset Password</title>
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
            .code {
                caret-color: transparent;
                border-radius: 5px;
                font-size: 25px;
                height: 75px;
                width: 600px;
                border: 1px solid #eee;
                margin: 1rem;
                text-align: center;
                font-weight: 100;
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

            .popup h4 {
                color: #384AEB;
                margin-bottom: 10px;
            }

            .popup p {
                margin-bottom: 20px;
            }

            .popup a {
                display: inline-block;
                padding: 10px 20px;
                background-color: #384AEB;
                color: white;
                text-decoration: none;
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
        </style>
        <script>
            function validateForm() {
                var password = document.getElementById("newpassword").value;
                var confirmPassword = document.getElementById("confirmpassword").value;

                if (password !== confirmPassword) {
                    document.getElementById("Fail3").innerHTML = "Confirm passwords do not match!";
                    return false;
                } else {
                    document.getElementById("Fail3").innerHTML = "";
                    return true;
                }
            }
        </script> 
    </head>
    <body>
        <%@include file="Header.jsp" %>
        <!--================Login Box Area =================-->
        
        <div class="popup-overlay" id="popupOverlay"></div>
        <div class="popup" id="activationPopup">
            <c:if test="${status == 'success'}">
                <p style="color: #4B5CED; font-weight: bold; font-size: 15px;">Reset Password Success</p>
                 <button class="close-button" id="countdown">
                    5 
                </button>
                <script>
                    let countdown = 5;
                    const countdownDisplay = document.getElementById('countdown');
                    const redirectInterval = setInterval(() => {
                        countdown--;
                        countdownDisplay.innerText = countdown;
                        if (countdown <= 0) {
                            clearInterval(redirectInterval);
                            window.location.href = 'login';
                        }
                    }, 1000);
                </script>
                <strong style="margin-top: 40px; display: block;">Password changed successfully. You will be redirected to the login page in 5 seconds.</strong>
            </c:if>
        </div>
        <section class="login_box_area section-margin">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="login_box_img">
                            <div class="hover">
                                <div class="div-container">
                                    <div class="container">
                                        <form action="resetpassword" onsubmit="return validateForm()" id="contactForm" method="post">
                                            <h4>Reset Your Password</h4>
                                            <div class="code-container">
                                                <input name="newpassword" id="newpassword" placeholder="New Password" type="password" class="code" value="${newpass}" required> <br>
                                                <input name="confirmpassword" id="confirmpassword" placeholder="Confirm New Password" type="password" value="${newpass}" class="code" required>                                               
                                            </div>
                                            <div class="col-md-12 form-group" id="Fail3" style="color: white; font-weight: bold;">${mess}</div>   
                                            <div style="margin-top: 20px; font-size: 25px;">
                                                <button type="submit" class="button button-account">Submit</button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>                  
                </div>
            </div>
        </section>
        <%@include file="Footer.jsp" %>
        <c:if test="${not empty status}">
            <script>
                document.getElementById("popupOverlay").style.display = "block";
                document.getElementById("activationPopup").style.display = "block";
            </script>
        </c:if>
        <!--================End Login Box Area =================-->
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