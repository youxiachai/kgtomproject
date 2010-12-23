<?php
// 本类由系统自动生成，仅供测试用途
class IndexAction extends Action {
	public function index() {
		$array = array ();
		
		$array ['name'] = 'thinkphp';
		
		$array ['email'] = 'liu21st@gmail.com';
		
		$array ['phone'] = '12335678';
		$this->assign($array); 
		$this->display ();
	}
	
	function function_name() {
		;
	}
	
	public function products(){
		$pagesize = 10;	//每页显示的记录数
		$Photo = M("Photo");
		import("ORG.Util.Page");
		$count = $Photo->count();
		$page = new Page($count,$pagesize);
		$show = $page->show();
		$file = $Photo->limit($page->firstRow.','.$page->listRows)->select();
		$this->assign('file',$file);
		$this->assign('page',$show);
		$this->display();

	}
}
?>