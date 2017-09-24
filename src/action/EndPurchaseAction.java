package action;

import beans.ListaProdotti;
import beans.UtenteConnesso;
import beans.Farmaco;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import Utility.StaticConn;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class EndPurchaseAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        ListaProdotti listaRicette = (ListaProdotti) request.getSession().getAttribute("acquisto");
        String cf = (String) request.getSession().getAttribute("cf");
        StaticConn login = (StaticConn) request.getSession().getAttribute("login");
        ArrayList<String> ricette = (ArrayList<String>) request.getSession().getAttribute("idricette");
        //lista codici ricette

        HttpSession session= request.getSession(true);
        UtenteConnesso u = (UtenteConnesso)session.getAttribute("userCon");
        int result=-1;

        boolean fail = false;
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        String query;
        int id=-1;
        int i = 0;
        try {
            System.out.println("entrato nella fine vendita");
            connection= StaticConn.getConn();
            query = "INSERT INTO vendita ( date, id_venditore, paz_cf) VALUES (?,?,?)";
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            java.sql.Date odierna = new java.sql.Date(Calendar.getInstance().getTime().getTime());

            statement.setDate(1, odierna);
            statement.setInt(2, u.getUserId());
            statement.setString(3, (listaRicette.countRicetta() > 0) ? cf : null);

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

                while(resultSet.next()) {
                    id = resultSet.getInt(1);
                }
                //query per prendere l'id

                if (id != -1) {
                    for (Farmaco prod : listaRicette) {
                       /* if (prod.isRicetta()) {
                            query = "INSERT INTO ObbligoRicetta VALUES (?,?)";
                            statement = connection.prepareStatement(query);
                            statement.setInt(1, id);
                            statement.setInt(2, Integer.decode(ricette.get(i++)));
                            if (statement.executeUpdate() <= 0) {
                                fail = true;
                                break;
                            }
                       }*/
                        query = "UPDATE magazzino SET disponibilita_pezzi= magazzino.disponibilita_pezzi-? WHERE id_farmaco_magazzino=? AND id_farmacia=?";
                        statement = connection.prepareStatement(query);
                        statement.setInt(1, prod.getQuantita());
                        statement.setInt(2, prod.getProdotto());
                        statement.setInt(3, u.getIdFarmacia());
                        if (statement.executeUpdate() <= 0) {
                            fail = true;
                            break;
                        }
                        query = "INSERT INTO storico VALUES (?,?,?)";
                        statement = connection.prepareStatement(query);
                        statement.setInt(1, prod.getQuantita());
                        statement.setInt(2, prod.getProdotto());
                        statement.setInt(3, id);
                        if (statement.executeUpdate() <= 0) {
                            fail = true;
                            break;
                        }
                    }
                }

            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            fail = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (fail) {
            request.setAttribute("errore", "Impossibile concludere l'acquisto");
            return (mapping.findForward("error"));
        }
        request.getSession().removeAttribute("idricette");
        request.getSession().removeAttribute("cf");
        return (mapping.findForward("sell-made"));
    }

}