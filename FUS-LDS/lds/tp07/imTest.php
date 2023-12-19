<?php
header("Content-type: image/png");
$image = imagecreate(200, 200);
$palette["JAUNE"] = imagecolorallocate($image, 255, 255, 160);
imagepng($image)
?>