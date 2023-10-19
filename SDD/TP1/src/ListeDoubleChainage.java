public class ListeDoubleChainage<T> implements MyList<T> {
    private Element tete;
    private Element ec;
    private Element queue;

    public ListeDoubleChainage(){
        this.ec = new Element();
        this.tete = this.ec;
        this.queue = new Element();
        this.ec.suivant = this.queue;
        this.queue.precedent = this.ec;
    }

    @Override
    public ListeDoubleChainage<T> entete() {
        this.ec = this.tete.suivant;
        return this;
    }

    @Override
    public ListeDoubleChainage<T> enqueue() {
        this.ec = this.queue.precedent;
        return this;
    }

    @Override
    public ListeDoubleChainage<T> succ() throws MyListOutOfBoundsException, MyListEmptyException {
        if (this.estVide()) throw new MyListEmptyException();
        if (this.estSorti()) throw new MyListOutOfBoundsException();
        this.ec = this.ec.suivant;
        return this;
    }

    @Override
    public ListeDoubleChainage<T> pred() throws MyListOutOfBoundsException, MyListEmptyException {
        if (this.estVide()) throw new MyListEmptyException();
        if (this.estSorti()) throw new MyListOutOfBoundsException();
        this.ec = this.ec.precedent;
        return this;
    }

    @Override
    public ListeDoubleChainage<T> ajouterD(T obj) throws MyListOutOfBoundsException {
        if (this.estSorti() && !this.estVide()) throw new MyListOutOfBoundsException();
        Element e = new Element();
        e.valeur = obj;
        e.suivant = this.ec.suivant;
        e.precedent = this.ec;
        this.ec.suivant.precedent = e;
        this.ec.suivant = e;
        this.ec = e;
        return this;
    }

    @Override
    public ListeDoubleChainage<T> oterec() throws MyListOutOfBoundsException, MyListEmptyException {
        if (this.estVide()) throw new MyListEmptyException();
        if (this.estSorti()) throw new MyListOutOfBoundsException();
        this.ec.precedent.suivant = this.ec.suivant;
        this.ec.suivant.precedent = this.ec.precedent;
        this.ec = this.ec.suivant;
        return this;
    }

    @Override
    public T valec() throws MyListOutOfBoundsException, MyListEmptyException {
        if (this.estVide()) throw new MyListEmptyException();
        if (this.estSorti()) throw new MyListOutOfBoundsException();
        return this.ec.valeur;
    }

    @Override
    public boolean estSorti() {
        return (this.ec == this.queue || this.ec == this.tete);
    }

    @Override
    public boolean estVide() {
        return (this.tete.suivant == this.queue);
    }

    private class Element {
        public T valeur;
        public Element suivant;
        public Element precedent;
    }
}
