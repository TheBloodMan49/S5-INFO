#!/usr/bin/python
from PyQt5.QtWidgets import QMessageBox, QLabel, QMdiSubWindow, QScrollArea, QFileDialog
from PyQt5.QtGui import QPixmap, QImage, qRgb, qRed, qBlue, qGreen
from PyQt5.QtCore import Qt
import math

class FenetreFilleTC(QMdiSubWindow):
    LUM_MAX=255
    TAILLE_TDC=256

    def __init__(self,nom="", parent=0):
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

        # Suivant le type d'appel, on initialise la fenêtre avec l'image contenue dans un fichier
        # ou avec les dimensions de l'image contenue dans la fenere créatrice
        if self.parent == 0:
            self.image=QImage(self.nom)
        else:
            # Création d'une image au même format que l'image d'origine
            self.image=QImage(self.parent.image.size(), QImage.Format_Indexed8)
            # Création d'une table des couleurs par défaut en 256 niveaux de gris
            self.image.setColorCount(FenetreFilleTC.TAILLE_TDC)
            self.origine()

        # Si l'image est correctement chargée, mettre à jour l'affichage
        if not self.image.isNull():
            self.imageChargee=1
            self.majPixmap(self.image)
        else:
             QMessageBox.information(self,"Problème","Création de l'image dans la fenêtre fille")

        # Fixer les paramètres de la sous-fenêtre
        self.setWidget(self.scrollArea)
        self.resize(self.scrollArea.size())

    # Recopie de l'image dans le pixmap, puis le label
    def recopieImage(self,image):
        self.pixmap=QPixmap.fromImage(image)
        if self.pixmap.isNull():
            QMessageBox.information(self,"Problème","Chargement pixmap à partir de l'image")
        self.labelImage.setPixmap(self.pixmap.fromImage(image))

    # Mise à jour du pixmap, du label et de la fenêtre à ascenseurs
    def majPixmap(self,image):
        self.recopieImage(image)
        self.labelImage.resize(self.pixmap.size())
        self.scrollArea.resize(self.labelImage.size())
        self.resize(self.scrollArea.size())

    # Remise de la table des couleurs dans son état d'origine
    def origine(self):
        for i in range(FenetreFilleTC.TAILLE_TDC):
            self.image.setColor(i, qRgb(i, i, i))
        # Recopie de l'image avec la nouvelle table des couleurs dans la fenêtre
        self.recopieImage(self.image)
        return(self)

    # Application d'une palette HLS
    # Attention: bien 2.0 et non 2. qui n'est pas licite en Ruby
    def paletteHLSMax(self):
        angle1=0.0
        angle2=2.0*math.pi/3.0
        angle3=4.0*math.pi/3.0
        pas=2.0 * math.pi / FenetreFilleTC.TAILLE_TDC
        for i in range(FenetreFilleTC.TAILLE_TDC):
            self.image.setColor(i, qRgb(int((math.cos(angle1)+1.0)*FenetreFilleTC.LUM_MAX/2.0),
                                    int((math.cos(angle2)+1.0)*FenetreFilleTC.LUM_MAX/2.0),
                                    int((math.cos(angle3)+1.0)*FenetreFilleTC.LUM_MAX/2.0)))
            angle1=angle1+pas
            angle2=angle2+pas
            angle3=angle3+pas
        # Recopie de l'image avec la nouvelle table des couleurs dans la fenêtre
        self.recopieImage(self.image)
        return(self)

    #=============== Opérateur d'inversion (Question 10) ==================

    # Inversion d'une image
    def inverser(self):
        for i in range(FenetreFilleTC.TAILLE_TDC):
            c=self.image.color(i)
            # A compléter pour obtenir une bonne inversion...
            self.image.setColor(i, qRgb(255-qRed(c), 255-qGreen(c), 255-qBlue(c)))
        # Recopie de l'image avec la nouvelle table des couleurs dans la fenêtre
        self.recopieImage(self.image)
        return(self)

    #=============== Opérateur de contours (Question 11) ==================

    # Détection de contours suivant roberts
    def roberts(self):
        fille = FenetreFilleTC(self.nom, self)
        for y in range(self.image.height()-1):
            for x in range(self.image.width()-1):
                pixel1=self.image.pixelIndex(x,y)
                pixel2=self.image.pixelIndex(x+1,y)
                # A compléter avec des pixel3, pixel4 et bonne valeur pour nbg...
                pixel3=self.image.pixelIndex(x,y+1)
                pixel4=self.image.pixelIndex(x+1,y+1)
                # La fonction abs(expression) calcule une valeur absolue
                nbg=int(0.5*(abs(pixel1-pixel4)+abs(pixel2-pixel3)))
                fille.image.setPixel(x,y,nbg)
        fille.majPixmap(fille.image)
        return(fille)

    #===================== Seuillage (Question 12) ========================

    # Seuillage d'une image à la valeur n
    def seuil(self,n):
        if n<0 or n>FenetreFilleTC.LUM_MAX:
            QMessageBox.information(self,"Problème","Mauvais parmètre pour la fonction seuil")
            return(self)
        # A modifier/compléter pour construire les deux parties de la table des couleurs...
        for i in range(0,FenetreFilleTC.LUM_MAX+1):
            if(i<n):
                self.image.setColor(i,qRgb(0,0,0))
            else:
                self.image.setColor(i, qRgb(FenetreFilleTC.LUM_MAX, FenetreFilleTC.LUM_MAX, FenetreFilleTC.LUM_MAX))
        # Recopie de l'image avec la nouvelle table des couleurs dans la fenêtre
        self.recopieImage(self.image)
        return(self)

    #===================== Floyd-Steinberg (Question 13) ========================

    # Calcul de la nouvelle valeur pour un point pour Floyd-Steinberg
    def nvlValeur(self,n):
        if n>=128:
            return 255
        else:
            return 0

    # Simulation de niveaux de gris
    def floyd(self):
        # Recopie de l'image d'origine dans la nouvelle image de la feneêtre fille
        fille = FenetreFilleTC(self.nom, self)
        for y in range(self.image.height()):
            for x in range(self.image.width()):
                nbg=self.image.pixelIndex(x,y)
                fille.image.setPixel(x,y,nbg)

        # Pour chaque point, trouver la bonne valeur et diffuser l'erreur
        for y in range(self.image.height()-1):
            # Cas de la première colonne
            nbg=self.image.pixelIndex(0,y)
            nvl=self.nvlValeur(nbg)
            fille.image.setPixel(0,y,nvl)
            for x in range(1,self.image.width()-1):
                nbg=fille.image.pixelIndex(x,y)
                nvl=self.nvlValeur(nbg)
                fille.image.setPixel(x,y,nvl)
                err=-nvl+nbg;
                # Propagation de l'erreur sur les 4 pixels voisins
                nvv_d=int(max(0,min(fille.image.pixelIndex(x+1,y)+7*err/16,255),0))
                fille.image.setPixel(x+1,y,nvv_d)
                # A compléter pour diffuser l'erreur sur les points de l'image fille...
                nvv_bg=int(max(0,min(fille.image.pixelIndex(x-1,y+1)+3*err/16,255),0))
                fille.image.setPixel(x - 1, y + 1, nvv_bg)
                nvv_b=int(max(0,min(fille.image.pixelIndex(x,y+1)+5*err/16,255),0))
                fille.image.setPixel(x, y + 1, nvv_b)
                nvv_bd=int(max(0,min(fille.image.pixelIndex(x+1,y+1)+1*err/16,255),0))
                fille.image.setPixel(x + 1, y + 1, nvv_bd)

            # Cas de la dernière colonne (sans diffusion d'erreur)
            nbg=fille.image.pixelIndex(self.image.width()-1,y)
            nvl=self.nvlValeur(nbg)
            fille.image.setPixel(self.image.width()-1,y,nvl)

        # Dernière ligne (sans diffusion d'erreur)
        for x in range(self.image.width()):
            nbg=fille.image.pixelIndex(x,self.image.height()-1)
            nvl=self.nvlValeur(nbg)
            fille.image.setPixel(x,self.image.height()-1,nvl)
        fille.majPixmap(fille.image)
        return(fille)

    #================== Bit de poids faible simple (Question 14) =====================

    # Visualisation du bit de poids faible
    def bitPoidsFaible(self,n):
        for i in range(FenetreFilleTC.TAILLE_TDC):
            # A compléter pour visualiser le bit de poids faible (bit 0)...
            self.image.setColor(i, qRgb((i&1)*FenetreFilleTC.LUM_MAX,(i&1)*FenetreFilleTC.LUM_MAX,(i&1)*FenetreFilleTC.LUM_MAX))

        # Recopie de l'image avec la nouvelle table des couleurs dans la fenêtre
        self.recopieImage(self.image)
        return(self)

    #=============== Bit de poids faible codé (Question 15) ===================

    # Initialisation d'une table des couleurs pour révéler une image cachée
    def mystere(self):
        for i in range(FenetreFilleTC.TAILLE_TDC):
            # A compléter pour visualiser le bit de poids faible (bit 0) codé à l'aide du bit 1...
            if (i&0b10)>>1:
                nc = (i&1)*FenetreFilleTC.LUM_MAX
            else:
                nc = ((i&1)==0)*FenetreFilleTC.LUM_MAX
            self.image.setColor(i, qRgb(nc, nc, nc))
        # Recopie de l'image avec la nouvelle table des couleurs dans la fenêtre
        self.recopieImage(self.image)
        return(self)
        
if __name__ == "__main__":
    print("\nCe programme n'est pas le programme principal!\n")
