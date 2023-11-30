#!/usr/bin/ruby
#require 'Math'
from PyQt5.QtWidgets import QMainWindow, QTextEdit, QVBoxLayout, QHBoxLayout
from PyQt5.QtWidgets import QMenu, QAction, QToolBar, QFileDialog, QMessageBox
from PyQt5.QtWidgets import QLabel, QWidget, QGridLayout, QComboBox, QSpinBox
from PyQt5.QtWidgets import QLineEdit, QTabWidget, QDateEdit, QPushButton
from PyQt5.QtGui import QIcon
from PyQt5.QtCore import QDate
from gestionBD import BD
import os.path

# =============================================================================
# Zone textuelle sauvegardable qui va servir de zone d'affichage si nécessaire
class Texte(QTextEdit):
  # Constructeur
  def __init__(self):
    # Appel constructeur de la classe mère
    QTextEdit.__init__(self)
    # Affichage du widget
    self.show()

  # Afficher texte
  def afficher(self,t):
    # Affichage message d'erreur dans l'interface Qt
    cursor=self.textCursor()
    cursor.insertText(t)

  # Effacement du texte de la fenêtre
  def effacer(self):
    self.setPlainText("")

class Fenetre(QMainWindow):
  def __init__(self):
    # Appel constructeur de la classe mère
    QMainWindow.__init__(self)

    # Mise d'une base de données par défaut
    self.nomBD="baseMilitaires.bd"
    # Allocation d'un objet base de données
    self.bd=BD(self,self.nomBD)

    # Initialisation de l'interface
    self.init_ui()

    # Visualisation de la fenêtre
    self.show()

    # Emission d'un signal de modification sur le champ d'édition du matricule
    self.editMatric.setValue(2)
    self.editMatric.setValue(1)

  # Mise en place de zones non éditables
  def zoneNonEditable(self,zone):
    # Mise en place d'une zone non modifiable
    zone.setReadOnly(True)
    zone.setStyleSheet("QLineEdit { background-color : lightGray; color : darkGray; }");
      
  # Ajout d'un choix à un menu
  def ajoutChoixMenu(self,texte,menu,fonction,image=""):
    action = QAction(texte, self)
    if image!="": action.setIcon(QIcon(image))
    menu.addAction(action)
    action.triggered.connect(fonction)
    return(action)
    
  # Ajout du menu Fichiers
  def ajoutMenuFichiers(self):
    # Création et ajout du menu Fichiers
    self.menuFichier=QMenu("&Fichier")
    self.menubar.addMenu(self.menuFichier)
    
    # Gestion du choix ouvrir
    self.actionOuvrir=self.ajoutChoixMenu("&Ouvrir",self.menuFichier,
                                          self.ouvrirClicked,"ouvrir.png")
    # Gestion du choix sauver
    self.actionSauver=self.ajoutChoixMenu("&Sauver sous...",self.menuFichier,
                                          self.sauverClicked,"SauverSous.png")
    # Gestion du choix effacer la fenêtre texte
    self.actionEffacer=self.ajoutChoixMenu("&Effacer texte",self.menuFichier,
                                          self.effacerTexteClicked,"gomme.png")
    # Gestion du choix informations
    self.actionInformations=self.ajoutChoixMenu("&Informations",self.menuFichier,
                                          self.informationsClicked,"informations.png")
    # Gestion du choix quitter
    self.actionQuitter=self.ajoutChoixMenu("&Quitter",self.menuFichier,
                                          self.close,"quitter.png")
    
  # Ajout du menu BD
  def ajoutMenuBD(self):
    # Création et ajout du menu base de données
    self.menuBD=QMenu("&Base de données")
    self.menubar.addMenu(self.menuBD)

    # Gestion du choix de création de la base de données
    self.actionCreerBD=self.ajoutChoixMenu("&Créer BD",self.menuBD,
                                          self.creerBDClicked,"bd.png")
    # Gestion du choix de remplissage de la table des lieux
    self.actionInitTableLieux=self.ajoutChoixMenu("&Init. table lieux",self.menuBD,
                                          self.initTableLieuxClicked,"importer.png")
    # Gestion du choix d'affichage de la liste des militaires
    self.actionListeMilitaires=self.ajoutChoixMenu("Liste des &militaires",self.menuBD,
                                          self.listeMilitairesClicked,"liste.png")
    # Gestion du choix d'affichage de la liste des lieux
    self.actionListeLieux=self.ajoutChoixMenu("Liste des &lieux",self.menuBD,
                                          self.listeLieuxClicked,"france.png")
    # Gestion du choix d'affichage de la liste des résidences
    self.actionListeResidences=self.ajoutChoixMenu("Liste des &résidences",self.menuBD,
                                          self.listeResidencesClicked,"maison.png")
    
  # Ajout de la barre de menu
  def ajoutMenu(self):
    self.menubar=self.menuBar()
    self.ajoutMenuFichiers()
    self.ajoutMenuBD()
   
  # Création d'une barre d'outils
  def ajoutToolBar(self):
    # Création d'une barre d'outils
    self.toolBar=self.addToolBar("Raccourcis")

    # Gestion des boutons de la barre d'outils
    self.toolBar.addAction(self.actionOuvrir)
    self.toolBar.addAction(self.actionSauver)
    self.toolBar.addAction(self.actionEffacer)
    self.toolBar.addSeparator()
    self.toolBar.addAction(self.actionCreerBD)
    self.toolBar.addAction(self.actionInitTableLieux)
    self.toolBar.addSeparator()
    self.toolBar.addAction(self.actionListeLieux)
    self.toolBar.addAction(self.actionListeMilitaires)
    self.toolBar.addAction(self.actionListeResidences)
    self.toolBar.addSeparator()
    self.toolBar.addAction(self.actionInformations)
    self.toolBar.addSeparator()
    self.toolBar.addAction(self.actionQuitter)
    
    # Invalidation des boutons non nécessaires
    self.actionSauver.setEnabled(False)
    self.actionEffacer.setEnabled(False)

  # Ajout de l'onglet Personne
  def ajoutOngletPersonne(self):
    # Gestion de l'onglet personne
    self.personneWidget=QWidget()
    self.gridP1=QGridLayout()
    self.labelSexe=QLabel("Sexe")
    self.editSexe=QComboBox()
    self.editSexe.addItem("M")
    self.editSexe.addItem("F")
    self.labelNom=QLabel("Nom")
    self.editNom=QLineEdit()
    self.labelPrenom=QLabel("Prénom")
    self.editPrenom=QLineEdit()
    self.labelProfession=QLabel("Profession")
    self.editProfession=QLineEdit()
    self.labelDateNaissance=QLabel("Date naissance")
    self.editDateNaissance=QDateEdit()
    self.editDateNaissance.setDisplayFormat("yyyy-MM-dd")
    self.labelLieuNaissance=QLabel("Lieu naissance")
    self.editLieuNaissance=QLineEdit()
    self.editCodeLieuNaissance=QLineEdit()
    self.zoneNonEditable(self.editCodeLieuNaissance)

    # Gestion des fins de modification de certains champs
    self.editLieuNaissance.editingFinished.connect(self.validerChampLieuN)

    # Mise en place des widgets dans le layout
    lig=0; col=0
    self.gridP1.addWidget(self.labelSexe,lig,col);
    col+=1; self.gridP1.addWidget(self.editSexe,lig,col)
    col+=1; self.gridP1.addWidget(self.labelNom,lig,col)
    col+=1; self.gridP1.addWidget(self.editNom,lig,col)
    col+=1; self.gridP1.addWidget(self.labelPrenom,lig,col)
    col+=1; self.gridP1.addWidget(self.editPrenom,lig,col)
    lig+=1; col=0
    self.gridP1.addWidget(self.labelProfession,lig,col)
    col+=1; self.gridP1.addWidget(self.editProfession,lig,col)
    col+=1; self.gridP1.addWidget(self.labelDateNaissance,lig,col)
    col+=1; self.gridP1.addWidget(self.editDateNaissance,lig,col)
    col+=1; self.gridP1.addWidget(self.labelLieuNaissance,lig,col)
    col+=1; self.gridP1.addWidget(self.editLieuNaissance,lig,col)
    col+=1; self.gridP1.addWidget(self.editCodeLieuNaissance,lig,col)
    self.personneWidget.setLayout(self.gridP1)
    
    # Ajout de l'onglet
    self.tabWidget.addTab(self.personneWidget,"&Personne")

  # Ajout de l'onglet Residence
  def ajoutOngletResidence(self):
    # Gestion de l'onglet résidence
    self.residenceWidget=QWidget()
    self.gridP2=QGridLayout()
    self.labelDateResidence=QLabel("Date résidence")
    self.editDateResidence=QDateEdit()
    self.editDateResidence.setDisplayFormat("yyyy-MM-dd")
    self.labelLieuResidence=QLabel("Lieu résidence")
    self.editLieuResidence=QLineEdit()
    self.editCodeLieuResidence=QLineEdit()
    self.zoneNonEditable(self.editCodeLieuResidence)

    # Gestion des fins de modification de certains champs
    self.editLieuResidence.editingFinished.connect(self.validerChampLieuR)
    self.editLieuResidence.editingFinished.connect(self.validerChampR)
    self.editDateResidence.dateChanged.connect(self.validerChampR)

    # Mise en place des widgets dans le layout
    lig=0; col=0
    self.gridP2.addWidget(self.labelDateResidence,lig,col)
    col+=1; self.gridP2.addWidget(self.editDateResidence,lig,col)
    col+=1; self.gridP2.addWidget(self.labelLieuResidence,lig,col)
    col+=1; self.gridP2.addWidget(self.editLieuResidence,lig,col)
    col+=1; self.gridP2.addWidget(self.editCodeLieuResidence,lig,col)
    self.residenceWidget.setLayout(self.gridP2)
    
    # Ajout de l'onglet
    self.tabWidget.addTab(self.residenceWidget,"&Résidence")
    
  # Ajout de la partie onglet du formulaire
  def ajoutOngletsFormulaire(self):
    # Gestion des onglets
    self.tabWidget=QTabWidget()
    lig=1; col=0
    self.gridL.addWidget(self.tabWidget,lig,col,1,7)
    self.ajoutOngletPersonne()
    self.ajoutOngletResidence()

  # Ajout de la partie formulaire
  def ajoutFormulaire(self):
    # Objet de positionnement des widgets du formulaire
    self.gridL=QGridLayout()

    # Gestion de la première ligne du formulaire
    self.labelBureau=QLabel("Bureau")
    self.editBureau=QComboBox()
    self.editBureau.addItems(["Caen","Falaise","Lisieux"])
    self.labelAnnee=QLabel("Année")
    self.editAnnee=QSpinBox()
    self.editAnnee.setRange(1850,1950)
    self.labelMatric=QLabel("Matricule")
    self.editMatric=QSpinBox()
    self.editMatric.setRange(1,4000)
    self.editCode=QLineEdit()
    self.zoneNonEditable(self.editCode)

    # Gestion des fins de modification de certains champs
    self.editBureau.currentIndexChanged.connect(self.validerChamp)
    self.editAnnee.valueChanged.connect(self.validerChamp)
    self.editMatric.valueChanged.connect(self.validerChamp)

    # Mise en place des widgets dans le layout
    lig=0; col=0
    self.gridL.addWidget(self.labelBureau,lig,col)
    col+=1; self.gridL.addWidget(self.editBureau,lig,col)
    col+=1; self.gridL.addWidget(self.labelAnnee,lig,col)
    col+=1; self.gridL.addWidget(self.editAnnee,lig,col)
    col+=1; self.gridL.addWidget(self.labelMatric,lig,col)
    col+=1; self.gridL.addWidget(self.editMatric,lig,col)
    #col+=1; self.gridL.addWidget(self.labelCode,lig,col)
    col+=1; self.gridL.addWidget(self.editCode,lig,col)

    # Ajout de la partie à onglets
    self.ajoutOngletsFormulaire()
    
    # Gestion des boutons
    self.boutonEnregistrer=QPushButton("Enregistrer")
    self.boutonEffacerPersonne=QPushButton("Effacer personne")
    self.boutonEffacerResidence=QPushButton("Effacer résidence")
    self.boutonEffacerChamps=QPushButton("Effacer champs")
    lig=2; col=0
    self.gridL.addWidget(self.boutonEnregistrer,lig,col)
    col+=2; self.gridL.addWidget(self.boutonEffacerPersonne,lig,col)
    col+=2; self.gridL.addWidget(self.boutonEffacerResidence,lig,col)
    col=6; self.gridL.addWidget(self.boutonEffacerChamps,lig,col)

    # Gestion des signaux des deux boutons du formulaire
    self.boutonEnregistrer.clicked.connect(self.enregistrerDonnees)
    self.boutonEffacerPersonne.clicked.connect(self.effacerPersonne)
    self.boutonEffacerResidence.clicked.connect(self.effacerResidence)
    self.boutonEffacerChamps.clicked.connect(self.effacerChamps)

    # Mise en place d'une valeur significative en valeur d'origine
    self.editAnnee.setValue(1896)    

    # Ajout du formulaire à la boite verticale principale
    self.boxVPrinc.addLayout(self.gridL)

  # Initialisation de l'interface
  def init_ui(self):
    # Mise d'un titre à la fenêtre
    self.setWindowTitle("Application SQLite")

    # Allocation de la zone textuelle pour afficher les résultats
    self.texte=Texte()
    
    # Le widget principal
    self.window = QWidget()
    # Création d'une boite verticale (associée à un widget)
    self.boxVPrinc=QVBoxLayout()
    self.window.setLayout(self.boxVPrinc)
  
    # Ajout du formulaire complet
    self.ajoutFormulaire()
    self.boxVPrinc.addWidget(self.texte)
    # Gestion des signaux de changement de la zone textuelle
    self.texte.textChanged.connect(self.changementTexte)    
    # Mise de la zone centrale
    self.setCentralWidget(self.window)
    
    # Mise en place du Menu
    self.ajoutMenu()
    # Création d'une barre d'outils
    self.ajoutToolBar()

  # Afficher texte
  def afficher(self,t):
    self.texte.afficher(t)

  # Gestion du changement de la zone de texte
  def changementTexte(self):
    if self.texte.toPlainText()=="":
      self.actionSauver.setEnabled(False)
      self.actionEffacer.setEnabled(False)
    else:
      self.actionSauver.setEnabled(True)
      self.actionEffacer.setEnabled(True)

  # Validation des champs de la première ligne
  def validerChamp(self):
    if self.editAnnee.text()!="" and self.editMatric.text()!="":
      self.editCode.setText(self.editBureau.currentText()[0]+self.editAnnee.text()+"_"+self.editMatric.text())

      # Récupération des données correspondants à un individu
      res=self.bd.personneCode(self.editCode.text())
      self.editSexe.setCurrentIndex(self.editSexe.findText(res[0]))
      self.editNom.setText(res[1])
      self.editPrenom.setText(res[2])
      self.editProfession.setText(res[3])
      if res[4]!="":
        t=res[4].split("-")
        d=QDate(int(t[0]),int(t[1]),int(t[2]))
      else:
        d=QDate(int(self.editAnnee.text())-20,1,1)
      self.editDateNaissance.setDate(d)
      self.editLieuNaissance.setText(res[5])
      self.editCodeLieuNaissance.setText(res[6])

    # Validation du bouton d'effacement si la personne est enregistrée dans la base de données
    exist=self.bd.personneExistence(self.editCode.text())
    if exist!=0:
      self.boutonEffacerPersonne.setEnabled(True)
    else:
      self.boutonEffacerPersonne.setEnabled(False)
      self.boutonEffacerResidence.setEnabled(False)

  # Validation des champs de la première ligne
  def validerChampR(self):
    self.boutonEffacerResidence.setEnabled(False)
    if self.editCodeLieuResidence.text()!="":
      exist=self.bd.residenceExistence(self.editCode.text(),self.editCodeLieuResidence.text(),self.editDateResidence.text())
      if exist!=0:
        self.boutonEffacerResidence.setEnabled(True)

  # Validation du champ de lieu de naissance
  def validerChampLieuN(self):
    if self.nomBD!="":
      if self.editLieuNaissance.text()!="":
        t=self.bd.lieuCode(self.editLieuNaissance.text())
        self.editCodeLieuNaissance.setText(t)
      else:
        self.editCodeLieuNaissance.setText("")
    else:
      afficher("Veuillez choisir au préalable une base de données\n")

  # Validation du champ de lieu de résidence
  def validerChampLieuR(self):
    if self.nomBD!="":
      if self.editLieuResidence.text()!="":
        t=self.bd.lieuCode(self.editLieuResidence.text())
        self.editCodeLieuResidence.setText(t)
      else:
        self.editCodeLieuResidence.setText("")
    else:
      afficher("Veuillez choisir au préalable une base de données\n")

  # Enregistrement des données
  def enregistrerDonnees(self):
      if self.nomBD!="":
          # Détection de l'onglet actif
          n=self.tabWidget.tabText(self.tabWidget.currentIndex())

          # La personne existe-t-elle ?
          exist=self.bd.personneExistence(self.editCode.text())
          if n=="&Personne":
              # Création/modification d'une personne
              if exist==1:
                  # Modification d'un individu existant
                  t=[    self.editSexe.currentText(),self.editNom.text(),self.editPrenom.text(),
                      self.editProfession.text(),self.editDateNaissance.text(),int(self.editCodeLieuNaissance.text()),self.editCode.text()]
                  self.bd.personneMiseAJour(t)
              else:
                  # Insertion d'un nouvel individu
                  t=[    self.editCode.text(),self.editSexe.currentText(),self.editNom.text(),self.editPrenom.text(),
                      self.editProfession.text(),self.editDateNaissance.text(),int(self.editCodeLieuNaissance.text())]
                  self.bd.personneInsertion(t)
          else:
              # Création modification d'un lieu de résidence
              if exist==1:
                  # Insertion d'un nouveau lieu de résidence
                  t=[    self.editCode.text(),int(self.editCodeLieuResidence.text()), self.editDateResidence.text()]
                  self.bd.residenceInsertion(t)
                  self.boutonEffacerResidence.setEnabled(True)
      else:
          afficher("Veuillez choisir au préalable une base de données\n")

  # Effacement des champs des formulaires
  def effacerChamps(self):
      # Effacement de tous les champs de saisie
      self.editSexe.setCurrentIndex(0)
      self.editNom.setText("")
      self.editPrenom.setText("")
      self.editProfession.setText("")
      d=QDate(int(self.editAnnee.text())-20,1,1)
      self.editDateNaissance.setDate(d)
      self.editLieuNaissance.setText("")
      self.editCodeLieuNaissance.setText("")
      self.editDateResidence.setDate(d)
      self.editLieuResidence.setText("")
      self.editCodeLieuResidence.setText("")

  # Effacement d'une personne de la base de données
  def effacerPersonne(self):
      # Création/modification d'une personne
      exist=self.bd.personneExistence(self.editCode.text())
      if exist!=0:
          self.bd.effacerPersonne(self.editCode.text())
          self.boutonEffacerPersonne.setEnabled(False)
          self.boutonEffacerResidence.setEnabled(False)
      else:
          afficher("La personne d'ident %s n'existe pas...\n" % self.editCode.text())

  # Effacement d'un lieu de résidence pour une personne
  def effacerResidence(self):
      if self.editCodeLieuResidence.text()!="":
          exist=self.bd.residenceExistence(self.editCode.text(),self.editCodeLieuResidence.text(),self.editDateResidence.text())
          if exist!=0:
              t=[self.editCode.text(),int(self.editCodeLieuResidence.text()), self.editDateResidence.text()]
              self.bd.effacerResidence(t)
              self.boutonEffacerResidence.setEnabled(False)

  # Gestion de l'ouverture de la base de données
  def ouvrirClicked(self):
      fileName=QFileDialog.getOpenFileName(self, "Sélection de la base de données")
      if (not fileName.isNull()) and fileName.length!=0:
          self.nomBD=fileName
          self.bd.setNom(self.nomBD)
          self.actionInformations.setEnabled(True)

  # Gestion du bouton de sauvegarde
  def sauverClicked(self):
      # Récupération du texte de la fenêtre
      texte = self.texte.toPlainText()

      # Affichage d'une fenêtre de dialogue pour fixer le nom du fichier de sauvegarde
      fileName=QFileDialog.getSaveFileName(self, "Sauvegarde de la trace","trace.txt")

      # On ne peut effectuer une sauvegarde que dans un nouveau fichier
      if (not fileName.isNull()) and fileName.length!=0 and not isFile(fileName):
          # Ouverture du fichier en écriture
          fh=QFile(fileName)
          if not fh.open(QIODevice.WriteOnly):
              QMessageBox.information(self,"Erreur","La sauvegarde de la trace n'a pas été effectuée")
              return
          stream=QTextStream(fh)
          # Sauvegarde du texte
          stream << texte
          fh.close()

  # Gestion du bouton d'effacement du texte
  def effacerTexteClicked(self):
      self.texte.effacer()

  # Gestion du bouton de récupération d'information sur la base de données
  def informationsClicked(self):
      QMessageBox.information(self,"Information","\nNom de la base de données : "+self.nomBD)

  # Gestion du bouton de création de la base de données
  def creerBDClicked(self):
      # Création de la base de données
      self.bd.creerBD()

  # Gestion du bouton de remplissage de la table des lieux
  def initTableLieuxClicked(self):
      # Création de la base de données
      self.bd.initTableLieux("listeCom14sla.txt")

  # Gestion du bouton d'affichage de la liste des militaires
  def listeMilitairesClicked(self):
      # Affichage de la liste des militaires
      self.bd.listeMilitaires()

  # Gestion du bouton d'affichage de la liste des lieux
  def listeLieuxClicked(self):
      # Affichage de la liste des lieux
      self.bd.listeLieux()

  # Gestion du bouton d'affichage de la liste des résidences
  def listeResidencesClicked(self):
      # Affichage de la liste des lieux
      self.bd.listeResidences()

# ===================================================================
#                         Programme principal
# ===================================================================
if __name__ == "__main__":
    print("\nCe programme n'est pas le programme principal!\n")