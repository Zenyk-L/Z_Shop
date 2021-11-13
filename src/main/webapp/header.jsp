
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Header</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" />

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />



    <link type="text/css" href="style/header.css" rel="stylesheet">

</head>
<body>
<header class="section-header">
    <section class="header-main border-bottom">
        <div class="container-fluid">
            <div class="row align-items-center">
                <div class="col-lg-3 col-sm-4 col-md-4 col-5"> <a href="/home" class="brand-wrap" data-abc="true">
                    <!-- <img class="logo" src="http://ampexamples.com/data/upload/2017/08/bootstrap2_logo.png"> --> <span class="logo">Z SHOP</span> </a> </div>
                <div class="col-lg-4 col-xl-5 col-sm-8 col-md-4 d-none d-md-block">
                    <form action="#" class="search-wrap">
                        <div class="input-group w-100"> <input type="text" class="form-control search-form" style="width:55%;" placeholder="Search">
                            <div class="input-group-append"> <button class="btn btn-primary search-button" type="submit"> <i class="fa fa-search"></i> </button> </div>
                        </div>
                    </form>
                </div>
                <div class="col-lg-5 col-xl-4 col-sm-8 col-md-4 col-7">
                    <div class="d-flex justify-content-end"> <a target="_blank" href="#" data-abc="true" class="nav-link widget-header"> <i class="fas fa fa-shopping-cart"></i></a> <span class="vl"></span>
                        <a target="_blank" href="#" data-abc="true" class="nav-link widget-header"> <i class="fas fa fa-user"></i></a> <span class="vl"></span>
                        <div class="dropdown btn-group"> <a class="nav-link nav-icons" href="#" id="navbarDropdownMenuLink1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" data-abc="true"><i class="fas fa fa-cog<%--fa-bell--%>"></i></a>
                            <ul class="dropdown-menu dropdown-menu-right notification-dropdown">
                                <li>
                                    <div class="notification-title">Admin menu</div>
                                    <div class="notification-list">
                                        <div class="list-group"> <a href="/createProduct" class="list-group-item list-group-item-action active" data-abc="true">
                                            <div class="notification-info">
                                                <div class="notification-list-user-img"><%--<img src="https://img.icons8.com/nolan/100/000000/helping-hand.png" alt="" class="user-avatar-md rounded-circle">--%> <i class="fa fa-plus"></i> <h6>Create product</h6></div>
                                            </div>
                                        </a> <a href="redemption-center" class="list-group-item list-group-item-action active" data-abc="true">
                                            <div class="notification-info">
                                                <div class="notification-list-user-img"><img src="https://img.icons8.com/bubbles/100/000000/prize.png" alt="" class="user-avatar-md rounded-circle"></div>
                                                <div class="notification-list-user-block"><span class="notification-list-user-name">Redemption Center</span> </div>
                                            </div>
                                        </a> <a href="#" class="list-group-item list-group-item-action active" data-abc="true">
                                            <div class="notification-info">
                                                <div class="notification-list-user-img"><img src="https://img.icons8.com/ultraviolet/100/000000/medal.png" alt="" class="user-avatar-md rounded-circle"></div>
                                                <div class="notification-list-user-block"><span class="notification-list-user-name">Achievements</span> </div>
                                            </div>
                                        </a> <a href="#" class="list-group-item list-group-item-action active" data-abc="true">
                                            <div class="notification-info">
                                                <div class="notification-list-user-img"><img src="https://img.icons8.com/bubbles/100/000000/call-female.png" alt="" class="user-avatar-md rounded-circle"></div>
                                                <div class="notification-list-user-block"><span class="notification-list-user-name">Contact us</span> </div>
                                            </div>
                                        </a> </div>
                                    </div>
                                </li>
                            </ul>
                        </div> <span class="vl"></span> <a class="nav-link nav-user-img" href="#" data-toggle="modal" data-target="#login-modal" data-abc="true"><span class="login"><i class="fa fa-sign-in"></i>  LOGIN</span></a>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <nav class="navbar navbar-expand-md navbar-main border-bottom">
        <div class="container-fluid">
            <form class="d-md-none my-2">
                <div class="input-group"> <input type="search" name="search" class="form-control" placeholder="Search" required="">
                    <div class="input-group-append"> <button type="submit" class="btn btn-secondary"> <i class="fa fa-search"></i> </button> </div>
                </div>
            </form> <button class="navbar-toggler collapsed" type="button" data-toggle="collapse" data-target="#dropdown6" aria-expanded="false"> <span class="navbar-toggler-icon"></span> </button>
            <div class="navbar-collapse collapse" id="dropdown6" style="">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item dropdown"> <a class="nav-link dropdown-toggle" href="" data-toggle="dropdown" data-abc="true" aria-expanded="false">Laptops</a>
                        <div class="dropdown-menu"> <a class="dropdown-item" href="" data-abc="true">Lenovo</a> <a class="dropdown-item" href="" data-abc="true">Dell</a> <a class="dropdown-item" href="" data-abc="true">HP</a> <a class="dropdown-item" href="" data-abc="true">Apple</a> </div>
                    </li>
                    <li class="nav-item"> <a class="nav-link" href="" data-abc="true">Refurbished Mobile</a> </li>
                    <li class="nav-item"> <a class="nav-link" href="" data-abc="true">Accessories & Peripheral</a> </li>
                    <li class="nav-item"> <a class="nav-link" href="" data-abc="true">Blog</a> </li>
                    <li class="nav-item"> <a class="nav-link" href="" data-abc="true">Support</a> </li>
                </ul>
            </div>
        </div>
    </nav>
</header>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript">

    $(document).ready(function(){

        function openNav() {
            document.getElementById("mySidenav").style.width = "70%";

            document.body.style.backgroundColor = "rgba(0,0,0,0.4)";
        }

        function closeNav() {
            document.getElementById("mySidenav").style.width = "0";
            document.body.style.backgroundColor = "rgba(0,0,0,0)";
        }
    });
</script>
</body>
</html>