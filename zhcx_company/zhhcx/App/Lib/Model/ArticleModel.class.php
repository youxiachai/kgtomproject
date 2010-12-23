<?php
/**
 * 模型文件(上传表)
 * @author 吴奇成
 * @version 1.0
 * create Date 12 15,2010
 */

class ArticleModel extends RelationModel{

	protected $_validate=array(
		array("title","require","文章标题不能文空"),
		array("author","require","文章作者不能为空"),
		array("content","require","文章内容不能为空"),
	);
	
	protected $_auto = array(
 		array('createtime','getTime',1,'callback'),
 		array('modifytime','getTime',2,'callback'),
 	);
 	
 	function getTime($date){
 		return strtotime($date);
 	}
	
	protected $_map = array(
		'id' =>'ART_ID', //文章ID
		'title' =>'ART_TITLE',	//文章标题
		'author' =>'ART_AUTHOR',	//文章作者
		'content' =>'ART_CONTENT',	//文章内容
		'createtime' =>'ART_CREATETIME',	//发表时间
		'modifytime' =>'ART_MODIFYIME',		//最后修改时间
	
	);
}

?>