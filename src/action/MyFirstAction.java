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

        Connection conn = null;
        Statement st = null;

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Db_Farmacia", "postgres", "$Iltronodispade22.");
            conn.createStatement();

        }
        catch (Exception e) {
            System.out.println("Impossibile connettersi al database"+ e.getMessage() );

        }



        if((email.trim().length()<1))
           return(mapping.findForward("bad-user"));
        if((psw.trim().length()<1))
            return(mapping.findForward("bad-password"));
        else
            return mapping.findForward("success");



    }




}
