<%--
  Created by IntelliJ IDEA.
  User: Andrea
  Date: 25/05/2017
  Time: 11:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>LogOutPage</title>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/6.0.0/normalize.css">
    <link href="style.css" rel="stylesheet" >
</head>
<body>

<jsp:useBean id="userCon" scope="session" class="beans.UtenteConnesso" />

<%
    userCon.setConnesso(false);
%>

<div class="login__pagediv">
    <div class="div__login">
        <h1>LogOut effettuato!</h1>
        <h2> <a href="index.jsp">Torna alla homepage</a></h2>
    </div>
</div>
</body>
</html>
