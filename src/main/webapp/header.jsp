<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Header</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"/>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>


    <link type="text/css" href="style/header.css" rel="stylesheet">
    <link rel="stylesheet" href="style/login.css">


</head>
<body>
<header class="section-header">
    <section class="header-main border-bottom">

        <div class="d-flex justify-content-end">

            <c:if test="${sessionScope.user.amount > 0}">
                <div class="text-white mr-5"> Amount: ${sessionScope.user.amount}</div>
            </c:if>
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

                        <%--                        language switcher--%>
                        <%--                        <div>--%>

                        <%--                            <fieldset>--%>
                        <%--                                <label style="color: white"  > Language</label>--%>
                        <%--                                <select id="localesSession" style="color: #007bff">--%>
                        <%--                                    <c:forEach items="${languages}" var="language">--%>

                        <%--&lt;%&ndash;                                       <c:if test="${language.shortName == sessionScope.lang}">&ndash;%&gt;--%>
                        <%--&lt;%&ndash;                                           <option hidden value ="${language.fullName}"></option>&ndash;%&gt;--%>
                        <%--&lt;%&ndash;                                       </c:if>&ndash;%&gt;--%>

                        <%--                                        <option value="${language.shortName}">${language.fullName}</option>--%>
                        <%--                                    </c:forEach>--%>
                        <%--                                </select>--%>

                        <%--                            </fieldset>--%>
                        <%--                        </div >--%>
                        <%--                            <div id="userEmail">${sessionScope.user.email}</div>--%>
                        <%--                        <a target="_blank" href="#" data-abc="true" class="nav-link widget-header"> <i--%>
                        <%--                                class="fas fa fa-shopping-cart"></i></a> <span class="vl"></span>--%>
                        <%--                        <a target="_blank" href="#" data-abc="true" class="nav-link widget-header"> <i--%>
                        <%--                                class="fas fa fa-user"></i></a> <span class="vl"></span>--%>
                        <%--                        <div class="dropdown btn-group"><a class="nav-link nav-icons" href="#"--%>
                        <%--                                                           id="navbarDropdownMenuLink1" data-toggle="dropdown"--%>
                        <%--                                                           aria-haspopup="true" aria-expanded="false" data-abc="true"><i class="fas fa fa-cog&lt;%&ndash;fa-bell&ndash;%&gt;"></i></a>--%>
                        <%--                            <ul class="dropdown-menu dropdown-menu-right notification-dropdown">--%>
                        <%--                                <li>--%>
                        <%--                                    <div class="notification-title">Admin menu</div>--%>
                        <%--                                    <div class="notification-list">--%>
                        <%--                                        <div class="list-group"><a href="/createProduct"--%>
                        <%--                                                                   class="list-group-item list-group-item-action active"--%>
                        <%--                                                                   data-abc="true">--%>
                        <%--                                            <div class="notification-info">--%>
                        <%--                                                <div class="notification-list-user-img">&lt;%&ndash;<img src="https://img.icons8.com/nolan/100/000000/helping-hand.png" alt="" class="user-avatar-md rounded-circle">&ndash;%&gt;--%>
                        <%--                                                    <i class="fa fa-plus"></i> <h6>Create product</h6></div>--%>
                        <%--                                            </div>--%>
                        <%--                                        </a> <a href="redemption-center"--%>
                        <%--                                                class="list-group-item list-group-item-action active" data-abc="true">--%>
                        <%--                                            <div class="notification-info">--%>
                        <%--                                                <div class="notification-list-user-img"><img--%>
                        <%--                                                        src="https://img.icons8.com/bubbles/100/000000/prize.png" alt=""--%>
                        <%--                                                        class="user-avatar-md rounded-circle"></div>--%>
                        <%--                                                <div class="notification-list-user-block"><span--%>
                        <%--                                                        class="notification-list-user-name">Redemption Center</span>--%>
                        <%--                                                </div>--%>
                        <%--                                            </div>--%>
                        <%--                                        </a> <a href="#" class="list-group-item list-group-item-action active"--%>
                        <%--                                                data-abc="true">--%>
                        <%--                                            <div class="notification-info">--%>
                        <%--                                                <div class="notification-list-user-img"><img--%>
                        <%--                                                        src="https://img.icons8.com/ultraviolet/100/000000/medal.png"--%>
                        <%--                                                        alt="" class="user-avatar-md rounded-circle"></div>--%>
                        <%--                                                <div class="notification-list-user-block"><span--%>
                        <%--                                                        class="notification-list-user-name">Achievements</span></div>--%>
                        <%--                                            </div>--%>
                        <%--                                        </a> <a href="#" class="list-group-item list-group-item-action active"--%>
                        <%--                                                data-abc="true">--%>
                        <%--                                            <div class="notification-info">--%>
                        <%--                                                <div class="notification-list-user-img"><img--%>
                        <%--                                                        src="https://img.icons8.com/bubbles/100/000000/call-female.png"--%>
                        <%--                                                        alt="" class="user-avatar-md rounded-circle"></div>--%>
                        <%--                                                <div class="notification-list-user-block"><span--%>
                        <%--                                                        class="notification-list-user-name">Contact us</span></div>--%>
                        <%--                                            </div>--%>
                        <%--                                        </a></div>--%>
                        <%--                                    </div>--%>
                        <%--                                </li>--%>
                        <%--                            </ul>--%>
                        <%--                        </div>--%>
                        <c:if test="${sessionScope.buckets.size() > 0}">
                            <a class="text-white mr-5" style=" position: relative;" href="/bucket">
                                <span class="number"
                                      style=" position: absolute; right: 6px">${sessionScope.buckets.size()}</span>
                                <img class="img-fluid" src="/image/cart.png" alt="cart"/>
                                    <%--                                <i class="fas fa fa-shopping-cart"></i>--%>
                            </a>
                        </c:if>
                        <c:if test="${sessionScope.success == 'success' }">
                            <a class="text-white mr-5" href="/editUser"><i class="fas fa fa-user"></i></a>
                        </c:if>
                        <a class="text-white mr-5" href="/createProduct"><i class="fas fa fa-cog"></i></a>
                        <%--                            <div class="text-white mr-5">${sessionScope.success}</div>--%>
                        <c:if test="${sessionScope.success != 'success'}">
                            <a class="text-white mr-5 togglePopup" href="#"><i
                                    class="fa fa-sign-in"></i> LOGIN </a>
                        </c:if>
                        <c:if test="${sessionScope.success == 'success' }">
                            <a class="text-white mr-4" href="${pageContext.request.contextPath}/logout"><i
                                    class="fa fa-sign-out"></i> LOGOUT</a>
                        </c:if>
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





