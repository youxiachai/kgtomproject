<?php if (!defined('THINK_PATH')) exit();?><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>文件上传</title>
<link rel="stylesheet" type="text/css" href="__PUBLIC__/style/myPage.css" />
<link rel="stylesheet" type="text/css" href="__PUBLIC__/style/upload_list.css" />
</head>
<body>
<div id="fileMain">
<div id="title"><span>文件列表</span><a href="__URL__/addFile">添加新文件</a></div>
<p id="classify"><a href="">全部(1)</a> | 
<table cellSpacing="1" cellPadding="3" border=0>
<thead>
	<tr class="head"><td>文件名</td><td>上传时间</td><td>类别</td><td>操作</td></tr>
</thead>
<tbody>
<?php if(is_array($file)): $i = 0; $__LIST__ = $file;if( count($__LIST__)==0 ) : echo "" ;else: foreach($__LIST__ as $key=>$file): ++$i;$mod = ($i % 2 )?><tr>
		<td><?php echo ($file["img_name"]); ?></td>
		<td><?php echo ($file["img_create_time"]); ?></td>
		<td><?php echo ($file["img_category"]); ?></td>
		<td><a href="__URL__/deleteFile">删除</a></td>
	</tr><?php endforeach; endif; else: echo "" ;endif; ?>
</tbody>
<tr id="pagetr">
	<td colspan="5" align="right"><div class="msdn"><?php echo ($page); ?></div></td>
</tr>
</table>
</div>
</body>
</html>