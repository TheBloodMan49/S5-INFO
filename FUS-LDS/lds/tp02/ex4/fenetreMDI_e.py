#!/usr/bin/python
from PyQt5.QtWidgets import QMessageBox, QMainWindow, QMdiArea
from PyQt5.QtWidgets import QMenu, QAction, QToolBar, QFileDialog
from PyQt5.QtGui import QIcon, QImage
from PyQt5.QtCore import Qt

import os.path

from fenetreFilleRGB_e import FenetreFilleRGB
from fenetreFilleTC_e import FenetreFilleTC

class FenetreMDI(QMainWindow):
    def __init__(self, parent=None):
        # Appel constructeur de la classe mère
        QMainWindow.__init__(self, parent)
        # Intialisation de l'interface
        self.initUI()
        # Visualisation de la fenêtre
        self.show()

    # Invalidation des boutons
    def invaliderBoutons(self):
        self.actionInformations.setEnabled(False)
        self.actionSauver.setEnabled(False)
        self.actionInverser.setEnabled(False)
        self.actionPasserNB.setEnabled(False)
        # A compléter pour la question 8...
        self.actionSaturerRouge.setEnabled(False)
        self.actionSeuil128.setEnabled(False)
        self.actionRoberts.setEnabled(False)
        self.actionTCHLS.setEnabled(False)
        self.actionFloyd.setEnabled(False)
        self.actionOrigine.setEnabled(False)
        self.actionPlanBit1.setEnabled(False)
        self.actionMystere.setEnabled(False)

    def changerFenetre(self):
        # Récupération de la fenêtre active
        fille=self.mdiArea.activeSubWindow()
        self.invaliderBoutons()
        if fille and (isinstance(fille,FenetreFilleRGB) or isinstance(fille,FenetreFilleTC)):
            # Actions communes aux 2 types de fenêtre
            self.actionInformations.setEnabled(True)
            self.actionSauver.setEnabled(True)
            self.actionInverser.setEnabled(True)
            # Actions accessibles qu'à un seul type de fenêtre
            if isinstance(fille,FenetreFilleRGB):
                self.actionPasserNB.setEnabled(True)
                self.actionSaturerRouge.setEnabled(True)
                # A compléter pour la question 8...
            elif isinstance(fille,FenetreFilleTC):
                self.actionSeuil128.setEnabled(True)
                self.actionPlanBit1.setEnabled(True)
                self.actionMystere.setEnabled(True)
                self.actionRoberts.setEnabled(True)
                self.actionTCHLS.setEnabled(True)
                self.actionFloyd.setEnabled(True)
                self.actionOrigine.setEnabled(True)

    # Ajout d'un choix à un menu
    def ajoutChoixMenu(self,texte,menu,fonction,image=""):
        action = QAction(texte, self)
        if image!="": action.setIcon(QIcon(image))
        menu.addAction(action)
        action.triggered.connect(fonction)
        return(action)
        
    # Ajout du menu Fichiers
    def ajoutMenuFichiers(self):
        # Gestion du menu Fichier
        self.menuFichier=QMenu("&Fichier")
        self.menubar.addMenu(self.menuFichier)
    
        # Gestion du choix ouvrir un fichier généalogique
        self.actionOuvrir=self.ajoutChoixMenu("&Ouvrir",self.menuFichier,self.ouvrirClicked,"ouvrir.png")
        # Gestion du choix sauver
        self.actionSauver=self.ajoutChoixMenu("&Sauver sous...",self.menuFichier,self.sauverClicked,"SauverSous.png")
        # Gestion du choix informations
        self.actionInformations=self.ajoutChoixMenu("&Informations",self.menuFichier,self.informationsClicked,"informations.png")
        # Gestion du choix quitter
        self.actionQuitter=self.ajoutChoixMenu("&Quitter",self.menuFichier,self.close,"quitter.png")
            
    # Ajout du menu traitements communs
    def ajoutMenuTraitementsCommuns(self):
        # Gestion du menu lié aux traitements communs
        self.menuTraitementsCommuns=QMenu("Traitements communs")
        self.menuBar().addMenu(self.menuTraitementsCommuns)
        
        # Gestion du choix inverser
        self.actionInverser=self.ajoutChoixMenu("&Inverser",self.menuTraitementsCommuns,self.inverserClicked,"inverser.png")
  
    # Ajout du menu traitements communs
    def ajoutMenuTraitements24Bits(self):
        # Gestion du menu lié aux traitements 24 bits
        self.menuTraitements24b=QMenu("Traitements &24 bits")
        self.menuBar().addMenu(self.menuTraitements24b)
    
        # Gestion du choix passage en noir et blanc
        self.actionPasserNB=self.ajoutChoixMenu("&Noir et Blanc",self.menuTraitements24b,self.NBClicked,"couleurNB.png")
        # Gestion du choix saturation en rouge
        # A compléter pour la question 8...
        self.actionSaturerRouge=self.ajoutChoixMenu("&Saturer en rouge",self.menuTraitements24b, self.saturationClicked, "saturerRouge.png")

    # Ajout du menu traitements communs
    def ajoutMenuTraitements8Bits(self):
        # Gestion du menu lié aux traitements 8 bits
        self.menuTraitements8b=QMenu("Traitements &8 bits")
        self.menuBar().addMenu(self.menuTraitements8b)
        
        # Gestion du choix remise de la table des couleurs à son état d'origine
        self.actionOrigine=self.ajoutChoixMenu("&Table des couleurs NB croissante",self.menuTraitements8b,self.origineClicked,"origine.png")
        # Gestion du choix sélection du plan bit 1
        self.actionPlanBit1=self.ajoutChoixMenu("Plan bit 1",self.menuTraitements8b,self.planBit1Clicked,"planBit1.png")
        # Gestion du choix calcul d'une table des couleurs adaptée à une incrustation spécifique
        self.actionMystere=self.ajoutChoixMenu("Table des couleurs mystere",self.menuTraitements8b,self.mystereClicked,"mystere.png")
        # Gestion du choix seuil à 128
        self.actionSeuil128=self.ajoutChoixMenu("Seuillage &128",self.menuTraitements8b,self.seuil128Clicked,"seuil128.png")
        # Gestion du choix application d'une table des couleurs HLS
        self.actionTCHLS=self.ajoutChoixMenu("&Table des couleurs : HLS",self.menuTraitements8b,self.TCHLSClicked,"hls.png")
        # Gestion du choix détecteur de contour Roberts
        self.actionRoberts=self.ajoutChoixMenu("&Detecteur de contours : Roberts",self.menuTraitements8b,self.robertsClicked,"roberts.png")
        # Gestion du choix simulation de niveaux de gris
        self.actionFloyd=self.ajoutChoixMenu("&Floyd-Steinberg",self.menuTraitements8b,self.floydClicked,"floyd.png")
      
    # Ajout de la barre de menu
    def ajoutMenu(self):
        self.menubar=self.menuBar()
        self.ajoutMenuFichiers()
        self.ajoutMenuTraitementsCommuns()
        self.ajoutMenuTraitements24Bits()
        self.ajoutMenuTraitements8Bits()
  
    # Création d'une barre d'outils
    def ajoutToolBar(self):
        # Création d'une barre d'outils
        self.toolBar = QToolBar("Raccourcis")
        self.addToolBar(self.toolBar)
    
        #self.toolBar=addToolBar("Raccourcis")
        
        # Gestion des boutons de la barre d'outils
        self.toolBar.addAction(self.actionOuvrir)
        self.toolBar.addAction(self.actionSauver)
        self.toolBar.addSeparator()
        self.toolBar.addAction(self.actionPasserNB)
        # A compléter pour la question 8...
        self.toolBar.addSeparator()
        self.toolBar.addAction(self.actionInverser)
        self.toolBar.addSeparator()
        self.toolBar.addAction(self.actionOrigine)
        self.toolBar.addAction(self.actionPlanBit1)
        self.toolBar.addAction(self.actionMystere)
        self.toolBar.addAction(self.actionSeuil128)
        self.toolBar.addAction(self.actionTCHLS)
        self.toolBar.addAction(self.actionRoberts)
        self.toolBar.addAction(self.actionFloyd)
        self.toolBar.addSeparator()
        self.toolBar.addAction(self.actionInformations)
        self.toolBar.addSeparator()
        self.toolBar.addAction(self.actionQuitter)
        
        # La plupart des boutons sont invalides si aucune fenêtre ouverte
        self.invaliderBoutons()

    # Initialisation de l'interface
    def initUI(self):
        # Mise d'un titre à la fenêtre
        self.setWindowTitle("Application image")

        # Création d'une fenêtre avec ascenseur
        self.mdiArea=QMdiArea()
        # A compléter pour mettre des ascenseurs si nécessaire (question 6)
        self.mdiArea.setVerticalScrollBarPolicy(Qt.ScrollBarAsNeeded)
        self.mdiArea.setHorizontalScrollBarPolicy(Qt.ScrollBarAsNeeded)

        self.setCentralWidget(self.mdiArea)
       
        # Ajout du menu
        self.ajoutMenu()
    
        # Ajout d'une barre d'outils    
        self.ajoutToolBar()

        # Gestion de l'événement activation d'une nouvelle fenêtre fille
        self.mdiArea.subWindowActivated.connect(self.changerFenetre)

    # Gestion de l'ouverture avec choix de la bonne image
    def ouvrirClicked(self):
        fileName=QFileDialog.getOpenFileName(self, "Sélection du fichier image")
        if os.path.isfile(fileName[0]):
            image=QImage(fileName[0])
            if  not image.isNull():
                if image.format()==QImage.Format_RGB32:
                    fille = FenetreFilleRGB(fileName[0],0)
                elif image.format()==QImage.Format_Indexed8:
                    fille = FenetreFilleTC(fileName[0],0)
                else:
                    QMessageBox.information(self,"Problème","Image non aux formats RGB32/Indexed8")
                    return
                
                if not fille:
                    QMessageBox.information(self,"Problème","Echec création fenêtre fille")
                else:
                    # Ajout de la fenêtre fille dans la zone QMdiArea
                    self.mdiArea.addSubWindow(fille)
                    fille.show()

    # Gestion du bouton de sauvegarde
    def sauverClicked(self):
        # Récupération de la fenêtre active
        fille=self.mdiArea.activeSubWindow()
        # Si la fenêtre n'existe pas...
        if not fille:
            QMessageBox.information(self,"Problème","Pas d'image à sauvegarder")
        else:
            # Récupération du nom de l'image sans le nom du répertoire
            l=fille.nom.split("/")
            # Affichage d'une fenêtre de dialogue pour fixer le nom du fichier de sauvegarde
            fileName=QFileDialog.getSaveFileName(self, "Sauvegarde de l'image",l[len(l)-1])
            print(fileName)
            # On ne peut effectuer une sauvegarde que dans un nouveau fichier
            if len(fileName[0])!=0 and not os.path.isfile(fileName[0]):
                fille.image.save(fileName[0])
            else:
                QMessageBox.information(self,"Information","La sauvegarde n'a pas été effectuée")

    # Gestion du bouton de transformation en noir et blanc
    def NBClicked(self):
        # Fonction de réaction
        if self.mdiArea.currentSubWindow():
            fille=self.mdiArea.currentSubWindow().nb()
            if not fille:
                QMessageBox.information(self,"Problème","Ajout fenêtre image en noir et blanc")
            else:              
                self.mdiArea.addSubWindow(fille)
                fille.show()

    # Gestion du bouton de saturation en rouge
    def saturationClicked(self):
        # Fonction de réaction
        if self.mdiArea.currentSubWindow():
            fille=self.mdiArea.currentSubWindow().saturation()
            if not fille:
                QMessageBox.information(self,"Problème","Ajout fenêtre image saturée en rouge")
            else:              
                self.mdiArea.addSubWindow(fille)
                fille.show()

    # Gestion du bouton d'inversion
    def inverserClicked(self):
        # Fonction de réaction
        if self.mdiArea.currentSubWindow():
            fille=self.mdiArea.currentSubWindow().inverser()
            if not fille:
                QMessageBox.information(self,"Problème","Ajout fenêtre image inversée")
            elif fille != self.mdiArea.currentSubWindow():
                self.mdiArea.addSubWindow(fille)
                fille.show()               

    # Gestion du bouton de remise de la table des couleurs d'origine
    def origineClicked(self):
        # Fonction de réaction
        if self.mdiArea.currentSubWindow():
            fille=self.mdiArea.currentSubWindow().origine()
            if not fille:
                QMessageBox.information(self,"Problème","Ajout fenêtre image table couleur croissante")
            elif fille != self.mdiArea.currentSubWindow():
                self.mdiArea.addSubWindow(fille)
                fille.show()               

    # Gestion du bouton de sélection du bit de poids faible
    def planBit1Clicked(self):
        # Fonction de réaction
        if self.mdiArea.currentSubWindow():
            fille=self.mdiArea.currentSubWindow().bitPoidsFaible(0)
            if not fille:
                QMessageBox.information(self,"Problème","Ajout fenêtre image de visualisation du bit de poids faible")
            elif fille != self.mdiArea.currentSubWindow():
                self.mdiArea.addSubWindow(fille)
                fille.show()               

    # Gestion du bouton de visualisation du bit de poids faible avec un codage particulier
    def mystereClicked(self):
        # Fonction de réaction
        if self.mdiArea.currentSubWindow():
            fille=self.mdiArea.currentSubWindow().mystere()
            if not fille:
                QMessageBox.information(self,"Problème","Ajout fenêtre image de visualisation du bit de poids faible codé")
            elif fille != self.mdiArea.currentSubWindow():
                self.mdiArea.addSubWindow(fille)
                fille.show()               

    # Gestion du bouton de calcul d'une image seuillée à 128
    def seuil128Clicked(self):
        # Fonction de réaction
        if self.mdiArea.currentSubWindow():
            fille=self.mdiArea.currentSubWindow().seuil(128)
            if not fille:
                QMessageBox.information(self,"Problème","Ajout fenêtre image seufille.instance_of? FenetreFilleRGBillée à 128")
            elif fille != self.mdiArea.currentSubWindow():
                self.mdiArea.addSubWindow(fille)
                fille.show()               

    # Gestion du bouton de calcul d'une image avec table des couleurs HLS
    def TCHLSClicked(self):
        # Fonction de réaction
        if self.mdiArea.currentSubWindow():
            fille=self.mdiArea.currentSubWindow().paletteHLSMax()
            if not fille:
                QMessageBox.information(self,"Problème","Ajout fenêtre image tab;le des couleurs HLS")
            elif fille != self.mdiArea.currentSubWindow():
                self.mdiArea.addSubWindow(fille)
                fille.show()
             
    # Gestion du bouton de calcul d'une image de contour avec l'opérateur de Roberts
    def robertsClicked(self):
        # Fonction de réaction
        if self.mdiArea.currentSubWindow():
            fille=self.mdiArea.currentSubWindow().roberts()
            if not fille:
                QMessageBox.information(self,"Problème","Ajout fenêtre image de contours par l'opérateur de Roberts")
            elif fille != self.mdiArea.currentSubWindow():
                self.mdiArea.addSubWindow(fille)
                fille.show()               
             
    # Gestion du bouton de calcul de similigravure par l'algorithme de Floyd-Steinberg
    def floydClicked(self):
        # Fonction de réaction
        if self.mdiArea.currentSubWindow():
            fille=self.mdiArea.currentSubWindow().floyd()
            if not fille:
                QMessageBox.information(self,"Problème","Ajout fenêtre image de Floyd-Steinberg")
            elif fille != self.mdiArea.currentSubWindow():
                self.mdiArea.addSubWindow(fille)
                fille.show()               

    # Gestion du bouton de récupération d'information sur une image
    def informationsClicked(self):
        # Fonction de réaction
        if self.mdiArea.currentSubWindow():
            fille=self.mdiArea.currentSubWindow()
            if fille:
                type="Image couleur (rouge, vert, bleu) 24 bits"
                if isinstance(fille,FenetreFilleTC):
                    type="Image 8 bits avec table des couleurs"
                QMessageBox.information(self,"Information",type+"\nLargeur : "+str(fille.image.width())+"\nHauteur : "+str(fille.image.height()))

if __name__ == "__main__":
    print("\nCe programme n'est pas le programme principal!\n")
    
# Documentation: http://zetcode.com/gui/rubyqt/
# Photos libre de droit : http://www.photo-libre.fr/n1.htm
