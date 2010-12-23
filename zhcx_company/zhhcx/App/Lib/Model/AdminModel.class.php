<?php
/**
 * 模型文件(管理员)
 * @author 谭锐
 * @version 1.0
 * create Date November 23,2010
 */
class AdminModel extends RelationModel {

	 protected 	$_validate=array(
 
 
		 array("adm_name","require","登录名不能为空"),
 
 
		 array("adm_password","require","密码不能为空"),
 
 	);
 
 	protected $_auto = array(
 	
 		array('adm_createtime','getTime',1,'callback'),
 		
 	);
 	
	function getTime(){

 		 return date("Y-m-d H:i:s");
 	 }
 
 	
 	protected $_map = array(
 	
 		'id' =>	'adm_id',
 	
		'name' => 'adm_name',
 	
		'pwd' => 'adm_password',
 	
		'heading' => 'adm_heading',
 	
		'question' => 'adm_question',
 	
		'answer' => 'adm_answer',
 	
		'createtime' => 'adm_createtime',
 	
	);
	
	public $_link = array(
	
        'Reply'=>array(

			'mapping_type'=>HAS_MANY,

            'class_name'=>'Reply',

            'foreign_key'=>'adm_id',

        ),
		
        'Article'=>array(

			'mapping_type'=>HAS_MANY,

            'class_name'=>'Article',

            'foreign_key'=>'adm_id',

        ),
        
         'Upload'=>array(

			'mapping_type'=>HAS_MANY,

            'class_name'=>'Upload',

            'foreign_key'=>'adm_id',

        ),
	);

	
}
?>