# -*- coding: utf-8 -*-
"""
Created on Wed Jan  3 10:49:14 2018
@author: leplumey

Sujet: Création d'une DVDthèque

"""

# Importation interface
import tkinter as tk
# import tkinter.messagebox as tm
import tkinter.filedialog as tf

# Importation module codes-barres
import ean13

# Importation du parser web pour le site DVDfr
import parserWeb as prw

# Importation du module de gestion de la base de données de DVDs
import bdDVD


class Interface:
    def information(self, message=""):
        tk.messagebox.showinfo(title="Information")

    # Mises de valeurs par défaut pour les 2 parties du code-barres     
    def valDefautPays(self):
        self.valuePays.set("333")

    def valDefautProduit(self):
        self.valueProduit.set("329930413")

    # Validation du champ de saisie pays
    def estOKPays(self, apres):
        if (len(apres) == 2):
            if not (apres[0] == '2' and apres[1].isdigit()):
                self.valDefautPays()
        if not (len(apres) == 3 and apres[0].isdigit() and apres[1].isdigit() and apres[2].isdigit()):
            self.valDefautPays()
        return (True)

    # Validation du champ de saisie produit
    def estOKProduit(self, apres):
        # Vérification de la taille
        if len(apres) != 9 and len(apres) != 10:
            self.valDefautProduit()
        # Vérification que tous les caractères sont numériques
        for i in range(len(apres)):
            if not apres[i].isdigit():
                self.valDefautProduit()
        # Mise à jour de tous les autres champs
        self.miseAJourChamps()

        return (True)

    # Mise à jour de tous les champs à partir du numéro de code-barres    
    def miseAJourChamps(self):
        # Mise à jour de la clé
        self.positCle()

        # Mise à jour des champs du film soit à partir de la base de données,
        # soit à partir du site web DVDfr
        tbl = []
        tbl = self.bd.interrogerParCB(self.valuePays.get(), self.valueProduit.get() + self.valueCRC.get())
        if len(tbl) == 0:
            # Préparation de la recherche web
            self.rbr.changeBR(self.valuePays.get() + self.valueProduit.get() + self.valueCRC.get())
            # Recherche sur le web
            self.rbr.rechercheURLs()
            # Recopie des résultats de la recherche
            self.valueTitre.set(self.rbr.titre)
            self.valueAnnee.set(self.rbr.annee)
            self.valueDuree.set(self.rbr.duree)
            self.valueSupport.set(self.rbr.type)
        else:
            # print("Trouvé dans BD")
            self.valueTitre.set(tbl[0][0])
            self.valueAnnee.set(tbl[0][1])
            self.valueDuree.set(tbl[0][2])
            self.valueSupport.set(tbl[0][3])

    # Calcul de la clé à partir des 12 autres chiffres du code-barres    
    def positCle(self):
        ean = ean13.EAN13(self.valuePays.get() + self.valueProduit.get())
        if (len(ean.code) == ean13.EAN13.LGEAN13):
            self.valueCRC.set(ean.code[ean13.EAN13.LGEAN13 - 1])
        else:
            self.valueCRC.set("7")

    # Constructeur de l'interface
    def __init__(self, bd):
        # Mémorisation de la base de données utilisée
        self.bd = bd

        # Allocation d'un objet de recherche dans les pages internet de DVDfr
        self.rbr = prw.RechercheBR()

        # Création de la fenêtre principale
        self.fenetre = tk.Tk()
        self.fenetre.title("DVDthèque")
        self.fenetre.resizable(width=False, height=False)

        # Création d'un menu
        self.menubar = tk.Menu(self.fenetre)
        self.menu1 = tk.Menu(self.menubar, tearoff=0)
        self.menu1.add_command(label="Créer base de données", command=self.creerBD)
        self.menu1.add_separator()
        self.menu1.add_command(label="Effacer zone de texte", command=self.effacer)
        self.menu1.add_command(label="Enregistrer zone de texte", command=self.enregistrer)
        self.menu1.add_separator()
        self.menu1.add_command(label="Enregistrer le DVD courant", command=self.enregistrerDVD)
        self.menu1.add_command(label="Afficher tous les DVD", command=self.afficherTout)
        self.menu1.add_separator()
        self.menu1.add_command(label="Quitter", command=self.fenetre.destroy)
        self.menubar.add_cascade(label="Fichier", menu=self.menu1)
        self.fenetre.config(menu=self.menubar)

        self.pv = tk.PanedWindow(self.fenetre, orient=tk.VERTICAL)
        self.ph1 = tk.PanedWindow(self.pv, orient=tk.HORIZONTAL)
        self.ph2 = tk.PanedWindow(self.pv, orient=tk.HORIZONTAL)
        # self.pv.pack(side=tk.TOP, expand=tk.Y, fill=tk.BOTH, pady=2, padx=2)

        # Ajout d'une zone de texte pour procéder aux affichages si besoin
        # La zone de texte est intégrée à une frame pour pouvoir y disposer
        # des ascenseurs
        self.frame = tk.Frame(self.pv, bd=2, relief=tk.SUNKEN)

        self.frame.grid_rowconfigure(0, weight=1)
        self.frame.grid_columnconfigure(0, weight=1)

        self.xscrollbar = tk.Scrollbar(self.frame, orient=tk.HORIZONTAL)
        self.xscrollbar.grid(row=1, column=0, sticky=tk.E + tk.W)

        self.yscrollbar = tk.Scrollbar(self.frame)
        self.yscrollbar.grid(row=0, column=1, sticky=tk.N + tk.S)

        self.text = tk.Text(self.frame, wrap=tk.NONE, bd=0, \
                            xscrollcommand=self.xscrollbar.set, yscrollcommand=self.yscrollbar.set, \
                            height=10)

        self.text.grid(row=0, column=0, sticky=tk.N + tk.S + tk.E + tk.W)

        self.xscrollbar.config(command=self.text.xview)
        self.yscrollbar.config(command=self.text.yview)

        self.frame.pack()

        # Contenu
        self.label = tk.Label(self.ph1, text="Code-barres", background='#A0A0E0', anchor=tk.CENTER, width=15)
        self.ph1.add(self.label)

        # Variables associées aux différents champs
        self.valuePays = tk.StringVar()
        self.valueProduit = tk.StringVar()
        self.valueCRC = tk.StringVar()
        self.valueTitre = tk.StringVar()
        self.valueAnnee = tk.StringVar()
        self.valueDuree = tk.StringVar()
        self.valueSupport = tk.StringVar()

        # Entrée code-barres
        self.valDefautPays()
        okEntreePays = self.fenetre.register(self.estOKPays)
        self.entreePays = tk.Entry(self.ph1, textvariable=self.valuePays, width=3, \
                                   validate='focusout', validatecommand=(okEntreePays, '%P'))
        self.ph1.add(self.entreePays)

        self.valDefautProduit()
        okEntreeProduit = self.fenetre.register(self.estOKProduit)
        self.entreeProduit = tk.Entry(self.ph1, textvariable=self.valueProduit, width=9, \
                                      validate='focusout', validatecommand=(okEntreeProduit, '%P'))
        self.ph1.add(self.entreeProduit)

        self.positCle()
        self.entreeCRC = tk.Entry(self.ph1, textvariable=self.valueCRC, state='disabled', width=1)
        self.ph1.add(self.entreeCRC)

        # Champ vide pour alignement
        # Niveau de gris Linux : #D8D8D8, niveau de gris Windows : #F0F0F0
        self.ph1.add(tk.Label(self.ph1, text="", background='#F0F0F0', anchor=tk.CENTER, width=1))

        # Ligne données film
        self.labelF = tk.Label(self.ph2, text="Film", background='#A0A0E0', anchor=tk.CENTER, width=15)
        self.ph2.add(self.labelF)

        self.entreeTitre = tk.Entry(self.ph2, textvariable=self.valueTitre, state='disabled', width=30)
        self.ph2.add(self.entreeTitre)

        self.entreeAnnee = tk.Entry(self.ph2, textvariable=self.valueAnnee, state='disabled', width=4)
        self.ph2.add(self.entreeAnnee)

        self.entreeDuree = tk.Entry(self.ph2, textvariable=self.valueDuree, state='disabled', width=5)
        self.ph2.add(self.entreeDuree)

        self.entreeSupport = tk.Entry(self.ph2, textvariable=self.valueSupport, state='disabled', width=8)
        self.ph2.add(self.entreeSupport)

        # Champ vide pour alignement
        # Niveau de gris Linux : #D8D8D8, niveau de gris Windows : #F0F0F0
        self.ph2.add(tk.Label(self.ph2, text="", background='#F0F0F0', anchor=tk.CENTER, width=1))

        # Ajout des 3 panels verticaux
        self.pv.add(self.ph1)
        self.pv.add(self.ph2)
        self.pv.add(self.frame)
        self.pv.pack()

    # Initialiser la base de données
    def creerBD(self):
        self.bd.creerBase()
        self.bd.remplirTPays("codesBarresSA.csv")

    # Effacement de la zone de texte
    def effacer(self):
        self.text.delete('0.0', 'end')

    # Enregistrement de la zone de texte
    def enregistrer(self):
        # tm.showinfo(title="Information", message="Enregistrer")
        nom = tf.asksaveasfilename(defaultextension='.txt',
                                   filetypes=[("Fichiers texte", "*.txt"), ("Tous les fichiers", "*.*")])
        if nom != "":
            self.afficher(nom + "\n")
            # Ouverture du fichier
            f = open(nom, "w")
            tt = self.text.get('0.0', 'end')
            print(tt, file=f)
            f.close()

    def enregistrerDVD(self):
        self.bd.enregistrer(self.valuePays.get(), self.valueProduit.get() + self.valueCRC.get(),
                            self.valueTitre.get(), self.valueAnnee.get(), self.valueDuree.get(),
                            self.valueSupport.get())

    def afficherTout(self):
        tt = ""
        tbl = self.bd.interroger()
        for i in range(len(tbl)):
            tt += tbl[i][0] + " (" + tbl[i][1] + ") - " + tbl[i][2] + " - " + tbl[i][3] + " - " + tbl[i][4] + "\n"
        self.afficher(tt)

    # Affichage dans la zone de texte
    def afficher(self, tt):
        self.text.insert('insert', tt)

    # Boucle de gestion des événements
    def gestionEvenements(self):
        self.fenetre.mainloop()

    # Programme principal


if __name__ == "__main__":
    bd = bdDVD.BaseDonneesDVD("DVD.db")
    interf = Interface(bd)
    interf.gestionEvenements()
    bd.fermeture()
