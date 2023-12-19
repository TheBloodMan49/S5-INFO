import re


# Fonction de recherche des sabotiers
def listePatronymes(nom, dct):
    f = open(nom, 'r')
    etat = 0
    reg1 = re.compile(r"\bINDI\b")
    reg2 = re.compile(r"\bNAME\b.*/(.+)/")
    reg3 = re.compile(r"\bOCCU\b")
    for ligne in f:
        if etat == 0:  # Etat initial
            res = reg1.search(ligne)
            if res != None:  # Etat individu détecté
                etat = 1
        elif etat == 1:
            res = reg2.search(ligne)
            if res != None:
                if dct.get(res.group(1)) == None:
                    dct[res.group(1)] = 1
                else:
                    dct[res.group(1)] += 1
                etat = 2
        elif etat == 2:
            res = reg3.search(ligne)
            if res != None:
                etat = 3
            res = reg1.search(ligne)
            if res != None: # Etat individu détecté
                etat = 1
        elif etat == 3:
            res = reg1.search(ligne)
            if res != None:  # Etat individu détecté
                etat = 1
    f.close()


# Affichage d’un dictionnaire
def affichageDict(dct):
    for c in dct.keys():
        print(c, " : ", dct[c])


# Programme principal
if __name__ == "__main__":
    dct = {}
    listePatronymes("sabotiers.ged", dct)
    affichageDict(dct)
