#!/usr/bin/python
from PyQt5.QtWidgets import QMessageBox, QLabel, QMdiSubWindow, QScrollArea
from PyQt5.QtGui import QPixmap, QImage, qRgb, qRed, qBlue, qGreen
from PyQt5.QtCore import Qt
from fenetreFilleTC_e import FenetreFilleTC

class FenetreFilleRGB(QMdiSubWindow):
    def __init__(self, nom="", parent=None):
        # Appel du constructeur de la classe de base
        QMdiSubWindow.__init__(self)
        # Mémorisation des paramètres
        self.nom=nom
        self.parent=parent
        # Initialisation de l'interface
        self.initUI()
        # Destruction de la fenêtre sur fermeture
        self.setAttribute(Qt.WA_DeleteOnClose)

    # Initialisation de l'interface
    def initUI(self):
        # Mise d'un titre à la fenêtre fille
        self.setWindowTitle(self.nom)

        # Création d'une fenêtre avec ascenseur
        self.scrollArea=QScrollArea()

        # Gestion d'une image
        self.pixmap=QPixmap()
        self.labelImage = QLabel()
        self.scrollArea.setWidget(self.labelImage)

        # Suivant le type d'appel, on initialise la feneêtre avec l'image contenue dans un fichier
        # ou avec les dimensions de l'image contenue dans la fenere créatrice
        if self.parent == 0:
            self.image=QImage(self.nom)
        else:
            # Création d'une image au même format que l'image d'origine
            self.image=QImage(self.parent.image.size(), self.parent.image.format())

        # Si l'image est correctement chargée, mettre à jour l'affichage
        if not self.image.isNull():
            self.imageChargee=1
            self.majPixmap(self.image)
        else:
             QMessageBox.information(self,"Problème","Création de l'image dans la fenêtre fille")

        # Fixer les paramètres de la sous-fenêtre
        self.setWidget(self.scrollArea)
        self.resize(self.scrollArea.size())

    # Mise à jour du Pixmap
    def majPixmap(self,image):
        self.pixmap=QPixmap.fromImage(image)
        self.labelImage.resize(self.pixmap.size())
        self.scrollArea.resize(self.pixmap.size())
        self.labelImage.setPixmap(self.pixmap.fromImage(image))
        self.resize(self.pixmap.size());

    #===================== Inversion (Question 7) ========================

    # Gestion de l'inversion d'une image
    def inverser(self):
        if self.imageChargee:
            fille = FenetreFilleRGB(self.nom,self)
            if fille:
                for y in range(self.image.height()):
                    for x in range(self.image.width()):
                        pixel=self.image.pixel(x,y)
                        # A compléter pour procéder à l'inversion...
                        fille.image.setPixel(x,y,qRgb(255-qRed(pixel), 255-qGreen(pixel), 255-qBlue(pixel)))
                fille.majPixmap(fille.image)
                return fille

    #===================== Saturation en rouge (Question 8) ========================

    # Calcul de l'image saturée en rouge
    def saturation(self):
        if self.imageChargee:
            fille = FenetreFilleRGB(self.nom,self)
            if fille:
                # A compléter pour créer l'image saturée en rouge en s'aidant de la question 7...
                for y in range(self.image.height()):
                    for x in range(self.image.width()):
                        pixel=self.image.pixel(x,y)
                        # A compléter pour procéder à l'inversion...
                        fille.image.setPixel(x,y,qRgb(255, qGreen(pixel), qBlue(pixel)))
                fille.majPixmap(fille.image)
                return fille


    #===================== Passage en noir et blanc (Question 9) ========================

    # Calcul de l'image en noir et blanc
    def nb(self):
        if self.imageChargee:
            fille = FenetreFilleTC(self.nom,self)
            for y in range(self.image.height()):
                for x in range(self.image.width()):
                    pixel=self.image.pixel(x,y)
                    # A compléter pour créer l'image 8 bits en noir et blanc...
                    nbg=int(0.2126*qRed(pixel)+0.7152*qGreen(pixel)+0.0722*qBlue(pixel))
                    fille.image.setPixel(x,y,nbg)
            fille.majPixmap(fille.image)
            return(fille)
            
if __name__ == "__main__":
    print("\nCe programme n'est pas le programme principal!\n")