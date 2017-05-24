<%--
  Created by IntelliJ IDEA.
  User: Andrea
  Date: 24/05/2017
  Time: 14:40
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
<header class="header clearfix">
    <a href="" class="header__Logo">Logo</a>
    <a href="" class="header__icon-bar">
        <span></span>
        <span></span>
        <span></span>
    </a>
    <ul class="header__menu">
        <li class="header__menu__item"><a href="index.html">Home</a></li>
        <li class="header__menu__item"><a href="LoginPage.jsp">Login</a></li>
        <li class="header__menu__item"><a href="">Chi siamo</a></li>
        <li class="header__menu__item"><a href="">FAQ</a></li>
    </ul>


</header>

<section class="cover">
</section>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<script>
    $(document).ready(function(){

        $(".header__icon-bar").click(function(e){

            $(".header__menu").toggleClass('is-open');
            e.preventDefault();

        });
    });
</script>

<jsp:useBean id="userCon" scope="session" class="beans.UtenteConnesso" />


<%
    System.out.println(userCon.getConnesso());
    System.out.println(userCon.getNome());
    if(userCon.connesso==true){
%>
    <h1>Utente autenticato con successo</h1>
<%
    }
    else {
    %>
    <h1>Utente non autenticato con successo</h1>
    <%
        }
    %>

</body>
</html>
