<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<jsp:include page="header.jsp"></jsp:include>


    <div class="container1 pb-5  mt-md-n3 pl-3">
        <div class="row">
            <div class="col-xl-9 col-md-8">
                <h2 class="h6 d-flex flex-wrap justify-content-between align-items-center px-4 py-3 bg-secondary"><span>Products</span><a class="font-size-sm" style="color: #1a1a1a" href="/home"><svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-chevron-left" style="width: 1rem; height: 1rem;"><polyline points="15 18 9 12 15 6"></polyline></svg>Continue shopping</a></h2>
        <c:forEach items="${buckets}" var="bucket">
    <c:set var="product"  value="${productMap[bucket.productId]}"/>
                <!-- Item-->
                <div class="d-sm-flex justify-content-between my-4 pb-4 border-bottom">
                    <div class="media d-block d-sm-flex text-center text-sm-left">
                        <a class="cart-item-thumb mx-auto mr-sm-4" href="#"><div style="background-image:url('${pageContext.request.contextPath}/image/${product.image}'); background-size:100% 100%; height: 150px; width: 150px"></div> </a>
                        <div class="media-body pt-3">
                            <h3 class="product-card-title font-weight-semibold border-0 pb-0"><a href="#"> ${product.category.translations[sessionScope.lang]}</a> <a href="#">${product.name}</a></h3>
                            <div class="font-size-sm"><span class="text-muted mr-2">Size:</span>${product.scale}</div>
                            <div class="font-size-sm"><span class="text-muted mr-2">Color:</span>${product.color}</div>
                            <div class="font-size-lg text-primary pt-2"><b>Price: </b> ${product.price}</div>
                        </div>
                    </div>
                    <div class="media-body pt-5 ml-5">
                        <div class="font-size-sm"><span class="text-muted mr-2">Description:</span></div>
                        <div class="font-size-sm"><span class="text-muted mr-2">${product.description}</span></div>

                    </div>
                    <div class="pt-2 pt-sm-0 pl-sm-3 mx-auto mx-sm-0 text-center text-sm-left" style="max-width: 10rem;">
                        <div class="form-group mb-2">
                            <label >Quantity</label>
                            <p> ${bucket.quantity}</p>
                        </div>
                        <div class="form-group mb-2">
                            <label >Buy date</label>
                            <p> ${bucket.purchaseDate}</p>
                        </div>
                    </div>
                </div>
        </c:forEach>


            </div>
        </div>
    </div>


<script src="js/bucket.js"></script>
</body>
</html>
