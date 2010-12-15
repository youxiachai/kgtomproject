<?php
  $title = $_POST['txtTitle'];
  $format = $_POST['txtFormat'];
  $genre = $_POST['txtGenre'];
  
  $dom = new DomDocument(); 
  $dom->preserveWhiteSpace = false;
  $dom->formatOutput = true;
  $dom->load("contacts.xml"); 

  $root = $dom->documentElement;
  $DVDelements = $dom->getElementsByTagName("Person");
  $newID =  $DVDelements->length + 1;
  
  $newDVDElement = $dom->createElement("Person");
  $newDVDElement->setAttribute("id",$newID);

  $newTitleElement = $dom->createElement("name");
  $newTitleElement->appendChild($dom->createTextNode($title));
    
  $newFormatElement = $dom->createElement("sex");
  $newFormatElement->appendChild($dom->createTextNode($format));
    
  $newGenreElement = $dom->createElement("phone");
  $newGenreElement->appendChild($dom->createTextNode($genre));
    
  $newDVDElement->appendChild($newTitleElement);
  $newDVDElement->appendChild($newFormatElement);
  $newDVDElement->appendChild($newGenreElement);
    
  $root->appendChild($newDVDElement);

  
$dom->save("contacts.xml");
?>
<html>
  <head>
    <link href="styles.css" type="text/css" rel="stylesheet" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  </head>
    <body>
		<div id="divMessage">更新成功</div>
		<a href="personList.php">返回</a>
	</body>
</html>
