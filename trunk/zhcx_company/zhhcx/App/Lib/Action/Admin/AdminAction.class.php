<?php


!defined('THINK_PATH') && exit('Access Denied!');
class AdminAction extends Action{
	
	public function addAdmin(){
		$this->check_logined();
		$this->display();
	}
	
	public function insertAdmin(){
		$this->check_logined();
		$admin = new AdminModel();
		if($admin->create()){
			if($admin->add()){
				$this->success('新增管理员成功!!!');
			}else{
				$this->error('新增管理员失败!!!');
			}
		}else{
			echo $admin->getError();
		}
	}
	
	public function editArticletype(){
	$this->check_logined();
		$id = $_GET['id'];
		$admin = M('Admin');
		$info = $admin->find($id);
		$this->assign('info',$info);
		$this->display();
	}
	
	public function updateArticletype(){
		$this->check_logined();
		$id = $_GET['id'];
		$admin = M('ArticleType');
		if($admin->create()){
			if($admin->add()){
				$this->success("修改成功!!!");
			}else{
				$this->error("修改失败!!!");
			}
		}else{
			$admin->getError();
		}
	}
	
	public function deleteArticletype(){
		$this->check_logined();
		$id = $_GET['id'];
		$admin = M('Admin');
		if($admin->delete($id)){
			$this->success("删除成功!!!");
		}else{
			$this->error("删除失败!!!");
		}	
	}
	
}	

?>