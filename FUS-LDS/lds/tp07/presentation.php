<!doctype html public "-//w3c//dtd html 4.0 transitional//en">
<html>
<head>
   <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
   <meta name="Author" content="Jean Camillerapp">
   <meta name="Description" content="Appel dynamique">
   <title>Image dynamique</title>
</head>
<body>
<h2> Présentation des données </h2>
<hr>
<?php
$file="description1.xml";
if($_GET['file']) {$file=$_GET['file'];}
echo "<img src=\"tp7.php?file=",$file, "\" /><br>";
?>

</body>
</html>
