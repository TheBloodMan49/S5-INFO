public class ListeTabulee<T> implements MyList<T> {
    protected static final int TAILLE_MAX = 100;

    protected T[] elements;
    protected int size;

    @SuppressWarnings("unchecked")
    public ListeTabulee() {
        this.size = 0;
        this.elements = (T[]) new Object[TAILLE_MAX];
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
}
