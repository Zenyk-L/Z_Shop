<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Edit User</title>
</head>
<body>
<fmt:setLocale value = "${sessionScope.lang}"/>
<fmt:setBundle basename = "resources"/>
<jsp:include page="header.jsp"></jsp:include>


<%--    <div>--%>
<h3><fmt:message key="editUser.Edit_user" /> </h3>
        <form  action="/editUser" method="POST">

            <div class="form-group">
                <label><fmt:message key="header.First_name" /></label>
                <input type="text" name="firstName" class="form-control" placeholder="<fmt:message key="editUser.Enter" /> <fmt:message key="header.First_name" />" value="${sessionScope.user.firstName}" required >
            </div>

            <div class="form-group">
                <label><fmt:message key="header.Last_name" /></label>
                <input type="text" name="lastName" class="form-control" placeholder="<fmt:message key="editUser.Enter" /> <fmt:message key="header.Last_name" />" value="${sessionScope.user.lastName}" required>
            </div>

            <div class="form-group">
                <label><fmt:message key="header.email_address" /></label>
                <input type="email" name="email" class="form-control" placeholder="<fmt:message key="editUser.Enter" /> <fmt:message key="header.email_address" />"  value="${sessionScope.user.email}"  required>
            </div>

            <div class="form-group">
                <label><fmt:message key="editUser.new"/> <fmt:message key="header.password" />"</label>
                <input type="password" name="newPassword" class="form-control" placeholder="<fmt:message key="editUser.Enter" /> <fmt:message key="editUser.new"/> <fmt:message key="header.password" />">
            </div>

            <div class="form-group">
                <label> <fmt:message key="editUser.Confirm" /> <fmt:message key="editUser.new"/> <fmt:message key="header.password" /></label>
                <input type="password" name="newPasswordConfirm" class="form-control" placeholder="<fmt:message key="editUser.Confirm" /> <fmt:message key="editUser.new"/> <fmt:message key="header.password" />" >
            </div>

            <div class="form-group">
                <label>Old <fmt:message key="header.password" /></label>
                <input type="password" name="oldPassword" class="form-control" placeholder="<fmt:message key="editUser.Enter" /> <fmt:message key="editUser.old" /> <fmt:message key="header.password" />" required>
            </div>

            <button type="submit" class="register"><fmt:message key="home.Edit" /></button>
            <button type="reset" class="cancel"><fmt:message key="header.Cancel" /></button>

        </form>


<%--    </div>--%>

<jsp:include page="footer.jsp"/>

</body>
</html>
