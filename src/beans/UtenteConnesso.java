package beans;
import org.apache.struts.action.ActionForm;

import java.io.*;
import java.util.*;
import java.sql.*;
import java.io.Serializable;

public class UtenteConnesso implements java.io.Serializable {
    private String nome;
    private String password;
    private String userid;
    private String codUser;
    public boolean connesso;

    public UtenteConnesso() {
        super();
    }
    public UtenteConnesso(String nome, String cognome) {
        super();
        this.nome = nome;
        this.connesso=false;

    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }



    public boolean getConnesso() {
        return connesso;
    }

    public void setConnesso(boolean newValid) {connesso= newValid;}


}

