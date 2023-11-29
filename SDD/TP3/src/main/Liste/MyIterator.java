package Liste;

import java.util.Iterator;

public interface MyIterator<T> extends Iterator<T> {
    MyIterator<T> entete();
    MyIterator<T> enqueue();
    MyIterator<T> succ() throws MyListOutOfBoundsException, MyListEmptyException;
    MyIterator<T> pred() throws MyListOutOfBoundsException, MyListEmptyException;
    MyIterator<T> ajouterD(T obj) throws MyListOutOfBoundsException;
    MyIterator<T> ajouterG(T obj) throws MyListOutOfBoundsException;
    MyIterator<T> oterec() throws MyListOutOfBoundsException, MyListEmptyException;
    T valec() throws MyListOutOfBoundsException, MyListEmptyException;
    MyIterator<T> modifec(T obj) throws MyListEmptyException;
    boolean estSorti();

}
