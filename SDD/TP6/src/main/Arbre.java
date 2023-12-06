public interface Arbre {

    String racine();
    Arbre arbreG();
    Arbre arbreD();
    boolean estVide();
    void vider();
    void modifRacine(Object r);
    void modifArbreG(Arbre a);
    void modifArbreD(Arbre a);
    String toString();
    void dessiner();
    int hauteur() throws LArbreEstVideException;
    int denombrer(String n);
    void remplacer(String n1, String n2);

}
