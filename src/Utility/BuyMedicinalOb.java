package Utility;

import beans.Farmaco;
import beans.ListaProdotti;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Andrea on 31/05/2017.
 */
public class BuyMedicinalOb {

    Connection conn = null;
    PreparedStatement st = null;
    ResultSet rs;

    public BuyMedicinalOb() {
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Db_Farmacia", "postgres", "$Postgres22.");
        } catch (Exception e) {
            System.out.println("Impossibile connettersi al database" + e.getMessage());

        }
    }

    public String VendiMedicinali(int farmacia,String permessi) {
        String query;

        if (!permessi.equals("ob")) {
            query = "SELECT farmaco.id_farmaco, farmaco.nome_farmaco, farmaco.abilitazione, farmaco.costo, magazzino.disponibilita_pezzi FROM farmaco JOIN magazzino ON farmaco.id_farmaco = magazzino.id_farmaco_magazzino WHERE id_farmacia=? AND magazzino.disponibilita_pezzi>0";
        } else
            query = "SELECT farmaco.id_farmaco, farmaco.nome_farmaco, farmaco.abilitazione, farmaco.costo, magazzino.disponibilita_pezzi FROM farmaco JOIN magazzino ON farmaco.id_farmaco = magazzino.id_farmaco_magazzino WHERE id_farmacia=? AND farmaco.abilitazione='ob' AND magazzino.disponibilita_pezzi>0";
        String out = "";

        try {
            st = conn.prepareStatement(query);
            st.setInt(1, farmacia);
            rs = st.executeQuery();
            int x = 0;
            while (rs.next()) {
                out = out.concat("<tr><td><p>" + rs.getInt(1) + "</p></td><td><p>" + rs.getString(2) + "</p></td>");
                if (!permessi.equals("ob")) {
                    String s="";
                    if(rs.getString(3).equals("df")){
                        s="1";
                    }
                    else{
                        s="0";
                    }
                    out = out.concat("<td><p>" +s+ "</p></td>");

                }
                out = out.concat("<td><p>" + rs.getBigDecimal(4) + " &#8364</p></td><td>" + rs.getInt(5) + "</p></td><td><p><input type=\"text\" name=\"ordina" + x + "\" size=\"3\" id=\"ordina" + x + "\" value=\"0\" class=\"ordina\"><input class=\"add\"type=\"button\" id=\"add" + x + "\" value=\"+\"><input class=\"sub\"type=\"button\" id=\"sub" + x++ + "\" value=\"-\">");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return out;
    }

    public String listaAcquisto(ListaProdotti acquisto) {
        String query;
        String out = "";
        try {
            for (Farmaco prodottoAcquistato : acquisto) {
                query = "SELECT nome_farmaco,costo FROM farmaco WHERE id_farmaco=?";
                st = conn.prepareStatement(query);
                st.setInt(1, prodottoAcquistato.getProdotto());
                rs = st.executeQuery();
                if (rs.next()) {
                    out = out.concat("<tr><td><p>" + prodottoAcquistato.getProdotto() + "</p></td><td><p>" + rs.getString(1) + "</p></td><td><p>" + rs.getString(2) + "</p></td><td><p>" + prodottoAcquistato.getQuantita() + "</p></td></tr>");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out;
    }

    public String prezzo(ListaProdotti acquisto) {
        String query;
        double out = 0;
        try {
            for (Farmaco prodottoAcquistato : acquisto) {
                query = "SELECT costo FROM farmaco WHERE id_farmaco=?";
                st = conn.prepareStatement(query);
                st.setInt(1, prodottoAcquistato.getProdotto());
                rs = st.executeQuery();
                if (rs.next()) {
                    out += rs.getBigDecimal(1).doubleValue() * prodottoAcquistato.getQuantita();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return String.valueOf(out) + " €";
    }

    public void close() {
        try {
            rs.close();
            st.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}




