<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Header</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"/>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>


    <link type="text/css" href="style/header.css" rel="stylesheet">
    <link rel="stylesheet" href="style/login.css">
    <script src="js/header.js"></script>

</head>
<body>

<header class="section-header">
    <section class="header-main border-bottom">

        <div class="d-flex justify-content-end">


            <div id="userEmail" class="text-white mr-5">${sessionScope.user.email}</div>

            <fieldset>
                <label style="color: white"> Language</label>
                <select id="localesSession" style="color: #007bff" class="mr-5">
                    <c:forEach items="${sessionScope.languages}" var="language">

                        <option value="${language.shortName}">${language.fullName}</option>
                    </c:forEach>
                </select>

            </fieldset>
        </div>

        <div class="container-fluid">
            <div class="row align-items-center">
                <div class="col-lg-3 col-sm-4 col-md-4 col-5"><a href="/home" class="brand-wrap" data-abc="true">

                    <%--                    <input type="text" name="" value="${sessionScope.lang}" />--%>
                    <!-- <img class="logo" src="http://ampexamples.com/data/upload/2017/08/bootstrap2_logo.png"> -->
                    <span class="logo">Z SHOP</span> </a></div>
                <div class="col-lg-4 col-xl-5 col-sm-8 col-md-4 d-none d-md-block">
                    <form action="#" class="search-wrap">
                        <div class="input-group w-100"><input type="text" id="searchNameInput"
                                                              class="form-control search-form" style="width:55%;"
                                                              placeholder="Search by name"
                                                              onkeyup="searchSortFunction()">
                            <div class="input-group-append">
                                <button class="btn btn-primary search-button" type="submit"><i class="fa fa-search"></i>
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="col-lg-5 col-xl-4 col-sm-8 col-md-4 col-7">

                    <div class="d-flex justify-content-end">

                        <c:if test="${sessionScope.buckets.size() > 0}">
                            <a class="text-white mr-5" style=" position: relative;" href="/bucket">
                                <span class="number"
                                      style=" position: absolute; right: 6px">${sessionScope.buckets.size()}</span>
                                <img class="img-fluid" src="/image/cart.png" alt="cart"/>
                                    <%--                                <i class="fas fa fa-shopping-cart"></i>--%>
                            </a>
                        </c:if>
                        <c:if test="${sessionScope.user.role == 'ADMIN' || sessionScope.user.role == 'USER'}">
                            <a class="text-white mr-5" href="/editUser"><i class="fas fa fa-user"></i></a>
                        </c:if>

                        <c:if test="${sessionScope.user.role == 'ADMIN' }">
                            <a class="text-white mr-5" href="/createProduct"><i class="fas fa fa-cog"></i></a>
                        </c:if>

                        <c:choose>
                            <c:when test="${sessionScope.user.role == 'ADMIN' || sessionScope.user.role == 'USER'}">
                                <a class="text-white mr-4" href="${pageContext.request.contextPath}/logout"><i
                                        class="fa fa-sign-out"></i> LOGOUT</a>
                            </c:when>
                            <c:otherwise>
                                <a class="text-white mr-5 togglePopup" href="#"><i
                                        class="fa fa-sign-in"></i> LOGIN </a>
                            </c:otherwise>
                        </c:choose>


                        <%--                        <span class="vl"></span> <a class="nav-link nav-user-img" href="${pageContext.request.contextPath}/login.jsp" data-toggle="modal"--%>
                        <%--                                                    data-target="#login-modal" data-abc="true" ><span class="login"><i--%>
                        <%--                            class="fa fa-sign-in"></i>  LOGIN</span></a>--%>
                    </div>

                </div>
            </div>
        </div>
    </section>
    <nav class="navbar navbar-expand-md navbar-main border-bottom">
        <div class="container-fluid">
            <div class="navbar-collapse collapse" id="dropdown6" style="">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item"><p class="nav-link"> SORT BY CATEGORY</p></li>
                    <li class="nav-item dropdown">


                        <select name="category" id="sortByCategory" class="nav-link  dropdown-toggle"
                                style="border-color:white" onchange="searchSortFunction()">
                            <option value="default">ALL</option>
                            <c:forEach items="${categories}" var="category">
                                <option value="${category.id}">${category.translations[sessionScope.lang]}</option>
                            </c:forEach>
                            < </select>

                    </li>
                    <%--                    <li class="nav-item"><p class="nav-link"> </p></li>--%>
                    <li class="nav-item dropdown">

                        <select name="category" id="sortByName" class="nav-link dropdown-toggle"
                                style="border-color:white" onchange="searchSortFunction()">
                            <option class="nav-item" value="default"> SORT BY NAME</option>
                            <option value="UP">NAME UP</option>
                            <option value="DOWN">NAME DOWN <i class="fas fa-chevron-circle-down"></i></option>
                        </select>

                    </li>
                    <%--                    <li class="nav-item"><p class="nav-link"> </p></li>--%>
                    <li class="nav-item dropdown">

                        <select name="category" id="sortByPrice" class="nav-link dropdown-toggle"
                                style="border-color:white" onchange="searchSortFunction()">
                            <option value="default"> SORT BY PRICE</option>
                            <option value="UP">PRICE UP</option>
                            <option value="DOWN">PRICE DOWN</option>
                        </select>

                    </li>


                    <li class="nav-item"><a class="nav-link" href="#" data-abc="true">Support</a></li>
                </ul>
            </div>
        </div>
    </nav>

</header>
    <div class="login-page" id="popup">
        <div class="form">

            <img src="https://cdn4.vectorstock.com/i/1000x1000/89/13/user-login-icon-vector-21078913.jpg" height="50px"
                 width="50px" style="margin: 0 auto" onclick="javascript:openLogin()">
            <br>
            <form class="register-form" action="/registration" method="POST">
                <input type="text" name="firstName" class="firstName" placeholder="First name" required/>
                <input type="text" name="lastName" placeholder="Last name" required/>
                <input type="email" name="email" placeholder="email address" required/>
                <input type="password" name="password" placeholder="password" required/>
                <%--            <input name="cpassword" type="password" placeholder="confirm password"/>--%>

                <button type="submit" class="register">Register</button>
                <button type="reset" class="cancel">Cancel</button>
                <p class="message">Already registered? <a href="#">Sign In</a></p>
            </form>

            <form class="login-form"  action="/login" method="POST">

                <input type="email" name="email" class="email" placeholder="email address" value="user@mail.com" required/>
                <input type="password" name="password" class="password" placeholder="password" value="user" required/>

                <button type="submit" class="login">Login</button>
                <button type="reset" class=" cancel">Cancel</button>
                <p class="message">Not registered? <a href="#">Create an account</a></p>
            </form>
        </div>
        <div class="alert alert-success alert-dismissible fade show"
             role="alert">
            <b>Success</b> You are registered.
            <button type="button" class="btn-close " data-bs-dismiss="alert"
                    aria-label="Close"></button>
        </div>
    </div>


<input type="hidden" name="sessionLanguage" id="sessionLanguage" value="${sessionScope.lang}"/>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
<script src="js/login.js"></script>


</body>
</html>
