import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class HashTableTest {

    private TableCouples t;
    private Mot mot1;
    private Mot mot2;
    private Mot mot3;
    private Couple couple;

    @BeforeEach
    public void setup() {
        t = new TableCouples();
        mot1 = new Mot("Table");
        mot2 = new Mot("table");
        mot3 = new Mot("Bateau");
        couple = new Couple(mot1, mot2);
    }

    @Test
    public void MotEqualsTest() {
        assertEquals(mot1, mot2);
    }

    @Test
    public void compCoupleMotTest() {
        assertEquals(couple.compCoupleMot(mot1), mot2);
    }

    @Test
    public void compCoupleMotNullTest() {
        assertNull(couple.compCoupleMot(mot3));
    }
    
    @Test
    public void glossaireTest() {
        t.ajouter( new Mot("Accès direct"), new Mot("direct access, random access") );
        t.ajouter( new Mot("Accès séquentiel"), new Mot("serial access") );
        t.ajouter( new Mot("Afficher"), new Mot("to display") );
        t.ajouter( new Mot("Algorithmique"), new Mot("algorithmics") );
        t.ajouter( new Mot("Amorce"), new Mot("boot") );
        t.ajouter( new Mot("Amorcer"), new Mot("to boot") );
        t.ajouter( new Mot("Antémémoire"), new Mot("cache memory, cache storage") );
        t.ajouter( new Mot("Anticrénelage"), new Mot("antialiasing") );
        t.ajouter( new Mot("Appariement de formes"), new Mot("pattern matching") );
        t.ajouter( new Mot("Ardoise électronique"), new Mot("notepad computer") );
        t.ajouter( new Mot("Arrière-plan (d'), "), new Mot("background") );
        t.ajouter( new Mot("Autodocumenté"), new Mot("self documented") );
        t.ajouter( new Mot("Autonome"), new Mot("off-line, offline") );
        t.ajouter( new Mot("Banque de données"), new Mot("data bank") );
        t.ajouter( new Mot("Base de connaissances"), new Mot("knowledge base") );
        t.ajouter( new Mot("Base de données"), new Mot("data base") );
        t.ajouter( new Mot("Bit"), new Mot("bit") );
        t.ajouter( new Mot("Bloc"), new Mot("block") );
        t.ajouter( new Mot("Bogue"), new Mot("bug") );
        t.ajouter( new Mot("Boule de commande"), new Mot("trackball, rolling ball") );
        t.ajouter( new Mot("Bus"), new Mot("bus") );
        t.ajouter( new Mot("Cadre"), new Mot("frame") );
        t.ajouter( new Mot("Calcul intensif"), new Mot("supercomputing") );
        t.ajouter( new Mot("Clicher"), new Mot("to dump") );
        t.ajouter( new Mot("Cliquer"), new Mot("to click") );
        t.ajouter( new Mot("Codet"), new Mot("code element") );
        t.ajouter( new Mot("Connectabilité"), new Mot("connectivity") );
        t.ajouter( new Mot("Connexion"), new Mot("log in, log on") );
        t.ajouter( new Mot("Connexité"), new Mot("connectivity") );
        t.ajouter( new Mot("Coprocesseur"), new Mot("coprocessor") );
        t.ajouter( new Mot("Courtier"), new Mot("broker") );
        t.ajouter( new Mot("Crénelage"), new Mot("aliasing") );
        t.ajouter( new Mot("Déboguer"), new Mot("to debug") );
        t.ajouter( new Mot("Débogueur"), new Mot("debugger") );
        t.ajouter( new Mot("Défaillance"), new Mot("failure") );
        t.ajouter( new Mot("Défilement"), new Mot("scrolling") );
        t.ajouter( new Mot("Dessineur"), new Mot("drawing software") );
        t.ajouter( new Mot("Dévideur"), new Mot("streamer") );
        t.ajouter( new Mot("Didacticiel"), new Mot("courseware, teachware") );
        t.ajouter( new Mot("Disque optique compact"), new Mot("CD-ROM") );
        t.ajouter( new Mot("Disquette"), new Mot("diskette, floppy disk") );
        t.ajouter( new Mot("Donnée"), new Mot("data") );
        t.ajouter( new Mot("Échange de données informatisé"), new Mot("electronic data interchange, EDI") );
        t.ajouter( new Mot("Écran pixélisé"), new Mot("bit map screen") );
        t.ajouter( new Mot("Écran tactile"), new Mot("touch screen") );
        t.ajouter( new Mot("Éditeur"), new Mot("editor") );
        t.ajouter( new Mot("Éditique"), new Mot("electronic publishing") );
        t.ajouter( new Mot("Élément binaire"), new Mot("bit") );
        t.ajouter( new Mot("En ligne"), new Mot("on-line") );
        t.ajouter( new Mot("Étiquette"), new Mot("label") );
        t.ajouter( new Mot("Évolution d'un système"), new Mot("upgrade") );
        t.ajouter( new Mot("Exécuteur"), new Mot("runtime software") );
        t.ajouter( new Mot("Externalisation"), new Mot("outsourcing") );
        t.ajouter( new Mot("Forme"), new Mot("pattern") );
        t.ajouter( new Mot("Fusionner"), new Mot("to merge") );
        t.ajouter( new Mot("Grapheur"), new Mot("graphics software") );
        t.ajouter( new Mot("Grappe"), new Mot("cluster") );
        t.ajouter( new Mot("Groupe"), new Mot("cluster") );
        t.ajouter( new Mot("Heuristique"), new Mot("heuristics") );
        t.ajouter( new Mot("Icône"), new Mot("icon") );
        t.ajouter( new Mot("Iconiser"), new Mot("to iconize, to stow") );
        t.ajouter( new Mot("Implanter"), new Mot("to implement") );
        t.ajouter( new Mot("Implémenter"), new Mot("to implement") );
        t.ajouter( new Mot("Infogérance"), new Mot("facilities management, F.M") );
        t.ajouter( new Mot("Ingénierie inverse"), new Mot("reverse engineering") );
        t.ajouter( new Mot("Instaurer"), new Mot("to set") );
        t.ajouter( new Mot("Instruction"), new Mot("instruction, statement") );
        t.ajouter( new Mot("Intelligence artificielle"), new Mot("artificial intelligence") );
        t.ajouter( new Mot("Invite"), new Mot("prompt") );
        t.ajouter( new Mot("Langage à objets"), new Mot("object-oriented language") );
        t.ajouter( new Mot("Listage"), new Mot("listing") );
        t.ajouter( new Mot("Lister"), new Mot("to list") );
        t.ajouter( new Mot("Logement"), new Mot("slot") );
        t.ajouter( new Mot("Logiciel"), new Mot("software") );
        t.ajouter( new Mot("Logiciel contributif"), new Mot("shareware") );
        t.ajouter( new Mot("Logiciel de groupe"), new Mot("groupware") );
        t.ajouter( new Mot("Logiciel public"), new Mot("freeware") );
        t.ajouter( new Mot("Macroordinateur"), new Mot("mainframe") );
        t.ajouter( new Mot("Manche à balai"), new Mot("joystick") );
        t.ajouter( new Mot("Mappe"), new Mot("map") );
        t.ajouter( new Mot("Marquage"), new Mot("highlighting") );
        t.ajouter( new Mot("Matériel"), new Mot("hardware") );
        t.ajouter( new Mot("Mémoire"), new Mot("storage memory") );
        t.ajouter( new Mot("Mémoire de masse"), new Mot("mass storage") );
        t.ajouter( new Mot("Mémoire morte"), new Mot("ROM, read only memory") );
        t.ajouter( new Mot("Mémoire tampon"), new Mot("buffer") );
        t.ajouter( new Mot("Mémoire vive"), new Mot("RAM, random access memory") );
        t.ajouter( new Mot("Messagerie électronique"), new Mot("message handling, electronic mail") );
        t.ajouter( new Mot("Microédition"), new Mot("desktop publishing") );
        t.ajouter( new Mot("Micromisation"), new Mot("downsizing") );
        t.ajouter( new Mot("Micromiser"), new Mot("to downsize") );
        t.ajouter( new Mot("Microordinateur"), new Mot("microcomputer") );
        t.ajouter( new Mot("Microprogramme"), new Mot("firmware") );
        t.ajouter( new Mot("Mise à niveau"), new Mot("upgrade") );
        t.ajouter( new Mot("Mise en réseau"), new Mot("networking") );
        t.ajouter( new Mot("Mode dialogué"), new Mot("conversational mode") );
        t.ajouter( new Mot("Morphage"), new Mot("morphing") );
        t.ajouter( new Mot("Mot-clé"), new Mot("keyword") );
        t.ajouter( new Mot("Moteur d'inférence"), new Mot("inference engine") );
        t.ajouter( new Mot("Multiprocesseur"), new Mot("multi-processor") );
        t.ajouter( new Mot("Neurone formel"), new Mot("artificial neurone") );
        t.ajouter( new Mot("Numérique"), new Mot("digital, numerical, numeric") );
        t.ajouter( new Mot("Numériser"), new Mot("to digitize") );
        t.ajouter( new Mot("Numéro d'urgence"), new Mot("hot line") );
        t.ajouter( new Mot("Octet"), new Mot("byte") );
        t.ajouter( new Mot("Ordinateur"), new Mot("computer") );
        t.ajouter( new Mot("Ordinateur bloc-notes"), new Mot("notebook computer") );
        t.ajouter( new Mot("Ordinateur de poche"), new Mot("palmtop computer, pocket computer") );
        t.ajouter( new Mot("Ordinateur portable"), new Mot("portable computer") );
        t.ajouter( new Mot("Ordinateur portatif"), new Mot("laptop computer") );
        t.ajouter( new Mot("Ordinateur de table"), new Mot("desktop computer") );
        t.ajouter( new Mot("Organiseur"), new Mot("organizer") );
        t.ajouter( new Mot("Panne"), new Mot("fault") );
        t.ajouter( new Mot("Partage de temps"), new Mot("time sharing") );
        t.ajouter( new Mot("Permutation"), new Mot("swap") );
        t.ajouter( new Mot("Photostyle"), new Mot("light pen") );
        t.ajouter( new Mot("Pilote"), new Mot("driver") );
        t.ajouter( new Mot("Pixel"), new Mot("pixel") );
        t.ajouter( new Mot("Pointeur"), new Mot("pointer") );
        t.ajouter( new Mot("Police"), new Mot("font") );
        t.ajouter( new Mot("Processeur"), new Mot("processor") );
        t.ajouter( new Mot("Processeur vectoriel"), new Mot("array processor") );
        t.ajouter( new Mot("Progiciel"), new Mot("package") );
        t.ajouter( new Mot("Programmation par objets"), new Mot("object-oriented programming") );
        t.ajouter( new Mot("Raccourci clavier"), new Mot("hot key") );
        t.ajouter( new Mot("Réalité virtuelle"), new Mot("virtual reality") );
        t.ajouter( new Mot("Réamorcer"), new Mot("to reboot") );
        t.ajouter( new Mot("Référentiel"), new Mot("repository") );
        t.ajouter( new Mot("Réinitialiser"), new Mot("to reset") );
        t.ajouter( new Mot("Relancer"), new Mot("to restart") );
        t.ajouter( new Mot("Répertoire"), new Mot("directory") );
        t.ajouter( new Mot("Requête"), new Mot("request") );
        t.ajouter( new Mot("Réseau informatique"), new Mot("computer network") );
        t.ajouter( new Mot("Réseau local"), new Mot("local area network") );
        t.ajouter( new Mot("Réseau neuronal"), new Mot("neural network") );
        t.ajouter( new Mot("Réseautique"), new Mot("networking") );
        t.ajouter( new Mot("Restaurer"), new Mot("to reset ; to restore") );
        t.ajouter( new Mot("Résumé"), new Mot("abstract") );
        t.ajouter( new Mot("Révision"), new Mot("release") );
        t.ajouter( new Mot("Scanneur"), new Mot("scanner") );
        t.ajouter( new Mot("Scrutation"), new Mot("polling") );
        t.ajouter( new Mot("Secours (de)"), new Mot("back up") );
        t.ajouter( new Mot("Secours informatique"), new Mot("back-up") );
        t.ajouter( new Mot("Serveur"), new Mot("on line data service") );
        t.ajouter( new Mot("Souris"), new Mot("mouse") );
        t.ajouter( new Mot("Spoule"), new Mot("spool") );
        t.ajouter( new Mot("Surbrillance"), new Mot("brightening") );
        t.ajouter( new Mot("Survol"), new Mot("browsing") );
        t.ajouter( new Mot("Système exclusif"), new Mot("proprietary system") );
        t.ajouter( new Mot("Système expert"), new Mot("expert system") );
        t.ajouter( new Mot("Système d'exploitation"), new Mot("operating system") );
        t.ajouter( new Mot("Système de gestion de base de données"), new Mot("Data Base Management System, D.B.M.S") );
        t.ajouter( new Mot("Tableur"), new Mot("spreadsheet") );
        t.ajouter( new Mot("Tel écran - tel écrit"), new Mot("wysiwyg") );
        t.ajouter( new Mot("Télémaintenance"), new Mot("remote maintenance") );
        t.ajouter( new Mot("Télétraitement"), new Mot("teleprocessing") );
        t.ajouter( new Mot("Télétraitement par lots"), new Mot("remote batch teleprocessing") );
        t.ajouter( new Mot("Temps réel"), new Mot("real time") );
        t.ajouter( new Mot("Test de performance"), new Mot("benchmark") );
        t.ajouter( new Mot("Texte intégral (en)"), new Mot("full text") );
        t.ajouter( new Mot("Texteur"), new Mot("word processor, text processor") );
        t.ajouter( new Mot("Tirage"), new Mot("hard copy") );
        t.ajouter( new Mot("Tolérance aux pannes"), new Mot("fault tolerance") );
        t.ajouter( new Mot("Tolérant aux pannes"), new Mot("fault tolerant") );
        t.ajouter( new Mot("Traitement automatique des données"), new Mot("automatic data processing (A.D.P.)") );
        t.ajouter( new Mot("Traitement par lots"), new Mot("batch processing") );
        t.ajouter( new Mot("Traitement de texte"), new Mot("word processing, text processing") );
        t.ajouter( new Mot("Tutoriel"), new Mot("tutorial") );
        t.ajouter( new Mot("Version"), new Mot("release, version") );
        t.ajouter( new Mot("Visu"), new Mot("display device") );
        assertEquals(t.traduire(new Mot("Télémaintenance")), new Mot("remote maintenance"));
    }

}
