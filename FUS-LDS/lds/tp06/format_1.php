<?php

// Lecture ligne par ligne du fichier et transformation en HTML

function lireLigne($nomFich) {
$fp = fopen($nomFich, "r") ;
while(!feof($fp)) {
    // A modifier
    $ligne=fgets($fp) ; 

    echo "$ligne" ; // A compl�ter
    echo "<br/>\n" ;

    }
fclose($fp) ;
}

?>

<HTML>
    <HEAD><TITLE>Lecture brute</TITLE> </HEAD>
<BODY>
    <center><h2> Fichier &minus;&gt; ligne HTML</h2></center>
    <hr> <!-- G�n�ration filet -->

    <?php
    lireLigne("don_8.csv") ;
    ?>

</BODY>
</HTML>
