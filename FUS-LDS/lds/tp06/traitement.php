<?php

// Binome    xxx et yyyy
//------------------------------------------------------------------------------
// Fonction d'affichage en HTML du contenu d'un tableau
// Utile en mise au point

function dump_tab($t) {

foreach ($t as $key => $value) {
  print " Cl� : $key => $value <br>\n" ;
  }
print "<hr>\n" ;
}

function show_result($t){
  echo "<center><h3>Répartition</h3><hr>" ;
  $first = 0;
  $second = 0;
  
}

?>

<!-- ----------------------------------------------------------------- -->

<HTML><HEAD><TITLE> Traitement</TITLE></HEAD>
<BODY><P>
<?php
show_result($_POST) ;
?>
</BODY>
</HTML>
