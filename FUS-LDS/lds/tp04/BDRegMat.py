# -*- coding: utf-8 -*-
"""
Created on Sun Nov 16 17:41:01 2014

@author: leplumey
"""

# Imporation du module de bases de données : SQLite
import sqlite3 as lite
import sys

# Classe de gestion de la base de données avec prise en charge des erreurs
class BDRegMat:
    def __init__(self,nom='tp4.db'):
        self.con = None
        try:
            self.con = lite.connect(nom)      
            self.cur = self.con.cursor()
            # Activer les vérifications sur les clés étrangères
            # (par défaut, cette fonctionnalité est inactive)
            self.cur.execute('PRAGMA foreign_keys = ON')
        except lite.Error as e:
            print ("Error %s:" % e.args[0])
            if self.con:
                self.con.close()   
            sys.exit(1)
                
    def requete(self, reqsql):
        #print reqsql
        try:
            with self.con:
                self.cur.execute(reqsql)
        except lite.Error as e:  
            print ("Error %s (%s)" % (e.args[0],reqsql))
            
    def question(self, quest):
        try:
            with self.con:
                self.cur.execute(quest)
                # Rend en résultat le tableau des lignes résultats
                rows=self.cur.fetchall()
                return rows
        except lite.Error as e:  
            print ("Error %s (%s)" % (e.args[0],quest))
            return ()

    def fermeture(self):
        self.con.close()
        