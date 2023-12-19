<?php

# Binome xxx et yyy
# 
# Test unitaire de la transforamtion d'un fichier xml en appel graphique.
#
# Version mars 2010
#

$image ;
$min_x ; $max_x ;
$min_y ; $max_y ;
$c_taille ;
$c_forme ;
$c_couleur ;
$palette ;


//======================== Fonctions fant�mes ne faisant que des impressions
//

//------------------------------------------------------------------------------
// Cr�ation d'une image GD � partir des limites

function creerImage($dx,$fx,$dy,$fy,$fond) {
echo "creerImage $dx $fx $dy $fy $fond<br>" ;
}

//------------------------------------------------------------------------------
// Dessin d'un carr�
function carre($image, $x, $y, $taille, $couleur) {
echo "carre    $x $y t=$taille $couleur<br>" ;
}

//------------------------------------------------------------------------------
// Dessin d'une croix
function croix($image, $x, $y, $taille, $couleur) {
echo "croix    $x $y t=$taille $couleur<br>" ;
}


//------------------------------------------------------------------------------
// Dessin d'un triangle
function triangle($image, $x, $y, $taille, $couleur) {
echo "triangle $x $y t=$taille $couleur<br>" ;
}

//======================== Fonctions utilisant des attributs des balises
//------------------------------------------------------------------------------
// Utilisation des attributs d'une balise LIMITES
function limites(&$at) {
        global $min_x, $max_x, $min_y, $max_y, $image ;
        $min_x = $at["DX"] ;
        $max_x = $at["FX"] ;
        $min_y = $at["DY"] ;
        $max_y = $at["FY"] ;
        $largeur = abs($max_x - $min_x) ;
        $hauteur = abs($max_y - $min_y) ;
        $image = imagecreate($largeur, $hauteur);
}


//------------------------------------------------------------------------------
// M�morisation des attributs associ�s � une balise ENSEMBLE
function ensemble(&$at) {
global $c_forme, $c_couleur, $c_taille ;
$c_forme = $at["FORME"] ;
$c_couleur = $at["COULEUR"] ;
$c_taille = $at["TAILLE"] ;
}

//------------------------------------------------------------------------------
// Conversion des attributs d'une balise POINT en trac� dans l'image
//
// $at le tableau des attributs
function point(&$at) {
global $c_forme, $c_couleur, $c_taille, $image, $palette ;
switch($c_forme) {
        case "CROIX" :
                croix($image, $at["X"], $at["Y"], $c_taille, $palette[$c_couleur]);
                break;
        case "CARRE" :
                carre($image, $at["X"], $at["Y"], $c_taille, $palette[$c_couleur]);
                break;
        case "TRIANGLE" :
                triangle($image, $at["X"], $at["Y"], $c_taille, $palette[$c_couleur]);
                break;
        default :
                echo "Forme inconnue $c_forme<br>";
}
}


//------------------------------------------------------------------------------
// D�tection d'une balise ouvrante
//
// $parser une r�f�rence vers le parseur
// $balise la cha�ne de caract�res identifiant la balise
// $attrs tableau associatif contenant les attributs associ�s � la balise.
function ouverture ($parser, $balise, $attrs){
switch($balise) {
    case "LIMITES" :
        limites($attrs) ;
        break ;
    case "ENSEMBLE" :
        ensemble($attrs) ;
        break ;
    case "POINT" :
        point($attrs) ;
        break ;
    default :
        echo "Balise inconnue $balise<br>" ;
}
}

//------------------------------------------------------------------------------
// D�tection d'une balise fermante
function fermeture ($parser, $balise ){
//echo " > $balise<br>" ;
}

//----------------------- Programme principal ----------------------------------

$tout = file_get_contents("description1.xml");

$parse = xml_parser_create() ;
xml_set_element_handler($parse, "ouverture", "fermeture") ;
xml_parse($parse, $tout) or die (
        sprintf("Erreur XML : %s � la ligne %d\n",
        xml_error_string(xml_get_error_code($parse) ), 
        xml_get_current_line_number($parse)) ) ;
?>

