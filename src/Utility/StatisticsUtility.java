package Utility;

import java.sql.*;

/**
 * Created by Andrea on 25/09/2017.
 */

public  class StatisticsUtility{

        Connection conn=null;
        PreparedStatement st=null;
        ResultSet rs;

public StatisticsUtility()
        {
        try{
        Class.forName("org.postgresql.Driver");
        conn= DriverManager.getConnection("jdbc:postgresql://localhost:5432/Db_Farmacia","postgres","$Postgres22.");
        }
        catch(Exception e){
        System.out.println("Impossibile connettersi al database"+e.getMessage());

        }
        }


public class StatisticsReg {


    public String totNumberPurchaseReg() {


        String output = "";
        String query = "select count(date) from vendita";
        try {
            st = conn.prepareStatement(query);

            ResultSet rs = st.executeQuery();

            while (rs.next()) //per passare alla prossima riga

            {
                output = output.concat("<p> Numero complessivo di acquisti:" + rs.getString(1) + "</p><br>");
            }


            query ="select sum(quantita) from storico";//numero di oggetti venduti
            rs=null;
            st=null;

            st = conn.prepareStatement(query);

            rs = st.executeQuery();

            while (rs.next()) //per passare alla prossima riga

            {
                output = output.concat("<p> Numero complessivo di farmaci venduti:" + rs.getString(1) + "</p><br>");
            }

            query="select sum(quantita) from storico join farmaco on farmaco.id_farmaco=storico.id_prodotto where farmaco.abilitazione='df'";

            rs=null;
            st=null;

            st = conn.prepareStatement(query);

            rs = st.executeQuery();

            while (rs.next()) //per passare alla prossima riga

            {
                output = output.concat("<p> Numero di farmaci con ricetta venduti:" + rs.getString(1) + "</p><br>");
            }





            rs.close();
            st.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Impossibile connettersi al database" + e.getMessage());

        }

        return output;
    }
}

}