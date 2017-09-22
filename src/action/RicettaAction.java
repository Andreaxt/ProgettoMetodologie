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
        ArrayList<String> codRicette = new ArrayList<>();
        for (int i = 0; i < listaRicette.size(); i++)
            codRicette.add(request.getParameter("codR" + i));
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
            conn= StaticConn.getConn();
            for (int i = 0; i < listaRicette.size(); i++) {
                query = "SELECT cf_paz FROM ricetta WHERE id_ricetta=?";
                statement = conn.prepareStatement(query);
                statement.setInt(1, Integer.decode(codRicette.get(i)));
                rs = statement.executeQuery();
                if (!rs.isBeforeFirst()) {
                    request.setAttribute("errore", "ricetta non esistente");
                    fail=true;
                }
                else
                    while (rs.next()) {
                        if (cod_fiscale_paz== null)
                            cod_fiscale_paz = rs.getString(1);
                        else if (cod_fiscale_paz.equals(rs.getString(1)))
                            diffpatient = true;
                    }
            }
            if (diffpatient)
                request.setAttribute("errore", "Puoi usare un solo paziente alla volta");
            else if(!fail){
                query = "SELECT count(*) FROM Paziente WHERE CF=?";
                statement = conn.prepareStatement(query);
                statement.setString(1, cod_fiscale_paz);
                rs = statement.executeQuery();
                rs.next();
                if (rs.getInt(1) == 0)
                    pazNonInserito = true;
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