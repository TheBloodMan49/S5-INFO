#!/usr/bin/python
from PyQt5.QtWidgets import QApplication
from SqliteQt import Fenetre
import sys

# ===================================================================
#                         Programme principal
# ===================================================================

if __name__ == "__main__":
    # Vérification de la syntaxe d'appel
    syntaxe ="Syntaxe: "+sys.argv[0]
    if(len(sys.argv) != 1):
        print(syntaxe)
        exit
        
    app=QApplication([])
    mf=Fenetre()
    app.exec_()
