# -*- coding: utf-8 -*-
# ...

from random import randint
from time import time


# La classe TriTablau contient un tableau de valeurs numériques et des méthodes
# travaillant sur ce tableau
class TableauTri:
    tab = []

    # Le constructeur qui alloue un tableau vide ou d'une certaine taille
    # en fonction du paramètre
    def __init__(self, taille=0):
        self.tab = [0]*taille

    # Initialisation d'un tableau avec l'ajout de valeurs
    def initialiserTableau(self, *t: int):
        self.tab = list(t)

    # Initialisation d'un tableau déjà dimmensionné avec des valeurs aléatoires
    def initialiserAleatoire(self, mini: int, maxi: int):
        self.tab = [randint(mini, maxi) for _ in range(len(self.tab))]

    # Création d'un nouveau tableau identique à celui qui sert à appeler la méthode
    def copie(self) -> 'TableauTri':
        nvTab = TableauTri()
        nvTab.tab = list(self.tab)
        return nvTab

    # Affichage du tableau
    def afficher(self):
        print(self.tab)

    # Tri par sélection du tableau contenu dans la classe
    def triSelection(self):
        for i in range(len(self.tab)):
            current_min = i
            for j in range(i+1, len(self.tab)):
                if self.tab[j] < self.tab[current_min]:
                    current_min = j
            tmp = self.tab[i]
            self.tab[i] = self.tab[current_min]
            self.tab[current_min] = tmp

    def triInsertion(self):
        for i in range(1, len(self.tab)):
            tmp = self.tab[i]
            j = i-1
            while j >= 0 and self.tab[j] > tmp:
                self.tab[j+1] = self.tab[j]
                j -= 1
            self.tab[j+1] = tmp

    def triABulle(self):
        for i in reversed(range(len(self.tab))):
            trie = True
            for j in range(i):
                if self.tab[j+1] < self.tab[j]:
                    tmp = self.tab[j]
                    self.tab[j] = self.tab[j+1]
                    self.tab[j+1] = tmp
                    trie = False
            if trie:
                break

    def triRapide(self):
        self.triRapideRec(0, len(self.tab)-1)

    def triRapideRec(self, premier, dernier):
        if premier < dernier:
            pivot = self.partitionner(premier, dernier, randint(premier, dernier))
            self.triRapideRec(premier, pivot-1)
            self.triRapideRec(pivot+1, dernier)

    def partitionner(self, premier, dernier, pivot):
        tmp = self.tab[pivot]
        self.tab[pivot] = self.tab[dernier]
        self.tab[dernier] = tmp
        j = premier
        for i in range(premier, dernier):
            if self.tab[i] <= self.tab[dernier]:
                tmp = self.tab[i]
                self.tab[i] = self.tab[j]
                self.tab[j] = tmp
                j += 1
        tmp = self.tab[j]
        self.tab[j] = self.tab[dernier]
        self.tab[dernier] = tmp
        return j

    def triBogo(self):
        while not self.estTrie():
            self.melanger()

    def estTrie(self):
        for i in range(len(self.tab)-1):
            if self.tab[i+1] < self.tab[i]:
                return False
        return True

    def melanger(self):
        for i in range(len(self.tab)):
            tmp = self.tab[i]
            j = randint(0, len(self.tab)-1)
            self.tab[i] = self.tab[j]
            self.tab[j] = tmp


def instrumentaliser(texte: str, f: callable):
    debut = time()
    f()
    print(f"Durée d'exécution {texte} : {time()-debut}")


if __name__ == "__main__":
    # Test constructeur d'un tableau vide   
    t1 = TableauTri()
    # Test ajout de valeurs au tableau    
    t1.initialiserTableau(5, 4, 3, 7, 6, 2, 1, 10, 8, 9)
    # Test tri par sélection    
    t1.triRapide()
    # Test tri et affichage    
    t1.afficher()
    # Test constructeur d'un tableau d'une taille donnée   
    t2 = TableauTri(10)
    # Test remplissage aléatoire d'un tableau dimensionné    
    t2.initialiserAleatoire(0, 50)
    # Test copie de tableau    
    t3 = t2.copie()
    # Test tri et affichage sur la copie    
    t3.triRapide()
    # Les deux affichages suivants doivent donner des valeurs différentes
    t2.afficher()
    t3.afficher()

    # Test de l'instrumentalisation
    tab = TableauTri(10000)
    tab.initialiserAleatoire(0, 10000)
    instrumentaliser("Tri par insertion", tab.triInsertion)
    tab.initialiserAleatoire(0, 10000)
    instrumentaliser("Tri par sélection", tab.triSelection)
    tab.initialiserAleatoire(0, 10000)
    instrumentaliser("Tri à bulle", tab.triABulle)
    tab.initialiserAleatoire(0, 10000)
    instrumentaliser("Tri rapide", tab.triRapide)

    # Test du tri bogo
    t2.initialiserAleatoire(0, 50)
    instrumentaliser("Tri bogo", t2.triBogo)