<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
<script src="js/login.js"></script>
<script type="text/javascript">

    $(document).ready(function () {
        var language = window.navigator.language;
        <%--alert(language.split('-')[1].toLowerCase());--%>
        <%--console.log('${sessionScope.lang}');--%>

        function openNav() {
            document.getElementById("mySidenav").style.width = "70%";

            document.body.style.backgroundColor = "rgba(0,0,0,0.4)";
        }

        function closeNav() {
            document.getElementById("mySidenav").style.width = "0";
            document.body.style.backgroundColor = "rgba(0,0,0,0)";
        }

        var selItem = '${sessionScope.lang}';
        if (selItem == "") {
            selItem = language.split('-')[1].toLowerCase();
            var searchText = $('#searchNameInput').val();
            var sortByCategory = $('#sortByCategory').val();
            var sortByPrice = $('#sortByPrice').val();
            var sortByName = $('#sortByName').val();
            $.get("home", {
                lang: selItem,
                searchText: searchText,
                sortByCategory: sortByCategory,
                sortByPrice: sortByPrice,
                sortByName: sortByName
            }, function (response) {
                // $(".containerBody").html($($.parseHTML(response)).filter(".containerBody").html());
                $(document.body).html(response);
            });
        }
        // localStorage.getItem("localesSession");
        $("#localesSession").val(selItem);
        $("#localesSession").change(function () {
            var selectedOption = $("#localesSession").val()
            console.log("selectedOption " + selectedOption);
            if (selectedOption) {
                // localStorage.setItem("localesSession", selectedOption);
                var searchText = $('#searchNameInput').val();
                var sortByCategory = $('#sortByCategory').val();
                var sortByPrice = $('#sortByPrice').val();
                var sortByName = $('#sortByName').val();
                $.get("home", {
                    lang: selectedOption,
                    searchText: searchText,
                    sortByCategory: sortByCategory,
                    sortByPrice: sortByPrice,
                    sortByName: sortByName
                }, function (response) {
                    $(".containerBody").html($($.parseHTML(response)).filter(".containerBody").html());
                });
            }
        });
    });


    /*function searching by name, sorting by category, by name, by price from db */
    /* with caching parameters */
    function searchSortFunction() {
        var searchText = $('#searchNameInput').val();

        var searchText = $('#searchNameInput').val();
        var sortByCategory = $('#sortByCategory').val();
        var sortByPrice = $('#sortByPrice').val();
        var sortByName = $('#sortByName').val();
        $.get("home", {
            searchText: searchText,
            sortByCategory: sortByCategory,
            sortByPrice: sortByPrice,
            sortByName: sortByName
        }, function (response) {
            $(".containerBody").html($($.parseHTML(response)).filter(".containerBody").html());
            // $(document.body).html(response);
        });
    }

    /*function showNewWindow(){
        window.open("/index.jsp", "name1", params);

    }*/

</script>

</body>
</html>
