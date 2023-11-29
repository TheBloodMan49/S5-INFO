package Liste;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.function.Consumer;

public class ListeTabulee<T> extends AbstractList<T> implements MyList<T> {
    protected static final int TAILLE_MAX = 100;

    protected T[] elements;
    protected int size;

    @SuppressWarnings("unchecked")
    public ListeTabulee() {
        this.size = 0;
        this.elements = (T[]) new Object[TAILLE_MAX];
    }

    @Override
    public T get(int index) {
        return this.elements[index];
    }

    @Override
    public IterateurDeListeTabulee<T> iterateur() {
        return new IterateurDeListeTabulee<T>(this);
    }

    @Override
    @SuppressWarnings("unchecked")
    public ListeTabulee<T> vider() {
        this.elements = (T[]) new Object[TAILLE_MAX];
        this.size = 0;
        return this;
    }

    @Override
    public boolean estVide() {
        return this.size == 0;
    }

    @Override
    public Iterator<T> iterator() {
        return iterateur();
    }

    @Override
    public void forEach(Consumer action) {
        MyList.super.forEach(action);
    }

    public boolean contains(Object el) {
        if (this.estVide()) return false;
        for(int i = 0; i < this.size; i++) {
            T elem = this.elements[i];
            if(elem.equals(el)) return true;
        }
        return false;
    }

    public void clear() {
        this.vider();
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.estVide();
    }
}
