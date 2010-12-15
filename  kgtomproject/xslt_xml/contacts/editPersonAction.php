<?php
  $id = $_POST['txtID'];
  $title = $_POST['txtTitle'];
  $format = $_POST['txtFormat'];
  $genre = $_POST['txtGenre'];
  
  
  $dom = new DomDocument();
  $dom->preserveWhiteSpace = false;
  $dom->formatOutput = true;
  $dom->load("contacts.xml"); 
  
  $path = "/contacts/Person[@id=" . $id . "]";
  $xPath = new domxpath($dom);
  $selectedNode = $xPath->query($path)->item(0);

  foreach ($selectedNode->childNodes as $child) {
	if ($child->nodeName == "name") { 
	  $child ->firstChild->nodeValue = $title;
	}
	elseif ($child->nodeName == "sex") { 
      $child->firstChild->nodeValue = $format;
	}
	elseif ($child->nodeName == "phone") { 
      $child->firstChild->nodeValue = $genre;
	}
  }

$dom->save("contacts.xml");
?>
<html>
  <head>
    <link href="styles.css" type="text/css" rel="stylesheet" />
     <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  </head>
    <body>
		<div id="divMessage">修改成功</div>
			<a href="personList.php">返回</a>
	</body>
</html>
