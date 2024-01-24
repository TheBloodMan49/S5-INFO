import java.util.ArrayList;
import java.util.List;

public class Gamma<T> {
    private MultiEnsembleMap<T> ensemble;
    private R<T> r;
    private A<T> a;

    public Gamma(MultiEnsembleMap<T> ensemble, R<T> r, A<T> a) {
        this.ensemble = ensemble;
        this.r = r;
        this.a = a;
    }

    public List<T> choixReactifs() {
        List<T> result = new ArrayList<T>();
           for (T x : ensemble) {
                for (T y : ensemble) {
                     if (x != y && r.canReact(x, y)) {
                         ensemble.remove(x);
                         ensemble.remove(y);
                         result.add(x);
                         result.add(y);
                         return result;
                     }
                }
            }
        return null;
    }

    public void go() {
        List<T> reactifs = choixReactifs();
        while (reactifs != null) {
            T x = reactifs.get(0);
            T y = reactifs.get(1);
            ensemble.add(a.f(x, y));
            reactifs = choixReactifs();
        }
    }

    public static void main(String[] args) {
        MultiEnsembleMap<Integer> ensemble = new MultiEnsembleMap<Integer>();
        ensemble.add(1);
        ensemble.add(2);
        ensemble.add(3);
        ensemble.add(4);
        ensemble.add(5);
        ensemble.add(6);
        ensemble.add(7);
        ensemble.add(8);
        ensemble.add(9);


    }


}
