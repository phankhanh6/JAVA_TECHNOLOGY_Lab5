<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Sign in</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <!-- jQuery library -->
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.3/dist/jquery.slim.min.js"></script>
    <!-- Popper JS -->
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <!-- Latest compiled JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
    <style>
        body{
        }
        .container{
            border: 1px solid #000000;
            border-radius: 10px;
            width: 400px;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-self: center;
            padding: 10px 10px 10px 10px;

        }
    </style>
</head>
<body>
<div class="container">
    <h3 class="heading">Information</h3>
    <div class="space"></div>
    <form action="./signupServlet" method="POST" >
        <%--        <h3 class="heading">Thành viên đăng kí</h3>--%>
        <%--        <div class="space"></div>--%>
        <div class ="form-group">
            <label for="fullname" class="form-label">Fullname</label>
            <input id="fullname" name="fullname" type="text" placeholder="vd: Phan Văn Khánh" class="form-control">
            <span class="form-message"></span>
        </div>
        <div class="form-group">
            <label for="email" name="email" class="form-label">Email</label>
            <input id="email" name="email" type="text" placeholder="vd: vandebeek17@gmail.com" class="form-control">
            <span class="form-message"></span>
        </div>
        <div class ="form-group">
            <label for="username" class="form-label">Username</label>
            <input id="username" name="username" type="text" placeholder="vd: PhanKhanh" class="form-control">
            <span class="form-message"></span>
        </div>
        <div class="form-group">
            <label for="password" name="password" class="form-label" >Password</label>
            <input id="password" name="password" type="password" class="form-control" placeholder="Password">
            <span class="form-message"></span>
        </div>
        <div class="form-group">
            <label for="password" name="password" class="form-label" >Confirm password</label>
            <input id="password-after" name="password-after" type="password" class="form-control" placeholder="Confirm password">
            <span class="form-message"></span>
        </div>
            <div class="form-group">
                <span id="form-message"></span>
            </div>
        <button type="submit" class="btn btn-primary">Register</button>
    </form>
</div>
<script>
    var msg = '${message}';
    var span  = document.getElementById("form-message");
    console.log(span);
    if('${message}'!=''){
        span.parentElement.classList.add("alert-success");
        span.innerText = msg;
    }
</script>
</body>
</html>
