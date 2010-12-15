<?php
class UploadAction extends Action{

	function addFile(){
		$this->display("add");
	}
	
	function uploadFile() {
		
		import ( "ORG.Net.UploadFile" );
		$upload = new UploadFile();
		$upload->maxSize = 3145728;
		$upload->allowExts = array('jpg','jpeg','txt','gif');
		$upload->savePath ="./Public/upload/";
		if ($upload->upload()) {
			$this->error($upload->getErrorMsg());
		}else {
			$info = $upload->getUploadFileInfo();
		}
			
		$uploadinfo = new UploadModel();
		if ($uploadinfo->create()) {
			$uploadinfo->UPL_FILENAME = $info[0]['savename'];
			if ($uploadinfo->add()) {
				$this->success('上传成功');
			}else {
				$this->error('上传失败！');
			}
		}else {
			$uploadinfo->getError();
		}
	}

}
?>