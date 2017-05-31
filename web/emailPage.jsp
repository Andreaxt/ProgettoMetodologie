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
    <a href="" class="header__Logo">Logo</a>
    <a href="" class="header__icon-bar">
        <span></span>
        <span></span>
        <span></span>
    </a>
    <ul class="header__menu">
        <li class="header__menu__item"><a href="index.html">Home</a></li>
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

        <li class="header__menu__item"><a href="">Chi siamo</a></li>
        <li class="header__menu__item"><a href="">FAQ</a></li>
    </ul>


</header>

<table>
    <thead>
    <tr><th>Mittente</th><th>Destinatario</th><th>Testo</th></tr>
    </thead>
    <tbody>
        <%String user= userCon.getNome(); System.out.println(user);%>
        <% String result="";%>
        <% EmailUtility mostra = new EmailUtility();%>
        <%  result= mostra.GeneraTabella(user); System.out.println(result);%>
        <%=result%>
    </tbody>

</table>

</body>
</html>
