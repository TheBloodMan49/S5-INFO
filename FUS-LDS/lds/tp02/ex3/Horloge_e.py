# -*- coding: utf-8 -*-
import re
from datetime import datetime
from PyQt5.QtWidgets import QMainWindow
from PyQt5.QtWidgets import QMenu, QAction
from PyQt5.QtWidgets import QWidget, QHBoxLayout, QMessageBox, QLabel
from PyQt5.QtWidgets import QApplication
from PyQt5.QtGui import QPixmap, QImage, qRgb
from PyQt5.QtGui import QIcon
from PyQt5 import QtCore

# Classe Horloge permettant d'afficher l'heure par le biais de l'utilisation
# de la table des couleurs
class Horloge(QMainWindow):
  LUM_MAX=255
  TAILLE_TDC=256
  NBSEG=7
  # Quels segments allumer pour un chiffre donné ?
  SEGMENTS_CHIFFRES= [[1, 1, 1, 0, 1, 1, 1 ],[0, 0, 0, 0, 1, 0, 1 ],
		                  [0, 1, 1, 1, 1, 1, 0 ],[0, 1, 0, 1, 1, 1, 1 ],
		                  [1, 0, 0, 1, 1, 0, 1 ],[1, 1, 0, 1, 0, 1, 1 ],
		                  [1, 1, 1, 1, 0, 1, 1 ],[0, 1, 0, 0, 1, 0, 1 ],
		                  [1, 1, 1, 1, 1, 1, 1 ],[1, 1, 0, 1, 1, 1, 1 ]]
  
  def __init__(self, parent=None):
    QWidget.__init__(self, parent)
    self.initUI()
    self.setWindowIcon(QIcon("horloge.png"))
    self.show()

  def ajoutToolBar(self):
    # Création d'une barre d'outils
    self.toolBar=self.addToolBar("Raccourcis")

    # Gestion des boutons de la barre d'outils
    self.toolBar.addAction(self.actionStart)
    self.toolBar.addAction(self.actionStop)
    self.toolBar.addSeparator()
    self.toolBar.addAction(self.actionInformations)
    self.toolBar.addSeparator()
    self.toolBar.addAction(self.actionQuitter)
      
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

    # Gestion du choix start
    self.actionStart=self.ajoutChoixMenu("Start",self.menuFichier,self.startClicked,"start.png")
    # Gestion du choix stop
    self.actionStop=self.ajoutChoixMenu("Stop",self.menuFichier,self.stopClicked,"stop.png")
    # Gestion du choix informations
    self.actionInformations=self.ajoutChoixMenu("&Informations",self.menuFichier,self.informationsClicked,"informations.png")
    # Gestion du choix quitter
    self.actionQuitter=self.ajoutChoixMenu("&Quitter",self.menuFichier,self.close,"exit.png")
          
  # Ajout de la barre de menu
  def ajoutMenu(self):
    self.menubar=self.menuBar()
    self.ajoutMenuFichiers()

  def initUI(self):
    # Titre et position de la fenêtre
    self.setWindowTitle("Horloge")
    self.move(250, 200)

    # Organisation des widgets
    self.hbox = QHBoxLayout()
    self.window = QWidget()
    self.window.setLayout(self.hbox)
    
    # Gestion du menu
    self.ajoutMenu()        
    # Gestion d'une toolbar
    self.ajoutToolBar()

    # L'image affichée n'ayant pas besoin d'être manipulée, on n'utilise 
    # pas le widget QImage
    #self.image=QImage(500,150, QImage.Format_Indexed8)
    self.ouvrirImage("horloge.bmp")
    
    # Création d'une table des couleurs par défaut en 256 niveaux de gris
    # Cette intialisation est obligatoire sous peine de ne pas pouvoir changer les images
    self.image.setColorCount(Horloge.TAILLE_TDC)

    # Niveau de gris des segments non allumés
    self.gr=30
    # Mise à cette valeur de tous les niveaux depuis le premier segment
    self.aplat(150,self.gr)

    # Dimensionnement d'un tableau pour accueillir les chiffres de l'heure courante et l'heure précedente
    self.c=[0]*Horloge.NBSEG
    self.cp=[-1]*Horloge.NBSEG
    
    # Affichage de l'image et positionnement dans la fenêtre
    self.pixmap=QPixmap.fromImage(self.image)
    if self.pixmap.isNull():
            QMessageBox.information(self,u"Problème",u"Initialisation image")
    self.label = QLabel()
    self.label.setPixmap(QPixmap.fromImage(self.image))
    self.hbox.addWidget(self.label)
    self.setCentralWidget(self.window)

  # Mise d'un aplat de niveaux de gris sur les couleurs
  def aplat(self,deb,gr):
    for i in range(deb,Horloge.TAILLE_TDC):
        self.image.setColor(i, qRgb(gr, gr, gr))      
    
  # Gestion de l'ouverture d'une image
  def ouvrirImage(self,nom):
    self.image=QImage(nom)
    if  self.image.isNull():
      QMessageBox.information(self,"Problème","Image non aux formats RGB32/Indexed8")

  # Gestion du bouton de récupération d'information sur la base de données
  def informationsClicked(self):
    QMessageBox.information(self,"Information","Application de fonctionnement d'une horloge\n"+
                              "Auteur: M. Ivan Leplumey")
        
  # Affichage d'un chiffre
  def affichageChiffre(self,ch,base):
    for i in range(Horloge.NBSEG):
      #print(str(c1)+" - "+str(i))
      if Horloge.SEGMENTS_CHIFFRES[ch][i]==1:
        self.image.setColor(base+i, qRgb(255, 255, 255))
      else:
        self.image.setColor(base+i, qRgb(self.gr, self.gr, self.gr))

  # Gestion du lancement de l'horloge
  def majHorloge(self):
    temps=str(datetime.now().time())
    # 17:40:49.030946
    result=re.compile("(\d)(\d):(\d)(\d):(\d)(\d)").search(temps)
    if (result!=None):
      for i in range(6):
        self.c[i]=int(result.group(i+1))
      
      # Balayage des 6 chiffres de l'horloge
      for j in range(5,-1,-1):
        if self.cp[j]!=self.c[j]:
          self.affichageChiffre(self.c[j],150+j*10)
          self.cp[j]=self.c[j]
        else:
          # On arrête si les chiffres ne bougent plus...
          break
          
      # Mise à jour de l'affichage
      self.label.setPixmap(self.pixmap.fromImage(self.image))

  # Gestion du lancement de l'horloge
  def startClicked(self):
    print(datetime.now().time())
    # 17:40:49.030946
    self.timer = QtCore.QTimer()
    self.timer.start(1000) # lancer un top toutes les secondes (=1000 millisecondes)
    self.timer.timeout.connect(self.majHorloge)
        
  # Gestion de l'arrêt de l'horloge
  def stopClicked(self):
    if self.timer.isActive(): 
      self.timer.stop()        

if __name__ == "__main__":
    app=QApplication([])
    mf=Horloge()
    app.exec_()
