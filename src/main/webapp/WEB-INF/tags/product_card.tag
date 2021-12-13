<%@ attribute name="product" type="com.z.shop.entity.Product" required="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<h5 class="card-title"><fmt:message key="home.Name" />: ${product.name}</h5>
<h6 class="card-title" id="categoryName" ><fmt:message key="header.Category" /> : ${product.category.translations[sessionScope.lang]} </h6>

<p class="card-text"><fmt:message key="home.Description" />: ${product.description} </p>
<p class="card-text"><fmt:message key="home.Color" />: ${product.color}</p>
<p class="card-text"><fmt:message key="home.Scale" />: ${product.scale}</p>
<p class="card-text"><fmt:message key="home.Added" />: ${product.addingDate}</p>
<span class="card-text"><fmt:message key="home.Quantity" />: ${product.quantity}</span>

<span class="card-text ml-2"><b><fmt:message key="home.Price" />: ${product.price}</b></span>