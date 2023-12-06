# -*- coding: utf-8 -*-
"""
Created on Sat Jan  6 08:36:27 2018

@author: leplumey

Module de gestion des données web
"""

# Importation module expressions régulières
import re

# Importation module de lecture de pages web sur le réseau
import urllib.request

# Importation parser HTML
from bs4 import BeautifulSoup


# Essai de traduction de lettres accentuées
def tr(ch):
    ch = re.compile(r"\\n'b'").sub(" ", ch)
    ch = re.compile(r"\\xc3").sub("", ch)
    ch = re.compile(r"\\x89").sub("É", ch)
    ch = re.compile(r"\\xa0").sub("à", ch)
    ch = re.compile(r"\\xa8").sub("è", ch)
    ch = re.compile(r"\\xa9").sub("é", ch)
    ch = re.compile(r"\\xaf").sub("ï", ch)
    ch = re.compile(r"\\xb4").sub("ô", ch)
    ch = re.compile(r"\\t").sub("", ch)
    # ch=re.compile(r"\\\\").sub("",ch)
    # for i in range(len(ch)):
    #    print(ch[i],end=" ")
    return (ch)


# Lecture d'une page web dans un objet de type PageWeb
# - soit la page est accessible ligne par ligne par un tableau : lignes
# - soit la page est accessible sous forme d'une unique chaine de caractères : tout
class PageWeb:
    def __init__(self, url="http://www.dvdfr.com"):
        data = urllib.request.urlopen(url)
        self.lignes = data.readlines()
        self.tout = ""
        for i in range(len(self.lignes)):
            self.tout += str(self.lignes[i])


# Classe de recherche des informations sur un DVD sur le site site DVDfr
class RechercheBR:
    def __init__(self, br="3333299304137"):
        self.br = br
        self.trouve = False
        self.titre = ""
        self.annee = ""
        self.type = ""
        self.duree = ""

    def changeBR(self, br):
        self.br = br
        self.trouve = False

        # Recherche de l'ensemble des URL liés à un code-barres pour trouver la bonne édition

    def rechercheURLs(self):
        # Récupération de la page web associée au numéro de code-barres
        pw = PageWeb("http://www.dvdfr.com/search/multisearch.php?multiname=ean%3D" + self.br)
        soup = BeautifulSoup(pw.tout, "html.parser")
        # Recherche d'une balise spécifique associée à l'élement recherché
        td = soup.find("h2", class_="bleu")
        if td == None:
            return (False)
        # Récupération de l'édition principale
        self.analyseURL("http:" + td.a['href'])
        if self.trouve == False:
            # Recherche des autres éditions
            td = soup.find_all("td", class_="autresEditionsFiche")
            for ed in td:
                if self.trouve == False:
                    self.analyseURL("http:" + ed.a['href'])
                    if self.trouve == True: return (True)
        return (False)

    # Analyse d'une URL pour extraire les attributs associés à un film        
    def analyseURL(self, url):
        pws = PageWeb(url)
        soups = BeautifulSoup(pws.tout, "html.parser")
        # Recherche pour savoir si la page contient le code-barres désiré
        ean = soups.find(string=re.compile(self.br))
        if (ean != None):
            id = soups.find("div", id="fiche")
            print(id.div.h1.string)
            result = tr(id.div.h1.string)
            print("==> " + result)
            # Récupération du titre, de l'année et du type du support
            groups = re.compile(r"(.+) \((\d{4})\)\s+- (\S+)\s*").search(result).groups()
            self.titre = groups[0]
            self.annee = groups[1]
            self.type = groups[2]
            # Récupération du champ duree quand il existe
            durees = id.findAll("time", datetime=re.compile(r"P.+M"))
            if len(durees) == 1:
                self.duree = durees[0].string
            self.trouve = True


# Programme principal
if __name__ == "__main__":
    lst = ["3333299304137", "3344428069940"]
    rbr = RechercheBR()
    for el in lst:
        rbr.changeBR(el)
        rbr.rechercheURLs()
