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
<header class="header clearfix">
  <a href="" class="header__Logo">Farmacie Della Regione Piemonte</a>
  <a href="" class="header__icon-bar">
    <span></span>
    <span></span>
    <span></span>
  </a>
  <ul class="header__menu">
    <li class="header__menu__item"><a href="index.jsp">Home</a></li>
    <li class="header__menu__item"><a href="LoginPage.jsp">Login</a></li>
    <li class="header__menu__item"><a href="">Chi siamo</a></li>
    <li class="header__menu__item"><a href="">FAQ</a></li>
  </ul>
</header>
<div class="login__pagediv">
  <form action="login.do" method="post"  class="div__login" >
    Username:  <input type="text" name="user" class="casella__user"><br>
    Password:<input type="password" name="psw" class="casella__user"/><br>
    <input type="submit" id="invio" value="Login" class="botton__submit">
  </form>
</div>




<footer class="footer">
  <p>Copyright &copy; Andrea Viviani</p>
</footer>




</body>
</html>
