<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Five Castles Shop - Category</title>
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
                        <h1>Shop Category</h1>
                        <nav aria-label="breadcrumb" class="banner-breadcrumb">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item"><a href="#">Home</a></li>
                                <li class="breadcrumb-item active" aria-current="page">Shop Category</li>
                            </ol>
                        </nav>
                    </div>
                </div>
            </div>
        </section>
        <!-- ================ end banner area ================= -->


        <!-- ================ category section start ================= -->		  
        <section class="section-margin--small mb-5">
            <div class="container">
                <div class="row">
                    <div class="col-xl-3 col-lg-4 col-md-5">

                        <div class="sidebar-categories">
                            <div class="head">Brands</div>
                            <ul class="main-categories">
                                <li class="common-filter">
                                    <form action="allproduct" method="GET" id="brand">
                                        <input type="hidden" name="action" value="filterByBrand" />
                                        <ul>
                                            <c:forEach var="br" items="${Brand}">
                                                <li class="filter-list">
                                                    <!--Khi click vào label, sẽ gọi hàm handleBrandClick để thay đổi sản phẩm -->
                                                    <input class="pixel-radio" 
                                                           type="radio" 
                                                           id="br-${br.id}" 
                                                           name="brand" 
                                                           value="${br.id}"  
                                                           onChange="handleBrandClick('${br.id}')">
                                                    <label for="br-${br.id}">
                                                        <span class="name">${br.name}</span>
                                                    </label>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </form>
                                </li>
                            </ul>
                        </div>
                        <div>
                            <!--space-->
                        </div>

                        <div class="sidebar-categories">
                            <div class="head">Categories</div>
                            <ul class="main-categories">
                                <li class="common-filter">
                                    <form action="allproduct" method="GET" id="categoryForm">
                                        <input type="hidden" name="action" value="filterByCategory" />
                                        <ul>
                                            <c:forEach var="ct" items="${Category}">
                                                <li class="filter-list">
                                                    <!-- Trigger the handleCategoryClick when radio button is clicked -->
                                                    <input class="pixel-radio" 
                                                           type="radio" 
                                                           id="ct-${ct.id}" 
                                                           name="categoryid" 
                                                           value="${ct.id}" 
                                                           onClick="handleCategoryClick('${ct.id}')">
                                                    <label for="ct-${ct.id}">
                                                        <span class="name">${ct.name}</span>
                                                    </label>
                                                </li>
                                            </c:forEach>       
                                        </ul>
                                    </form>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-xl-9 col-lg-8 col-md-7">
                        <!-- Start Filter Bar -->
                        <div class="filter-bar d-flex flex-wrap align-items-center">
                            <div class="sorting">
                                <select id="sortSelect" onchange="sortProducts()">
                                    <option value="high">PRICE (HIGH-LOW)</option>
                                    <option value="low">PRICE (LOW-HIGH)</option>
                                    <option value="name">NAME (A-Z)</option>
                                </select>
                            </div>
                            <div>
                                <form action="allproduct">
                                    <div class="input-group filter-bar-search">
                                        <input type="hidden" name="action" value="search">
                                        <input type="text" name="text" id="searchInput" placeholder="Search" oninput="showSuggestions(this.value)">
                                        <div class="input-group-append">
                                            <button type="button"><i class="ti-search"></i></button>
                                        </div>
                                    </div>
                                </form>
                                <!-- Container to display suggestions -->
                                <div id="suggestions" class="suggestions-box" style="display:none;">
                                    <!-- Suggestions will be dynamically added here -->
                                </div>
                            </div>
                        </div>
                        <!-- End Filter Bar -->
                        <!-- Start Best Seller -->
                        <section class="lattest-product-area pb-40 category-list">
                            <div class="row">
                                <c:forEach items="${productList}" var="p">
                                    <div class="col-md-6 col-lg-4">
                                        <div class="card text-center card-product">
                                            <div class="card-product__img">
                                                <img class="" src="${p.thumbnail}" alt="image">
                                                <ul class="card-product__imgOverlay">
                                                    <li><button><a class="ti-info" href="product_detail?id=${p.id}"></a></button></li>
                                                    <li><button><i class="ti-shopping-cart"></i></button></li>
                                                </ul>
                                            </div>
                                            <div class="card-body">                                               
                                                <h4 class="card-product__title">
                                                    <a href="product_detail?id=${p.id}">${p.title}</a>
                                                </h4>                                              
                                                <p class="card-product__price">${p.price}</p>
                                            </div>                                   
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </section>
                        <!-- End Best Seller -->
                    </div>
                </div>
            </div>
        </section>
        <!-- ================ category section end ================= -->		  
        <nav class="custome-pagination">
            <ul class="d-flex justify-content-center pagination-md">
                <c:if test="${page.index != 0}">
                    <li class="page-item">
                        <a href='${pageContext.request.contextPath}/allproduct?action=null&index=0' class="page-link"
                           style="color: var(--theme-color);"> Home </a>
                        </>
                    <li class="page-item">
                        <a href='${pageContext.request.contextPath}/allproduct?action=null&index=${page.index -1}'
                           class="page-link" style="color: var(--theme-color);">
                            Previous </a>
                    </li>
                </c:if>
                <c:forEach var="p" begin='${page.pageStart}' end='${page.pageEnd}'>
                    <li class="page-item">
                        <a href='${pageContext.request.contextPath}/allproduct?action=null&index=${p}' class="page-link"
                           style="color: var(--theme-color);"> Page ${p
                                                                   +1}</a>
                    </li>
                </c:forEach>
                <c:if test="${page.index  != page.totalPage - 1}">
                    <li class="page-item">
                        <a href="${pageContext.request.contextPath}/allproduct?action=null&index=${page.index+1}"
                           class="page-link" style="color: var(--theme-color);">
                            Next</a>
                    </li>
                    <li class="page-item">
                        <a href="${pageContext.request.contextPath}/allproduct?action=null&index=${page.totalPage-1}"
                           class="page-link" style="color: var(--theme-color);"> End </a>
                    </li>
                </c:if>
            </ul>
        </nav>
        <!-- ================ top product area start ================= -->	
        <!-- ================ top product area end ================= -->		

        <!-- ================ Subscribe section start ================= -->		  
        <section class="subscribe-position">
            <div class="container">
                <div class="subscribe text-center">
                    <h3 class="subscribe__title">Get Update From Anywhere</h3>
                    <p>Bearing Void gathering light light his eavening unto dont afraid</p>
                    <div id="mc_embed_signup">
                        <form target="_blank" action="https://spondonit.us12.list-manage.com/subscribe/post?u=1462626880ade1ac87bd9c93a&amp;id=92a4423d01" method="get" class="subscribe-form form-inline mt-5 pt-1">
                            <div class="form-group ml-sm-auto">
                                <input class="form-control mb-1" type="email" name="EMAIL" placeholder="Enter your email" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Your Email Address '" >
                                <div class="info"></div>
                            </div>
                            <button class="button button-subscribe mr-auto mb-1" type="submit">Subscribe Now</button>
                            <div style="position: absolute; left: -5000px;">
                                <input name="b_36c4fd991d266f23781ded980_aefe40901a" tabindex="-1" value="" type="text">
                            </div>

                        </form>
                    </div>

                </div>
            </div>
        </section>
        <!-- ================ Subscribe section end ================= -->		  


        <!--================ Start footer Area  =================-->	
        <%@include file="Footer.jsp"%>
        <!--================ End footer Area  =================-->

        <script>
            function sortProducts() {
                var sortType = document.getElementById("sortSelect").value;
                window.location.href = "allproduct?action=sort&type=" + sortType;
            }
        </script>
        <script>
            function showSuggestions(searchTerm) {
                if (searchTerm.length === 0) {
                    document.getElementById("suggestions").style.display = "none";
                    return;
                }

                // Gửi yêu cầu đến server qua AJAX (hoặc Fetch API)
                fetch("searchSuggestions?query=" + searchTerm)
                        .then(response => response.json())
                        .then(data => {
                            let suggestionsBox = document.getElementById("suggestions");
                            suggestionsBox.innerHTML = ""; // Xóa gợi ý cũ

                            if (data.length > 0) {
                                suggestionsBox.style.display = "block"; // Hiển thị danh sách gợi ý

                                data.forEach(product => {
                                    // Tạo HTML cho mỗi gợi ý
                                    let suggestionItem = document.createElement("div");
                                    suggestionItem.className = "suggestion-item";
                                    suggestionItem.innerHTML = `<a href="productDetail?productId=${product.id}">${product.title}</a>`;
                                    suggestionsBox.appendChild(suggestionItem);
                                });
                            } else {
                                suggestionsBox.style.display = "none"; // Ẩn nếu không có gợi ý
                            }
                        });
            }
        </script>
        <script>
            function handleBrandClick(brandId) {
                // Lấy form từ DOM bằng id
                const form = document.getElementById('brand');

                // Thay đổi giá trị của input radio tương ứng với brandId đã chọn
                form.action = `allproduct?brandId=${brandId}&action=filterByBrand`;

                // Gửi form
                form.submit();
            }
        </script>
        <script>
            function handleCategoryClick(categoryId) {
                // Lấy form từ DOM bằng id
                const form = document.getElementById('categoryForm');

                // Thay đổi giá trị của action trong form với categoryId đã chọn
                form.action = `allproduct?categoryId=${categoryId}&action=filterByCategory`;

                // Gửi form
                form.submit();
            }
        </script>

        <script src="vendors/jquery/jquery-3.2.1.min.js"></script>
        <script src="vendors/bootstrap/bootstrap.bundle.min.js"></script>
        <script src="vendors/skrollr.min.js"></script>
        <script src="vendors/owl-carousel/owl.carousel.min.js"></script>
        <script src="vendors/nice-select/jquery.nice-select.min.js"></script>
        <script src="vendors/nouislider/nouislider.min.js"></script>
        <script src="vendors/jquery.ajaxchimp.min.js"></script>
        <script src="vendors/mail-script.js"></script>
        <script src="js/main.js"></script>
    </body>
</html>
