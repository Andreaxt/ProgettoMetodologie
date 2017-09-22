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

        <%   String permessi=userCon.getPermessi();
            boolean fatto=false;
            if(permessi.equals("reg")){%>
        <li class="header__menu__item"><a href="homeRegione.jsp">AreaRiservata</a></li>

        <%fatto=true;}
            if(permessi.equals("tf")){%>
        <li class="header__menu__item"><a href="homeTitolareFarmacia.jsp">AreaRiservata</a></li>

        <% fatto=true;}

            if(permessi.equals("df")) {%>

        <li class="header__menu__item"><a href="homeMedicoFarmacista.jsp">AreaRiservata</a></li>

        <% fatto= true;}

            if(permessi.equals("ob"))   { fatto= true;%>
        <li class="header__menu__item"><a href="homeOperatoriBanco.jsp">AreaRiservata</a></li>
        <%}%>

        <li class="header__menu__item"><a href="">Chi siamo</a></li>
        <li class="header__menu__item"><a href="">FAQ</a></li>
    </ul>

</header>

<% if(permessi.equals("tf")||permessi.equals("df")){ %>

<h1>Inserisci i dati della ricetta</h1>
<div class="login__pagediv">
    <form action="newUtente.do" method="post"  class="div__login" >
        Codice Ricetta:<input type="text" name="user" class="casella__user"><br>
        Codice Fiscale<input type="text" class="casella__user" name="psw"/><br>
        <input type="submit" id="invio" value="Verifica" class="botton__submit">
    </form>
</div>

<% }

else{ %>
<div class="login__pagediv">
    <div class="div__login">
        <h1>Utente non autenticato con successo!</h1>
        <h2> <a href="LoginPage.jsp">Torna alla pagina di login</a></h2>
    </div>
</div>

<% }%>

<footer class="footer">
    <p>Copyright &copy; Andrea Viviani</p>
</footer>




</body>
</html>
