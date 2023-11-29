package Liste;

import java.util.function.Consumer;

public class IterateurDeListeTabulee<T> implements MyIterator<T> {

    protected final ListeTabulee<T> liste;
    private int ec;

    public IterateurDeListeTabulee(ListeTabulee<T> liste) {
        this.liste = liste;
        this.ec = 0;
    }

    @Override
    public IterateurDeListeTabulee<T> entete() {
        this.ec = 0;
        return this;
    }

    @Override
    public IterateurDeListeTabulee<T> enqueue() {
        this.ec = this.liste.estVide()? 0 : this.liste.size - 1;
        return this;
    }

    @Override
    public IterateurDeListeTabulee<T> succ() throws MyListOutOfBoundsException, MyListEmptyException {
        if (this.liste.estVide()) throw new MyListEmptyException();
        if (this.ec == this.liste.size-1) throw new MyListOutOfBoundsException();

        this.ec++;
        return this;
    }

    @Override
    public IterateurDeListeTabulee<T> modifec(T obj) throws MyListEmptyException {
        if (this.liste.estVide()) throw new MyListEmptyException();
        this.liste.elements[ec] = obj;
        return this;
    }

    @Override
    public IterateurDeListeTabulee<T> pred() throws MyListOutOfBoundsException, MyListEmptyException {
        if (this.liste.estVide()) throw new MyListEmptyException();
        if (this.ec == 0) throw new MyListOutOfBoundsException();

        this.ec--;
        return this;
    }

    @Override
    public IterateurDeListeTabulee<T> ajouterD(T obj) throws MyListOutOfBoundsException {
        if (this.liste.size == ListeTabulee.TAILLE_MAX) throw new MyListOutOfBoundsException();
        for (int i = this.liste.size - 1; i > this.ec; i--) {
            this.liste.elements[i] = this.liste.elements[i-1];
        }
        this.liste.elements[this.ec+1] = obj;
        this.liste.size++;
        this.ec++;
        return this;
    }

    @Override
    public IterateurDeListeTabulee<T> ajouterG(T obj) throws MyListOutOfBoundsException {
        if (this.liste.size == ListeTabulee.TAILLE_MAX) throw new MyListOutOfBoundsException();
        for (int i = this.liste.size - 1; i >= this.ec; i--) {
            this.liste.elements[i] = this.liste.elements[i-1];
        }
        this.liste.elements[this.ec] = obj;
        this.ec--;
        this.liste.size++;
        return this;
    }

    @Override
    public IterateurDeListeTabulee<T> oterec() throws MyListOutOfBoundsException, MyListEmptyException {
        if (this.liste.size == ListeTabulee.TAILLE_MAX) throw new MyListOutOfBoundsException();
        if (this.liste.estVide()) throw new MyListEmptyException();
        for (int i = this.liste.size - 1; i>= this.ec; i--){
            this.liste.elements[i-1] = this.liste.elements[i];
        }
        this.liste.size--;
        return this;
    }

    @Override
    public T valec() throws MyListOutOfBoundsException, MyListEmptyException {
        if (this.liste.size == ListeTabulee.TAILLE_MAX) throw new MyListOutOfBoundsException();
        if (this.liste.estVide()) throw new MyListEmptyException();

        return this.liste.elements[this.ec];
    }

    @Override
    public boolean estSorti() {
        return this.ec >= this.liste.size || this.ec < 0;
    }

    @Override
    public boolean hasNext() {
        return !estSorti();
    }

    @Override
    public T next() {
        try {
            return succ().valec();
        } catch (MyListOutOfBoundsException | MyListEmptyException e) {
            return null;
        }
    }

    @Override
    public void remove() {
        try {
            this.oterec();
        } catch (Exception ex) {
            System.out.println("Exception in remove operation on IterateurDeListeTabulee");
        }
    }

    @Override
    public void forEachRemaining(Consumer action) {
        MyIterator.super.forEachRemaining(action);
    }
}
