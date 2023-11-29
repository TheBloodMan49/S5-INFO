import java.util.*;

public class EmploiDuTemps {

    private final Map<String,Map<Horaire,String>> cours;

    public EmploiDuTemps(List<String> noms){
        this.cours = new HashMap<>();
        for (String n : noms) {
            this.cours.put(n, new HashMap<>());
        }
    }

    public void ajoutCours(String prof, String jour, int heure, String nom){
        if (!this.cours.containsKey(prof)) throw new RuntimeException();
        if (this.cours.get(prof).containsKey(new Horaire(jour, heure))) throw new RuntimeException();
        this.cours.get(prof).put(new Horaire(jour, heure), nom);
    }

    public Map<Horaire,String> getCours(String nom) {
        if (!this.cours.containsKey(nom)) return null;
        return this.cours.get(nom);
    }

    public int mondayTen() {
        int nb = 0;
        for (String nom : this.cours.keySet()){
            for(Horaire horaire : this.cours.get(nom).keySet()){
                if (horaire.equals(new Horaire("lundi", 10))) nb++;
            }
        }
        return nb;
    }

    public int monday() {
        int nb = 0;
        for (String nom : this.cours.keySet()){
            for(Horaire horaire : this.cours.get(nom).keySet()){
                if (horaire.getJour().equalsIgnoreCase("lundi")) nb++;
            }
        }
        return nb;
    }

    public String earlyBird() {
        String prof = "";
        int nbBest = 0;
        for (String nom : this.cours.keySet()) {
            int nbTry = 0;
            for (Horaire horaire : this.cours.get(nom).keySet()){
                if (horaire.getHeure() < 12) {
                    nbTry++;
                }
            }
            if (nbTry > nbBest) {
                nbBest = nbTry;
                prof = nom;
            }
        }
        return prof;
    }

    public String champion() {
        String prof = "";
        int nbBest = 0;
        for (String nom : this.cours.keySet()) {
            HashSet<String> coursTry = new HashSet<>();
            for (String cours : this.cours.get(nom).values()) coursTry.add(cours);
            if (coursTry.size() > nbBest) {
                nbBest = coursTry.size();
                prof = nom;
            }
        }
        return prof;
    }

    public Map<Horaire, Map<String, String>> autreStruc() {
        Map<Horaire, Map<String, String>> struct = new HashMap<>();
        for(String enseignant:this.cours.keySet()) {
            for(Horaire horaire:this.cours.get(enseignant).keySet()) {
                if (!struct.containsKey(horaire)){
                    Map<String, String> cours = new HashMap<>();
                    cours.put(enseignant, this.cours.get(enseignant).get(horaire));
                    struct.put(horaire, cours);
                } else {
                    struct.get(horaire).put(enseignant, this.cours.get(enseignant).get(horaire));
                }
            }
        }
        return struct;
    }

    public int minimalRooms() {
        if (this.cours.isEmpty()) return 0;
        int minRooms = 0;
        Map<Horaire, Map<String, String>> horaires = autreStruc();
        for (Horaire h : horaires.keySet()) {
            if (horaires.get(h).size() > minRooms) minRooms = horaires.get(h).size();
        }
        return minRooms;
    }
}
