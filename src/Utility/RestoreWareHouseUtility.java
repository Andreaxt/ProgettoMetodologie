package Utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Andrea on 31/05/2017.
 */
public class RestoreWareHouseUtility {

    Connection conn = null;
    PreparedStatement st = null;
    ResultSet rs;

    public RestoreWareHouseUtility()
    {
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Db_Farmacia", "postgres", "$Postgres22.");
        }
        catch (Exception e) {
            System.out.println("Impossibile connettersi al database"+ e.getMessage() );

        }
    }

    public String GeneraTabellaMedicinaliMagazzino(int id_farmacia){

        int x=0;
        String output="";
        String query="SELECT tabella.id_farmaco , tabella.nome_farmaco , tabella.disponibilita_pezzi FROM(SELECT * from farmaco LEFT JOIN magazzino ON farmaco.id_farmaco=magazzino.id_farmaco_magazzino)AS tabella WHERE id_farmacia=? OR id_farmacia ISNULL";
        try {
            st = conn.prepareStatement(query);
            st.setInt(1, id_farmacia);

            ResultSet rs = st.executeQuery();

            while (rs.next()) //per passare alla prossima riga

            {
                output = output.concat("<tr><td><p>" + rs.getString(1) +
                        "</p></td><td><p>" + rs.getString(2) +
                        "</p></td><td><p>"+ rs.getString(3) +
                        "</p></td><td><p><input type=\"number\" min=\"0\" max=\"100\" name=\"ord" + x + "\" size=\"3\">"+
                        "</p></td><td><p> <input type=\"button\" id=\"ord"+x+"\" value=\"Ordina\" class=\"ordinare\"></p></td></tr>");

            }

            rs.close();
            st.close();
            conn.close();
        }

        catch (Exception e) {
            System.out.println("Impossibile connettersi al database"+ e.getMessage() );

        }

        return output;
    }


}
