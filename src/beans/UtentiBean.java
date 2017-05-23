package beans;
import org.apache.struts.action.ActionForm;

import java.io.*;
import java.util.*;
import java.sql.*;

public class UtentiBean {

    private String nome;
    private String cognome;
    private String email;
    private String username;
    private String password;
    private String hobby;
    private String dbUrl;
    private Connection c;
    private Statement s;

    //variabile booleana per gestire la sessione dell'utente
    private boolean auth=false;
    //variabile per recuperare e gestire i messaggi di errore
    private String msgErrore="";

    //connessione al database
    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public void connect() {
        try {
   		/* the following command load the jdbc driver */
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Db_Farmacia", "postgres", "$Iltronodispade22.");
            s = c.createStatement();
        } catch(Exception e){e.printStackTrace();}
    }

    public void disconnect() {
        try {
            c.close();
        }catch(Exception e){e.printStackTrace();}
    }
    //fine metodi di connessione al database

    //metodi SET e GET
    public String getNome() {
        return nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }
    public void setCognome(String cognome){
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }

    public String getHobby() {
        return hobby;
    }
    public void setHobby(String hobby){
        this.hobby = hobby;
    }

    //metodo per recuperare il messaggio in caso d'errore
    public String getMsgErrore() {
        return msgErrore;
    }

    //metodo per ottenere l'autorizzazione dopo il login
    public boolean getAuth() {
        return auth;
    }


    //metodo per il salvataggio di un nuovo utente
    public boolean salvaUtente() {
        boolean checkSave=false;
        //PreparedStatement stmt = null;
        //ResultSet rs = null;
        //Connection cn=null;

        try {
            String insert = "INSERT INTO iscritti (Nome, Cognome, Username, Password, Email, Hobby) VALUES ('"+getNome()+"', '"+getCognome()+"', '"+getUsername()+"', '"+getPassword()+"', '"+getEmail()+"', '"+getHobby()+"');";
            s.executeUpdate(insert);
            checkSave=true; //quando ho salvato l'utente nel database check diventa vera
        } catch(Exception e) {msgErrore=e.getMessage();}
        return checkSave;
    }

    //metodo per eseguire il login: imposta la variabile auth a vero se l'utente esiste
    public boolean loginUtente(){
        auth=false;
        try {
            ResultSet r = s.executeQuery("SELECT * FROM ISCRITTI WHERE username='"+getUsername()+"' and password='"+getPassword()+"'");
            if(r.next()) {
                nome = r.getString("nome");
                cognome = r.getString("cognome");
                email = r.getString("email");
                username = r.getString("username");
                password = r.getString("password");
                hobby = r.getString("hobby");
                auth=true; //se l'utente esiste nel database
            }
        } catch(Exception e) {msgErrore=e.getMessage();}
        return auth;
    }

    //metodo per il logout: imposta la variabile auth a falso
    public boolean logout() {
        nome="";
        cognome="";
        email="";
        username="";
        password="";
        hobby="";
        auth=false;
        return auth;
    }


}