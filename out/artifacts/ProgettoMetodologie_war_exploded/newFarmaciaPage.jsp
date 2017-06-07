<%--
  Created by IntelliJ IDEA.
  User: Andrea
  Date: 07/06/2017
  Time: 15:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>email</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/6.0.0/normalize.css">
    <link rel="stylesheet" href="style.css">

    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
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

<% if(userCon.getConnesso()&& userCon.getPermessi().equals("reg")){%>


<div class="login__pagediv">
    <form action="newFarm.do" method="post"  class="div__login" >
        <p>Nome Farmacia:</p><input type="text" name="NomeFarmacia" class="casella__user"><br>
        <p>indirizzo:</p><input type="text" class="casella__user" name="Indirizzo"/><br>
        <p>Nome Titolare:</p><input type="text" name="NomeTitolare" class="casella__user"><br>
        <p>Numero di telefono:</p><input type="text" name="NumeroDiTelefono" class="casella__user"><br>
        <p>usernameTitolare:</p><input type="text" name="Username" class="casella__user"><br>
        <p>password:</p><input type="password" name="Password" class="casella__user"><br>
        <p>email:<p><input type="text" name="Email" class="casella__user"><br>
        <input type="submit" id="invio" value="Invia" class="botton__submit">
    </form>
</div>







<%} else{ %>
    <div class="login__pagediv">
        <div class="div__login">
            <h1>Utente non autenticato con successo!</h1>
            <h2> <a href="LoginPage.jsp">Torna alla pagina di login</a></h2>
        </div>
    </div>
        <%
    }%>

    <footer class="footer">
        <p>Copyright &copy; Andrea Viviani</p>
    </footer>


</body>
</html>