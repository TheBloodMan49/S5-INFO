# -*- coding: utf-8 -*-
from PyQt5.QtGui import QImageReader, QImageWriter

if __name__ == "__main__":
    print("Formats acceptés en lecture:")
    for format in QImageReader.supportedImageFormats(): 
        print(format)
    print("\nFormats acceptés en écriture:")
    for format in QImageWriter.supportedImageFormats():
        print(format)
