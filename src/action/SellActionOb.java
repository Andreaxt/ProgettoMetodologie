package action;

import beans.ListaProdotti;
import beans.UtenteConnesso;
import com.sun.corba.se.spi.presentation.rmi.IDLNameTranslator;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import beans.Farmaco;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;

public class SellActionOb extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        int[] idprodotti = Arrays.stream(request.getParameterValues("prodotti")).mapToInt(Integer::parseInt).toArray();
        int[] quantita = Arrays.stream(request.getParameterValues("quantita")).mapToInt(Integer::parseInt).toArray();

        HttpSession session= request.getSession(true);
        UtenteConnesso u = (UtenteConnesso)session.getAttribute("userCon");

        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement statement = null;

        ListaProdotti prodottiAcquistati = new ListaProdotti();
        boolean fail = false;
        String query;
        int result = -1;
        int id = -1;
        Integer x , y;
        boolean richiedeRicetta = false;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Db_Farmacia", "postgres", "$Postgres22.");
            query = "SELECT id_farmaco,abilitazione FROM Farmaco WHERE abilitazione='df'";
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            HashMap<Integer, Integer> hashMap = new HashMap<>(); //HashMap prodotto- ricetta
            while (resultSet.next()) {
                 x=resultSet.getInt(1);
                if(resultSet.getString(2).equals("ob")){
                    y=0;
                }
                else{
                     y=1;
                }
                hashMap.put(x,y);
            }
            for (int i = 0; i < quantita.length; i++) {
                Integer value = hashMap.get(idprodotti[i]);
                if (value != null)
                    richiedeRicetta = true;
                prodottiAcquistati.add(new Farmaco((value != null), idprodotti[i], quantita[i]));
            }

            if (!richiedeRicetta) {
                query = "INSERT INTO vendita ( date, id_venditore, paz_cf) VALUES (?,?,NULL )";
                statement = connection.prepareStatement(query,statement.RETURN_GENERATED_KEYS);
                java.sql.Date odierna = new java.sql.Date(Calendar.getInstance().getTime().getTime());
                Calendar cal = Calendar.getInstance();
                statement.setDate(1, odierna);
                statement.setInt(2, u.getUserId());
                result = statement.executeUpdate();

                if (result <= 0) {

                    fail = true;
                }

                else {

                    query = "SELECT max(id_vendita)  from vendita where id_venditore=? and date=?";
                    statement = connection.prepareStatement(query);
                    statement.setInt(1, u.getUserId());
                    statement.setDate(2, odierna);
                    resultSet = statement.executeQuery();

                    while(resultSet.next()){
                        id=resultSet.getInt(1);
                    }


                            for (int i = 0; i < idprodotti.length; i++) {
                                query = "UPDATE magazzino SET disponibilita_pezzi= magazzino.disponibilita_pezzi-? WHERE id_farmaco_magazzino=? AND id_farmacia=?";
                                statement = connection.prepareStatement(query);
                                statement.setInt(1, quantita[i]);
                                statement.setInt(2, idprodotti[i]);
                                statement.setInt(3, u.getIdFarmacia());
                                result = statement.executeUpdate();
                                if (result <= 0) {
                                    fail = true;
                                    break;
                                }
                                query = "INSERT INTO storico VALUES (?,?,?)";
                                statement = connection.prepareStatement(query);
                                statement.setInt(1, quantita[i]);
                                statement.setInt(2, idprodotti[i]);
                                statement.setInt(3, id);
                                if (statement.executeUpdate() <= 0) {
                                    fail = true;
                                    break;
                                }

                            }// fine for

                }//fine else
            }//fine if
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Tipo di return
        response.addHeader("Content-Type", "text/plain");
        response.setContentType("text/plain; charset=windows-1252");
        try {
            if (fail) {
                response.getOutputStream().print("error");
            } else {
                request.getSession().setAttribute("acquisto", prodottiAcquistati);
                if (!richiedeRicetta) {
                    System.out.println(" vendita conclusa");
                    response.getOutputStream().print("sell-made");
                } else {
                    System.out.println("continua la vendita");
                    response.getOutputStream().print("purchase");
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

}