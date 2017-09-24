<%@ page import="Utility.BuyMedicinalUtility" %>
<%@ page import="Utility.BuyMedicinalOb" %>

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

    <script src="library/jquery-3.2.1.min.js" ></script>
    <script src="utilityJS/utilityBuyOb.js"></script>

</head>

<body>
<jsp:useBean id="userCon" scope="session" class="beans.UtenteConnesso" />
<jsp:useBean id="acquisto" scope="session" class="beans.ListaProdotti" />

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
<div style="overflow-x:auto;" id="log">
    <form method="post" action="/ricetta.do">
        <table id="lm">
            <thead>
            <tr>
                <th>Codice Prodottto</th>
                <th>Nome prodotto</th>
                <th>COD. Ricetta</th>
            </tr>
            </thead>
            <tbody>
            <% BuyMedicinalOb vendita = new BuyMedicinalOb();%>
            <%=vendita.listaRicetta(acquisto)%>
            </tbody>
        </table>
        <input class="ricetta" type="submit" id="ricetta" value="Invia Codici" style="float:right;">
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