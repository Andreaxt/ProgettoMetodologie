package beans;

import java.io.Serializable;
import java.util.ArrayList;

public class ListaProdotti extends ArrayList<Farmaco> implements Serializable{
    public ListaProdotti() {
    }

    @Override
    public boolean add(Farmaco prodottoAcquistato) {
        return super.add(prodottoAcquistato);
    }

    @Override
    public Farmaco get(int index) {
        return super.get(index);
    }
}