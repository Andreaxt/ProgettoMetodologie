package beans;

public class UtenteConnesso implements java.io.Serializable {
    private String nome;
    private String password;
    private int userid;
    private String codUser;
    private boolean connesso;
    private String email;
    private String permessi="";
    private int id_farmacia=0;

    public UtenteConnesso() {
        super();
    }
    public UtenteConnesso(String nome, String cognome) {
        super();
        this.nome = nome;
        this.connesso=false;

    }

    public int getIdFarmacia() {
        return id_farmacia;
    }
    public void setIdFarmacia(int id_farmacia)
    {
        this.id_farmacia = id_farmacia;
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

