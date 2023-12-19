<?php

// Binome xxx et yyy

// Ce script utilise d'une mani�re non coh�rente 
//  - des variables globales
//  - des variables pass�es en param�tre.
// Ceci est destin� � illustrer les deux techniques, mais il vaudrait mieux
// avoir des conventions coh�rentes.

// Tableau contenant la premi�re ligne du fichier
$entete ;

// Tableau bidimensionnel contenant les autres lignes du fichier
$champs = array();

//------------------------------------------------------------------------------
// Lecture du fichier et d�composition en champs
// Initialise �galement le tableau global $entete

function lireChamps($nomFich, &$tab) {
	global $entete ;

	$fp = fopen($nomFich, "r") ;        // Ouverture du fichier
	if( ! $fp) die("Abandon $nomFich non lisible") ;

	$entete = fgetcsv($fp, 0, ";") ;               // Lecture de la premi�re ligne
		  
	// Boucle de lecture du reste du fichier
	while(!feof($fp)) {
		  // A compl�ter
		  // Voir poly PHP page 7 pour empiler une valeur dans un tableau
      array_push($tab, fgetcsv($fp, 0, ";")) ;
      if ($tab[count($tab) - 1][2] == 1) {
        $tab[count($tab) - 1][3] = 0;
      }
		  }
	fclose($fp) ;
}

//------------------------------------------------------------------------------

// Affichage du tableau
// G�n�re une ligne d'entete puis une ligne par personne

function afficherTable(&$tab) {
	global $entete ;
  echo "<form action=\"traitement.php\" method=\"post\">" ;
  echo "<table border=\"1\">\n" ; // Si border=0 alors pas de quadrillage

	// Ligne d'entete
	echo "<tr>";
	for($col = 0 ; $col < count($entete) ; $col++) {
		  echo "<th>", $entete[$col], "</th>" ;
		  }
	echo "</tr>\n";

	// Une ligne par personne
	for($lig = 0 ; $lig < count($tab) - 1 ; $lig++) {
      echo "<tr>";
      for ($col = 0; $col < count($tab[$lig]); $col++) {
        if ($col == 2 || $col == 3) {
          $checked = $tab[$lig][$col] == 1 ? "checked" : "";
          echo "<td><input type=\"radio\" name=\"", $tab[$lig][0],"-",$tab[$lig][1], "\" value=\"", $entete[$col], "\" $checked /></td>";
        } else {
          echo "<td>", $tab[$lig][$col], "</td>";
        }
      }
      echo "</tr>\n";
		}
	echo "</table>\n" ;
  echo "<input type=\"hidden\" name=\"count\" value=\"", count($tab), "\" />" ;
  echo "<input type=\"reset\" value=\"Reset\" />" ;
  echo "<input type=\"submit\" value=\"Valider\" />" ;
  echo "</form>" ;
}

// ----------------------------------------------------------------------------


?>

<!-- ----------------------------------------------------------- -->
<HTML>
    <HEAD><TITLE>Format n</TITLE>
   </HEAD>
<BODY>

<?php
$fichier="don_8.csv" ;
if($_GET['file']) {$fichier=$_GET['file'];}

echo '<center><h3>Affectation de ', $fichier, '</h3><hr>' ;

if(file_exists($fichier) ) {

    lireChamps($fichier, $champs) ;

    // Impression de mise au point pour valider la lecture du fichier.
    // print("Entete : ") ; print_r($entete) ;

    // Comparer les 3 styles d'affichage
    // print ("\n-----------------------\n var print_r\n"); print_r ($champs) ;
    // echo "<br/>\n" ;
    // print ("\n------------------------\n var dump\n"); var_dump($champs) ;
    // echo "<br/>\n" ;
    // print ("\n------------------------\n var export\n"); var_export($champs) ;

    afficherTable($champs) ;
    }
else { echo '<center>Fichier inconnu</center>' ;}
?>
</center>
</BODY>
</HTML>
