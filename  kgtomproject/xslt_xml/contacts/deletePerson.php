<?php
  $id = $_REQUEST['id'];
  $dom = new DomDocument();
  $dom->load("contacts.xml");
  $root = $dom->documentElement;
  $path = "/contacts/Person[@id=" . $id . "]";
  $xPath = new domxpath($dom);
  $DVDelement = $xPath->query($path)->item(0);
  $root -> removeChild($DVDelement);
  $dom->save("contacts.xml");
  ?>
<html>
  <head>
    <link href="styles.css" type="text/css" rel="stylesheet" />
     <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  </head>
  <body>
    <div id="divMessage">删除成功</div>
    <a href="personList.php">return</a>
  </body>
</html>
