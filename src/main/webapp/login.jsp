<!DOCTYPE html>
<html lang="en">
<head>
    <title>LOGIN</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

    <link rel="stylesheet" href="style/login.css">
    <link rel="stylesheet" href="style/index.css">

</head>
<body>
<script   src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<button class="togglePopup">Open Popup</button>


<div class="login-page" id="popup">
    <div class="form">

        <img src="https://cdn4.vectorstock.com/i/1000x1000/89/13/user-login-icon-vector-21078913.jpg" height="50px" width="50px" style="margin: 0 auto" onclick="javascript:openLogin()">
        <br>
        <form class="register-form" action="/registration" method="POST">
            <input type="text" name="firstName" placeholder="First name"/>
            <input type="text" name="lastName"  placeholder="Last name"/>
            <input type="text" name="email" placeholder="email address"/>
            <input type="password" name="password" placeholder="password"/>
<%--            <input name="cpassword" type="password" placeholder="confirm password"/>--%>

            <button type="submit" class="register" >Register</button>
            <button type="reset" class="togglePopup ">Cancel</button>
            <p class="message">Already registered? <a href="#">Sign In</a></p>
        </form>

        <form class="login-form" action="/login" method="POST">

            <input type="text"  name="email" placeholder="email address" value="user@mail.com"/>
            <input type="password" name="password" placeholder="password" value="user"/>
            <button type="submit" class="login">Login</button>
            <button type="reset" class="togglePopup">Cancel</button>
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

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>


<script src="js/login.js"></script>

</body>
</html>