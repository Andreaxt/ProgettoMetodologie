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
        int id_utente=-1;
        System.out.println("Stampo il testo del messaggio"+testo);
        ResultSet rs=null;

        java.sql.Date odierna = new java.sql.Date(Calendar.getInstance().getTime().getTime());


        HttpSession session= request.getSession(true);
        UtenteConnesso u = (UtenteConnesso)session.getAttribute("userCon");

        String nomeUser=u.getEmail();
        int iduser=u.getUserId();


        if(!destinatario.equals("allFarm")) {

            try {
                Class.forName("org.postgresql.Driver");
                conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Db_Farmacia", "postgres", "$Postgres22.");

                System.out.println("connesisone effutata inzio query");
                String query = "INSERT INTO messaggi (testo,mittente ,destinatario,data,id_utente,oggetto)VALUES (?,?,?,?,?,?) ";
                st = conn.prepareStatement(query);
                st.setString(1, testo);
                st.setString(2, u.getEmail());
                st.setString(3, destinatario);
                st.setDate(4, odierna);
                st.setInt(5, u.getUserId());
                st.setString(6, oggetto);

                st.executeUpdate();

                st.close();
                conn.close();
            } catch (Exception e) {
                System.out.println("Impossibile connettersi al database nel metodo messaggio" + e.getMessage());

            }
        }//fine if
        else{
            try {
                Class.forName("org.postgresql.Driver");
                conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Db_Farmacia", "postgres", "$Postgres22.");


                String query="SELECT email from utenti where id_farmacia_lavoro=? AND id_utente!=?" ;
                st = conn.prepareStatement(query);
                st.setInt(1,u.getIdFarmacia());
                st.setInt(2,u.getUserId());

                rs = st.executeQuery();

                while (rs.next()) //per passare alla prossima riga

                {
                    destinatario = rs.getString("email");



                    try {
                        PreparedStatement st2 = null;
                        String query2="INSERT INTO messaggi (testo,mittente,destinatario,data,oggetto,id_utente)VALUES(?,?,?,?,?,?) " ;
                        st2 = conn.prepareStatement(query2);
                        st2.setString(1,testo);
                        st2.setString(2,nomeUser);
                        st2.setString(3,destinatario);
                        st2.setDate(4,odierna);
                        st2.setString(5,oggetto);
                        st2.setInt(6,u.getUserId());
                        st2.executeUpdate();

                        st2.close();

                    }
                    catch (Exception e) {
                        System.out.println("Impossibile connettersi al datab nel while primo if tf: "+ e.getMessage() );

                    }
                }// fine while

                rs.close();
                st.close();
                conn.close();
            }//fine try
            catch (Exception e) {
                System.out.println("Impossibile connettersi al database primo if all"+ e.getMessage() );

            }

        }//fine else

        return mapping.findForward("success");
    }


}
