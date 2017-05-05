package action;
/**
 * Created by Andrea on 28/04/2017.
 */
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;


public class MySecondAction extends Action {
    public ActionForward execute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
            throws Exception {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if ((email == null) ||
                (email.trim().length() < 3) ||
                (email.indexOf("@") == -1)) {
            return(mapping.findForward("bad-address"));
        } else if ((password == null) ||
                (password.trim().length() < 6)) {
            return(mapping.findForward("bad-password"));
        } else {
            return(mapping.findForward("success"));
        }
    }
}
