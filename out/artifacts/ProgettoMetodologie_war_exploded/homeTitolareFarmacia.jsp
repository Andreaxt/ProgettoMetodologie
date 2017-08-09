<%--
  Created by IntelliJ IDEA.
  User: Andrea
  Date: 07/06/2017
  Time: 15:07
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
        <li class="header__menu__item"><a href="homeTitolareFarmacia.jsp">AreaRiservata</a></li>
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
    if(userCon.getConnesso()==true && userCon.getPermessi().equals("tf")){
%>

<section class="cards clearfix">
    <div class="card">
        <img class="card__image"  src="Image/MailImage2.jpg" alt="Nature">
        <div class="card__copy">
            <h3><a href="emailPage.jsp"> Email Page </a></h3>
            <p> Invia email in modo sicuro e rapido ai tuoi colleghi!</p>
        </div>
    </div>

    <div class="card">
        <img class="card__image"  src="Image/magazzino.jpg" alt="Nature">
        <div class="card__copy">
            <h3><a href="restoreWareHouseTF.jsp">Riforinisci il magazzino!</a></h3>
            <p>Puoi ordinare o reintegrare il magazzino in questa pagina.</p>
        </div>
    </div>


</section>

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


<footer class="footer">
    <p>Copyright &copy; Andrea Viviani</p>
</footer>


</body>
</html>