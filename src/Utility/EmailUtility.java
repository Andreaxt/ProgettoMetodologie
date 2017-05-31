package Utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Andrea on 31/05/2017.
 */
public class EmailUtility {

    Connection conn = null;
    PreparedStatement st = null;
    ResultSet rs;

    public EmailUtility()
    {
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Db_Farmacia", "postgres", "$Postgres22.");
        }
        catch (Exception e) {
            System.out.println("Impossibile connettersi al database"+ e.getMessage() );

        }
    }

    public String GeneraTabella(String nomeUtente){


        String output="";
        String query="SELECT mittente , destinatario , testo FROM messaggi WHERE mittente=? OR destinatario=?";
        try {
            st = conn.prepareStatement(query);
            st.setString(1, nomeUtente);
            st.setString(2, nomeUtente);
            ResultSet rs = st.executeQuery();

            while (rs.next()) //per passare alla prossima riga

            {
                output = output.concat("<tr><td><p>" + rs.getString("mittente") + "</p></td><td><p>" + rs.getString("destinatario") + "</p></td><td><p>" + rs.getString("testo") + "</p></td>");

            }

            rs.close();
            st.close();
            conn.close();
        }

        catch (Exception e) {
            System.out.println("Impossibile connettersi al database"+ e.getMessage() );

        }

        System.out.println("metodo genera tabella concluso");


       
        return output;
    }
}