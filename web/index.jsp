<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Document</title>
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


            <li class="header__menu__item"><a href="homeCoreSito.jsp">AreaRiservata</a></li>
             <li class="header__menu__item"><a href="">Chi siamo</a></li>
         <li class="header__menu__item"><a href="">FAQ</a></li>
        </ul>
    </header>

    <section class="cover">
        <div class="cover__filter"></div>
        <div class="cover__caption">
            <div class="cover__caption__copy">
                <h1>Benvenuto nel sito delle Farmacia della regione Piemonte</h1>
                <h2>Sei un titolare di una farmacia? registrati qui!</h2>
                <a href="" class="button">Registrati subito!</a>
            </div>
        </div>
    </section>


<footer class="footer">
    <p>Copyright &copy; Andrea Viviani</p>
</footer>




    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <script>
        $(document).ready(function(){

            $(".header__icon-bar").click(function(e){

                $(".header__menu").toggleClass('is-open');
                e.preventDefault();

            });
        });
    </script>


</body>
</html>