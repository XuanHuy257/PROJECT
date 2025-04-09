<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Aroma Shop - Forgot Password</title>
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
                color: #384AEB; /* Màu tiêu ?? */
                margin-bottom: 10px; /* Kho?ng cách d??i tiêu ?? */
            }

            .popup p {
                margin-bottom: 20px; /* Kho?ng cách d??i ?o?n v?n */
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
    </head>
    <body>
        <%@include file="Header.jsp" %>
        <!--================Login Box Area =================-->
        <div class="popup-overlay" id="popupOverlay"></div>
        <div class="popup" id="activationPopup">
            <button id="closePopup" class="close-button">
                <i class="fas fa-times"></i> 
            </button>
            <c:if test="${mess != null}">
                <p style="color: #4B5CED; font-weight: bold; font-size: 15px;"><c:if test="${EmailFail != null}">Authentication Failed!</c:if><c:if test="${EmailFail == null}">Authentication Success~</c:if></p>
                    <strong style="margin-top: 55px;">${mess}</strong>
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
                                        <form action="forgotpassword" method="POST">
                                            <h4>Enter Your Email To Reset Password</h4>
                                            <p>Please enter the CORRECT email address associated with your account to proceed with the password reset.</p>
                                            <div class="code-container">
                                                <input name="email" placeholder="Enter Your Email" value="${EmailFail}" type="email" class="code" required> <br>                                              
                                            </div>                                                                                      
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
        <c:if test="${not empty mess}">
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