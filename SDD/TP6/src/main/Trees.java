import java.util.HashMap;
import java.util.Map;

/**
 * Toolbox to draw trees (inspired by command-line 'tree').
 * In-order Traversal by default, with right-child first (customizable).
 *
 * @author Kévin Tollemer & surtout pas Paul Gasnier
 * @version 20.11.25
 */
public class Trees {
    // Premier fils dessiné : g ou d
    // - g (gauche) ordre naturel
    // - d (droit) permet une vue intéressante (équivalente à une rotation de 90° de l'arbre).
    protected static char first = 'd'; // fils droit en premier par défaut
    protected static char order = '='; // in-order par défaut

    /**
     * Configure the order to draw.
     *
     * @param newOrder + for pre-order, = for in-order, - for post-order
     */
    public void setOrder(char newOrder) {
        switch (newOrder) {
            case '+':
            case '=':
            case '-':
                order = newOrder; // I hope you like their music ;-)
                break;
            default:
                throw new IllegalArgumentException("setOrder accepts only 3 values: + = -");
        }
    }

    /** Configure to draw left child first. */
    public static void setLeftFirst() {
        first='g';
    }

    /** Configure to draw right child first. */
    public static void setRightFirst() {
        first='d';
    }

    /** Draw the tree, according to the current configuration. */
    public static void draw(Arbre a) {
        Map<Integer,Boolean> from = new HashMap<>();
        tree(a, 0, '.', from);
    }

    /**
     * Recursive auxiliary method to draw.
     *
     * @param a Tree to draw
     * @param depth Current depth where the method starts
     * @param child Is it a first child or a second relatively to its parent?
     * @param from Map giving the current connections to draw with an upper parent
     */
    protected static void tree(Arbre a, int depth, char child, Map<Integer,Boolean> from) {
        if ( !a.estVide() ) {
            // Prepare quel fils est dessiné en 1er et en 2e
            Arbre a1,a2;
            if (first=='g')	{a1=a.arbreG(); a2=a.arbreD();}
            else 			{a1=a.arbreD(); a2=a.arbreG();}

            // 2 dessins récursifs, plaçant la racine avant, au milieu, ou après
            if (order=='+') treeRoot(a, depth, child, from);
            tree(a1, depth+1 , '1', from);
            if (order=='=') treeRoot(a, depth, child, from);
            tree(a2, depth+1 , '2', from);
            if (order=='-') treeRoot(a, depth, child, from);
        }
    }

    protected static void treeRoot(Arbre a, int depth, char child, Map<Integer,Boolean> from) {
        // Tous les niveaux décalés de N espaces plus loin, en figurant les connexions en cours
        for(int i=0; i<depth-1; i++) {
            if (from.containsKey(i))
                System.out.print("│   ");
            else
                System.out.print("    ");
        }
        // Dessine la connexion finale à la racine (vers son parent)
        if (depth>0) {
            System.out.print(connect(child));
            if (child=='1')
                from.put(depth-1, true);	// désormais il faut dessiner la connexion au parent
            else
                from.remove(depth-1);		// la connexion au pere n'a plus besoin d'être dessinée
        }
        // Dessine la racine au bout de la connexion dessinée avant
        System.out.println( a.racine() );
    }

    protected static String connect(char child) {
        // La bonne paire de dessins de connexions au parent, selon l'ordre choisi
        return switch (order) {
            case '+' -> (child == '1' ? "├── " : "└── ");
            case '-' -> (child == '1' ? "┌── " : "├── ");
            default -> (child == '1' ? "┌── " : "└── ");
        };
    }
}
