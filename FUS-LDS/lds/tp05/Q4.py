from Q2 import Automate


class AutomateComptage(Automate):
    # Constructeur
    def __init__(self):
        super().__init__()
        self.struct["Nombre occurences"] = 0
        # Description de l'automate
        self.automateDon = {0: [[r"\b(INDI)\b", 0, self.trait]]}

    # Traitement spécifique pour une ligne donnée
    def trait(self, m1):
        self.struct["Nombre occurences"] += 1


if __name__ == "__main__":
    # Création d'un automate
    a = AutomateComptage()
    # Analyse du fichier
    a.analyser("sabotiers.ged")
    # Impression de la structure
    a.afficher()
