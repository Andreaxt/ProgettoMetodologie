package beans;

public class UtenteConnesso implements java.io.Serializable {
    private String nome;
    private String password;
    private int userid;
    private String codUser;
    private boolean connesso;
    private String email;
    private String permessi="";

    public UtenteConnesso() {
        super();
    }
    public UtenteConnesso(String nome, String cognome) {
        super();
        this.nome = nome;
        this.connesso=false;

    }
    public String getPermessi() {
        return permessi;
    }
    public void setPermessi(String permessi)
    {
        this.permessi = permessi;
    }

    public String getEmail() {
        return nome;
    }
    public void setEmail(String nome)
    {
        this.nome = nome;
    }


    public String getNome() {
        return nome;
    }
    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public int getUserId(){
        return userid;
    }
    public void setUserId(int userid){
        this.userid=userid;
    }

    public boolean getConnesso() {
        return connesso;
    }

    public void setConnesso(boolean newValid) {connesso= newValid;}


}

