package action;

import beans.MyFirstFormBean;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import java.sql.*;
import java.util.*;
import java.lang.*;


import beans.UtenteConnesso;

/**
 * Created by Andrea on 28/04/2017.
 */
public class MyFirstAction extends Action{
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws Exception {
        String email= request.getParameter("user");
        String psw =request.getParameter("psw");

        Connection conn = null;
        PreparedStatement st = null;
        String u="";
        String p="";
        String emailUtente="";
        String permessiUtente="";
        int id_utente=-1;

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Db_Farmacia", "postgres", "$Postgres22.");


            String query="SELECT username , password, id_utente,email,permessi FROM utenti WHERE username=? AND PASSWORD=?";
            st = conn.prepareStatement(query);
            st.setString(1,email);
            st.setString(2,psw);
            ResultSet rs = st.executeQuery();

            while (rs.next()) //per passare alla prossima riga

            {

                u = rs.getString("userName");

                p = rs.getString("password");

                id_utente= rs.getInt("id_utente");

                emailUtente=rs.getString("email");

                permessiUtente=rs.getString("permessi");

            }
            rs.close();
            st.close();
            conn.close();

        }
        catch (Exception e) {
            System.out.println("Impossibile connettersi al database"+ e.getMessage() );

        }


        if(!u.equals(email)||(email.trim().length()<1))
            return(mapping.findForward("bad-user"));
        if(!p.equals(psw)||(psw.trim().length()<1))
            return(mapping.findForward("bad-password"));
        else {
            UtenteConnesso userCon = new UtenteConnesso();
            userCon.setNome(u);
            userCon.setUserId(id_utente);
            userCon.setConnesso(true);
            userCon.setEmail(emailUtente);
            userCon.setPermessi(permessiUtente);
            System.out.println(userCon.getUserId());
            request.getSession().setAttribute("utenteConnesso", userCon);
            request.getSession().setAttribute("userCon", userCon);

            return mapping.findForward("success");
        }
    }



}
