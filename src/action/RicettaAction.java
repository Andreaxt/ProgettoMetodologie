package action;

import Utility.StaticConn;
import beans.ListaProdotti;
import beans.UtenteConnesso;
import beans.Farmaco;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;
import java.util.ArrayList;


public class RicettaAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        ListaProdotti listaRicette = ((ListaProdotti) request.getSession().getAttribute("acquisto")).ricettaElement();
        //lista codici ricette
        System.out.println("entrato in ricettaAction");
        ArrayList<String> codRicette = new ArrayList<>();
        for (int i = 0; i < listaRicette.size(); i++)
            codRicette.add(request.getParameter("cr" + i));


        boolean diffpatient = false;
        boolean pazNonInserito = false;
        boolean fail = false;


        String cod_fiscale_paz = null;
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement statement = null;
        String query;
        //Controllo che tutti gli id sia di unico paziente
        try {
            System.out.println("entrato nel try");
            conn= StaticConn.getConn();
            System.out.println("dopo la connessione");




            for (int i = 0; i < listaRicette.size(); i++) {
                query = "SELECT cf_paz FROM ricetta WHERE id_ricetta=?";
                statement = conn.prepareStatement(query);
                int id_ricetta=Integer.decode(codRicette.get(i));
                System.out.println(id_ricetta);
                statement.setInt(1, id_ricetta);
                rs = statement.executeQuery();
                if (!rs.isBeforeFirst()) {
                    request.setAttribute("errore", "ricetta non esistente");
                    fail=true;
                }
                else
                    while (rs.next()) {
                        if (cod_fiscale_paz== null) {
                            cod_fiscale_paz = rs.getString(1);
                            System.out.println("codice paz"+cod_fiscale_paz);
                        }
                        else if (cod_fiscale_paz.equals(rs.getString(1))) {
                            System.out.println("else if");
                            diffpatient = true;
                        }
                    }
            }
            if (diffpatient)
                request.setAttribute("error", "Puoi usare un solo paziente alla volta");
            else if(!fail){
                System.out.println("codice fiscale"+ cod_fiscale_paz);
                query = "SELECT count(*) FROM paziente WHERE codice_fiscale=?";
                statement = conn.prepareStatement(query);
                statement.setString(1, cod_fiscale_paz);
                rs = statement.executeQuery();
                rs.next();
                if (rs.getInt(1) == 0) {
                    System.out.println("paz non inserito");
                    pazNonInserito = true;
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (diffpatient ||fail)
            return (mapping.findForward("error"));
        request.getSession().setAttribute("cf", cod_fiscale_paz);
        request.getSession().setAttribute("idricette", codRicette);
        if (pazNonInserito)
            return (mapping.findForward("insert-patient"));
        return (mapping.findForward("sell"));
    }
}