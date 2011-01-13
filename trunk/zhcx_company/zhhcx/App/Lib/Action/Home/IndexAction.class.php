<?php
// 本类由系统自动生成，仅供测试用途
class IndexAction extends Action {
	public function index() {
		
		$this->display ();
	}
	

	
	public function products(){
		//接受分类值
		
		$category = $_GET['category'];
		
		if(!isset($category)){
			$condition['img_category'] = "精密模切";
		}else{
			$condition['img_category'] = $category;
		}
		
		
		
		$pagesize = 9;	//每页显示的记录数
		$Photo = M("Photo"); //读取数据库
		import("ORG.Util.Page"); //导入分页类包
		$count = $Photo->where($condition)->count();//查询条数
		$page = new Page($count,$pagesize); //设置分页
		$show = $page->show(); //显示分页
		$file = $Photo->where($condition)->limit($page->firstRow.','.$page->listRows)->select();//分页查询
		
		$this->assign('prodcuts',$file);
		$this->assign('page',$show);
		//
		$Contacts = M('Comcontacts');
 		$contact = $Contacts->find(0);
    	$this->assign('contact',$contact);
		$this->display();

	}
	
	function about(){
		
		$Summary = M("Comsummary");
 		$content = $Summary->find(0);	
 		$this->assign('content',$content); 
 		
 		$Contacts = M('Comcontacts');
 		$contact = $Contacts->find(0);
    	$this->assign('contact',$contact);
    	$this->display();
	}
	
	function comcase(){
		$Contacts = M('Comcontacts');
 		$contact = $Contacts->find(0);
    	$this->assign('contact',$contact);
    	$this->display();
		
	}
	
	function contact() {
		$Contacts = M('Comcontacts');
 		$contact = $Contacts->find(0);
    	$this->assign('contact',$contact);
    	$this->display();
	}
}
?>