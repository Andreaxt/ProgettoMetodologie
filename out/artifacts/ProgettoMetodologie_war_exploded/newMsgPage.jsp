<%--
  Created by IntelliJ IDEA.
  User: Andrea
  Date: 05/06/2017
  Time: 11:39
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
<jsp:useBean id="userCon" scope="session" class="beans.UtenteConnesso" />

<header class="header clearfix">
    <a href="" class="header__Logo">Farmacie Della Regione Piemonte</a>
    <a href="" class="header__icon-bar">
        <span></span>
        <span></span>
        <span></span>
    </a>
    <ul class="header__menu">
        <li class="header__menu__item"><a href="index.jsp">Home</a></li>
        <% if(userCon.getConnesso()==true){
        %>
        <li class="header__menu__item"><a href="LogOutPage.jsp">LogOut</a></li>
        <%
        }else{
        %>
        <li class="header__menu__item"><a href="LoginPage.jsp">Login</a></li>
        <%
            }
        %>
        <li class="header__menu__item"><a href="homeCoreSito.jsp">AreaRiservata</a></li>
        <li class="header__menu__item"><a href="">Chi siamo</a></li>
        <li class="header__menu__item"><a href="">FAQ</a></li>
    </ul>


</header>


<div class="login__pagediv">
    <form action="newMsg.do" method="post"  class="div__login" >
        Invia a:<input type="text" name="destinatario" class="casella__user"><br>
        Oggetto:<input type="text" class="casella__user" name="oggetto"/><br>
       <p>Testo:</p><textarea  style="resize:none" cols="30" rows="10" class="casella__password" name="testo"></textarea><br>
        <input type="submit" id="invio" value="Invia" class="botton__submit">
    </form>
</div>

<footer class="footer">
    <p>Copyright &copy; Andrea Viviani</p>
</footer>




</body>
</html>
