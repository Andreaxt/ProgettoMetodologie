<%@ page import="Utility.BuyMedicinalOb" %><%--
  Created by IntelliJ IDEA.
  User: Andrea
  Date: 13/09/2017
  Time: 10:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/6.0.0/normalize.css">
    <link rel="stylesheet" href="style.css">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

</head>

<body>
<jsp:useBean id="userCon" scope="session" class="beans.UtenteConnesso" />
<jsp:useBean id="acquisto" scope="session" class="beans.ListaProdotti"/>

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
        <%    permessi=userCon.getPermessi();
            fatto=false;
            if(permessi.equals("reg")){%>
        <li class="header__menu__item"><a href="homeRegione.jsp">AreaRiservata</a></li>

        <%fatto=true;}
            if(permessi.equals("tf")){%>
        <li class="header__menu__item"><a href="homeTitolareFarmacia.jsp">AreaRiservata</a></li>

        <% fatto=true;}

            if(permessi.equals("df")) {%>

        <li class="header__menu__item"><a href="homeMedicoFarmacista.jsp">AreaRiservata</a></li>

        <% fatto= true;} %>


        <li class="header__menu__item"><a href="">Chi siamo</a></li>
        <li class="header__menu__item"><a href="">FAQ</a></li>
    </ul>


</header>




<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<script>
    $(document).ready(function(){

        $(".header__icon-bar").click(function(e){

            $(".header__menu").toggleClass('is-open');
            e.preventDefault();

        });
    });
</script>




<%
    if(userCon.getConnesso()==true && !userCon.getPermessi().equals("reg")){
%>

<h1>Lista prodotti comprati</h1>

<div>
<table id="lm">
    <thead>
    <tr>
        <th>Codice Prodottto</th>
        <th>Nome prodotto</th>
        <th>Prezzo</th>
        <th>Quantità</th>
    </tr>
    </thead>
    <tbody>
    <% BuyMedicinalOb vendita = new BuyMedicinalOb();%>
    <%=vendita.listaAcquisto(acquisto)%>
    </tbody>
    <p style="float: right", font-size:="125%"  > totale prezzo vendita:<%= vendita.prezzo(acquisto)%></p>
</table>
</div>





<%
}
else {
%>
<div class="login__pagediv">
    <div class="div__login">
        <h1>Utente non autenticato!</h1>
        <h2> <a href="LoginPage.jsp">Torna alla pagina di login</a></h2>
    </div>
</div>
<%
    }
%>

<div>
<footer class="footer">
    <p>Copyright &copy; Andrea Viviani</p>
</footer>
</div>

</body>
</html>