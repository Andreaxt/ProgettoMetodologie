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

    public int countRicetta() {
        int count = 0;
        for (Farmaco fr : this) {
            if (fr.isRicetta())
                count++;
        }
        return count;
    }

    public ListaProdotti ricettaElement() {
        ListaProdotti list= new ListaProdotti();
        for (Farmaco fr : this) {
            if (fr.isRicetta())
                list.add(fr);
        }
        return list;
    }
}

