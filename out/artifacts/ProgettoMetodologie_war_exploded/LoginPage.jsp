<%--
  Created by IntelliJ IDEA.
  User: Andrea
  Date: 28/04/2017
  Time: 15:05
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<style>
  input[type=submit]:hover {
    background-color: #45a049;
  }

</style>
<head>

  <title>$LoginPage$</title>

  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/6.0.0/normalize.css">
  <link href="style.css" rel="stylesheet" >

</head>

<body>


<form action="login.do" method="post"  class="div__login" >
  Username <input type="text" name="user" class="casella__user"><br>
  Password <input type="password" class="casella__password" name="psw"/><br>
  <input type="submit" id="invio" value="Login" class="botton__submit">
</form>



</body>
</html>
