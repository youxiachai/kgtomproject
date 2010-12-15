<?php
/**
 * 模型文件(上传表)
 * @author 吴奇成
 * @version 1.0
 * create Date 12 15,2010
 */
class UploadModel extends RelationModel{
	
	protected $_validate=array(
		array("","",""),
		array("","",""),
		array("","",""),
	);
	
	
	protected $_auto = array(
 		array('UPL_CREATETIME','getTime',1,'callback')
 	);
 	
 	function getTime(){

 		return date("Y-m-d H:i:s");
 	}
	
	protected $_map = array(
		'id' => 'UPL_ID',
		'filename' => 'UPL_FILENAME',
		'fileurl' => 'UPL_FILEURL',
		'createtime' => 'UPL_CREATETIME',
	);
	
}
?>