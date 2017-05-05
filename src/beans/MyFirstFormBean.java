package beans;

import org.apache.struts.action.ActionForm;

/**
 * Created by Andrea on 28/04/2017.
 */
public class MyFirstFormBean extends ActionForm{
    private String email, password;

    public String getEmail(){
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
