
package action;

import beans.UtenteConnesso;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;

/**
 * Created by Andrea on 07/06/2017.
 */
public class NewInsertUserAction extends Action{
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws Exception {
        String userName= request.getParameter("user");
        String psw =request.getParameter("psw");
        String email=request.getParameter("email");
        String impiego= request.getParameter("impiego");


        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs=null;


        HttpSession session= request.getSession(true);
        UtenteConnesso u = (UtenteConnesso)session.getAttribute("userCon");
       int idFarmacia =u.getIdFarmacia();


        System.out.println(userName + psw + email + impiego + idFarmacia);




        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Db_Farmacia", "postgres", "$Postgres22.");

            String query="INSERT INTO utenti (username,password,email,permessi,id_farmacia_lavoro)VALUES (?,?,?,?,?) " ;
            st = conn.prepareStatement(query);
            st.setString(1,userName);
            st.setString(2,psw);
            st.setString(3,email);
            st.setString(4,impiego);
            st.setInt(5,idFarmacia);
            st.executeUpdate();

            st.close();
            conn.close();
        }
        catch (Exception e) {
            System.out.println("Impossibile connettersi al database nella prima query nuovo utente: "+ e.getMessage() );

        }

        conn = null;
        rs=null;
        st = null;

        return mapping.findForward("success");
    }



}