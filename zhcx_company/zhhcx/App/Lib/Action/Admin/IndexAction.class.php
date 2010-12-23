<?php

! defined ( 'THINK_PATH' ) && exit ( 'Access Denied!' );
class IndexAction extends Action {
	
	public function _initialize() {
		header ( "Content-type:text/html;charset=utf-8" );
	}
	
	/**
     +----------------------------------------------------------
	 * 当用户访问后台首页时跳转到登录页面(login.html)
     +----------------------------------------------------------
	 * @access public
     +----------------------------------------------------------
	 */
	public function index() {
		$url = U ( "login" );
		header ( "Location:$url" );
	}
	
	/**
     +----------------------------------------------------------
	 * 检测用户登录,若登录成功,将用户的id和name保存到session中
     +----------------------------------------------------------
	 * @access public
     +----------------------------------------------------------
	 */
	public function check_login() {
		session_start ();
		$admin = M ( 'Admin' );
		$data ['adm_name'] = $_POST ['userName'];
		$data ['adm_password'] = $_POST ['passWord'];
		// 定义查询条件 
		

		$condition ['adm_username'] = $data ['adm_name'];
		$info = $admin->where ( $condition )->find ();
		
		print_r ( $info );
		
		$errorurl = 'login';
		$successurl = U ( 'admin' );
		
		if (! $info) {
		
			$this->redirect ( $errorurl );
		}
		if ($info ['adm_password'] != $data ['adm_password']) {

			$this->redirect ( $errorurl );
		}
//		if (! $this->verifyCheck ()) {
//			echo "bbb";
//			//$this->redirect ( $errorurl );
//		}
		$_SESSION ['adm_name'] = $info ['adm_username'];
		$_SESSION ['adm_id'] = $info ['adm_id'];
		header ( "location:$successurl" );
	
	}
	
	public function verify() { //创建验证码
		import ( "ORG.Util.Image" );
		Image::buildImageVerify ();
	}
	
	public function verifyCheck() { //检测验证码是否正确    
		if (md5 ( $_POST ['check'] ) != $_SESSION ['verify']) {
			return false;
		} else {
			return true;
		}
	}
	
	public function login() { //登录页面  
		$this->display ();
	}
	
	public function admin() {
		check_logined();
		$this->assign ( 'adm_name', $_SESSION ['adm_name'] );
		$this->display ();
	}
	
	public function left() {
		$this->display ();
	}
	public function main() {
		$this->display ();
	}
	

}
?>