<?php

/**
 * 留言管理Action
 * @author 潘欢任
 * @version 1.0
 * create Date November 23,2010
 */

!defined('THINK_PATH') && exit('Access Denied!');
class MessageAction extends Action{
	
	public function _initialize(){
    	header("Content-type:text/html;charset=utf-8");
    }
    
	
	/**
     +----------------------------------------------------------
     * 获取数据库所有已审核的留言记录,分页输出到模版(list.html)
     +----------------------------------------------------------
     * @access public
     +----------------------------------------------------------
     */
    public function managerAuditMessage(){
//    	$this->check_logined();
    	$page = 10;	//每页显示的记录
		$message = D('Message');	//创建Message对象
		import('ORG.Util.Page');
		$count = $message->where('meg_state=1')->count();	//查询留言记录
		$page = new Page($count,$page);
		$show = $page->show();
		$list = $message->limit($page->firstRow.','.$page->listRows)->where('meg_state=1')->relation(true)->findAll();
		$this->assign('list',$list);	//留言列表
		$this->assign('page',$show);	//分页变量
		$this->display('audited');	//输出模板
    }
    
    
    /**
     +----------------------------------------------------------
     * 获取数据库所有未审核的留言记录,分页输出到模版(unaudit.html)
     +----------------------------------------------------------
     * @access public
     +----------------------------------------------------------
     */
    public function managerUnauditMessage(){
//    	$this->check_logined();
    	$page = 10;	//每页显示的记录
		$message = D('Message');	//创建Message对象
		import('ORG.Util.Page');
		$count = $message->where('meg_state=0')->count();	//查询留言记录
		$page = new Page($count,$page);
		$show = $page->show();
		$list = $message->limit($page->firstRow.','.$page->listRows)->where('meg_state=0')->relation(true)->findAll();
		$this->assign('list',$list);	//留言列表
		$this->assign('page',$show);	//分页变量
		$this->display('unaudited');	//输出模板
    }
    
    /**
     +----------------------------------------------------------
     * 审核选定的留言信息
     +----------------------------------------------------------
     * @access public
     +----------------------------------------------------------
     */
    public function AuditMesssage(){
//    	$this->check_logined();
    	$id = $_GET['id'];
    	$message = D('Message');
    	$data['meg_state'] = 1;
    	if($message->where("meg_id=$id")->save($data)){
    		$url=U('lists');
        	$this->assign("jumpUrl",$url);
    		$this->success("审核成功!!!");
    	}else{
    		$this->error("审核失败!!!");
    	}
    	
    }
    
    
    /**
     +----------------------------------------------------------
     * 删除选定的留言信息
     +----------------------------------------------------------
     * @access public
     +----------------------------------------------------------
     */
    function deleteMessage(){
//    	 $this->check_logined();
    	 $id=$_GET['id'];
    	 $message = M('Message');
    	 $reply = M("Reply");
    	 if($reply->where("meg_id=$id")->delete()){
		 	//如果这条留言有回复,就把回复删除
    	 }
    	 if(!$message->delete($id)){
    	 	$this->error("删除失败");
    	 }
    	 $this->success("删除成功");    	
    }
    
    function getMessage(){
//    	 $this->check_logined();
    	 $id=$_POST['id'];
    	 $message = M('Message');
    	 $result=$message->find($id);
    	 $this->ajaxReturn($result,"test",0 ); 
    }
    
}
?>