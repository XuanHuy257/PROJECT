<%-- 
    Document   : single-blog
    Created on : Sep 13, 2024, 2:05:07 PM
    Author     : 
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Five Castles Shop - Blog</title>
        <link rel="icon" href="img/Fevicon.png" type="image/png">
        <link rel="stylesheet" href="vendors/bootstrap/bootstrap.min.css">
        <link rel="stylesheet" href="vendors/fontawesome/css/all.min.css">
        <link rel="stylesheet" href="vendors/themify-icons/themify-icons.css">
        <link rel="stylesheet" href="vendors/linericon/style.css">
        <link rel="stylesheet" href="vendors/owl-carousel/owl.theme.default.min.css">
        <link rel="stylesheet" href="vendors/owl-carousel/owl.carousel.min.css">

        <link rel="stylesheet" href="css/style.css">
        <style>
            .blog_right_sidebar .post_category_widget .cat-list li.active{
                border-color: #384aeb !important;
            }
            li.active a {
                color: #384aeb !important;
            }
            .blog-pagination .page-item.active .page-link {
                color: #fff !important;
            }
        </style>
    </head>
    <body>
        <%@include file="Header.jsp" %>

            <!-- ================ start banner area ================= -->	
            <section class="blog-banner-area" id="blog">
                <div class="container h-100">
                    <div class="blog-banner">
                        <div class="text-center">
                            <h1>Our Blog</h1>
                            <nav aria-label="breadcrumb" class="banner-breadcrumb">
                                <ol class="breadcrumb">
                                    <li class="breadcrumb-item"><a href="home">Home</a></li>
                                    <li class="breadcrumb-item active" aria-current="page">Blog</li>
                                </ol>
                            </nav>
                        </div>
                    </div>
                </div>
            </section>
            <!-- ================ end banner area ================= -->


            <!--================Blog Categorie Area =================-->
            <section class="blog_categorie_area" style="padding-bottom: 30px; padding-top: 30px">
            </section>
            <!--================Blog Categorie Area =================-->

            <!--================Blog Area =================-->
            <section class="blog_area">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-8">
                            <div class="blog_left_sidebar">
                            <c:forEach items="${blogs}" var="b">
                                <article class="row blog_item">
                                    <div class="col-md-3">
                                        <div class="blog_info text-right">
                                            <div class="post_tag">
                                                <!--<a href="#">Food,</a>-->
                                                <a class="active" href="blogs?search=&category=${b.postCategoryID}">${b.postCategoryName}</a>
                                                <!--<a href="#">Politics,</a>-->
                                                <!--<a href="#">Lifestyle</a>-->
                                            </div>
                                            <ul class="blog_meta list">
                                                <li>
                                                    <a href="#">${b.userName}
                                                        <i class="lnr lnr-user"></i>
                                                    </a>
                                                </li>
                                                <li>
                                                    <a href="#">${b.postDate}
                                                        <i class="lnr lnr-calendar-full"></i>
                                                    </a>
                                                </li>
                                                
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="col-md-9">
                                        <div class="blog_post">
                                            <img src="${b.postImg}" alt="" style="display: block; margin: 0 auto; ">
                                            <div class="blog_details">
                                                <a href="blog-detail?id=${b.postID}">
                                                    <h2>${b.title}</h2>
                                                </a>
                                                <p>${b.postBrief}</p>
                                                <a class="button button-blog" href="blog-detail?id=${b.postID}">View More</a>
                                            </div>
                                        </div>
                                    </div>
                                </article>
                            </c:forEach>
                            <nav class="blog-pagination justify-content-center d-flex">
                                <ul class="pagination">
                                <c:forEach begin="1" end="${totalPage}" var="p">
                                   
                                    <li class="page-item  <c:if test="${page == p}">active</c:if>">
                                        <a href="blogs?search=${search}&category=${category}&page=${p}" class="page-link">${p}</a>
                                    </li>
                                   
                                    </c:forEach>
                                </ul>
                            </nav>
                        </div>
                    </div>
                    <div class="col-lg-4">
                        <div class="blog_right_sidebar">
                            <aside class="single_sidebar_widget search_widget">
                                <div class="input-group">
                                    <input type="text" class="form-control" placeholder="Search Posts" value="${search}" id="searchBlog">
                                    <span class="input-group-btn">
                                        <button class="btn btn-default" type="button" onclick="search()">
                                            <i class="lnr lnr-magnifier"></i>
                                        </button>
                                    </span>
                                </div>
                                <!-- /input-group -->
                                <div class="br"></div>
                            </aside>
                           
                            <aside class="single_sidebar_widget post_category_widget">
                                <h4 class="widget_title">Post Catgories</h4>
                                <ul class="list cat-list">
                                     <li <c:if test="${category == 0}">class="active"</c:if>>
                                            <a href="blogs?search=${search}&category=0" class="d-flex justify-content-between">
                                                <p>All Blogs</p>
                                                <!--<p>37</p>-->
                                            </a>
                                        </li>
                                    <c:forEach items="${blogCategories}" var="c">
                                        <li <c:if test="${c.postCategoryID == category}">class="active"</c:if>>
                                            <a href="blogs?search=${search}&category=${c.postCategoryID}" class="d-flex justify-content-between">
                                                <p>${c.postCategoryName}</p>
                                               
                                            </a>
                                        </li>
                                    </c:forEach>
                                   
                                </ul>
                                <div class="br"></div>
                            </aside>
                            <aside class="single_sidebar_widget popular_post_widget">
                                <h3 class="widget_title">Lastest Posts</h3>
                            <c:forEach items="${top3LastestBlog}" var="p">
                                <div class="media post_item">
                                    <img src="${p.postImg}" alt="post" style="height: auto; width: 150px; display: block; margin: 0 auto; " >
                                    <div class="media-body">
                                        <a href="blog-detail?id=${p.postID}">
                                            <h3>${p.title}</h3>
                                        </a>
                                        <p>${p.postDate}</p>
                                    </div>
                                </div>
                                </c:forEach>

                                <div class="br"></div>
                            </aside>
                         
                            <aside class="single-sidebar-widget tag_cloud_widget">
                                <h4 class="widget_title">Tag Clouds</h4>
                                <ul class="list">
                                     <c:forEach items="${blogCategories}" var="c">
                            
                                         <li>
                                        <a href="blogs?search=${search}&category=${c.postCategoryID}">${c.postCategoryName}</a>
                                    </li>
                                    </c:forEach>

                                </ul>
                            </aside>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!--================Blog Area =================-->

        <!--================Instagram Area =================-->
       
        <!--================End Instagram Area =================-->


        <%@include file="Footer.jsp" %>



        <script src="vendors/jquery/jquery-3.2.1.min.js"></script>
        <script src="vendors/bootstrap/bootstrap.bundle.min.js"></script>
        <script src="vendors/skrollr.min.js"></script>
        <script src="vendors/owl-carousel/owl.carousel.min.js"></script>
        <script src="vendors/nice-select/jquery.nice-select.min.js"></script>
        <script src="vendors/jquery.ajaxchimp.min.js"></script>
        <script src="vendors/mail-script.js"></script>
        <script src="js/main.js"></script>
        <script type="text/javascript">
            function search(){
                var txtSearch = document.getElementById("searchBlog").value;
                var categoryId = `${category}`;
                console.log(categoryId);
                window.location.href = "blogs?search="+txtSearch+"&category=" + categoryId;
            }
        </script>
    </body>
</html>
