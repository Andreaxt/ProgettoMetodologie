
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Connection" %><%--
  Created by IntelliJ IDEA.
  User: Andrea
  Date: 05/06/2017
  Time: 11:39
  To change this template use File | Settings | File Templates.
--%>
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


<div class="login__pagediv">
    <form action="newMsg.do" method="post"  class="div__login" >

        Invia a:<select name="destinatario">
        <option value="allFarm">tutti i dipendenti</option>

        <%

            Connection conn = null;
            PreparedStatement st = null;
            ResultSet rs=null;

            try {
                Class.forName("org.postgresql.Driver");
                conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Db_Farmacia", "postgres", "$Postgres22.");

                String query="SELECT email from utenti where id_farmacia_lavoro=?";
                st = conn.prepareStatement(query);
                st.setInt(1,userCon.getIdFarmacia());
                rs = st.executeQuery();
                String output = "";

                while (rs.next()) //per passare alla prossima riga

                {
        %>
        <option value="<%=rs.getString(1)%>"> <%=rs.getString(1)%></option>
        <%

                }

            }
            catch (Exception e) {
                System.out.println("Impossibile connettersi al database nella prima query nuovo utente: "+ e.getMessage() );

            }


        %>
    </select><br>

        </select><br>



        Oggetto:<input type="text" class="casella__user" name="oggetto"/><br>
        <p>Testo:</p><textarea  style="resize:none" cols="30" rows="10" class="casella__password" name="testo"></textarea><br>
        <input type="submit" id="invio" value="Invia" class="botton__submit">
    </form>
</div>

<footer class="footer">
    <p>Copyright &copy; Andrea Viviani</p>
</footer>




</body>
</html>