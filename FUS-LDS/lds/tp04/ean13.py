# -*- coding: utf-8 -*-
"""
Created on Sat Jan  6 08:30:18 2018

@author: leplumey

Module de gestion des codes-barres
"""

# Importation module expressions régulières
import re


class EAN13:
    LGEAN13 = 13

    def __init__(self, code="3333299304137"):
        self.code = code
        self.valide = self.verification()

    # Vérification du code-barre et ajout si nécessaire du chiffre de contrôle    
    def verification(self):
        if re.compile("\D").search(self.code) != None:
            return False
        elif len(self.code) == self.LGEAN13 - 1:
            self.code += self.cle()
            return True
        elif len(self.code) == self.LGEAN13:
            if self.code[self.LGEAN13 - 1] == self.cle():
                return True
            else:
                return False
        else:
            return False


    # Calcul du chiffre de contrôle rendu sous forme de chaine de caractères        
    def cle(self):
        res = 0
        for i, chiffre in enumerate(self.code):
            if i % 2 == 0:
                res += int(chiffre)
            else:
                res += 3 * int(chiffre)

        if res % 10 == 0:
            return ("0")
        else:
            return (str(10 - res % 10))


# Programme principal
if __name__ == "__main__":
    ean13 = EAN13("732195134545")
    print(ean13.cle())

    # Liste de DVD récents
    lst = ["3333299304137", "3344428069940", "3475001049988", "3475001052476", "3475001054487", \
           "3700724902778", "5051889599852", "5051889606659", "5051889606666", "7321950126194"]
    print("Test de la classe EAN13 sur {0} codes".format(len(lst)))
    for i in range(len(lst)):
        ean13 = EAN13(lst[i])
