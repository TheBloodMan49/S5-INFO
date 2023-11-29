# -*- coding: utf-8 -*-
from PyQt5.QtWidgets import QWidget, QHBoxLayout, QMessageBox, QLabel
from PyQt5.QtWidgets import QApplication
from PyQt5.QtGui import QPixmap

class Exemple(QWidget):
    def __init__(self, parent=None):
        QWidget.__init__(self, parent)
        self.initUI()
        self.show()
  
    def initUI(self):
        self.setWindowTitle("Visualisation d'une image")
        self.move(250, 200)

        hbox = QHBoxLayout()
        # L'image affichée n'ayant pas besoin d'être manipulée, on n'utilise pas le widget QImage
        pixmap = QPixmap("carrerouge.png")
        if pixmap.isNull():
                QMessageBox.information(self,u"Problème",u"Image non trouvée")
        label = QLabel()
        label.setPixmap(pixmap)
        label.resize(200,200)
        hbox.addWidget(label)
        self.setLayout(hbox)

if __name__ == "__main__":
    app=QApplication([])
    mf=Exemple()
    app.exec_()
