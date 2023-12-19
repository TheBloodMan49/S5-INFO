<?php
header("Content-type: image/png");

# Binome xxx et yyy
#
# Test unitaire de la transforamtion d'un fichier xml en appel graphique.
#
# Version mars 2010
#

$image;
$min_x;
$max_x;
$min_y;
$max_y;
$c_taille;
$c_forme;
$c_couleur;
$palette;

//------------------------------------------------------------------------------
// Conversion de l'abscisse d'un point en abscisse pixel

function conv_x($x) {
    global $min_x, $max_x ;

    return abs($max_x - $min_x) - abs($max_x - $x);     // Eventuellement � modifier
}

//------------------------------------------------------------------------------
// Conversion de l'ordonn�e d'un point en ordonn�e pixel

function conv_y($y) {
    global $min_y, $max_y ;

    return abs($max_y - $min_y) - abs($y - $min_y); // Eventuellement � modifier
}


//------------------------------------------------------------------------------
// Cr�ation d'une image GD � partir des limites de l'espace logique
// - initialisation des variables globales $min_x, $max_x, $min_y, $max_y
// - cr�ation de l'image GD avec une couleur de fond
// - initialisation de la palette pour des couleurs pr�d�finies
// - affichage des axes dans l'espace logique
//
// $dx, $fx, intervalle de variation des x
// $dy, $fy  intervalle de variation des y

function creerImage($dx,$fx,$dy,$fy, $fond) {
    global $image, $min_x, $max_x, $min_y, $max_y ;
    global $palette ;

    $min_x = $dx ; $max_x = $fx ;
    $min_y = $dy ; $max_y = $fy ;

    $largeur = abs($max_x - $min_x) ;
    $hauteur = abs($max_y - $min_y) ;
    $image=imagecreate($largeur, $hauteur);

// initialisation de la palette

    $palette["ROUGE"] =imagecolorallocate($image,255,  0,  0);
    $palette["VERT"]  =imagecolorallocate($image,  0,255,  0);
    $palette["BLEU"]  =imagecolorallocate($image,  0,  0,255);
    $palette["JAUNE"] =imagecolorallocate($image,255,255,160);
    $palette["NOIR"]  =imagecolorallocate($image,  0,  0,  0);
    $palette["BLANC"] =imagecolorallocate($image,255,255,255);

// Initialisation de la couleur du fond
    imagefill($image,0,0,$palette[$fond]);

// Affichage de l'axe des x
    imageline($image,conv_x($min_x),conv_y(0),conv_x($max_x),conv_y(0),$palette["NOIR"]);
// Affichage de l'axe des y
    imageline($image,conv_x(0),conv_y($min_y),conv_x(0),conv_y($max_y),$palette["NOIR"]);
}


//------------------------------------------------------------------------------
// Dessin d'un carr�
// Il est centr� sur le point $x, $y
// $taille donne la dimension du c�t�
// $couleur est une chaine de caract�re

function carre($image, $x, $y, $taille, $couleur) {
    global $palette ;
    for($i=0;$i<$taille;$i++) {
        for($j=0;$j<$taille;$j++) {
            imagesetpixel($image,
                conv_x($x+$i-$taille/2), conv_y($y+$j-$taille/2),
                $palette[$couleur]);
        }
    }
}

//------------------------------------------------------------------------------
// Dessin d'une croix
// Elle est centr�e sur le point $x, $y
// $taille donne la valeur pour la hauteur et la largeur
// $couleur est une chaine de caract�re

function croix($image, $x, $y, $taille, $couleur) {
    global $palette ;
    imageline($image, conv_x($x-$taille/2), conv_y($y-$taille/2),
        conv_x($x+$taille/2), conv_y($y+$taille/2),
        $palette[$couleur]);
    imageline($image, conv_x($x-$taille/2), conv_y($y+$taille/2),
        conv_x($x+$taille/2), conv_y($y-$taille/2),
        $palette[$couleur]);
}


//------------------------------------------------------------------------------
// Dessin d'un triangle isoc�le
// Il est centr� sur le point $x, $y
// $taille donne la dimension de la base et de la hauteur.
// $couleur est une chaine de caract�re
function triangle($image, $x, $y, $taille, $couleur) {
    global $palette ;
    for($i=0;$i<$taille;$i++) {
        $start_x = conv_x($x+$i/2-$taille/2);
        $start_y = conv_y($y+$i-$taille/2);
        $end_x = conv_x($x-$i/2+$taille/2);
        $end_y = conv_y($y+$i-$taille/2);
        imageline($image, $start_x, $start_y, $end_x, $end_y, $palette[$couleur]);
    }
}



//======================== Fonctions utilisant des attributs des balises
//------------------------------------------------------------------------------
// Utilisation des attributs d'une balise LIMITES
function limites(&$at)
{
    global $min_x, $max_x, $min_y, $max_y, $image;
    $min_x = $at["DX"];
    $max_x = $at["FX"];
    $min_y = $at["DY"];
    $max_y = $at["FY"];
    $largeur = abs($max_x - $min_x);
    $hauteur = abs($max_y - $min_y);
    creerImage($min_x, $max_x, $min_y, $max_y, $at["FOND"]);
}


//------------------------------------------------------------------------------
// M�morisation des attributs associ�s � une balise ENSEMBLE
function ensemble(&$at)
{
    global $c_forme, $c_couleur, $c_taille;
    $c_forme = $at["FORME"];
    $c_couleur = $at["COULEUR"];
    $c_taille = $at["TAILLE"];
}

//------------------------------------------------------------------------------
// Conversion des attributs d'une balise POINT en trac� dans l'image
//
// $at le tableau des attributs
function point(&$at)
{
    global $c_forme, $c_couleur, $c_taille, $image, $palette;
    switch ($c_forme) {
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
function ouverture($parser, $balise, $attrs)
{
    switch ($balise) {
        case "LIMITES" :
            limites($attrs);
            break;
        case "ENSEMBLE" :
            ensemble($attrs);
            break;
        case "POINT" :
            point($attrs);
            break;
        case "PLAN" :
            break;
        default :
            echo "Balise inconnue $balise<br>";
    }
}

//------------------------------------------------------------------------------
// D�tection d'une balise fermante
function fermeture($parser, $balise)
{
//echo " > $balise<br>" ;
}

//----------------------- Programme principal ----------------------------------

$file = "description1.xml";
if($_GET['file']) {$file=$_GET['file'];}
$tout = file_get_contents($file);

$parse = xml_parser_create();
xml_set_element_handler($parse, "ouverture", "fermeture");
xml_parse($parse, $tout) or die (
sprintf("Erreur XML : %s � la ligne %d\n",
    xml_error_string(xml_get_error_code($parse)),
    xml_get_current_line_number($parse)));

imagepng($image);

