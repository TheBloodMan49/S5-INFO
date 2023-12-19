import re

from Q2 import Automate


class AutomateNomProf(Automate):
    # Constructeur
    def __init__(self):
        super().__init__()

        # Description de l'automate
        self.reIndi = [r"0.*(INDI)$", 1, self.traitIndi]
        self.reName = [r"1 NAME.*/(.+)/$", 2, self.traitName]
        self.reOccu = [r"1 OCCU (.+)$", 3, self.traitOccu]
        self.automateDon = {0: [self.reIndi],
                            1: [self.reName, self.reIndi],
                            2: [self.reOccu, self.reIndi],
                            3: [self.reIndi]}
        self.current_name = ""

    # Traitement spécifique pour une ligne donnée
    def traitIndi(self, m1):
        return

    def traitName(self, m1):
        self.current_name = m1

    def traitOccu(self, m1):
        # Gestion du cas où le nom n’est pas présent
        if self.current_name == "":
            return
        # Séparation des professions
        reg = re.compile(r'\b\w+(?=\s*\(.+\))')
        prof = reg.findall(m1)
        # Balayage des professions séparées
        for p in prof:
            # Cas d'une profession existante
            if self.struct.get(self.current_name) is not None:
                # Cas d'une profession existante
                if self.struct[self.current_name].get(p) is not None:
                    self.struct[self.current_name][p] += 1
                # Cas d'une profession inexistante
                else:
                    self.struct[self.current_name][p] = 1
            # Cas d'une profession inexistante
            else:
                self.struct[self.current_name] = {p: 1}

    # Impression de la structure
    def afficher(self):
        for cle1 in self.struct.keys():
            print(cle1, " : ", end="")
            for cle2 in self.struct[cle1].keys():
                print(cle2 + " (" + str(self.struct[cle1][cle2]) + ")", end=", ")
            print()


if __name__ == "__main__":
    # Création d'un automate
    a = AutomateNomProf()
    # Analyse du fichier
    a.analyser("sabotiers.ged")
    # Impression de la structure
    a.afficher()
