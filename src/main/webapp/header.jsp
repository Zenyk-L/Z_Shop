<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
<fmt:setLocale value = "${sessionScope.lang}"/>
<fmt:setBundle basename = "resources"/>

<header class="section-header">
    <section class="header-main border-bottom">

        <div class="d-flex justify-content-end">

            <c:if test="${not empty sessionScope.user}">
                 <div id="userEmail" class="text-white mr-5"><a <c:if test="${sessionScope.user.role == 'USER' }"> href="/buyHistory" </c:if>> <i class="fas fa fa-user-circle-o"></i></a>  ${sessionScope.user.email}</div>
            </c:if>
            <fieldset>
                <label style="color: white"> <fmt:message key="header.Language" /></label>
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

                    <span class="logo">Z SHOP</span> </a></div>
                <div class="col-lg-4 col-xl-5 col-sm-8 col-md-4 d-none d-md-block">
                    <form action="#" class="search-wrap">
                        <div class="input-group w-100"><input type="text" id="searchNameInput"
                                                              class="form-control search-form" style="width:55%;"
                                                              placeholder="<fmt:message key="header.Search_by_name" />"
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
                        <c:if test="${ sessionScope.user.role == 'USER'}">
                            <a class="text-white mr-5" href="/editUser"><i class="fas fa fa-user"></i> <fmt:message key="header.Profile_edit" /></a>
                        </c:if>

                        <c:if test="${sessionScope.user.role == 'ADMIN' }">
                            <!-- Example single danger button -->
                            <div class="btn-group">
                                <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <i class="fas fa fa-cog"></i> <fmt:message key="header.Admin_menu" />
                                </button>
                                <div class="dropdown-menu bg-primary" style="color: black">
                                    <a class="dropdown-item text-white" href="/createProduct"><i class="fas fa fa-plus"></i>  <fmt:message key="header.Add_product" /></a>
                                    <a class="dropdown-item text-white" href="/showAllBuckets"><i class="fas fa fa-file-archive-o"></i> <fmt:message key="header.See_All" /></a>
                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item text-white" href="/editUser"><i class="fas fa fa-user"></i> <fmt:message key="header.Profile_edit" /></a>
                                </div>
                            </div>

                        </c:if>

                        <c:choose>
                            <c:when test="${sessionScope.user.role == 'ADMIN' || sessionScope.user.role == 'USER'}">
                                <a class="text-white mr-4" href="${pageContext.request.contextPath}/logout"><i
                                        class="fa fa-sign-out"></i> <fmt:message key="header.LOGOUT" /></a>
                            </c:when>
                            <c:otherwise>
                                <a class="text-white mr-5 togglePopup" href="#"><i
                                        class="fa fa-sign-in"></i> <fmt:message key="header.LOGIN" /></a>
                            </c:otherwise>
                        </c:choose>

                    </div>

                </div>
            </div>
        </div>
    </section>
    <nav class="navbar navbar-expand-md navbar-main border-bottom" <c:if test="${requestScope['javax.servlet.forward.request_uri'] != '/home'}">hidden</c:if>>
        <div class="container-fluid">
            <div class="navbar-collapse collapse" id="dropdown6" style="">
                <ul class="navbar-nav mr-auto" >
                    <li class="nav-item"><p class="nav-link"> <fmt:message key="header.SORT_BY_CATEGORY" /></p></li>
                    <li class="nav-item dropdown">


                        <select name="category" id="sortByCategory" class="nav-link categoryLink dropdown-toggle"
                                style="border-color:white" onchange="searchSortFunction()">

                            <option value="default"><fmt:message key="header.ALL" /></option>
                            <c:forEach items="${categories}" var="category">
                                <c:choose>
                                    <c:when test="${category.id == sortByCategory}">
                                        <option value="${category.id}" selected>${category.translations[sessionScope.lang]}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${category.id}">${category.translations[sessionScope.lang]}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                            < </select>

                    </li>
                    <li class="nav-item dropdown">

                        <select name="category" id="sortByName" class="nav-link dropdown-toggle"
                                style="border-color:white" onchange="searchSortFunction()">
                            <option class="nav-item" value="default"> <fmt:message key="header.SORT_BY_NAME" /> </option>
                            <option value="UP"><fmt:message key="header.NAME_UP" /> </option>
                            <option value="DOWN"><fmt:message key="header.NAME_DOWN" />  <i class="fas fa-chevron-circle-down"></i></option>
                        </select>

                    </li>
                    <li class="nav-item dropdown">

                        <select name="category" id="sortByPrice" class="nav-link dropdown-toggle"
                                style="border-color:white" onchange="searchSortFunction()">
                            <option value="default"><fmt:message key="header.SORT_BY_PRICE" /></option>
                            <option value="UP"><fmt:message key="header.PRICE_UP" /></option>
                            <option value="DOWN"><fmt:message key="header.PRICE_DOWN" /></option>
                        </select>

                    </li>

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
                <input type="text" name="firstName" class="firstName" placeholder="<fmt:message key="header.First_name" />" required/>
                <input type="text" name="lastName" placeholder="<fmt:message key="header.Last_name" />" required/>
                <input type="email" name="email" placeholder="<fmt:message key="header.email_address" />" required/>
                <input type="password" name="password" placeholder="<fmt:message key="header.password" />" required/>
                <%--            <input name="cpassword" type="password" placeholder="confirm password"/>--%>

                <button type="submit" class="register"><fmt:message key="header.Register" /></button>
                <button type="reset" class="cancel"><fmt:message key="header.Cancel" /></button>
                <p class="message"><fmt:message key="header.Already_registered" />? <a href="#"><fmt:message key="header.Sign_In" /></a></p>
            </form>

            <form class="login-form"  action="/login" method="POST">

                <input type="email" name="email" class="email" placeholder="<fmt:message key="header.email_address" />" value="user@mail.com" required/>
                <input type="password" name="password" class="password" placeholder="<fmt:message key="header.password" />" value="user" required/>

                <button type="submit" class="login"><fmt:message key="header.Sign_In" /></button>
                <button type="reset" class=" cancel"><fmt:message key="header.Cancel" /></button>
                <p class="message"><fmt:message key="header.Not_registered" />? <a href="#"><fmt:message key="header.Create_an_account" /></a></p>
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
