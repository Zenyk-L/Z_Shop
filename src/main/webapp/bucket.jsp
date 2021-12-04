<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"
            integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"
            integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="style/bucket.css">
    <title>Bucket</title>
</head>
<body>
<fmt:setLocale value = "${sessionScope.lang}"/>
<fmt:setBundle basename = "resources"/>
<jsp:include page="header.jsp"></jsp:include>

<div class="container1 pb-5  mt-md-n3 pl-3">

        <div class="row">
            <div class="col-xl-9 col-md-8">
                <h2 class="h6 d-flex flex-wrap justify-content-between align-items-center px-4 py-3 bg-primary text-white"><span><fmt:message key="bucket.Bucket" /></span>
                    <a class="font-size-sm" style="color: #eeeeee" href="/home"><svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-chevron-left" style="width: 1rem; height: 1rem;"><polyline points="15 18 9 12 15 6"></polyline></svg><fmt:message key="bucket.Continue_shopping" /></a></h2>
        <c:forEach items="${sessionScope.buckets}" var="bucket">
    <c:set var="product"  value="${productMap[bucket.productId]}"/>
                <!-- Item-->
                <div class="d-sm-flex justify-content-between my-4 pb-4 border-bottom">
                    <div class="media d-block d-sm-flex text-center text-sm-left">
                        <a class="cart-item-thumb mx-auto mr-sm-4" href="#"><div style="background-image:url('${pageContext.request.contextPath}/image/${product.image}'); background-size:100% 100%; height: 150px; width: 150px"></div> </a>
                        <div class="media-body pt-3">
                            <h3 class="product-card-title font-weight-semibold border-0 pb-0"><a href="#"> ${product.category.translations[sessionScope.lang]}</a> <a href="#">${product.name}</a></h3>
                            <div class="font-size-sm"><span class="text-muted mr-2"><fmt:message key="home.Scale" />:</span>${product.scale}</div>
                            <div class="font-size-sm"><span class="text-muted mr-2"><fmt:message key="home.Color" />:</span>${product.color}</div>
                            <div class="font-size-sm"><span class="text-muted mr-2"><fmt:message key="bucket.Available" /> <fmt:message key="home.Quantity" />:</span>${product.quantity}</div>
                            <div class="font-size-lg text-primary pt-2"><b><fmt:message key="home.Price" />: </b> ${product.price}</div>
                        </div>
                    </div>
                    <div class="media-body pt-5 ml-5">
                        <div class="font-size-sm"><span class="text-muted mr-2"><fmt:message key="home.Description" />:</span></div>
                        <div class="font-size-sm"><span class="text-muted mr-2">${product.description}</span></div>
                    </div>
                    <div class="pt-2 pt-sm-0 pl-sm-3 mx-auto mx-sm-0 text-center text-sm-left" style="max-width: 10rem;">
                        <div class="form-group mb-2">
                            <label for="quantity${product.id}"><fmt:message key="home.Quantity" /></label>
                            <input class="form-control form-control-sm" type="number" min="0" max="${product.quantity}" id="quantity${product.id}" value="${bucket.quantity}" onchange="changeQuantity(${product.id}, ${bucket.id})">
                        </div>

<%--&lt;%&ndash;                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-refresh-cw mr-1">&ndash;%&gt;--%>
<%--&lt;%&ndash;                                <polyline points="23 4 23 10 17 10"></polyline>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                <polyline points="1 20 1 14 7 14"></polyline>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                <path d="M3.51 9a9 9 0 0 1 14.85-3.36L23 10M1 14l4.64 4.36A9 9 0 0 0 20.49 15"></path>&ndash;%&gt;--%>
<%--&lt;%&ndash;                            </svg>Update cart&ndash;%&gt;--%>

                        <c:if test="${sessionScope.success == 'success' }">
                        <a class="btn btn-primary btn-block" id="buy${bucket.id}" href="/buyProduct?bucketId=${bucket.id}"><svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-credit-card mr-2">
                            <rect x="1" y="4" width="22" height="16" rx="2" ry="2"></rect>
                            <line x1="1" y1="10" x2="23" y2="10"></line>
                        </svg><fmt:message key="bucket.Buy" /> </a>
                        </c:if>
                        <a  class="btn btn-outline-danger btn-sm btn-block mb-2" href="/removeFromBucket?productId=${product.id}&bucketId=${bucket.id}"><svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-trash-2 mr-1">
                            <polyline points="3 6 5 6 21 6"></polyline>
                            <path d="M19 6v14a2 2 0 0 1-2 2H7a2 2 0 0 1-2-2V6m3 0V4a2 2 0 0 1 2-2h4a2 2 0 0 1 2 2v2"></path>
                            <line x1="10" y1="11" x2="10" y2="17"></line>
                            <line x1="14" y1="11" x2="14" y2="17"></line>
                        </svg><fmt:message key="bucket.Remove" /></a>
                    </div>
                </div>
        </c:forEach>


            </div>
            <!-- Sidebar-->
            <div class="col-xl-3 col-md-4 pt-3 pt-md-0">
                <h2 class="h6 px-4 py-3 bg-primary text-white text-center"><fmt:message key="bucket.Subtotal" /></h2>
                <div class="h3 font-weight-semibold text-center py-3" id="subtotal">${subtotal}</div>
                <hr>
                <c:if test="${sessionScope.success != 'success' }">
                    <span class="btn   btn-danger btn-block"><fmt:message key="bucket.You_need_to_login_to_buy" />You need to login to buy</span>
                </c:if>
                <c:if test="${sessionScope.success == 'success' }">
                    <a class="btn btn-primary btn-block" id="buyAll" href="/buyAllProduct">
                        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-credit-card mr-2">
                            <rect x="1" y="4" width="22" height="16" rx="2" ry="2"></rect>
                            <line x1="1" y1="10" x2="23" y2="10"></line>
                        </svg><fmt:message key="bucket.Buy" /> <fmt:message key="bucket.All" /></a>

                <hr>
                    <a class="btn btn-outline-secondary btn-block" href="/buyHistory"><fmt:message key="bucket.Buying_history" /> Buying history </a>
                </c:if>
                <hr>


            </div>
        </div>
    </div>

<script src="js/bucket.js"></script>

</body>
<jsp:include page="footer.jsp"/>
</html>
