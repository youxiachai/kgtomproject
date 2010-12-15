<?php
$xsl = new DomDocument(); 
$xsl->load("person.xsl"); 
$inputdom = new DomDocument(); 
$inputdom->load("contacts.xml"); 


$proc = new XsltProcessor(); 
$xsl = $proc->importStylesheet($xsl); 

 

if(isset($_GET['id'])){
	$id = $_GET['id'];
	$proc->setParameter('', 'PageNo', $id);
	
}else{
	$proc->setParameter('', 'PageNo', 1);
}

$newdom = $proc->transformToDoc($inputdom); 

print $newdom->saveXML();

?>