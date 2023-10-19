public interface Table<K,V> {
    public int taille();

    public V val(K cle) throws CleNonPresenteException;

    public void ajouter(K cle, V valeur);

    public void supprimer(K cle);

    public boolean contient(K cle);
}