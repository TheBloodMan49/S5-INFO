public interface MyList<T> {

    public MyList<T> entete();
    public MyList<T> enqueue();
    public MyList<T> succ() throws MyListOutOfBoundsException, MyListEmptyException;
    public MyList<T> pred() throws MyListOutOfBoundsException, MyListEmptyException;
    public MyList<T> ajouterD(T obj) throws MyListOutOfBoundsException;
    public MyList<T> oterec() throws MyListOutOfBoundsException, MyListEmptyException;
    public T valec() throws MyListOutOfBoundsException, MyListEmptyException;
    public boolean estSorti();
    public boolean estVide();

}