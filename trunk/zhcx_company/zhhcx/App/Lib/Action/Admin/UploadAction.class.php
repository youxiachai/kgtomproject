<?php
class UploadAction extends Action {
	
	public function addFile() {
		$Photo = M ( 'Photo' );
		$list = $Photo->order ( 'img_create_time desc' )->limit ( 2 )->findAll ();
		$this->assign ( 'list', $list );
		$this->display ();
	}
	public function upload() {
		if (! empty ( $_FILES )) {
			//如果有文件上传 上传附件
			$this->_upload ();
			//$this->forward();
		}
	}
	
	// 文件上传
	protected function _upload() {
		import ( "ORG.Net.UploadFile" );
		$upload = new UploadFile ();
		//设置上传文件大小
		$upload->maxSize = 3292200;
		//设置上传文件类型
		$upload->allowExts = explode ( ',', 'jpg,gif,png,jpeg' );
		//设置附件上传目录
		$upload->savePath = './Public/uploads/';
		
		if (! $upload->upload ()) {
			//捕获上传异常
			$this->error ( $upload->getErrorMsg () );
		} else {
			//取得成功上传的文件信息
			$uploadList = $upload->getUploadFileInfo ();
			$_POST ['image'] = $uploadList [0] ['savename'];
		
		}
		$model = M ( 'Photo' );
		//保存当前数据对象
		$data ['img_name'] = $_POST ['image'];
		$data ['img_create_time'] = time ();
		
		echo $data ['img_name'];
		echo $data ['img_create_time'];
		
		$list = $model->add ( $data );
		
		if ($list !== false) {
			$this->success ( '上传图片成功！' );
		} else {
			$this->error ( '上传图片失败!' );
		}
	}
	/**
     +----------------------------------------------------------
	 * 获取数据库的所有上传信息,并分页显示输出到模板(index.html)
     +----------------------------------------------------------
	 */
	function managerFile(){
		check_logined();
		$pagesize = 10;	//每页显示的记录数
		$Photo = M("Photo");
		import("ORG.Util.Page");
		$count = $Photo->count();
		$page = new Page($count,$pagesize);
		$show = $page->show();
		$file = $Photo->limit($page->firstRow.','.$page->listRows)->select();
		$this->assign('file',$file);
		$this->assign('page',$show);
		$this->display('list');
	}
	
	function deleteFile(){	//根据id删除对应的记录
//		$this->check_logined();
		$id = $_GET['id'];
		$upload = M('Photo');
		$file = $upload->find($id);
		$filename =$file['UPL_FILEURL'];
		if($upload->delete($id)){
			 $bloo=unlink($_SERVER[DOCUMENT_ROOT].'/zhhcx/Public/uploads/'.$filename);
			
			$this->success("删除成功!!!");
		}else{
			$this->error("删除失败!!!");
		}
	}
}
?>