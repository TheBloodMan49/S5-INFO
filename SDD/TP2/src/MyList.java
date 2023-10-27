public interface MyList<T>{
    MyIterator<T> iterateur();
    boolean estVide();
    MyList<T> vider();
}