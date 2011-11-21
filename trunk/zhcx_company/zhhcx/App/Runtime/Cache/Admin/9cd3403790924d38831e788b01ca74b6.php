<?php if (!defined('THINK_PATH')) exit();?><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
 <head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <title>ThinkPHP示例：缩略图生成</title>
<link rel="stylesheet" type="text/css" href="__PUBLIC__/Css/common.css" />
 </head>
 <body>
 <div class="main">
 <h2>ThinkPHP示例之：缩略图生成</h2>
 本示例演示了如何使用内置的文件上传类库进行附件上传操作，并自动生成缩略图。
 <div id="main" class="main" > 
<div class="image">
 <?php if(is_array($list)): $i = 0; $__LIST__ = $list;if( count($__LIST__)==0 ) : echo "" ;else: foreach($__LIST__ as $key=>$vo): ++$i;$mod = ($i % 2 )?><table width="190" border="0" cellspacing="0" cellpadding="0">
 <tr>
    <td width="126" rowspan="2"><img src="__PUBLIC__/Uploads/<?php echo ($vo["img_name"]); ?>" width="198" height="130" /></td>
    <td width="64">&nbsp;</td>
  </tr>
  <tr>
    <td><img src="__PUBLIC__/Uploads/<?php echo ($vo["image"]); ?>" /></td>
  </tr>
</table><?php endforeach; endif; else: echo "" ;endif; ?>
</div>
<div class="content">
<form id="upload" method='post' action="__URL__/upload/" enctype="multipart/form-data">
<table cellpadding=3 cellspacing=3 width="450PX">
<tr>
	<td colspan="2" class="tLeft">
	<div class="result" style="background:#E9E9F3">上传允许文件类型：gif png jpg 图像文件，分别生产2张缩略图。并且把原图删掉</div>
	</td>
</tr>
<tr>
	<td class="tRight tTop"></td>
	<td class="tLeft tTop">
<div class="impBtn  fLeft" ><input name="image" id="image" type="file" class="file huge" /></div>
	<table id='tbl' style="clear:both"></table>
  <input type="submit" value="提交" class="button" >
</td>
</tr>
<tr>
<td class="tRight tTop"></td>
	<td  class="tLeft"><hr> 示例源码<br/>控制器IndexAction类<br/><?php highlight_file(LIB_PATH.'Action/IndexAction.class.php'); ?></td>
</tr>
</table>
</form>
</div>
</div>

</div>
 </body>
</html>