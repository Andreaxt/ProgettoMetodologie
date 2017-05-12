package action;

import beans.MyFirstFormBean;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import java.sql.*;
import java.util.*;

/**
 * Created by Andrea on 28/04/2017.
 */
public class MyFirstAction extends Action{
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws Exception {
       String email= request.getParameter("user");
       String psw =request.getParameter("psw");
       System.out.println("passata questa email" +email);
        System.out.println("passata questa psw" +psw);


        Connection conn = null;
        Statement st = null;
        String u="";
        String s="";

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Db_Farmacia", "postgres", "$Iltronodispade22.");
            st =conn.createStatement(); //effetua la quety al db
            ResultSet rs = st.executeQuery("SELECT utenti.userName,utenti.password FROM utenti WHERE utenti.userName="+email+" AND utenti.password="+ psw);

            while (rs.next()) //per passare alla prossima riga

            {

                 u = rs.getString("userName");

                 s = rs.getString("password");

            }
          //  rs.close();
            //st.close();
            //conn.close();

        }
        catch (Exception e) {
            System.out.println("Impossibile connettersi al database"+ e.getMessage() );

        }
        //(psw.trim().length()<1)
        u="Andreaxt";
        s="password";

        System.out.println("passata questa email " +email);
        System.out.println("passata questa u " +u);

        if(!u.equals(email))
           return(mapping.findForward("bad-user"));
        if(!s.equals(psw))
            return(mapping.findForward("bad-password"));
        else
            return mapping.findForward("success");
    }




}
