import java.util.HashSet;

public class MultiensembleSet<T> {

    private HashSet<Element> elements;

    public MultiensembleSet() {
        this.elements = new HashSet<>();
    }

    public MultiensembleSet(T[] elements) {
        this.elements = new HashSet<>();
        for (T element : elements) {
            this.elements.add(new Element(element, 1));
        }
    }


    public void add(T item) {
        for (Element element : elements) {
            if (element.valeur.equals(item)) {
                element.occurences++;
                return;
            }
        }
        elements.add(new Element(item, 1));
    }

    public T get(T item) {
        for (Element element : elements) {
            if (element.valeur.equals(item)) {
                return element.valeur;
            }
        }
        return null;
    }

    public void remove(T item) {
        for (Element element : elements) {
            if (element.valeur.equals(item)) {
                element.occurences--;
                if (element.occurences == 0) {
                    elements.remove(element);
                }
                return;
            }
        }
    }

    class Element {
        T valeur;
        int occurences;

        public Element(T valeur, int occurences) {
            this.valeur = valeur;
            this.occurences = occurences;
        }
    }
}
