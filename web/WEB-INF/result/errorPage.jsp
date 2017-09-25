<%--
  Created by IntelliJ IDEA.
  User: Andrea
  Date: 22/09/2017
  Time: 10:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>errorPage</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/6.0.0/normalize.css">
    <link href="style.css" rel="stylesheet" >
</head>
<body>
<jsp:useBean id="userCon" scope="session" class="beans.UtenteConnesso" />
<div class="login__pagediv">
    <div class="div__login">
        <h1>Errore nella vendita! alcuni dati possono essere errati</h1>
        <%
            String permessi = userCon.getPermessi();
            boolean fatto= false;
            if(permessi.equals("reg")){%>
        <h2> <a href="homeRegione.jsp">Torna alla home page</a></h2>

        <%fatto=true;}

            if(permessi.equals("tf")){%>
        <h2> <a href="homeTitolareFarmacia.jsp">Torna alla home page</a></h2>

        <% fatto=true;}

            if(permessi.equals("df")) {%>
        <h2> <a href="homeMedicoFarmacista.jsp">Torna alla home page</a></h2>
        <% fatto= true;}

            if(permessi.equals("ob"))   { fatto= true;%>
        <h2> <a href="homeOperatoriBanco.jsp">Torna alla home page</a></h2>
        <%}
        %>

    </div>
</div>

</body>
</html>
