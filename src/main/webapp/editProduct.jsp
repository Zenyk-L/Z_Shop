<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
    <title>Create Product</title>
</head>
<body>

<jsp:include page="header.jsp"></jsp:include>


<form id="form" action="editProduct" method="POST" enctype="multipart/form-data" accept-charset="utf-8">

    <div class="form-group">
        <label>Product Name</label>
        <input type="text" name="name" class="form-control productName"
               placeholder="Enter product name" value="${product.name}" required>
    </div>
    <div class="form-group">
        <label>Image</label>
        <input type="file" name="file" class="form-control productName"
               placeholder="Choose file" accept="image/*" value="${product.image}">
    </div>

    <div class="form-group">
        <label>Category</label>
        <select name="category" id="selectCat" class="form-control productName">
            <option value="${product.category.id}" selected hidden>${product.category.translations[sessionScope.lang]}</option>
            <c:forEach items="${categories}" var="category">
                <option value="${category.id}">${category.translations[sessionScope.lang]}</option>
            </c:forEach>
            <option id="addCategory" value="add">+ add category</option>
        </select>

        <c:forEach items="${languages}" var="language">
        <input type="text" name="newLanguage_${language.shortName}" class="form-control productName" style="display:none"
               placeholder="Enter product category on ${language.fullName}"  value="${language.fullName}" required>
        </c:forEach>
    </div>
    <div class="form-group">
        <label>Quantity</label>
        <input type="number" name="quantity" class="form-control productName"
               placeholder="Enter product quantity" min="0" value="${product.quantity}" required>
    </div>
    <div class="form-group">
        <label>Product Description</label>
        <input type="text" name="description" class="form-control productDescription"
               placeholder="Enter product description" value="${product.description}" required>
    </div>
    <div class="form-group">
        <label>Product color</label>
        <input type="text" name="color" class="form-control productName"
               placeholder="Enter product color"  value="${product.color}" required>
    </div>
    <div class="form-group">
        <label>Product scale</label>
        <input type="text" name="scale" class="form-control productName"
               placeholder="Enter product scale" value="${product.scale}" required>
    </div>

    <div class="form-group">
        <label>Product Price</label>
        <input type="number" step="0.01" name="price" class="form-control productPrice"
               placeholder="Enter product price" value="${product.price}" required>
    </div>


    <button class="btn btn-primary createProduct" >Submit</button>

</form>

<script>
    var form = document.getElementById('form');

    function alertOfSuccessShow() {
        if (form.checkValidity()) {
            alert("Product added.");
        }
    }

    $(document).ready(function () {
        $('#selectCat').change(function () {
            console.log($('#selectCat option:selected').text());
            console.log($('#selectCat option:selected').val());
            if($('#selectCat option:selected').text() == '+ add category'){
                $("input[name^=newLanguage]").val('');
                $("input[name^=newLanguage]").css("display", "block");
                $("#selectCat").hide();
            }
        })
    });



</script>

</body>
</html>