<?php if (!defined('THINK_PATH')) exit();?><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登陆成功</title>
<script type="text/javascript" src="__ROOT__/Public/js/jquery-1.4.4.min.js"></script>
<script>
	var setI=setInterval(function (){
		var i=parseInt(
		$('#Time').text())-1;$('#Time').text(i);
		if(i==0){
			clearTimeout(setI);
		window.location='<?php echo ($jumpUrl); ?>'}},1000)
</script>
</head>
<body>
<b><?php echo ($message); ?></b><br>
正在为你跳转...,还剩<font id="Time" color="red">3</font>秒</p>
如果不想等待，请点击<a href="<?php echo ($jumpUrl); ?>">跳转</a>
</body>
</html>