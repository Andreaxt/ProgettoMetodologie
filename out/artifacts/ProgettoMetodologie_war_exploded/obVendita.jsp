<%@ page import="Utility.BuyMedicinalOb" %>

<%--
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
    <script src="library/jquery-3.2.1.min.js" ></script>

    <script src="utilityJS/utilityBuyOb.js"></script>

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
            if(permessi.equals("ob")){%>
        <li class="header__menu__item"><a href="homeOperatoriBanco.jsp">AreaRiservata</a></li>

        <% fatto=true;}
        %>

        <li class="header__menu__item"><a href="">Chi siamo</a></li>
        <li class="header__menu__item"><a href="">FAQ</a></li>
    </ul>

</header>

<% if(permessi.equals("ob")){
%>

<table id="lm">
    <thead>
    <tr>
        <th>Codice Prodottto</th>
        <th>Nome prodotto</th>
        <%if (!userCon.getPermessi().equals("ob")) %><th>Ricetta</th>
        <th>Prezzo</th>
        <th>Quantità</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <% BuyMedicinalOb vendita = new BuyMedicinalOb();%>
    <%=vendita.VendiMedicinali(userCon.getIdFarmacia(), userCon.getPermessi())%>
    <%vendita.close();%>
    </tbody>
</table>
<input  type="button" class="vendi" id="vendi" value="vendi" style="float:right;">

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
