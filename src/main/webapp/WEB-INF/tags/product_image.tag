<%@ attribute name="file_name" type="java.lang.String" required="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<div style="width: 100%; height: 200px; background-image: url('https://kaverisias.com/wp-content/uploads/2018/01/catalog-default-img.gif'); background-size: 100% 100%">
    <div style="width:100%; height:100%; background-image:url('${pageContext.request.contextPath}/image/${file_name}'); background-size:contain; background-repeat: no-repeat; background-position: center">
    </div>
</div>