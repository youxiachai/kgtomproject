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
}
?>