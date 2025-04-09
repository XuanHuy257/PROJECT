<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Aroma - Cart Contact</title>
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
        <link href="/SWP_Group4/assets/jumbotron-narrow.css" rel="stylesheet">      
        <script src="/SWP_Group4/assets/jquery-1.11.3.min.js"></script>
    </head>
    <body>
        <!--================ Start Header Menu Area =================-->
        <%@include file="Header.jsp" %>
        <!--================ End Header Menu Area =================-->
        <!--================Cart Contact Area =================-->
        <section class="cart_contact_area section-margin">
            <div>
                <h2 style="font-size: 18px; margin-left: 20%; margin-top: 20px; margin-bottom: 40px; font-weight: bold; color: #0A68FF;">
                    <a href="home" style="color: #0A68FF; text-decoration: none; position: relative;">
                        Aroma
                        <span style="position: absolute; left: 0; bottom: -3px; width: 100%; height: 2px; background-color: #0A68FF; opacity: 0; transition: opacity 0.3s ease;"></span>
                    </a> /
                    <a href="cartcontact" style="color: #0A68FF; text-decoration: none; position: relative;">
                        Cart Contact
                        <span style="position: absolute; left: 0; bottom: -3px; width: 100%; height: 2px; background-color: #0A68FF; opacity: 0; transition: opacity 0.3s ease;"></span>
                    </a> 
                </h2>
                <div class="row">
                    <div class="col-md-1">
                    </div>
                    <div class="col-md-2" style="margin-right: 20px; ">
                        <div class="row">
                            <div class="col-12" style="background-color: #FAFAFA; border-radius: 2px; margin-bottom: 20px; padding: 10px;">

                            </div>
                            <div class="col-12" style="background-color: #FAFAFA; border-radius: 2px; padding: 10px;">

                            </div>
                        </div>
                    </div>

                    <div class="col-lg-5" style=" margin-right: 20px; ">
                        <div class="row">
                            <div class="col-12" style="background-color: #FAFAFA; border-radius: 2px; margin-bottom: 20px; padding: 10px;">
                                <div style="border-radius: 5px; border: 1px ridge #0A68FF; background-color: #FFFFFF; padding: 20px; font-family: Arial, sans-serif; position: relative;">
                                    <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 20px;">
                                        <h4 style="font-size: 24px; font-weight: bold; color: #00AB55; margin: 0; text-transform: uppercase;">Product List</h4>                                      
                                    </div>
                                    <hr style="border: 1px solid rgba(0, 171, 85, 0.3); margin: 10px 0;">
                                    <c:forEach var="p" items="${listitems}">
                                        <div style="display: flex; align-items: center; padding: 10px 0; border-bottom: 1px solid rgba(255, 182, 193, 0.6);">
                                            <img src="${p.thumbnail}" style="height: 60px; border-radius: 2px; border: 2px solid rgba(255, 182, 193, 0.6); box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);"> 
                                            <div style="margin-left: 15px; flex-grow: 1;"> 
                                                <div style="font-weight: bold; color: #333;">${p.title}</div> 
                                                <div style="color: #666;">ID: ${p.productID}</div> 
                                                <div style="color: #666;">Size: ${p.sizeName}</div>
                                            </div>
                                            <div style="text-align: right; margin-left: 20px;">
                                                <div style="margin-bottom: 5px; color: #333;">Qty: x${p.quantity}</div> 
                                                <div style="font-weight: bold; color: #00AB55;">${p.price * p.quantity}$</div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>

                            </div>
                            <div class="col-12" style="background-color: #FAFAFA; border-radius: 2px; padding: 10px;">
                                <div class="d-flex justify-content-between">
                                    <h2 style=" color: black; font-size: 18px;">Choose Payment method</h2>
                                </div>
                                <form action="cartcontact" id="frmCreateOrder" method="post">
                                    <div style="margin-top: 20px;">
                                        <label class="form-check-label" for="paymentMethod1" style="border-radius: 5px; border: 1px ridge #0A68FF; background-color: #DBEBFF; color: black; width: 75%; padding: 20px; padding-left: 40px; display: flex; align-items: center; margin-top: 10px;">
                                            <div class="form-check">
                                                <input class="form-check-input" type="radio" checked="" name="bankCode" id="paymentMethod1" value="COD" style="height: 15px; width: 15px;">
                                                <img src="images/Payment1.png" alt="Cash on Delivery" style="width: 30px; height: 30px; margin: 0 10px;">
                                                Cash on Delivery
                                            </div>
                                        </label>

                                        <input type="hidden" name="notes" id="notesHidden" />
                                        <input class="form-control" data-val="true" data-val-number="The field Amount must be a number." data-val-required="The Amount field is required." id="amount" max="100000000" min="1" name="amount" type="hidden" value="${totalprice}" />

                                        <label class="form-check-label" for="paymentMethod2" style="border-radius: 5px; border: 1px ridge #0A68FF; background-color: #DBEBFF; color: black; width: 75%; padding: 20px; padding-left: 40px; display: flex; align-items: center; margin-top: 10px;">
                                            <div class="form-check">
                                                <input class="form-check-input" type="radio" name="bankCode" id="paymentMethod2" value="VNBANK" style="height: 15px; width: 15px;">
                                                <img src="images/Payment2.jpg" alt="VNPAY" style="width: 30px; height: 30px; margin: 0 10px;">
                                                VNPAY
                                            </div>
                                        </label>                                     
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>

                    <div class="col-lg-3" >
                        <div class="row">
                            <div class="col-12" style="background-color: #FAFAFA; border-radius: 2px; margin-bottom: 10px; padding: 10px;">
                                <div class="d-flex justify-content-between">
                                    <span style="color: #000000;">Deliver To:</span>
                                    <a href="receiver" style=" color: #0A68FF;">Change</a>
                                </div>
                                <div class="d-flex justify-content-between">
                                    <span ">${now.receiverName} <a style="margin-right: 2px; margin-left: 2px; font-size: 20px; color: #0A68FF;"> | </a> ${now.mobile}</span>
                                </div>
                                <div class="d-flex justify-content-between">
                                    <span > ${now.address}</span>
                                </div>
                            </div>

                            <div class="col-12" style="background-color: #FAFAFA; border-radius: 2px; padding: 10px; margin-bottom: 20px;">

                            </div>    

                            <div class="col-12" style="background-color: #FAFAFA; border-radius: 2px; padding: 10px; margin-bottom: 20px;">
                                <label for="notes" style="display: block; color: #000000; margin-bottom: 5px;">Notes:</label>
                                <textarea id="notes" rows="4" style="width: 100%; border-radius: 4px; padding: 5px;" placeholder="Enter your order notes here...."></textarea>
                            </div>

                            <div class="col-12" style="background-color: #FAFAFA; border-radius: 2px; margin-bottom: 10px; padding: 10px;">
                                <div class="d-flex justify-content-between">
                                    <span style="color: black;">Order:<br>${totalitems} Product</span>
                                    <a href="cart" style=" color: #0A68FF;">Change</a>
                                </div>
                                <div style="height: 0.5px; background-color: gray; margin: 10px 0;"></div>
                                <div class="d-flex justify-content-between" style="border-radius: 2px;">
                                    <span style="color: #000000;">Subtotal:  <br> 
                                        Shipping Fee: <br>
                                        Discount From Voucher:</span>
                                    <span style="margin-right:10px; color: #000000; ">${totalprice}$<br> 
                                        0$<br>
                                        0$</span>
                                </div>
                                <div style="height: 0.5px; background-color: gray; margin: 10px 0;"></div>
                                <div class="d-flex justify-content-between" style="border-radius: 2px;">
                                    <span style="color: black;">Total Cost:</span>
                                    <span style="margin-right:10px;"><h4>${totalprice}$</h4></span>                                    
                                </div>                                
                                <div class="col-12 d-flex justify-content-center" style="text-align: center; margin-bottom: 20px;">
                                    <c:choose>
                                        <c:when test="${now == null}">
                                            <button onclick="window.location.href = 'receiver';" style="background-color: #00B6F0; color: #FFFF; border: none; border-radius: 5px; padding: 10px; width: 350px;">Add Receiver Address</button>                                           
                                        </c:when>
                                        <c:otherwise>                                          
                                            <button id="btnSubmitOrder" style="background-color: #FF424E; color: #FFFF; border: none; border-radius: 5px; padding: 10px; width: 350px;">Submit(${totalitems})</button>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>        
        <!--================ Start footer Area  =================-->	
        <%@include file="Footer.jsp" %>
        <!--================ End footer Area  =================-->
        <link href="https://pay.vnpay.vn/lib/vnpay/vnpay.css" rel="stylesheet" />
        <script src="https://pay.vnpay.vn/lib/vnpay/vnpay.min.js"></script>
        <script type="text/javascript">
                                                $(document).ready(function () {
                                                    $("#btnSubmitOrder").click(function () {
                                                        this.style.pointerEvents = "none";
                                                        $(this).text('Submitting...');

                                                        setNotes();
                                                        var postData = $("#frmCreateOrder").serialize();
                                                        var submitUrl = $("#frmCreateOrder").attr("action");
                                                        $.ajax({
                                                            type: "POST",
                                                            url: submitUrl,
                                                            data: postData,
                                                            dataType: 'JSON',
                                                            success: function (x) {
                                                                if (x.code === '00') {
                                                                    if (window.vnpay) {
                                                                        vnpay.open({width: 768, height: 600, url: x.data});
                                                                    } else {
                                                                        location.href = x.data;
                                                                    }
                                                                    return false;
                                                                } else if (x.code === '01') {
                                                                    location.href = x.data;
                                                                    return false;
                                                                } else {
                                                                    alert(x.Message);
                                                                }
                                                            }
                                                        });
                                                        return false;
                                                    });
                                                });
                                                function setNotes() {

                                                    var notesValue = document.getElementById('notes').value;

                                                    document.getElementById('notesHidden').value = notesValue;
                                                }
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