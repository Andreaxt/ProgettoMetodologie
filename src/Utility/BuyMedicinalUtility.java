package Utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Andrea on 31/05/2017.
 */
public class BuyMedicinalUtility {

    Connection conn = null;
    PreparedStatement st = null;
    ResultSet rs;

    public BuyMedicinalUtility()
    {
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Db_Farmacia", "postgres", "$Postgres22.");
        }
        catch (Exception e) {
            System.out.println("Impossibile connettersi al database"+ e.getMessage() );

        }
    }

    public String VendiMedicinali(int id_farmacia){


        String output="";
        String query="SELECT farmaco.nome_farmaco , magazzino.disponibilita_pezzi,farmaco.costo FROM magazzino INNER JOIN  farmaco ON magazzino.id_farmaco=farmaco.id_farmaco WHERE magazzino.id_farmacia=?";
        try {
            st = conn.prepareStatement(query);
            st.setInt(1, id_farmacia);

            ResultSet rs = st.executeQuery();

            while (rs.next()) //per passare alla prossima riga

            {
                output = output.concat("<tr><td><p>" + rs.getString(1) + "</p></td><td><p>");

                int i=0;
                int n= rs.getInt(2);
                output= output.concat("<select name=\"selQuant\">");
                for(i=0;i<=n;i++){
                    output=output.concat("<option>"+i+"</option>");
                }
                output= output.concat("</select></p></td><td><p>"+rs.getString(3)+ "£</p></td></tr>");

                output=output.concat("");

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
