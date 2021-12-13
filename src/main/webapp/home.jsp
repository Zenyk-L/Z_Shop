<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

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
    <title><fmt:message key="home.Title" /></title>
</head>
<body>

<jsp:include page="header.jsp"/>

<div class="containerBody overflow-hidden">

    <div class="row">

        <c:forEach items="${products}" var="product">

            <div class="card col-3 mt-3" >

                <tags:product_image file_name="${product.image}"></tags:product_image>

                <div class="card-body">

                    <tags:product_card product="${product}"></tags:product_card>

                    <p class="card-text"></p>
                    <div class="d-flex">
                        <c:if test="${product.quantity == 0}">
                            <span class=" ml-3 btn bg-warning text-dark"><b><fmt:message key="home.Out_of_stock" /></b></span>
                        </c:if>
                        <c:if test="${sessionScope.user.role != 'ADMIN' && product.quantity > 0}">
                            <a href="/addToBucket?productId=${product.id}"  class="btn btn-primary">+ <fmt:message key="home.add_to_bucket" /></a>
                        </c:if>
                        <c:if test="${sessionScope.user.role == 'ADMIN'}">
                            <a href="/editProduct?productId=${product.id}"  class="btn btn-info ms-auto"><fmt:message key="home.Edit" /></a>
                            <a href="#" onclick="deleteProduct(${product.id}, '${product.name}' )" class="btn btn-danger ms-auto"><fmt:message key="home.Delete" />  </a>
                        </c:if>
                    </div>
                </div>
            </div>
        </c:forEach>


    </div>


    <br/>
    <nav aria-label="..." class="d-flex justify-content-center mt-3">
    <ul class="pagination">
        <c:if test="${totalPages > 1}">
            <c:choose>
                <c:when test="${page  > 1}">
                    <li class="page-item "><a class="page-link " onclick="getPage(${page-1})" href="#"<%--href="/home?page=${page-1}"--%>><fmt:message key="home.Previous" /></a></li>
                </c:when>
                <c:otherwise>
                    <li class="page-item disabled"><a class="page-link" href="#"><fmt:message key="home.Previous" /></a></li>
                </c:otherwise>
            </c:choose>

            <c:forEach  begin="1" end="${totalPages}" var="pageNumber">
                <c:choose>
                    <c:when test="${pageNumber == page }">
                        <li class="page-item active"><a class="page-link" onclick="getPage(${pageNumber})" href="#"<%--href="/home?page=${pageNumber}"--%>>${pageNumber}</a></li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item"><a class="page-link" onclick="getPage(${pageNumber})" href="#" <%--href="/home?page=${pageNumber}"--%>>${pageNumber}</a></li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>


            <c:choose>
                <c:when test="${totalPages < page + 1 }">
                    <li class="page-item disabled"><a class="page-link " href="#"><fmt:message key="home.Next" /></a></li>
                </c:when>
                <c:otherwise>
                    <li class="page-item"><a class="page-link" onclick="getPage(${page+1})" href="#" <%--href="/home?page=${page+1}"--%>><fmt:message key="home.Next" /></a></li>
                </c:otherwise>
            </c:choose>
        </c:if>
    </ul>
</nav>
</div>
<br>


<jsp:include page="footer.jsp"/>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="js/home.js"></script>

</body>
</html>