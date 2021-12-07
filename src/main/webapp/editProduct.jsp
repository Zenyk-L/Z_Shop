<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
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

    <title><fmt:message key="home.Edit"/> <fmt:message key="createProduct.product"/></title>
</head>
<body>


<jsp:include page="header.jsp"></jsp:include>


<form id="form" class="mb-3" action="editProduct" method="POST" enctype="multipart/form-data" accept-charset="utf-8">

    <input type="text" name="productId" value="${product.id}" hidden>
    <div class="form-group">
        <label><fmt:message key="createProduct.Product" /> <fmt:message key="createProduct.name"/></label>
        <input type="text" name="name" class="form-control productName"
               placeholder="<fmt:message key="createProduct.Enter" /> <fmt:message key="createProduct.product" /> <fmt:message key="createProduct.name"/>" value="${product.name}" required>
    </div>
    <div class="form-group">
        <label><fmt:message key="createProduct.Image" /></label>
        <input type="file" name="file" class="form-control productName"
               placeholder="Choose file" accept="image/*" value="${product.image}">
    </div>

    <div class="form-group">
        <label><fmt:message key="createProduct.Category"/></label>
        <select name="category" id="selectCat" class="form-control productName">
            <option value="${product.category.id}" selected hidden>${product.category.translations[sessionScope.lang]}</option>
            <c:forEach items="${categories}" var="category">
                <option value="${category.id}">${category.translations[sessionScope.lang]}</option>
            </c:forEach>
            <option id="addCategory" value="add">+ <fmt:message key="createProduct.add_category"/></option>
        </select>

        <c:forEach items="${languages}" var="language">
        <input type="text" name="newLanguage_${language.shortName}" class="form-control productName" style="display:none"
               placeholder="<fmt:message key="createProduct.Enter" /> <fmt:message key="createProduct.product" /> <fmt:message key="createProduct.category"/> ${language.fullName}"  value="${language.fullName}" required>
        </c:forEach>
    </div>
    <div class="form-group">
        <label><fmt:message key="createProduct.Product" /> <fmt:message key="createProduct.quantity" /></label>
        <input type="number" name="quantity" class="form-control productName"
               placeholder="<fmt:message key="createProduct.Enter" /> <fmt:message key="createProduct.product" /> <fmt:message key="createProduct.quantity"/>" min="0" value="${product.quantity}" required>
    </div>
    <div class="form-group">
        <label><fmt:message key="createProduct.Product" /> <fmt:message key="createProduct.description" /> </label>
        <input type="text" name="description" class="form-control productDescription"
               placeholder="<fmt:message key="createProduct.Enter" /> <fmt:message key="createProduct.product" /> <fmt:message key="createProduct.description" />" value="${product.description}" required>
    </div>
    <div class="form-group">
        <label><fmt:message key="createProduct.Product" /> <fmt:message key="createProduct.color" /></label>
        <input type="text" name="color" class="form-control productName"
               placeholder="<fmt:message key="createProduct.Enter" /> <fmt:message key="createProduct.product" /> <fmt:message key="createProduct.color" />"  value="${product.color}" required>
    </div>
    <div class="form-group">
        <label><fmt:message key="createProduct.Product" /> <fmt:message key="createProduct.scale" /></label>
        <input type="text" name="scale" class="form-control productName"
               placeholder="<fmt:message key="createProduct.Enter" /> <fmt:message key="createProduct.product" /> <fmt:message key="createProduct.scale" />" value="${product.scale}" required>
    </div>

    <div class="form-group">
        <label><fmt:message key="createProduct.Product" /> <fmt:message key="createProduct.price" /></label>
        <input type="number" step="0.01" name="price" class="form-control productPrice"
               placeholder="<fmt:message key="createProduct.Enter" /> <fmt:message key="createProduct.product" /> <fmt:message key="createProduct.price" />" value="${product.price}" required>
    </div>


    <button class="btn btn-primary createProduct" onclick="alertOfSuccessShow()" ><fmt:message key="createProduct.Submit" /></button>

</form>
<br>
<jsp:include page="footer.jsp"/>
<script>
    var form = document.getElementById('form');

    function alertOfSuccessShow() {
        if (form.checkValidity()) {
            alert("<fmt:message key="createProduct.Product" /> <fmt:message key="createProduct.edited" />.");
        }
    }

    $(document).ready(function () {
        $('#selectCat').change(function () {
            console.log($('#selectCat option:selected').text());
            console.log($('#selectCat option:selected').val());
            if($('#selectCat option:selected').val() == 'add'){
                $("input[name^=newLanguage]").val('');
                $("input[name^=newLanguage]").css("display", "block");
                $("#selectCat").hide();
            }
        })
    });



</script>

</body>
</html>