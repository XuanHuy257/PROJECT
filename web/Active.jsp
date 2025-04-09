<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Five Castles Shop - Verify</title>
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
                font-size: 60px;
                height: 100px;
                width: 80px;
                border: 1px solid #eee;
                margin: 1rem;
                text-align: center;
                font-weight: 300;
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

            .close-button:hover {
                color: #2e3b9a;
            }
        </style>
        <style>

            input[type=number]::-webkit-outer-spin-button,
            input[type=number]::-webkit-inner-spin-button {
                -webkit-appearance: none;
                margin: 0;
            }
        </style>

    </head>
    <body>
        <%@include file="Header.jsp" %>
        <!--================Login Box Area =================-->
        <div class="popup-overlay" id="popupOverlay"></div>
        <div class="popup" id="activationPopup">
            <c:if test="${status == 'error'}">
                <button id="closePopup" class="close-button" onclick="closePopup()">
                    <i class="fas fa-times"></i> 
                </button>
            </c:if>
            <c:if test="${status == 'success'}">
                <p style="color: #4B5CED; font-weight: bold; font-size: 15px;">Account Activation Success</p>
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
            </c:if>
            <strong style="margin-top: 40px; display: block;">${message}</strong>
        </div>
        <section class="login_box_area section-margin">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="login_box_img">
                            <div class="hover">
                                <div class="div-container">
                                    <div class="container">
                                        <form action="activecustomer" method="post">
                                            <h4>Active Your Account</h4>
                                            <p>We have just sent you a six-digit code to your registered email. <br/> Enter the code below to active your email address.</p>
                                            <div class="code-container">
                                                <input name="number1" type="number" class="code" min="0" max="9" required>
                                                <input name="number2" type="number" class="code" min="0" max="9" required>
                                                <input name="number3" type="number" class="code" min="0" max="9" required>
                                                <input name="number4" type="number" class="code" min="0" max="9" required>
                                                <input name="number5" type="number" class="code" min="0" max="9" required>
                                                <input name="number6" type="number" class="code" min="0" max="9" required>
                                            </div>
                                            <div style="margin-top: 20px; margin-bottom: 10px;">
                                                <button type="submit" class="button button-account">Submit</button>
                                            </div>
                                            <p>Please keep this browser window open to ensure you can complete the account activation process.</p>
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
        <!--================End Login Box Area =================-->
        <script src="vendors/jquery/jquery-3.2.1.min.js"></script>
        <script src="vendors/bootstrap/bootstrap.bundle.min.js"></script>
        <script src="vendors/skrollr.min.js"></script>
        <script src="vendors/owl-carousel/owl.carousel.min.js"></script>
        <script src="vendors/nice-select/jquery.nice-select.min.js"></script>
        <script src="vendors/jquery.ajaxchimp.min.js"></script>
        <script src="vendors/mail-script.js"></script>
        <script src="js/main.js"></script>
        <script>
                    const inputs = document.querySelectorAll('.code');

                    inputs.forEach((input, index) => {

                        input.addEventListener('input', () => {

                            if (input.value.length > 1) {
                                input.value = input.value.slice(0, 1);
                            }

                            if (input.value.length === 1) {
                                if (index < inputs.length - 1) {
                                    inputs[index + 1].focus();
                                }
                            }
                        });

                        input.addEventListener('keydown', (e) => {
                            if (e.key === 'Backspace' && input.value === '') {
                                if (index > 0) {
                                    inputs[index - 1].focus();
                                }
                            }
                        });

                        input.addEventListener('paste', (e) => {
                            e.preventDefault();
                            const pasteData = e.clipboardData.getData('text');

                            const pasteLength = Math.min(pasteData.length, inputs.length - index);

                            for (let i = 0; i < pasteLength; i++) {
                                inputs[index + i].value = pasteData[i];
                            }

                            const nextInputIndex = index + pasteLength;
                            if (nextInputIndex < inputs.length) {
                                inputs[nextInputIndex].focus();
                            }
                        });
                    });
        </script>
        <c:if test="${not empty status}">
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
    </body>
</html>