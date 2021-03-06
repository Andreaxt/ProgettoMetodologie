<%@ page import="Utility.EmailUtility" %><%--
  Created by IntelliJ IDEA.
  User: Andrea
  Date: 31/05/2017
  Time: 15:28
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

<%if(userCon.getConnesso()==true){ %>

<%
if(permessi.equals("reg")){%>
<h1><a href="msgTargetReg.jsp">Nuovo Messaggio</a></h1>

<%}
    if(permessi.equals("tf")){%>
<h1><a href="newMsgPageTf.jsp">Nuovo Messaggio</a></h1>

<% }

    if(permessi.equals("df")) {%>

<h1><a href="newMsgPageob.jsp">Nuovo Messaggio</a></h1>

<% }

    if(permessi.equals("ob"))   {%>
<h1><a href="newMsgPageOb.jsp">Nuovo Messaggio</a></h1>
<%}%>





<table class="table" >
    <div>
        <h1>Posta Ricevuta</h1>
    <tr><th>Mittente</th><th>Oggetto</th><th>Testo</th></tr>
    </div>
    <tbody>
        <%String user= userCon.getEmail();%>
        <% String result="";%>
        <% EmailUtility mostra = new EmailUtility();%>
        <%  result= mostra.GeneraTabellaPostaRicevuta(user); %>
        <%=result%>
    </tbody>



    <table class="table">
        <div>
            <h1>Posta Inviata</h1>
            <tr><th>Destinatario</th><th>Oggetto</th><th>Testo</th></tr>
        </div>
        <tbody>
        <% user= userCon.getEmail(); %>
        <%  result="";%>
        <%  mostra = new EmailUtility();%>
        <%  result= mostra.GeneraTabellaPostaInviata(user);%>
        <%=result%>
        </tbody>

</table>

    <%
    }
    else{ %>
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
