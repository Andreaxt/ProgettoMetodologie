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
import java.sql.Date;

/**
 * Created by Andrea on 05/06/2017.
 */
public class NewMsgAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws Exception {
        String destinatario= request.getParameter("destinatario");
        String oggetto =request.getParameter("oggetto");
        String testo=request.getParameter("testo");
        Connection conn = null;
        PreparedStatement st = null;
        String u="";
        String p="";
        int id_utente=-1;

        java.sql.Date odierna = new java.sql.Date(Calendar.getInstance().getTime().getTime());

        HttpSession session= request.getSession();
        UtenteConnesso userConn = (UtenteConnesso)session.getAttribute("userConn");


        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Db_Farmacia", "postgres", "$Postgres22.");


            String query="INSERT INTO messaggi (testo,mittente ,destinatario,data,id_utente,oggetto)VALUES (?,?,?,?,?,?) " ;
            st = conn.prepareStatement(query);
            st.setString(1,testo);
            st.setString(2,userConn.getEmail());
            st.setDate(3,odierna);
            st.setInt(4,userConn.getUserId());
            st.setString(5,testo);

            ResultSet rs = st.executeQuery();

        }
        catch (Exception e) {
            System.out.println("Impossibile connettersi al database"+ e.getMessage() );

        }
        //(psw.trim().length()<1)
        //bean

        return mapping.findForward("success");
    }


}
