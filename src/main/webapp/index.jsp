<!DOCTYPE html>
<html lang="en">
<head>
    <title>File Upload</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="style/index.css">
</head>
<body>
<%--<form method="POST" action="upload" enctype="multipart/form-data" >--%>
<%--    File:--%>
<%--    <input type="file" name="file" id="file" /> <br/>--%>
<%--    Destination:--%>
<%--    <input type="text" value="/tmp" name="destination"/>--%>
<%--    </br>--%>
<%--    <input type="submit" value="Upload" name="upload" id="upload" />--%>
<%--</form>--%>
<button class="togglePopup">Open Popup</button>
<div id="popup">
    <div id="popupOverlay"></div>
    <div id="popupContent">
        <h1> my cool popup!</h1>
        <input type="text" >
        <input type="text" >
        <button class="togglePopup">Close Popup</button>
    </div>
</div>

<img src="https://cdn4.vectorstock.com/i/1000x1000/89/13/user-login-icon-vector-21078913.jpg" height="50px" width="130px" style="margin: 0 auto" onclick="javascript:openLogin()">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">

    $(".togglePopup").on('click',function(){
        $("#popup").fadeToggle();
    });

function openLogin(){
var p1 = 'scrollbars=no,resizable=no,status=no,location=no,toolbar=no,menubar=no'
var p2 = 'width=400,height=400,left=-500,top=-500'
open('login.jsp', 'login', p1+p2);
}
</script>

</body>
</html>