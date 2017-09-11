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
public class MsgReg extends Action {


    public int idFarmaciaValue(String s){
        int id=0;
        System.out.println("stringa passata "+ s);
        if(s.equals(""))
            return 0;

        else{
            Connection conn = null;
            PreparedStatement st = null;
            ResultSet rs=null;


            try {
                Class.forName("org.postgresql.Driver");
                conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Db_Farmacia", "postgres", "$Postgres22.");
                PreparedStatement st2 = null;
                String query2="select id_farmacia from farmacia where nomefarmacia=?" ;
                st = conn.prepareStatement(query2);
                st.setString(1,s);

                rs =st.executeQuery();
                rs.next();
                id=rs.getInt("id_farmacia");
                System.out.println("id farmacia preso "+ id);


                conn.close();
                st.close();

            }
            catch (Exception e) {
                System.out.println("Impossibile connettersi al database metodo idfarmaciaValue: "+ e.getMessage() );

            }

        }
        System.out.println("return "+ id);
        return id;
    }



    public String emailTf(int x){
        String email="";
        if(x==0)
            return "";
        else{
            Connection conn = null;
            PreparedStatement st = null;
            ResultSet rs=null;


            try {
                Class.forName("org.postgresql.Driver");
                conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Db_Farmacia", "postgres", "$Postgres22.");
                 st = null;
                String query2="SELECT email from utenti where id_farmacia_lavoro=? and permessi='tf'" ;
                st = conn.prepareStatement(query2);
                st.setInt(1,x);

                rs =st.executeQuery();
                rs.next();
                email=rs.getString("email");


                conn.close();
                st.close();

            }
            catch (Exception e) {
                System.out.println("Impossibile connettersi al database metodo email tf: "+ e.getMessage() );

            }

        }
        System.out.println("return email "+ email);
        return email;
    }
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws Exception {
        String soggettoEmail = request.getParameter("soggetto");
        String impiegoEmail = request.getParameter("impiego");
        String oggettoEmail = request.getParameter("oggetto");
        String testo = request.getParameter("testo");
        String destinatario = "";

        Connection conn = null;
        PreparedStatement st = null;
        int id_utente = -1;

        ResultSet rs=null;

        System.out.println("oggetto email -> " +soggettoEmail + "impiego email->"+ impiegoEmail + "oggetto email->" +oggettoEmail +"testo" +testo);
        java.sql.Date odierna = new java.sql.Date(Calendar.getInstance().getTime().getTime());


        HttpSession session = request.getSession(true);
        UtenteConnesso u = (UtenteConnesso) session.getAttribute("userCon");

        String nomeUser = u.getEmail();
        int iduser = u.getUserId();


        if (soggettoEmail.equals("tf")) {
            System.out.println("entrato if tf");
            if (impiegoEmail.equals("allFarm")) { // manda email a tutti i tf delle farmacie
                System.out.println("entrato if tf all farm");
                try {
                    Class.forName("org.postgresql.Driver");
                    conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Db_Farmacia", "postgres", "$Postgres22.");


                    String query="SELECT email from utenti where permessi='tf'" ;
                    st = conn.prepareStatement(query);

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
                            st2.setString(5,oggettoEmail);
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
            }//fine if soggetto == email
            else // singola farmacia
            {
                System.out.println("entrato else tf singola farmacia");
                try {

                    Class.forName("org.postgresql.Driver");
                    conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Db_Farmacia", "postgres", "$Postgres22.");
                    int id_farmacia_tf_destinatario=idFarmaciaValue(impiegoEmail);
                    String emailtf=emailTf(id_farmacia_tf_destinatario);

                    PreparedStatement st2 = null;
                    String query2="INSERT INTO messaggi (testo,mittente,destinatario,data,oggetto,id_utente)VALUES(?,?,?,?,?,?) " ;
                    st2 = conn.prepareStatement(query2);
                    st2.setString(1,testo);
                    st2.setString(2,nomeUser);
                    st2.setString(3,emailtf);
                    st2.setDate(4,odierna);
                    st2.setString(5,oggettoEmail);
                    st2.setInt(6,u.getUserId());
                    st2.executeUpdate();

                    st2.close();
                    conn.close();

                }
                catch (Exception e) {
                    System.out.println("Impossibile connettersi al database nella prima query nuovo utente: "+ e.getMessage() );

                }
            }
        }// fine if all farma


        else{//soggetto tutti

            if (impiegoEmail.equals("allFarm")) { // manda email a tutti i tf delle farmacie
                try {
                    Class.forName("org.postgresql.Driver");
                    conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Db_Farmacia", "postgres", "$Postgres22.");
                    String query="SELECT email from utenti where permessi!='reg'" ;
                    st = conn.prepareStatement(query);
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
                            st2.setString(5,oggettoEmail);
                            st2.setInt(6,u.getUserId());
                            st2.executeUpdate();

                            st2.close();

                        }
                        catch (Exception e) {
                            System.out.println("Impossibile connettersi al database nella prima query nuovo utente: "+ e.getMessage() );

                        }
                    }// fine while

                    rs.close();
                    st.close();
                    conn.close();
                }//fine try
                catch (Exception e) {
                    System.out.println("Impossibile connettersi al database nel metodo messaggio"+ e.getMessage() );
                }//fine catch
            }//fine if
            else{

                Class.forName("org.postgresql.Driver");
                conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Db_Farmacia", "postgres", "$Postgres22.");
                System.out.println("entrato else solo farmacia di un tipo");
                int id_farmacia_all_destinatario=idFarmaciaValue(impiegoEmail);

                String query="select email from utenti where id_farmacia_lavoro=?" ;
                st = conn.prepareStatement(query);
                st.setInt(1,id_farmacia_all_destinatario);
                rs=null;
                rs = st.executeQuery();
                while (rs.next()) //per passare alla prossima riga
                {
                    System.out.println("entrato nel while");
                    destinatario = rs.getString("email");
                    System.out.println("email destinatario"+ destinatario);

                    try {
                        PreparedStatement st2 = null;
                        String query2 = "INSERT INTO messaggi (testo,mittente,destinatario,data,oggetto,id_utente)VALUES(?,?,?,?,?,?) ";
                        st2 = conn.prepareStatement(query2);
                        st2.setString(1, testo);
                        st2.setString(2, nomeUser);
                        st2.setString(3, destinatario);
                        st2.setDate(4, odierna);
                        st2.setString(5, oggettoEmail);
                        st2.setInt(6,u.getUserId());

                        st2.executeUpdate();

                        st2.close();

                    } catch (Exception e) {
                        System.out.println("Impossibile connettersi al database nella prima query nuovo utente: " + e.getMessage());

                    }

                }//fine while
                conn.close();
                st.close();


            }//fine else
        }

        return mapping.findForward("success");
    }

}
