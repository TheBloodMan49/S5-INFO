package Liste;

import java.util.List;

public interface MyList<T> extends Iterable<T>, List<T> {
    MyIterator<T> iterateur();
    boolean estVide();
    MyList<T> vider();
}