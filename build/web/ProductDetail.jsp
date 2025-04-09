<%-- 
    Document   : ProductDetail
    Created on : Sep 14, 2024, 2:26:20 PM
    Author     : sontu
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Five Castles Shop - Product Details</title>
        <link rel="icon" href="img2/FIcon.png" type="image/png">
        <link rel="stylesheet" href="vendors/bootstrap/bootstrap.min.css">
        <link rel="stylesheet" href="vendors/fontawesome/css/all.min.css">
        <link rel="stylesheet" href="vendors/themify-icons/themify-icons.css">
        <link rel="stylesheet" href="vendors/linericon/style.css">
        <link rel="stylesheet" href="vendors/nice-select/nice-select.css">
        <link rel="stylesheet" href="vendors/owl-carousel/owl.theme.default.min.css">
        <link rel="stylesheet" href="vendors/owl-carousel/owl.carousel.min.css">
        <link rel="stylesheet" href="css/style.css">
        <style>
            .star-grey {
                color: grey !important;
            }
            .star {
                color: grey;/* Màu mặc định là xám cho tất cả các sao */
                cursor: pointer;
            }

            .star.gold {
                color: gold; /* Màu vàng cho các sao được chọn */
            }
            .button.primary-btn {
                background-color: #384AEB; /* Màu nền */
                margin-top: 10px;
                color: white; /* Màu chữ */
                border: none; /* Loại bỏ viền */
                padding: 10px 20px; /* Khoảng cách bên trong nút */
                border-radius: 5px; /* Bo góc */
                font-size: 16px; /* Kích thước chữ */
                cursor: pointer; /* Đổi con trỏ khi hover */
                transition: background-color 0.3s ease; /* Hiệu ứng khi hover */
            }

            .button.primary-btn:hover {
                background-color: #2b3bb1; /* Màu nền khi hover */
            }
            .size-btn {
                border: 1px solid #ddd;
                padding: 10px;
                cursor: pointer;
                margin-right: 5px;
                background-color: #f9f9f9;
            }

            .size-btn.active {
                background-color: #4CAF50;
                color: white;
                border-color: #4CAF50;
            }
            .owl-carousel {
                display: flex; /* Đảm bảo các phần tử con được căn chỉnh theo chiều ngang */
                overflow: hidden; /* Ẩn các phần tử ra ngoài vùng hiển thị */
            }

            .single-prd-item {
                flex: 0 0 auto; /* Đảm bảo các phần tử giữ kích thước cố định */
                margin-right: 15px; /* Giữ khoảng cách giữa các ảnh */
            }

            .img-fluid {
                width: 100%; /* Đảm bảo ảnh co dãn theo chiều ngang của phần tử chứa */
                height: auto; /* Giữ tỷ lệ ảnh */
            }
            .owl-carousel {
                overflow: hidden; /* Ẩn phần tử vượt ra ngoài */
            }
            .img-fluid {
                width: 100%; /* Đảm bảo ảnh chiếm toàn bộ chiều ngang */
                height: 200px; /* Đặt chiều cao cố định */
                object-fit: cover; /* Giữ tỷ lệ ảnh mà không bị bóp méo */
            }

        </style>       
    </head>
    <body>
        <!--================ Start Header Menu Area =================-->
        <%@include file="Header.jsp" %>
        <!--================ End Header Menu Area =================-->

        <!-- ================ start banner area ================= -->	
        <section class="blog-banner-area" id="blog">
            <div class="container h-100">
                <div class="blog-banner">
                    <div class="text-center">
                        <h1>Detail Information</h1>
                        <nav aria-label="breadcrumb" class="banner-breadcrumb">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item"><a href="home">Home</a></li>
                                <li class="breadcrumb-item active" aria-current="page">Detail</li>
                            </ol>
                        </nav>
                    </div>
                </div>
            </div>
        </section>
        <!-- ================ end banner area ================= -->

        <!--================Single Product Area =================-->
        <c:choose>
            <c:when test="${productDetail.quantity - productDetail.hold <= 0}">
                <div class="product_image_area">
                    <div class="container">
                        <div class="row s_product_inner">
                            <div class="col-lg-6">
                                <div class="owl-carousel owl-theme s_Product_carousel">
                                    <div class="single-prd-item">
                                        <img class="img-fluid" src="${productDetail.thumbnail}" style="margin: 10px;" alt="a">
                                    </div>
                                    <div class="single-prd-item">
                                        <img class="img-fluid" src="${productDetail.img1}" style="margin: 10px;" alt="a">
                                    </div>
                                    <div class="single-prd-item">
                                        <img class="img-fluid" src="${productDetail.img2}" style="margin: 10px;"alt="a">
                                    </div>
                                    <div class="single-prd-item">
                                        <img class="img-fluid" src="${productDetail.img3}" style="margin: 10px;"alt="a">
                                    </div> 
                                </div>
                            </div>
                            <div class="col-lg-5 offset-lg-1">
                                <div class="s_product_text">
                                    <h3>${productDetail.title}</h3>
                                    <h2>${productDetail.briefInfo}</h2>                    
                                    <p>Category: ${productDetail.category}</p>
                                    <p>Size:</p>
                                    <!-- Thay thế dropdown size thành các nút -->
                                    <div id="size-buttons">
                                        <c:forEach var="size" items="${avalibleSizeNameByID}">
                                            <a href="change_product_size?productID=${productDetail.productID}&size=${size.sizeName}" 
                                               class="size-btn ${productDetail.sizeName eq size.sizeName ? 'active' : ''}">
                                                ${size.sizeName}
                                            </a>
                                        </c:forEach>
                                    </div>

                                    <br>
                                    <!-- Hiển thị giá tùy thuộc vào status -->
                                    <p>Price: ${productDetail.sellPrice *(1- productDetail.discount/100)}</p>
                                    <input type="hidden" name="price" value="${productDetail.sellPrice *(1- productDetail.discount/100)}">                               
                                    <p>${productDetail.description}</p>
                                    <p>Avalible: ${productDetail.quantity - productDetail.hold}</p> 
                                    <p class="text-danger"><strong>This item isn't avalible at this time, come back later!</strong></p>
                                </div>
                            </div>
                        </div>                    
                    </div>
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <form action="add_Cart" method="POST">
                <div class="product_image_area">
                    <div class="container">
                        <div class="row s_product_inner">
                            <div class="col-lg-6">
                                <div class="owl-carousel owl-theme s_Product_carousel">
                                    <div class="single-prd-item">
                                        <img class="img-fluid" src="${productDetail.thumbnail}" style="margin: 10px;" alt="a">
                                    </div>
                                    <div class="single-prd-item">
                                        <img class="img-fluid" src="${productDetail.img1}" style="margin: 10px;" alt="a">
                                    </div>
                                    <div class="single-prd-item">
                                        <img class="img-fluid" src="${productDetail.img2}" style="margin: 10px;"alt="a">
                                    </div>
                                    <div class="single-prd-item">
                                        <img class="img-fluid" src="${productDetail.img3}" style="margin: 10px;"alt="a">
                                    </div> 
                                </div>
                            </div>
                            <div class="col-lg-5 offset-lg-1">
                                <div class="s_product_text">
                                    <input type="hidden" name="productID" value="${productDetail.productID}">
                                    <input type="hidden" name="title" value="${productDetail.title}">
                                    <input type="hidden" name="image" value="${productDetail.img1}">
                                    <input type="hidden" name="hold" value="${productDetail.hold}">
                                    <h3>${productDetail.title}</h3>
                                    <h2>${productDetail.briefInfo}</h2>                    
                                    <p>Category: ${productDetail.category}</p>

                                    <p>Size:</p>
                                    <!-- Thay thế dropdown size thành các nút -->
                                    <div id="size-buttons">
                                        <c:forEach var="size" items="${avalibleSizeNameByID}">
                                            <a href="change_product_size?productID=${productDetail.productID}&size=${size.sizeName}" 
                                               class="size-btn ${productDetail.sizeName eq size.sizeName ? 'active' : ''}">
                                                ${size.sizeName}
                                            </a>
                                        </c:forEach>
                                    </div>
                                    <input type="hidden" name="sizeName" id="size-input" value="${productDetail.sizeName}">

                                    <br>
                                    <!-- Hiển thị giá tùy thuộc vào status -->
                                    <p>Price: ${productDetail.sellPrice *(1- productDetail.discount/100)}</p>
                                    <input type="hidden" name="price" value="${productDetail.sellPrice *(1- productDetail.discount/100)}">                               
                                    <p>${productDetail.description}</p>
                                    <p>Avalible: ${productDetail.quantity - productDetail.hold}</p>

                                    <div class="product_count">
                                        <label for="qty">Quantity:</label>
                                        <!-- Nút tăng số lượng -->
                                        <button onclick="var result = document.getElementById('sst');
                                                var sst = parseInt(result.value, 10);
                                                if (!isNaN(sst) && sst < ${productDetail.quantity - productDetail.hold}) {
                                                    result.value = sst + 1;
                                                } else {
                                                    alert('Cannot select more than available stock!');
                                                }
                                                return false;"
                                                class="increase items-count" type="button">
                                            <i class="ti-angle-left"></i>
                                        </button>

                                        <!-- Ô nhập số lượng -->
                                        <input type="text" name="qty" id="sst" size="2" maxlength="12" value="1" title="Quantity:" class="input-text qty"
                                               onchange="validateQuantity(this, ${productDetail.quantity - productDetail.hold})">

                                        <!-- Nút giảm số lượng -->
                                        <button onclick="var result = document.getElementById('sst');
                                                var sst = parseInt(result.value, 10);
                                                if (!isNaN(sst) && sst > 1) {
                                                    result.value = sst - 1;
                                                }
                                                return false;"
                                                class="reduced items-count" type="button">
                                            <i class="ti-angle-right"></i>
                                        </button>

                                        <!-- Nút Thêm vào giỏ hàng -->
                                        <button class="button primary-btn" type="submit">Add to Cart</button>
                                    </div>
                                </div>

                            </div>
                            <!-- Thêm JavaScript -->                      
                        </div>
                    </div>
                </div>
            </form>
        </c:otherwise>
    </c:choose>
    <script>
        // Hàm kiểm tra số lượng nhập vào
        function validateQuantity(input, maxStock) {
            var value = parseInt(input.value, 10);
            if (isNaN(value) || value < 1) {
                input.value = 1; // Nếu giá trị không hợp lệ, đặt lại về 1
            } else if (value > maxStock) {
                alert('Cannot select more than available stock! The number will be returned 1!');
                input.value = 1; // Nếu giá trị lớn hơn stock, đặt lại về maxStock
            }
        }
        document.querySelectorAll('.size-btn').forEach(function (button) {
            button.addEventListener('click', function () {
                // Xóa class 'active' khỏi tất cả các nút
                document.querySelectorAll('.size-btn').forEach(function (btn) {
                    btn.classList.remove('active');
                });
                // Thêm class 'active' vào nút được chọn
                button.classList.add('active');
                // Cập nhật giá trị của size vào input ẩn
                document.getElementById('size-input').value = button.getAttribute('data-size');
            });
        });

    </script>
    <style>
        .product_count {
            display: flex;
            align-items: center;
            gap: 20px; /* Khoảng cách giữa các phần tử */
        }

        .items-count {
            background-color: #007bff; /* Màu nền */
            color: white; /* Màu chữ */
            border: none; /* Không có viền */
            border-radius: 50%; /* Bo tròn hoàn toàn */
            width: 40px; /* Chiều rộng */
            height: 40px; /* Chiều cao */
            display: flex; /* Để căn giữa biểu tượng */
            justify-content: center; /* Căn giữa nội dung */
            align-items: center; /* Căn giữa nội dung */
            cursor: pointer; /* Con trỏ chuột khi di chuột qua */
            transition: background-color 0.3s; /* Hiệu ứng chuyển màu */
        }

        .items-count:hover {
            background-color: #0056b3; /* Màu nền khi hover */
        }

        .input-text {
            width: 50px; /* Chiều rộng ô nhập */
            text-align: center; /* Canh giữa nội dung */
            border: 1px solid #ccc; /* Viền */
            border-radius: 5px; /* Bo tròn góc */
            padding: 5px; /* Khoảng cách bên trong */
        }

        .primary-btn {
            background-color: #28a745; /* Màu nền nút thêm vào giỏ hàng */
            color: white; /* Màu chữ */
            border: none; /* Không có viền */
            border-radius: 5px; /* Bo tròn góc */
            padding: 10px 20px; /* Khoảng cách bên trong */
            cursor: pointer; /* Con trỏ chuột khi di chuột qua */
            transition: background-color 0.3s; /* Hiệu ứng chuyển màu */
        }

        .primary-btn:hover {
            background-color: #218838; /* Màu nền khi hover */
        }


    </style>
    <!--================End Single Product Area =================-->
    <script>
        $(document).ready(function () {
            $(".s_Product_carousel").owlCarousel({
                items: 1, // Hiển thị 1 ảnh mỗi lần
                loop: true, // Tạo vòng lặp
                autoplay: true, // Tự động chạy
                nav: true, // Hiển thị nút điều hướng
                dots: true       // Hiển thị chấm điều hướng bên dưới
            });
        });
    </script>

    <!--================Product Description Area =================-->
    <section class="product_description_area">
        <div class="container">
            <ul class="nav nav-tabs" id="myTab" role="tablist">
                <li class="nav-item">
                    <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true">Description</a>
                </li>                   
                <li class="nav-item">
                    <a class="nav-link " id="review-tab" data-toggle="tab" href="#review" role="tab" aria-controls="review"
                       aria-selected="false">Reviews</a>
                </li>
            </ul>
            <div class="tab-content" id="myTabContent">
                <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                    <p>${productDetail.description}</p>
                </div>
                <div class="tab-pane fade " id="review" role="tabpanel" aria-labelledby="review-tab">
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="row total_rate">
                                <div class="col-6">
                                    <div class="box_total">
                                        <h5>Overall</h5>
                                        <h4>${avgRating}</h4>
                                        <h6>(${numberOfFeedback} Reviews)</h6>
                                    </div>
                                </div>
                                <div class="col-6">
                                    <div class="rating_list">
                                        <h3>Based on ${numberOfFeedback} Reviews</h3>
                                        <ul class="list">
                                            <li><a href="#">5 Star <i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i
                                                        class="fa fa-star"></i><i class="fa fa-star"></i> ${numberFBByRating.size() > 0 ? numberFBByRating.get(0) : 0}</a></li>
                                            <li><a href="#">4 Star <i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i
                                                        class="fa fa-star"></i><i class="fa fa-star star-grey"></i> ${numberFBByRating.size() > 1 ? numberFBByRating.get(1) : 0}</a></li>
                                            <li><a href="#">3 Star <i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i
                                                        class="fa fa-star star-grey"></i><i class="fa fa-star star-grey"></i> ${numberFBByRating.size() > 2 ? numberFBByRating.get(2) : 0}</a></li>
                                            <li><a href="#">2 Star <i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star star-grey"></i><i
                                                        class="fa fa-star star-grey"></i><i class="fa fa-star star-grey"></i> ${numberFBByRating.size() > 3 ? numberFBByRating.get(3) : 0}</a></li>
                                            <li><a href="#">1 Star <i class="fa fa-star"></i><i class="fa fa-star star-grey"></i><i class="fa fa-star star-grey"></i><i
                                                        class="fa fa-star star-grey"></i><i class="fa fa-star star-grey"></i> ${numberFBByRating.size() > 4 ? numberFBByRating.get(4) : 0}</a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <div class="review_list">
                                <c:forEach items="${feedbacks}" var="f">
                                    <div class="review_item" style="border-bottom: 1px solid black; margin-bottom: 30px">
                                        <div class="media">
                                            <div class="d-flex">
                                                <img src="${f.avatar}" alt="" style="width: 50px;height: 50px">
                                            </div>
                                            <div class="media-body">
                                                <h4>${f.fullName} - ${f.gender} - ${f.phoneNumber} - ${f.email}</h4> <p style="padding-top: 0px"><fmt:formatDate value="${f.feedbackDate}" pattern="yyyy-MM-dd HH:mm:ss" timeZone="Asia/Ho_Chi_Minh" /></p>
                                                <c:forEach var="i" begin="1" end="5">
                                                    <i class="fa fa-star star-grey" <c:if test="${i <= f.rating}">style="color: gold !important"</c:if>></i>
                                                </c:forEach>
                                            </div>
                                        </div>
                                        <p style="padding-top: 10px">
                                            <c:if test="${f.imageURL != null}">
                                                <img src="${f.imageURL}" width="100px" height="100px"/>
                                            </c:if>
                                        </p>
                                        <p>${f.feedbackText}</p>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                        <div class="col-lg-6">
                            <div class="review_box">
                                <h4>Add a Review</h4>
                                <p style="color: red">*Only people who have purchased this product can review it.</p>
                                <form action="feedback" class="form-contact form-review mt-3" method="post" enctype="multipart/form-data">
                                    <div class="form-group">      
                                        <p>Your Rating:</p>
                                        <ul class="list start-list">
                                            <li><i class="fa fa-star star" data-value="1" onclick="setRating(1)"></i></li>
                                            <li><i class="fa fa-star star" data-value="2" onclick="setRating(2)"></i></li>
                                            <li><i class="fa fa-star star" data-value="3" onclick="setRating(3)"></i></li>
                                            <li><i class="fa fa-star star" data-value="4" onclick="setRating(4)"></i></li>
                                            <li><i class="fa fa-star star" data-value="5" onclick="setRating(5)"></i></li>
                                        </ul>
                                        <input type="hidden" name="rating" id="ratingValue" value="5">
                                        <!--<p>Outstanding</p>-->
                                    </div>
                                    <input class="form-control" name="productId" type="hidden" value="${productDetail.productID}">
                                    Full Name: 
                                    <div class="form-group">
                                        <input class="form-control different-control w-100" name="name" required value="${sessionScope.account.fullName}">
                                    </div>
                                    Gender:
                                    <div class="form-group">
                                        <select class="form-control different-control w-100" name="gender">
                                            <option value="Male">Male</option>
                                            <option value="Female" <c:if test="${sessionScope.account.gender != null && sessionScope.account.gender eq 'Female'}">selected</c:if>>Female</option>
                                            </select>
                                        </div>
                                        Email: 
                                        <div class="form-group">
                                            <input type="email" class="form-control different-control w-100" name="email"  required value="${sessionScope.account.email}">
                                    </div>
                                    Mobile: 
                                    <div class="form-group">
                                        <input type="tel" class="form-control different-control w-100" name="mobile" pattern="^0\d{9}$" required value="${sessionScope.account.phoneNumber}">
                                    </div>
                                    <div class="form-group">                                          
                                        Image <input type="file" class="form-control" name="image" 
                                                     onchange="previewImage(this)"/>
                                    </div>
                                    <div class="form-group">
                                        <img src="" alt="Service Image" class="img-preview" id="img-preview-id"
                                             style="width: 200px; border: 1px solid #ddd; border-radius: 5px;" />
                                    </div>
                                    Message: 
                                    <div class="form-group">
                                        <textarea class="form-control different-control w-100" name="message" id="textarea" cols="30" rows="5" placeholder="Enter Message" required></textarea>
                                    </div>
                                    <div class="form-group text-center text-md-right mt-3">
                                        <c:if test="${canFeedback != null && canFeedback == true}">
                                            <button type="submit" class="button button--active button-review">Submit Now</button>
                                        </c:if>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!--================End Product Description Area =================-->

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

    <script>
                                                         document.addEventListener('DOMContentLoaded', function () {
                                                             const preview = document.getElementById('img-preview-id');
                                                             preview.style.display = "none";
                                                         });
                                                         function previewImage(input) {
                                                             const preview = document.getElementById('img-preview-id');

                                                             // Kiểm tra xem có file được chọn hay không
                                                             if (input.files && input.files[0]) {
                                                                 // Hiển thị preview của hình ảnh
                                                                 preview.src = window.URL.createObjectURL(input.files[0]);
                                                                 preview.style.display = "block"; // Hiển thị ảnh
                                                             } else {
                                                                 preview.style.display = "none"; // Ẩn ảnh nếu không có file nào
                                                             }
                                                         }
                                                         // Hàm để set giá trị rating khi người dùng chọn sao
                                                         function setRating(value) {
                                                             document.getElementById("ratingValue").value = value;
                                                             const stars = document.querySelectorAll('.start-list .star');
                                                             stars.forEach((star, index) => {
                                                                 if (index < value) {
                                                                     star.style.color = 'gold';  // Đổi thành màu vàng
                                                                 } else {
                                                                     star.style.color = 'grey';  // Đổi thành màu xám
                                                                 }
                                                             });
                                                         }

                                                         // Khi trang load lần đầu, set mặc định 5 sao vàng
                                                         window.onload = function () {
                                                             setRating(5);  // Gán giá trị mặc định là 5 sao vàng
                                                         };
    </script>
</body>
</html>
