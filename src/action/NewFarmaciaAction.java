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
public class NewFarmaciaAction extends Action{
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws Exception {
        String nomeFarmaciaNew= request.getParameter("NomeFarmacia");
        String indirizzo =request.getParameter("Indirizzo");
        String nomeTitolare=request.getParameter("NomeTitolare");
        String numeroDiTelefono= request.getParameter("NumeroDiTelefono");
        String usernameTtitolare =request.getParameter("Username");
        String password=request.getParameter("Password");
        String emailTitolare= request.getParameter("Email");
        int idFarmacia=0;

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs=null;



        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Db_Farmacia", "postgres", "$Postgres22.");

            String query="INSERT INTO farmacia (indirizzo,nomefarmacia,telefono,nome_titolare)VALUES (?,?,?,?) " ;
            st = conn.prepareStatement(query);
            st.setString(1,indirizzo);
            st.setString(2,nomeFarmaciaNew);
            st.setString(3,numeroDiTelefono);
            st.setString(4,nomeTitolare);
             st.executeUpdate();

            st.close();
            conn.close();
        }
        catch (Exception e) {
            System.out.println("Impossibile connettersi al database nella prima query nuova farmacia: "+ e.getMessage() );

        }

        conn = null;
        rs=null;
        st = null;
        System.out.println("valore di nomeFarmaciaNew"+ nomeFarmaciaNew);

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Db_Farmacia", "postgres", "$Postgres22.");


            String query="SELECT id_farmacia FROM  farmacia WHERE nomefarmacia=?" ;
            st = conn.prepareStatement(query);
            st.setString(1,nomeFarmaciaNew);
            rs = st.executeQuery();

            while (rs.next()) //per passare alla prossima riga

            {
                idFarmacia = rs.getInt("id_farmacia");
            }
            rs.close();
            st.close();
            conn.close();
        }
        catch (Exception e) {
            System.out.println("Impossibile connettersi al database nel metodo messaggio"+ e.getMessage() );

        }
        System.out.println("valore del id farmacia "+ idFarmacia);

        conn = null;
        rs=null;
        st = null;


        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Db_Farmacia", "postgres", "$Postgres22.");

            System.out.println("connesisone effutata inzio query");
            String query="INSERT INTO utenti (username,password,email,permessi,id_farmacia_lavoro)VALUES (?,?,?,?,?) " ;
            st = conn.prepareStatement(query);
            st.setString(1,usernameTtitolare);
            st.setString(2,password);
            st.setString(3,emailTitolare);
            st.setString(4,"tf");
            st.setInt(5,idFarmacia);

            st.executeUpdate();

            st.close();
            conn.close();
        }
        catch (Exception e) {
            System.out.println("Impossibile connettersi al database nella terza query"+ e.getMessage() );

        }




        return mapping.findForward("success");
    }



}
