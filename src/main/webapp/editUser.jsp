<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit User</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>


<%--    <div>--%>
<h3> Edit user</h3>
        <form  action="/editUser" method="POST">

            <div class="form-group">
                <label>First name</label>
                <input type="text" name="firstName" class="form-control" placeholder="Enter first name" value="${sessionScope.user.firstName}" required >
            </div>

            <div class="form-group">
                <label>Last name</label>
                <input type="text" name="lastName" class="form-control" placeholder="Enter last name" value="${sessionScope.user.lastName}" required>
            </div>

            <div class="form-group">
                <label>Email</label>
                <input type="email" name="email" class="form-control" placeholder="Enter email"  value="${sessionScope.user.email}"  required>
            </div>

            <div class="form-group">
                <label>New password</label>
                <input type="password" name="newPassword" class="form-control" placeholder="Enter new password">
            </div>

            <div class="form-group">
                <label>Confirm new password</label>
                <input type="password" name="newPasswordConfirm" class="form-control" placeholder="Confirm new password" >
            </div>

            <div class="form-group">
                <label>Old password</label>
                <input type="password" name="oldPassword" class="form-control" placeholder="Enter old password" required>
            </div>

            <button type="submit" class="register">Edit</button>
            <button type="reset" class="cancel">Cancel</button>

        </form>


<%--    </div>--%>



</body>
</html>
