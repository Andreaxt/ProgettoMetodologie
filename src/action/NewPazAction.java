package action;

import beans.UtenteConnesso;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import Utility.StaticConn;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

public class NewPazAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String nome_paz = request.getParameter("nome");
        String cognome_paz = request.getParameter("cognome");
        String cf = (String) request.getSession().getAttribute("cf");
        boolean fail = false;
        Connection connection = null;
        PreparedStatement statement = null;
        String query = "INSERT INTO paziente VALUES (?,?,?)";
        try {
            connection= StaticConn.getConn();
            statement = connection.prepareStatement(query);
            statement.setString(1, nome_paz);
            statement.setString(1, cognome_paz);
            statement.setString(2, cf);
            if (statement.executeUpdate() <= 0) {
                request.setAttribute("errore", "Impossibile aggiungere il paziente");
                fail = true;
            }
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }finally
        {
            try {
                statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (fail)
            return (mapping.findForward("error"));
        return (mapping.findForward("sell"));
    }
}