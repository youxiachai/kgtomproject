<?php
  $id = $_GET['id'];

  $dom = new DomDocument();
  $dom->load("contacts.xml");
  
  $path = "/contacts/Person[@id=" . $id . "]";
  $xPath = new domxpath($dom);
  $selectedNode = $xPath->query($path)->item(0);
  
  foreach ($selectedNode->childNodes as $child) { 
    if ($child->nodeName == "name") { 
      $title = $child->textContent;
	}
	elseif ($child->nodeName == "sex") { 
      $format = $child->textContent;
	}
	elseif ($child->nodeName == "phone") { 
      $genre = $child->textContent;
	}
  }
 
  ?>
<html>
<head>
    <link href="styles.css" type="text/css" rel="stylesheet" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
    <h1>Edit Person Details</h1>
    <form id="frmEditPerson" method="POST" action="editPersonAction.php">
	<input type="hidden" name="txtID" value="<?php echo $id; ?>"/>
        <table>
            <tr>
                <td class="emphasis">姓名</td>
                <td><input name="txtTitle" type="text" size="30" maxlength="50" value="<?php echo $title; ?>"/></td>
            </tr>
            <tr>
                <td class="emphasis">性别</td>
                <td><input name="txtFormat" type="text" size="30" maxlength="50" value="<?php echo $format; ?>"/></td>
            </tr>
            <tr>
                <td class="emphasis">电话号码</td>
                <td><input name="txtGenre" type="text" size="30" maxlength="50" value="<?php echo $genre; ?>"/></td>
            </tr>
            <tr>
                <td class="emphasis" colspan="2">
                    <input type="submit" id="btnAdd" value="Update Person"/>
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
