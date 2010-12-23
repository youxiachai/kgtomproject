<?php

	/**
     +----------------------------------------------------------
     * 在后台检测检测用户是否登录,若未登录跳转到登录页面
     +----------------------------------------------------------
     */
	function check_logined(){    //检测是否已经登录，注意跟下面的判断是否登录成功是不同的，这个要调用在各个页面中。
    	session_start();
    	$admin=M('Admin');
    	$condition['adm_username']=$_SESSION['adm_name'];
    	$info=$admin->where($condition)->find();
        if(!$info){
        	$url=U('login');
        	header("Location:$url");
        }
    }

?>