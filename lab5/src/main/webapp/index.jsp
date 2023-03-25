<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <!-- jQuery library -->
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.3/dist/jquery.slim.min.js"></script>
    <!-- Popper JS -->
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <!-- Latest compiled JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
    <title>Login</title>
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
    <h1>User Login</h1>
    <form action="./loginServlet" method="post">
        <div class="form-group">
            <label for="username" class="form-label">Username:</label>
            <input type="text" class="form-control" id="username" placeholder="Enter username" name="username">
        </div>
        <div class="form-group">
            <label for="password" class="form-label">Password:</label>
            <input type="password" class="form-control" id="password" placeholder="Enter password" name="password">
        </div>
        <div class="form-group" style="padding-left: 20px">
            <input class="form-check-input" type="checkbox" name="remember">
            <label class="form-check-label">Remember me</label>
        </div>
        <div class="form-group">
            <span id="form-message"></span>
        </div>
        <button type="submit" class="btn btn-primary">Login</button>
        <%--    </div>--%>
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