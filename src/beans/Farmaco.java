package beans;

import java.io.Serializable;


public class Farmaco implements Serializable {
    private boolean ricetta;
    private int prodotto;
    private int quantita;

    public Farmaco(boolean ricetta, int prodotto, int quantita) {
        this.ricetta = ricetta;
        this.prodotto = prodotto;
        this.quantita = quantita;
    }

    public Farmaco() {
    }

    public boolean isRicetta() {
        return ricetta;
    }

    public void setRicetta(boolean ricetta) {
        this.ricetta = ricetta;
    }

    public int getProdotto() {
        return prodotto;
    }

    public void setProdotto(int prodotto) {
        this.prodotto = prodotto;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }
}