import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MultiEnsembleMap<T> implements Iterable {
    private HashMap<T, Integer> elements;

    public MultiEnsembleMap() {
        this.elements = new HashMap<>();
    }

    public MultiEnsembleMap(T[] elements) {
        this.elements = new HashMap<>();
        for (T element : elements) {
            this.elements.put(element, 1);
        }
    }

    public void add(T item) {
        if (elements.containsKey(item)) {
            elements.put(item, elements.get(item) + 1);
        } else {
            elements.put(item, 1);
        }
    }

    public T get(T item) {
        if (elements.containsKey(item)) {
            return item;
        } else {
            return null;
        }
    }

    public int getOccurences(T item) {
        if (elements.containsKey(item)) {
            return elements.get(item);
        } else {
            return 0;
        }
    }

    public void remove(T item) {
        if (elements.containsKey(item)) {
            if (elements.get(item) == 1) {
                elements.remove(item);
            } else {
                elements.put(item, elements.get(item) - 1);
            }
        }
    }

    public IterateurMultiEnsemble iterator() {
        return new IterateurMultiEnsemble();
    }

    public class IterateurMultiEnsemble implements Iterator<T> {
        private int index;
        private int occurences;
        private T element;
        private int compteur;
        private int taille;
        private T[] keys;

        public IterateurMultiEnsemble() {
            this.index = 0;
            this.occurences = 0;
            this.element = null;
            this.compteur = 0;
            this.taille = 0;
            this.keys = (T[]) elements.keySet().toArray();
            for (T element : keys) {
                taille += elements.get(element);
            }
        }

        @Override
        public boolean hasNext() {
            return compteur < taille;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            if (occurences == 0) {
                element = keys[index];
                occurences = elements.get(element);
                index++;
            }
            compteur++;
            occurences--;
            return element;
        }

        @Override
        public void remove() {
            if (element == null) {
                return;
            }
            if (elements.get(element) == 1) {
                elements.remove(element);
            } else {
                elements.put(element, elements.get(element) - 1);
            }
        }
    }

}
