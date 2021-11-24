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
    <div class="login-page" id="popup">
        <div class="form">

            <img src="https://cdn4.vectorstock.com/i/1000x1000/89/13/user-login-icon-vector-21078913.jpg" height="50px"
                 width="50px" style="margin: 0 auto" onclick="javascript:openLogin()">
            <br>
            <form class="register-form" action="/registration" method="POST">
                <input type="text" name="firstName" placeholder="First name"/>
                <input type="text" name="lastName" placeholder="Last name"/>
                <input type="text" name="email" placeholder="email address"/>
                <input type="password" name="password" placeholder="password"/>
                <%--            <input name="cpassword" type="password" placeholder="confirm password"/>--%>

                <button type="submit" class="register">Register</button>
                <button type="reset" class="cancel">Cancel</button>
                <p class="message">Already registered? <a href="#">Sign In</a></p>
            </form>

            <form class="login-form" action="/login" method="POST">

                <input type="text" name="email" placeholder="email address" value="user@mail.com"/>
                <input type="password" name="password" placeholder="password" value="user"/>
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

<jsp:include page="header.jsp"></jsp:include>






    <div class="containerBody overflow-hidden">

<%--        <c:if test="${sessionScope.success == 'invalid input' || sessionScope.success == 'fail: user already registered' || sessionScope.success == 'registered'}">--%>
<%--            <H1> Hello ${success}</H1>--%>
<%--        </c:if>--%>






    <div class="row">


        <c:forEach items="${products}" var="product">

            <div class="card col-3 mt-3" >
                    <%--            <img src="https://kaverisias.com/wp-content/uploads/2018/01/catalog-default-img.gif" class="card-img-top" alt="Image not found">--%>

                <div style="width: 100%; height: 200px; background-image: url('https://kaverisias.com/wp-content/uploads/2018/01/catalog-default-img.gif'); background-size: 100% 100%">
                       <div style="width:100%; height:100%; background-image:url('${pageContext.request.contextPath}/image/${product.image}'); background-size:100% 100%">

                    </div>
                </div>
                       <%-- <img src="${pageContext.request.contextPath}/image/IT.jpg">--%>
                <div class="card-body">
                    <h5 class="card-title">Name: ${product.name}</h5>
                    <h6 class="card-title" hidden >Category: ${product.id}</h6>
                    <h6 class="card-title" hidden>Category:${sessionScope.lang}</h6>
                    <h6 class="card-title" id="categoryName" >Category : ${product.category.translations[sessionScope.lang]} </h6>

                    <p class="card-text">Description: ${product.description} </p>
                    <p class="card-text">Color: ${product.color}</p>
                    <p class="card-text">Scale: ${product.scale}</p>
                    <p class="card-text">Added: ${product.addingDate}</p>
                    <span class="card-text">Quantity: ${product.quantity}</span>

                    <span class="card-text ml-3"><b>Price: ${product.price}</b></span>
                    <p class="card-text"></p>
                    <div class="d-flex">
                        <a href="/addToBucket?productId=${product.id}"  class="btn btn-primary"> + add to bucket</a>
                        <a href="#" onclick="deleteProduct(${product.id}, '${product.name}' )" class="btn btn-danger ms-auto"> Delete </a>
                    </div>
                </div>
            </div>
        </c:forEach>


    </div>
</div>

<br/>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
function fillCategoryName(){
    $('#categoryName').html('Category: ${product.category.translations[param.lang]}');
}


function deleteProduct(productId, prodectName){

    if (window.confirm("Do you really want to delete "+ prodectName + " ?")) {
        window.open("/deleteProduct?productId=" + productId, "_self");
    }

    }




</script>

</body>
</html>