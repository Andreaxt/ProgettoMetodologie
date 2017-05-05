package action;

import beans.MyFirstFormBean;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Created by Andrea on 28/04/2017.
 */
public class MyFirstAction extends Action{
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws Exception {
       String email= request.getParameter("user");
       String psw =request.getParameter("psw");
       if((email.trim().length()<1))
           return(mapping.findForward("bad-user"));
        if((psw.trim().length()<1))
            return(mapping.findForward("bad-password"));
        else
            return mapping.findForward("success");



    }




}
