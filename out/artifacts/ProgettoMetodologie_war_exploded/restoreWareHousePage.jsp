<%@ page import="Utility.RestoreWareHouseUtility" %><%--
  Created by IntelliJ IDEA.
  User: Andrea
  Date: 07/08/2017
  Time: 11:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CompraProdotti</title>
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

        <%   String permessi=userCon.getPermessi();
            boolean fatto=false;
            if(permessi.equals("tf")){%>
        <li class="header__menu__item"><a href="homeTitolareFarmacia.jsp">AreaRiservata</a></li>

        <% fatto=true;}
        %>

        <li class="header__menu__item"><a href="">Chi siamo</a></li>
        <li class="header__menu__item"><a href="">FAQ</a></li>
    </ul>

</header>

<% if(permessi.equals("tf")){
%>

<table class="table">
    <div>
        <h1>Medicinali Disponibili</h1>
        <tr><th>Nome Farmaco</th><th>numero pezzi</th></tr>
    </div>
    <tbody>
    <% int id_farmacia= userCon.getIdFarmacia(); %>
    <% String result="";%>
    <% RestoreWareHouseUtility mostra = new RestoreWareHouseUtility();%>
    <%  result= mostra.GeneraTabellaMedicinaliMagazzino(id_farmacia); %>
    <%=result%>
    </tbody>

</table>

<%
}
else{
%>
<div class="login__pagediv">
    <div class="div__login">
        <h1>Conenuto pagina non accessibile con le tue credenziali!</h1>
        <h2> <a href="index.jsp">Torna alla home</a></h2>
    </div>
</div>

<%
    }
%>



<footer class="footer">
    <p>Copyright &copy; Andrea Viviani</p>
</footer>


</body>
</html>