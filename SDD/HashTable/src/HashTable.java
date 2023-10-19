public class HashTable<K, V> implements Table<K, V> {
    private int contenance;
    private Noeud<K, V>[] noeuds;

    @SuppressWarnings("unchecked")
    public HashTable(){
        this.contenance = 100;
        this.noeuds = (Noeud<K, V>[]) new Noeud[contenance]; // un peu de magie ici (contourner Java)
    }

    @Override
    public int taille(){
        int compteur = 0;
        for (Noeud<K, V> element : noeuds) {
            // Pour chaque noeud, on traverse la chaine de suivants
            while (element != null) {
                compteur++;
                element = element.suivant;
            }
        }
        return compteur;
    }

    @Override
    public V val(K cle) throws CleNonPresenteException {
        if (!this.contient(cle))
            throw new CleNonPresenteException();
        // On est désormais certain que l'élément est dans la table

        int hash = this.hashInterne(cle);
        Noeud<K, V> candidat = noeuds[hash];
        while (!candidat.cle.equals(cle)) {
            candidat = candidat.suivant; // On boucle
        }

        // candidat est l'élément qu'on recherchait
        return candidat.valeur;
    }

    @Override
    public void ajouter(K cle, V valeur) {
        int hash = this.hashInterne(cle);
        Noeud<K, V> candidat = noeuds[hash];

        if (candidat == null){
            // il n'y avait aucun objet avec ce hash (pas de collision)
            noeuds[hash] = new Noeud<>(cle, valeur);
        } else {
            boolean present = true;
            while (!candidat.cle.equals(cle)){
                if (candidat.suivant == null){
                    present = false;
                    break;
                }
                candidat = candidat.suivant;
            }

            if (present) {
                // on a trouvé le noeud avec la bonne clé
                // donc on modifie la valeur stockée
                candidat.valeur = valeur;
            }else {
                // il n'y avait pas de noeud à ce hash qui contenait la clé
                // donc on ajoute un nouveau noeud à candidat (qui est le dernier noeud à ce hash)
                candidat.suivant = new Noeud<>(cle, valeur);
            }
        }
    }

    @Override
    public void supprimer(K cle){
        int hash = this.hashInterne(cle);
        Noeud<K, V> candidat = noeuds[hash];

        if (candidat == null)
            return;

        if (candidat.cle.equals(cle)){
            // l'élément à supprimer est dans le noeud de + haut niveau
            noeuds[hash] = candidat.suivant;
            return;
        }

        Noeud<K, V> precedent = null;
        while (!candidat.cle.equals(cle)) {
            precedent = candidat;
            candidat = candidat.suivant;

            if (candidat == null)
                return; // on n'a jamais trouvé la clé à supprimer
        }

        precedent.suivant = candidat;
    }

    @Override
    public boolean contient(K cle){
        int hash = this.hashInterne(cle);
        Noeud<K, V> candidat = noeuds[hash];

        if (candidat == null)
            return false; // même pas de noeud avec ce hash : l'élément n'est pas dedans, c'est sûr

        while (!candidat.cle.equals(cle)) {
            candidat = candidat.suivant;

            if (candidat == null)
                return false; // on n'a toujours pas trouvé la clé et il n'y a plus de noeud suivant...
        }
        return true;
    }

    private int hashInterne(Object o){
        return o.hashCode() % contenance;
    }

    private static class Noeud<K,V> {
        public K cle;
        public V valeur;
        public Noeud<K,V> suivant;

        public Noeud(K cle, V valeur) {
            this.cle = cle;
            this.valeur = valeur;
            this.suivant = null;
        }
    }

}