<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
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
    <title>Home</title>
</head>
<body>

<jsp:include page="header.jsp"></jsp:include>

<div class="container overflow-hidden">
    <div class="row">


        <c:forEach items="${products}" var="product">

            <div class="card col-3 mt-3">
                    <%--            <img src="https://kaverisias.com/wp-content/uploads/2018/01/catalog-default-img.gif" class="card-img-top" alt="Image not found">--%>

                <div style="width: 100%; height: 200px; background-image: url('https://kaverisias.com/wp-content/uploads/2018/01/catalog-default-img.gif'); background-size: 100% 100%">
                   <%-- <div style="width:100%; height:100%; background-image:url('data:image/jpg;base64, ${product.image}'); background-size:100% 100%">--%>
                       <div style="width:100%; height:100%; background-image:url('${pageContext.request.contextPath}/image/${product.image}'); background-size:100% 100%">

                    </div>
                </div>
                       <%-- <img src="${pageContext.request.contextPath}/image/IT.jpg">--%>
                <div class="card-body">
                    <h5 class="card-title">Name: ${product.name}</h5>
                    <h6 class="card-title">Category: ${product.category}</h6>
                    <p class="card-text">Description: ${product.description}</p>
                    <p class="card-text">Color: ${product.color}</p>
                    <p class="card-text">Scale: ${product.scale}</p>
                    <p class="card-text">Added: ${product.addingDate}</p>
                    <p class="card-text">Quantity: ${product.quantity}</p>
                    <p class="card-text">Price: ${product.price}</p>

                    <div class="d-flex">
                        <a href="#" class="btn btn-primary"> + add to bucket</a>
                        <a href="/deleteProduct ${product.id}" class="btn btn-danger ms-auto"> Delete </a>
                    </div>
                </div>
            </div>
        </c:forEach>


    </div>
</div>

<br/>

</body>
</html>